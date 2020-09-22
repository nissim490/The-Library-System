package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import Controllers.AskForExtentionByReaderController.BorrowedBooks;
import common.Functions;
import entity.User;
import javafx.scene.Node;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import java.util.Arrays;

public class ManualExtensionByLibrarianOrManagerController implements Initializable {
	
	private User u;
	private ArrayList<ArrayList<String>> resultUser;
	private ArrayList<ArrayList<String>> resultStatus;
	private ArrayList<ArrayList<String>> resultReader;
	private ArrayList<ArrayList<String>> resultWait;
	private String LibrarianName;
	private BorrowedBooks borrowedBook;
	private Date date=null;
	private String CardStatus;
	@FXML
    private AnchorPane MainAnchor;//
    @FXML
    private StackPane MainStack;///
    @FXML
    private ResourceBundle resources;
    JFXButton yesButton = new JFXButton("Exit");//
	JFXButton okButton = new JFXButton("Alright");//

    @FXML
    private URL location;

    @FXML
    private JFXButton ConfirmExtension;

    @FXML
    private JFXTextField lblReaderName;

    @FXML
    private JFXTextField lblLibrarianName;

    @FXML
    private JFXTextField lblBookName;

    @FXML
    private JFXTextField lblCurrentReturnDate;

    @FXML
    private JFXDatePicker lblNewReturnDate;
    
   //@FXML
    //private Label TxtComments;

    /**
     * check if the book extension is legal and update if it does.
     * @param event-action
     * @return void
     */
    @FXML
    void ConfirmExtension(ActionEvent event) {
    	String sql="SELECT count(*) as wait FROM g17db.tblbookborrow as bb, g17db.tblbookorders as bo ,g17db.tblbookcopy as c WHERE bb.BookCopyID = c.BookCopyID And bo.BookID=c.BookID And  bb.BookBorrowID = ?;";
    	ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
	    askForResult.add("wait");
	    key.add(borrowedBook.getBookCopyID());
	    resultWait=Functions.askFromDB(sql ,key,askForResult);

    	if (date==null) {}
    	else { 
    		LocalDate mrd=this.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();//convert date to local date.
    	    LocalDate rd=mrd.minusWeeks(2);
    		if((lblNewReturnDate.getValue().isEqual(rd))||(lblNewReturnDate.getValue().isEqual(mrd))) {
    			if(resultWait.get(0).get(0).equals("0")) {
    				if(LocalDate.now().plusDays(8).isAfter(rd)) {
    					updateBorrow();
    					AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Success Massage","Your request has been received. The borrow was extended until the requested date");
    				}  
    				else
    					AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage","This borrow can not be extended. You still have more then week to have this book");
    			}
    			else
    				AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage","This borrow can not be extended, there are additional orders to this book");
    		}
    		else if(lblNewReturnDate.getValue().isBefore(mrd)) {
    				if(lblNewReturnDate.getValue().isAfter(rd)) {
    					if(resultWait.get(0).get(0).equals("0")) {
    						if(LocalDate.now().plusDays(8).isAfter(rd)) {
    							updateBorrow();
    							AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Success Massage","Your request has been received, The borrow was extended until the requested date");

    						}
    						else
    							AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage","This borrow can not be extended,You still have more then week to have this book");
    					}
    					else
    						AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage","This borrow can not be extended, there are additional orders to this book");
    	    		}
    				else
    					AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage","Date passed, please choose a date within the next two weeks ");
    		}
    	else
    		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage","Date too far, please choose a date within the next two weeks");	
    	}
    }
    /**
     *update the extension data
     * @param 
     * @param 
     * @return void
     */
	private void updateBorrow() {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
		String sql="UPDATE g17DB.tblbookBorrow SET ReturnDate = ? , LenderName = ? WHERE BookBorrowID = ?;";
    	ArrayList<String> key = new ArrayList<String>();
	    key.add(lblNewReturnDate.getValue().format(formatters));
	    key.add(lblLibrarianName.getText());
	    key.add(borrowedBook.getBorrowedBookID());
	    Functions.askFromDB(sql,key);
	}

	/**
	 * Initialize the window with this borrowed book details
	 * @param lst- the borrowed book to initial.
	 * 
	 */
	public void SetBook(BorrowedBooks lst) {
		ArrayList<String> keyReader = new ArrayList<String>();
	    ArrayList<String> askForReaderResult = new ArrayList<String>();
		String sqlReader ="SELECT u.FirstName ,u.Lastname ,r.CardStatus FROM g17db.tblbookborrow bb, g17db.tblreader as r ,g17db.tbluser as u WHERE bb.ReaderCardNumber = r.ReaderCardNumber AND r.UserID=u.UserID AND bb.BookBorrowID= ?;";
		keyReader.add(lst.getBorrowedBookID());
		askForReaderResult.add("FirstName");
		askForReaderResult.add("Lastname");
		askForReaderResult.add("CardStatus");
		resultReader = Functions.askFromDB(sqlReader ,keyReader,askForReaderResult);
		this.CardStatus=resultReader.get(0).get(2);
		
		
		ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
		this.borrowedBook=lst;
		String sql="SELECT b.Popularity FROM g17db.tblbookborrow as bb, g17db.tblbook as b ,g17db.tblbookcopy as c WHERE bb.BookCopyID = c.BookCopyID And b.BookID=c.BookID And  bb.BookCopyID = ?;";
		key.add(lst.getBookCopyID());
		askForResult.add("Popularity");
		resultStatus = Functions.askFromDB(sql ,key,askForResult);
		
		lblReaderName.setText(resultReader.get(0).get(0) + " " + resultReader.get(0).get(1));
		lblBookName.setText(lst.getBookName()+"");
		lblCurrentReturnDate.setText(lst.getReturnDate());
		String status=resultStatus.get(0).get(0);
		DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		try {
			if ((status.equals("Normal"))&&(CardStatus.equals("Active"))) {		
				this.date=f.parse(lst.getReturnDate());
				int noOfDays = 14; //i.e two weeks
				Calendar calendar = f.getCalendar();
				calendar.setTime(this.date);            
				calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
				this.date = calendar.getTime();
				lblNewReturnDate.setValue(this.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
			else {
				lblNewReturnDate.setEditable(false);
				yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event1) -> {
					((Node)event1.getSource()).getScene().getWindow().hide();
				});
				AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Error Massage", "This book is not available for extension(Frozen account or Demanded Book), Please click on the exit button");
				ConfirmExtension.setDisable(true);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	/**
	 * Initialize the window with this user details
	 * @param u the user to initial.
	 * 
	 */
	public void SetUser(String u) {
		ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
    	String sql;
    	//this.u=u;
    	sql = "SELECT u.FirstName , u.Lastname FROM g17db.tbluser as u WHERE  u.ID = ?;";
    	askForResult.add("FirstName");
    	askForResult.add("Lastname");
    	//key.add(u.getUserID());
    	key.add(u);
    	resultUser = Functions.askFromDB(sql ,key,askForResult);
    	String a = resultUser.get(0).get(0) +" " +resultUser.get(0).get(1);
    	this.LibrarianName = a;
    	lblLibrarianName.setText(this.LibrarianName);
    	//lblReaderName.setText(u.getFirstName() + " " + u.getLastName());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
