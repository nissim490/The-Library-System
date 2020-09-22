package Controllers;

import javafx.fxml.Initializable;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import common.Functions;
import entity.User;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;




public class BookDetailsByReaderController implements Initializable{
	private ArrayList<String> book = new ArrayList<String>();
	private ArrayList<ArrayList<String>>resultOrders;
	
	private Stage pStage;
	private AnchorPane AnchorPaneTabs;
	private String TableURL = "";
	private User MyUserData;

	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane MainAnchor;
    
    @FXML
    private ImageView btnViewContent;

    @FXML
    private JFXTextField txtBookCatalogNum;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtAuthorName;

    @FXML
    private JFXTextField TxtSubject;

    @FXML
    private JFXTextField txtAvailibleCopies;

    @FXML
    private JFXTextField txtExistingCopies;

    @FXML
    private JFXTextField txtShelfNum;

    @FXML
    private JFXTextField txtPopularity;

    @FXML
    private JFXTextField txtEditionNumber;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtPublishDate;

    @FXML
    private JFXTextField txtPurchasedDate;

  
    @FXML
    private JFXButton btnOrderThisBook;


    /**
	 * click to view this book table of contents
	 *
	 * @param MouseEvent
	 * return void
	 */
    @FXML
	void ViewContent(MouseEvent event) {
		String tableURL = book.get(11);

		
		
		
		ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
	      key.add(txtBookCatalogNum.getText());
	      askForResult.add("*");     
	      ArrayList<ArrayList<String>> arr = Functions.askFromDB("SELECT TableOfContents FROM g17DB.tblbook WHERE CatalogNumber = ?;",key,askForResult) ;
	      String url = arr.get(0).get(0);
	      String prompt = "C:\\Users\\Oriel\\eclipse-workspace\\Prototype-G17-13\\src\\PDF\\";
	      System.out.println("arr:"+url);
	      Functions.askFromFILE(prompt+url);
	      
	      if (Desktop.isDesktopSupported()) {
				try {//"/path/to/file.pdf"
					File myFile = new File(prompt+url);
					Desktop.getDesktop().open(myFile);
				} catch (IOException ex) {
					// no application registered for PDFs
				}
			}
	      
				//String tableURL = book.get(11);
		    	/*ArrayList<String> key = new ArrayList<String>();
			    ArrayList<String> askForResult = new ArrayList<String>();
			      key.add(txtBookCatalogNum.getText());
			      askForResult.add("*");     
			      ArrayList<ArrayList<String>> arr = Functions.askFromDB("SELECT TableOfContents FROM g17DB.tblbook WHERE CatalogNumber = ?;",key,askForResult) ;
			      String url = arr.get(0).get(0);
			      System.out.println("arr:"+url);
			      Functions.askFromFILE("C:\\Users\\Oriel\\eclipse-workspace\\Prototype-G17-13\\src\\PDF\\"+url);*/
	}

    /**
     * Try to order a specific book from the list
     * @param event action
     * @return nothing
     */
    @FXML
    void btnOrderThisBook(ActionEvent event) {
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	ArrayList<String> keyR = new ArrayList<String>();
    	ArrayList<String> R = new ArrayList<String>();
	    ArrayList<String> askForRResult = new ArrayList<String>();
	    String sqlR="SELECT ReaderCardNumber FROM g17db.tblreader as r, g17db.tbluser as u WHERE u.UserID=r.UserID AND u.UserID=?;";
	    keyR.add(MyUserData.getUserID());
	    askForRResult.add("*");
	    R=Functions.askFromDB(sqlR, keyR, askForRResult).get(0);
    	ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
	    ArrayList<String> maxOrder = new ArrayList<String>();
		String sql = "SELECT count(*), b.ExistingAmount ,b.BookID\r\n" + 
				"FROM g17db.tblbookorders bo,g17db.tblbook as b\r\n" + 
				"WHERE b.BookID=bo.BookID  AND b.BookID =(SELECT b1.BookID\r\n" + 
				"											FROM g17db.tblbook b1\r\n" + 
				"											WHERE b1.BookName=? AND b1.AuthorName =?) ";
		
		key.add(txtBookName.getText());
		key.add(txtAuthorName.getText());
		askForResult.add("*");
		int num1=0;
		String ExistingAmount,BookID;
		try {
			resultOrders=Functions.askFromDB(sql, key, askForResult);
			String numOnWaitingList=resultOrders.get(0).get(0);
			ExistingAmount=resultOrders.get(0).get(1);
			BookID=resultOrders.get(0).get(2);
	    	num1=Integer.parseInt(numOnWaitingList);
		}
		catch (Exception e) {
			sql="SELECT b1.BookID, b1.ExistingAmount FROM g17db.tblbook b1	WHERE b1.BookName=? AND b1.AuthorName =?;";
			key.clear();
			key.add(txtBookName.getText());
			key.add(txtAuthorName.getText());
			askForResult.clear();
			askForResult.add("bookID");
			askForResult.add("ExistingAmount");
			resultOrders=Functions.askFromDB(sql, key, askForResult);
			BookID=resultOrders.get(0).get(0);
			ExistingAmount=resultOrders.get(0).get(1);
		}
		int amount=Integer.parseInt(ExistingAmount);
    	if (amount==num1){
    		JOptionPane.showMessageDialog(null, "All existing copies have been ordered already...\nPlease try again later.");
    		return;
    	}
    	key.clear();
    	askForResult.clear();
    	askForResult.add("*");
    	maxOrder=Functions.askFromDB("SELECT max(OrderID) FROM g17db.tblbookorders", key, askForResult).get(0);
    	String str = maxOrder.get(0).substring(1);
    	int num=Integer.parseInt(str);
    	num++;
    	str=num+"";
    	num=str.length();
    	for(int i=0;i<7-num-1;i++)
    		str="0"+str;
    	str="O"+str;
    	sql="INSERT INTO `g17db`.`tblbookorders` (`OrderID`, `BookID`, `OrderDate`, `ReaderCardNumber`, `OrderStatus`,`ArrivedDate`) VALUES (?,?,?,?,?,?);";
    	key.clear();
    	key.add(str);
    	key.add(BookID);
    	key.add(LocalDate.now().format(formatter));
    	key.add(R.get(0));
    	if(num1==0) {
    		key.add("Arrived");
    		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		Date date = new Date();
    		key.add(dateFormat.format(date));}
    	else {
    		key.add("Waiting");
    		key.add("");}
    	Functions.askFromDB(sql, key);
    	JOptionPane.showMessageDialog(null, "Order Succeed you orderID is: "+str);
    	((Node)event.getSource()).getScene().getWindow().hide();
    	
    	
    	
    	
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
	 	actKey.add("Order Book"); 
	 	actKey.add(LocalDate.now()+""); 
	 	Functions.askFromDB("INSERT INTO `g17db`.`tbllistofhistory` (`ActivityID`, `ReaderCardNumber`, `Activity`, `Date`) VALUES (?, ?, ?, ?);", actKey);
	 
    }
     /**
 * Initialize the user info
 * @param thisUserData d
 *
 */

    public void setUser(User thisUserData) {
    	this.MyUserData = thisUserData;
  }
	
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	


	    }
		
/**
 * Initialize the book detail to the window.
 * @param book d
 *
 */
		public void loadBook(ArrayList<String> book){
			this.book = book;
			txtBookCatalogNum.setText(book.get(0));
			txtBookName.setText(book.get(1));
			txtAuthorName.setText(book.get(2));
			TxtSubject.setText(book.get(3));
			txtAvailibleCopies.setText(book.get(4));
			txtExistingCopies.setText(book.get(5));
			txtShelfNum.setText(book.get(6));
			txtPopularity.setText(book.get(7));
			txtEditionNumber.setText(book.get(8));
			txtPublishDate.setText(book.get(9)); 
			txtPurchasedDate.setText(book.get(10));
			this.TableURL = book.get(11);
			txtDescription.setText(book.get(12));
		}
		/**
		 * load last page stage to this one(to be able to control that last window)
		 * @param mainstage d
		 * 
		 * 
		 */
		public void loadStage(Stage mainstage){
			this.pStage = mainstage;
			
		}
		/**
		 * load last page AnchorPane to this one(to be able to control that last window)
		 * @param anchorpanetabs d
		 * 
		 
		 */
		public void loadAnchor(AnchorPane anchorpanetabs){
			this.AnchorPaneTabs = anchorpanetabs;
			
		}
		
		
}
