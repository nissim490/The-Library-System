 package Controllers;

	import java.awt.Container;
import java.net.URL;
	import java.text.DateFormat;
	import java.text.ParseException;
	import java.text.SimpleDateFormat;
	import java.time.LocalDate;
	import java.time.ZoneId;
	import java.time.format.DateTimeFormatter;
	import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
	import java.util.Date;
	import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import common.Functions;
	import entity.User;
	import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
	import javafx.fxml.Initializable;
	import javafx.scene.Node;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import com.jfoenix.controls.JFXButton;
	import com.jfoenix.controls.JFXDatePicker;
	import com.jfoenix.controls.JFXTextField;
	import javafx.stage.Stage;
//import library.assistant.alert.AlertMaker;
import javafx.scene.layout.StackPane;
	public class AskForExtentionByReaderController implements Initializable {
		 JFXButton yesButton = new JFXButton("Exit");//
			JFXButton okButton = new JFXButton("Alright");//
			  private  boolean name=false;
		private User u;
		private String UserReaderCardNumber;
		private BorrowedBooks book;
		private ArrayList<ArrayList<String>> resultUser;
		private ArrayList<ArrayList<String>> resultStatus;
		private ArrayList<ArrayList<String>> resultWait;
		private Date date=null;
		  @FXML
		    private StackPane MainStack;///
	    @FXML
	    private AnchorPane MainAnchor;
		
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;



	    @FXML
	    private JFXButton ConfirmExtensionRequestBtn;

	    @FXML
	    private JFXTextField lblReaderName;

	    @FXML
	    private JFXTextField lblBookName;

	    @FXML
	    private JFXTextField lblReturnDate;

	    @FXML
	    private JFXDatePicker lblNewReturnDate;
	
	    
		private String CardStatus;
		   /**
	     * going back to the homepage
	     *
	     * @param ResourceBundle
	     * @return void
	     */
	@FXML
    void Back(ActionEvent event) {/*
    	((Node)event.getSource()).getScene().getWindow().hide();
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();	    	
		loader.setLocation(getClass().getResource("/gui/ReaderHomepageController.fxml"));
		try {
			loader.load();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		ReaderHomepageController rhc = loader.getController();
		rhc.SetUser(new User("312576101","Tamir1020","123456","312576101","Tamir","Alalouf","Tamir1020@gmail.com","2015-01-19 00:00:00","Reader","Offline"));
		Parent p =loader.getRoot();
		Scene scene = new Scene(p);
		//scene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
		primaryStage.setTitle("Home Page - R");
		primaryStage.setScene(scene);
		primaryStage.showAndWait();
		*/
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
        LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/ReaderHomepage.fxml"), "Home Page - R", null);
   

    }
	   /**
     * after clicking the system check if this reader can extend his borrowing
     *
     * @param ResourceBundle
     * @return void
     */
    @FXML
    void ConfirmExtensionRequestBtn(ActionEvent event) {
    	String sql="SELECT count(*) as wait FROM g17db.tblbookborrow as bb, g17db.tblbookorders as bo ,g17db.tblbookcopy as c WHERE bb.BookCopyID = c.BookCopyID And bo.BookID=c.BookID And  bb.BookCopyID = ?;";
    	ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
	    askForResult.add("wait");
	    key.add(book.getBookCopyID());
	    resultWait=Functions.askFromDB(sql ,key,askForResult);

    	if (date==null) {}
    	else { 
    		LocalDate mrd=this.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	    LocalDate rd=mrd.minusWeeks(2);
    		if((lblNewReturnDate.getValue().isEqual(rd))||(lblNewReturnDate.getValue().isEqual(mrd))) {
    			if(resultWait.get(0).get(0).equals("0")) {
    				if(LocalDate.now().plusDays(8).isAfter(rd)) {
    					updateBorrow();
    					  AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Success Massage","Your request has been received, The borrow was extended until the requested date");     		
    					//setText("Your request has been received\nThe borrow was extended until the \nrequested date");
    					  //name=false;
    				}
    				else
    					
    					  AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","This borrow can not be extended, You still have more then week to have this book");     		
						///setText("This borrow can not be extended,\nYou still have more then week to \nhave this book");
    			}
    			else
    				
    				 AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","This borrow can not be extended, there are additional orders to this book");     		
    				//setText("This borrow can not be extended,\nthere are additional orders to \nthis book");
    		}
    		else if(lblNewReturnDate.getValue().isBefore(mrd)) {
    				if(lblNewReturnDate.getValue().isAfter(rd)) {
    					if(resultWait.get(0).get(0).equals("0")) {
    						if(LocalDate.now().plusDays(8).isAfter(rd)) {
    							updateBorrow();
    							
    							 AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Success Massage","Your request has been received, The borrow was extended until the requested date");   
    							
    							//setText("Your request has been received\nThe borrow was extended until the \nrequested date");
    						}
    						else
    							 AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","This borrow can not be extended, You still have more then week to have this book");
    						//	setText("This borrow can not be extended,\nYou still have more then week to \nhave this book");
    					}
    					else
    						 AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","This borrow can not be extended, there are additional orders to this book");
    						//setText("This borrow can not be extended,\nthere are additional orders to \nthis book");
    	    		}
    				else
    					AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Date passed, please choose a date within the next two weeks ");
    					//setText("Date passed, \nplease choose a date within the \nnext two weeks ");
    		}
    	else
    		AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Date too far, please choose a date within the next two weeks");
    		//setText("Date too far, \nplease choose a date within the \nnext two weeks");	
    	}
    	//name=false;
    	// ((Node)event.getSource()).getScene().getWindow().hide();
    	ArrayList<String> R = new ArrayList<String>();
    	String sqlR="SELECT ReaderCardNumber FROM g17db.tblreader as r, g17db.tbluser as u WHERE r.ReaderCardNumber=?;";
    		    ArrayList<String> keyR= new ArrayList<String>();
				keyR.add(UserReaderCardNumber);
    		    ArrayList<String> askForRResult= new ArrayList<String>();
				askForRResult.add("*");
    		    R=Functions.askFromDB(sqlR, keyR, askForRResult).get(0);

    	String actSql="SELECT * FROM g17db.tbllistofhistory;";
    	    	ArrayList<String> actKey = new ArrayList<String>();
    		    ArrayList<String> askForActResult = new ArrayList<String>();
    		    askForActResult.add("*");
    		    ArrayList<ArrayList<String>>resultAct =Functions.askFromDB(actSql, actKey, askForResult);
    	    	   	

    		 	   	 int cnt1=1;
    		 	   	 int sizelist = resultAct.size();
    		 	   	 String lastmessageid = resultAct.get(sizelist-1).get(0); ////Finds the largest number in the table
    		 	   	 String newmessageid = "A";
    		 	   	 int	amounutofdigits = lastmessageid.length()-1; //should be 6
    		 	   	 int	numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1) + 1);
    		
    		 	   	 int	amountofzero = amounutofdigits - numberlength;
    		 	   	 for(int b=0;b<amountofzero;b++) {
    		 	   		 newmessageid = newmessageid+"0";
    		 	   	 }	
    		 	   	 newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1);
    	                            actKey.clear();
    		 	actKey.add(newmessageid);   	 
    		 	actKey.add(R.get(0)); 
    		 	actKey.add(newmessageid); 
    		 	actKey.add(LocalDate.now()+""); 
    		 	Functions.askFromDB("INSERT INTO `g17db`.`tbllistofhistory` (`ActivityID`, `ReaderCardNumber`, `Activity`, `Date`) VALUES (?, ?, ?, ?);", actKey);
   
    		 	
    }
    /**
     * if the system did approve his extension this method update his details
     *
     * @param none
     * @return void
     */
	private void updateBorrow() {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		String sql="UPDATE g17DB.tblbookBorrow SET ReturnDate = ? WHERE BookCopyID = ?;";
    	ArrayList<String> key = new ArrayList<String>();
	    key.add(lblNewReturnDate.getValue().format(formatters));
	    key.add(book.getBookCopyID());
	    Functions.askFromDB(sql,key);
	    
	    
	     String actSql="SELECT * FROM g17db.tbllistofhistory;";
	    	ArrayList<String> actKey = new ArrayList<String>();
		    ArrayList<String> askForActResult = new ArrayList<String>();
		    askForActResult.add("*");
		    ArrayList<ArrayList<String>>resultAct =Functions.askFromDB(actSql, actKey, askForActResult);
	    	   	

		 	   	 int cnt11=1;
		 	   	 int sizelist1 = resultAct.size();
		 	   	 String lastmessageid1 = resultAct.get(sizelist1-1).get(0); ////Finds the largest number in the table
		 	   	 String newmessageid1 = "A";
		 	   	 int	amounutofdigits1 = lastmessageid1.length()-1; //should be 6
		 	   	 int	numberlength1 = (int) (Math.log10(Integer.parseInt(lastmessageid1.substring(1,lastmessageid1.length()))+cnt11) + 1);
		
		 	   	 int	amountofzero1 = amounutofdigits1 - numberlength1;
		 	   	 for(int b=0;b<amountofzero1;b++) {
		 	   		 newmessageid1 = newmessageid1+"0";
		 	   	 }	
		 	   	 newmessageid1 = newmessageid1+(Integer.parseInt(lastmessageid1.substring(1,lastmessageid1.length()))+cnt11);
	                            actKey.clear();
		 	actKey.add(newmessageid1);   	 
		 	actKey.add(this.UserReaderCardNumber); 
		 	actKey.add("Ask For Extension"); 
		 	actKey.add(LocalDate.now()+""); 
		 	Functions.askFromDB("INSERT INTO `g17db`.`tbllistofhistory` (`ActivityID`, `ReaderCardNumber`, `Activity`, `Date`) VALUES (?, ?, ?, ?);", actKey);

	    
	    String[] LibrarianN=book.getLenderName().split(" ", 2);
	    ArrayList<ArrayList<String>> L ;
	    ArrayList<String> keyL = new ArrayList<String>();
	    ArrayList<String> askForLResult = new ArrayList<String>();
	    keyL.add(LibrarianN[0]);
	    keyL.add(LibrarianN[1]);
	    askForLResult.add("UserID");
	    String sqlL="SELECT u.UserID FROM g17db.tbluser as u WHERE u.FirstName=? AND u.LastName=?;";
	   L=Functions.askFromDB(sqlL, keyL, askForLResult);
	    if(L==null)
	    	return;
	    String LibrarianUser=L.get(0).get(0);
		//DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
    	ArrayList<String> keyM = new ArrayList<String>();
        //create new messageID
    	 ArrayList<String> askForMNResult = new ArrayList<String>();
    	 ArrayList<String> keyMN = new ArrayList<String>();
		  String sqlMN = "SELECT * FROM g17db.tblmessages;"; 
		  askForMNResult.add("*");
		  ArrayList<ArrayList<String>> Result1 = Functions.askFromDB(sqlMN ,keyMN,askForMNResult);
    	int cnt1=1;
	   	 int size4 = Result1.size();
			String lastmessageid = Result1.get(size4-1).get(0); 
			String newmessageid = "M";
		int	amounutofdigits = lastmessageid.length()-1; //should be 6
		int	numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1) + 1);
			
		int	amountofzero = amounutofdigits - numberlength;
		for(int b=0;b<amountofzero;b++) {
				newmessageid = newmessageid+"0";
			}
		newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1);
		
    	keyM.add(newmessageid);
    	keyM.add(LibrarianUser);
    	keyM.add("Return a Book");
    	keyM.add(LocalDate.now().format(formatters));
    	keyM.add("System");
    	keyM.add("Unread");
        Functions.askFromDB("INSERT INTO `g17db`.`tblmessages` (`MessageID`, `UserID`, `Content`, `ArrivalDate`, `MessageFrom`, `Status`) VALUES (?, ?, ?, ?, ?, ?);", keyM);
	
   
	}

	public void start(Stage primaryStage) throws Exception {
		//pStage = primaryStage;
		/*Parent root = FXMLLoader.load(getClass().getResource("/gui/AskForExtentionByReader.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
		primaryStage.setTitle("Ask For Extention");
		primaryStage.setScene(scene);
		primaryStage.show();
		LibraryAssistantUtil.setStageIcon(primaryStage);*/
		
	}
   
	  /**
     * loading the book information on the screen
     *
     * @param lst s
     * 
     */
	public void SetBook(BorrowedBooks lst) {
		ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
		this.book=lst;
		String sql="SELECT b.Popularity FROM g17db.tblbookborrow as bb, g17db.tblbook as b ,g17db.tblbookcopy as c WHERE bb.BookCopyID = c.BookCopyID And b.BookID=c.BookID And  bb.BookCopyID = ?;";
		lblBookName.setText(lst.getBookName()+"");
		lblReturnDate.setText(lst.getReturnDate());
		key.add(lst.getBookCopyID());
		askForResult.add("Popularity");
		resultStatus = Functions.askFromDB(sql ,key,askForResult);
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
				//setText("This book is not available for extension(Frozen account or Demanded Book)\n Please press on Back");
				//lblNewReturnDate.getScene().getWindow().hide();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	 /**
     * initializing
     *@param arg0 s
     * @param arg1 d
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	 /**
     * receiveing data from other windows about this user info

     * @param u d
     * 
     */
	public void SetUser(String u) {
		ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
    	String sql;
    	sql = "SELECT r.ReaderCardNumber,r.CardStatus,u.FirstName,u.LastName FROM g17db.tbluser as u,g17db.tblreader as r WHERE u.UserID=r.UserID AND u.ID = ?;";
    	askForResult.add("ReaderCardNumber");
    	askForResult.add("CardStatus");
    	askForResult.add("FirstName");
    	askForResult.add("LastName");
    	//key.add(u.getUserID());
    	key.add(u);
    	resultUser = Functions.askFromDB(sql ,key,askForResult);
    	String a = resultUser.get(0).get(0);
    	this.UserReaderCardNumber = a;
    	String b =resultUser.get(0).get(1);
    	this.CardStatus=b;
    	lblReaderName.setText(resultUser.get(0).get(2) + " " + resultUser.get(0).get(3));
	}
	   public static class BorrowedBooks {

	        private final SimpleStringProperty RowNum;
	        private final SimpleStringProperty BorrowedBookID;
	        private final SimpleStringProperty BookCopyID;
	        private final SimpleStringProperty BookName;
	        private final SimpleStringProperty AuthorName;
	        private final SimpleStringProperty LenderName;
	        private final SimpleStringProperty BorrowingDate;
	        private final SimpleStringProperty ReturnDate;
	        
	        public BorrowedBooks(String rownum,String borrowedBook ,String bookid,String bookname, String author, String lender, String lendingDate, String returnDate) {
	           	this.RowNum = new SimpleStringProperty(rownum);
	           	this.BorrowedBookID = new SimpleStringProperty(borrowedBook);
	           	this.BookCopyID = new SimpleStringProperty(bookid);
	           	this.BookName = new SimpleStringProperty(bookname);
	        	this.AuthorName = new SimpleStringProperty(author);
	        	this.LenderName = new SimpleStringProperty(lender);
	        	this.BorrowingDate = new SimpleStringProperty(lendingDate);
	        	this.ReturnDate = new SimpleStringProperty(returnDate);
	        	
	        }
	        	
	        public BorrowedBooks(BorrowedBooks lst) {
	        	this.RowNum = new SimpleStringProperty(lst.getRowNum());
	        	this.BorrowedBookID = new SimpleStringProperty(lst.getBookCopyID());
	        	this.BookCopyID = new SimpleStringProperty(lst.getBookCopyID());
	        	this.BookName =  new SimpleStringProperty(lst.getBookName());
	        	this.AuthorName =  new SimpleStringProperty(lst.getAuthorName());
	        	this.LenderName =  new SimpleStringProperty(lst.getLenderName());
	        	this.BorrowingDate =  new SimpleStringProperty(lst.getBorrowingDate());
	        	this.ReturnDate =  new SimpleStringProperty(lst.getReturnDate());
			}

			public String getRowNum() {
	        	return RowNum.get();
	        }
	        public void setRowNum(String rownum) {
	        	RowNum.set(rownum);
	        }
	        
	        public String getBorrowedBookID() {
	        	return BorrowedBookID.get();
	        }
	        public void setBorrowedBookID(String borrowedBook) {
	        	BorrowedBookID.set(borrowedBook);
	        }
	        
	        public String getBookCopyID() {
	        	return BookCopyID.get();
	        }
	        public void setBookCopyID(String bookid) {
	        	BookCopyID.set(bookid);
	        }
	     

	        public String getBookName() {
	        	return BookName.get();
	        }
	        public void setBookName(String bookname) {
	        	BookName.set(bookname);
	        }
	        
	        
	        public String getAuthorName() {
	        	return AuthorName.get();
	        }
	        public void setAuthorName(String author) {
	        	AuthorName.set(author);
	        }
	        
	  
	        public String getLenderName() {
	        	return LenderName.get();
	        }
	        public void setLenderName(String lender) {
	        	LenderName.set(lender);
	        }
	        
	        
	        public String getBorrowingDate() {
	        	return BorrowingDate.get();
	        }
	        public void setBorrowingDate(String lending) {
	        	BorrowingDate.set(lending);
	        }
	        
	        public String getReturnDate() {
	        	return ReturnDate.get();
	        }
	        public void ReturnDate(String lendingr) {
	        	ReturnDate.set(lendingr);
	        }		       
	    }
	   /**
	     * poping up a popup message

	     * @param str d
	     * 
	     */
	   public void setText(String str) {
   		AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage", str);
	   
	   }
	
}