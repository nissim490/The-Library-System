package Controllers;


import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javax.swing.JOptionPane;
import javafx.scene.layout.StackPane;
import common.Functions;
import javafx.event.ActionEvent;
            
public class ReturnABookByLibrarianOrManagerController {

	private String LibrarianName;
	private String CardStatus;
	private String bookborrowid;
	private boolean flag=false;
	private ArrayList<ArrayList<String>> resultUser;
	private String bookcopynum;
	private ArrayList<ArrayList<String>> resultPageDetails;
	private ArrayList<ArrayList<String>> resultCopy;
	
	private LocalDate returndateborrow;
	private String amountofdelay;
	private String readernum;
	private String missed;
	private String amountavailible;
	private String bookid;
	JFXButton yesButton = new JFXButton("YES, Please");
	JFXButton okButton = new JFXButton("Alright");
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private StackPane MainStack;
    
    @FXML
    private JFXTextField BookCopySearchtxt;

    @FXML
    private JFXTextField txtBookCatalogNum;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtAuthorName;

    @FXML
    private JFXTextField txtReturnDate;

    @FXML
    private JFXTextField LenderNamelbl;

    @FXML
    private JFXTextField lblReaderName;

    @FXML
    private JFXButton btnReturnBook;

    @FXML
    private JFXDatePicker dpkrReturnDate;

    @FXML
    private JFXButton BookCopySearchbtn;
	private String archive;


    @FXML
    void BookCopySearchBtn(ActionEvent event) {
    	ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
    	String sql;
    	String enter;
    	enter=BookCopySearchtxt.getText().toString();
    	lblReaderName.clear();
    	LenderNamelbl.clear();
    	
    	
    	if(enter.isEmpty()) {
    		//JOptionPane.showMessageDialog(null,BookCopySearchtxt.getPromptText());
    		AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage",BookCopySearchtxt.getPromptText());  
    		return;
    	}
    	if(enter.length()!=8 && enter.length()!=6){
    		//JOptionPane.showMessageDialog(null,"Please enter right Book Copy ID");
    		AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Please enter right Book Copy ID"); 
    		return;
    	}
    	if(enter.length()==8) {
    		if (enter.substring(0, 2).equals("BC")&&enter.substring(2).matches("[0-9]+"))
    			this.bookcopynum=enter;
    		else {
    			AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Please enter right Book Copy ID"); 
    			//JOptionPane.showMessageDialog(null,"Please enter right Book Copy ID");
	    		return;
    		}	    			
    	}
    	else {
    		if(enter.matches("[0-9]+"))
    			this.bookcopynum="BC"+enter;
    		else {
    			AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Please enter right Book Copy ID"); 
    			//JOptionPane.showMessageDialog(null,"Please enter right Book Copy ID");
	    		return;
    		}	    		
    	}
    	
    	//bookcopynum is initialize here.
    	sql="SELECT b.CatalogNumber,b.BookName,b.AuthorName,bo.ReturnDate,u.FirstName,u.Lastname,r.CardStatus,bo.BookBorrowID,bo.ReturnDate,r.AmountOfBookDelays,r.ReaderCardNumber,r.MissedReturn,b.AmountAvailable,b.BookID,b.ArchiveStatus\r\n" + 
    			"FROM g17db.tblbookborrow as bo ,g17db.tblbook as b ,g17db.tblbookcopy as bc, g17db.tblreader as r, g17db.tbluser as u\r\n" + 
    			"WHERE bc.BookID=b.BookID AND bc.BookCopyID=bo.BookCopyID AND bo.Status='Active' AND bo.ReaderCardNumber=r.ReaderCardNumber AND r.UserID=u.UserID AND bc.BookCopyID=?;";
    	askForResult.add("CatalogNumber");
    	askForResult.add("BookName");
    	askForResult.add("AuthorName");
    	askForResult.add("ReturnDate");
    	askForResult.add("FirstName");
    	askForResult.add("Lastname");
    	askForResult.add("CardStatus");
    	askForResult.add("BookBorrowID");
    	askForResult.add("ReturnDate");
    	askForResult.add("AmountOfBookDelays");
    	askForResult.add("ReaderCardNumber");
    	askForResult.add("MissedReturn");
    	askForResult.add("AmountAvailable");
    	askForResult.add("BookID");
    	askForResult.add("ArchiveStatus");
    	
    	key.add(this.bookcopynum);
    	resultPageDetails=Functions.askFromDB(sql, key, askForResult);
    	
    	if(resultPageDetails.size()==0){
    		AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Could not found matching result for your search."); 
			//JOptionPane.showMessageDialog(null,"Could not found matching result for your search.");
    		return;
		}
    		
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	txtBookCatalogNum.setText(resultPageDetails.get(0).get(0));
    	txtBookName.setText(resultPageDetails.get(0).get(1));
    	txtAuthorName.setText(resultPageDetails.get(0).get(2));
    	txtReturnDate.setText(resultPageDetails.get(0).get(3));
    	dpkrReturnDate.setValue(LocalDate.now());
    	LenderNamelbl.setText(this.LibrarianName);
    	lblReaderName.setText(resultPageDetails.get(0).get(4) + " " + resultPageDetails.get(0).get(5));
    	this.CardStatus=resultPageDetails.get(0).get(6).trim();
    	this.flag=true;
    	this.bookborrowid = resultPageDetails.get(0).get(7);
    	this.returndateborrow=LocalDate.parse(resultPageDetails.get(0).get(8), formatter);
    	this.amountofdelay=resultPageDetails.get(0).get(9);
    	this.readernum=resultPageDetails.get(0).get(10);
    	this.missed=resultPageDetails.get(0).get(11);
    	this.amountavailible=resultPageDetails.get(0).get(12);
    	this.bookid=resultPageDetails.get(0).get(13);
    	this.archive=resultPageDetails.get(0).get(14);
 

    }
	
	/**
	 * return a book copy to the library and make it available again.
	 * @param event action
	 * @return void
	 */
    @FXML
    void btnReturnBook(ActionEvent event) {
    	DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    	ArrayList<String> key = new ArrayList<String>();
    	String sqlB="UPDATE g17db.tblbookborrow as bb,g17db.tblbookcopy as bc,g17db.tblreader as r \r\n" + 
    			"SET r.MissedReturn=?,r.AmountOfBookDelays=?,bb.ActualReturnDate = ? ,bb.Status ='Inactive' ,bb.BorrowedDelayed='Yes',bc.Status=?\r\n" + 
    			"WHERE bb.BookCopyID=bc.BookCopyID AND bb.BookBorrowID = ? And r.ReaderCardNumber=?;";
    	String sqlA="UPDATE g17db.tblbookborrow as bb,g17db.tblbookcopy as bc \r\n" + 
    			"SET bb.ActualReturnDate = ? ,bb.Status ='Inactive' ,bc.Status=?\r\n" + 
    			"WHERE bb.BookCopyID=bc.BookCopyID AND bb.BookBorrowID = ?;";
    	if(!flag){
    		AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Please search your book first."); 
			//JOptionPane.showMessageDialog(null,"Please search your book first.");
    		return;
		}
    	if(dpkrReturnDate.getValue().toString().trim().isEmpty()){
    		AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Please enter date of return date."); 
			//JOptionPane.showMessageDialog(null,"Please enter date of return date.");
    		return;
		}
    	else {
    		if(dpkrReturnDate.getValue().isAfter(LocalDate.now())){
    			AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Date is incorrect, today is: "+LocalDate.now()+"."); 
    			//JOptionPane.showMessageDialog(null,"Date is incorrect, today is: "+LocalDate.now()+".");
	    		return;
    		}
    	}

    	
    	key.add(dpkrReturnDate.getValue().format(formatters));
    	if(archive.equals("Normal"))
    		key.add("Shelf");
    	else
    		key.add("Removed");
    	key.add(bookborrowid);
    	if(dpkrReturnDate.getValue().isBefore(LocalDate.now()))
    	Functions.askFromDB(sqlA, key);
    	else {
    		key.add(0, "Yes");
    		key.add(1, (Integer.parseInt(this.amountofdelay)+1)+"");
    		key.add(this.readernum);
    		Functions.askFromDB(sqlB, key);
    	}
    	Unfreeze();
    	UpdateCopy();
    	AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Succeed Massage","Return succeed"); 
    	//JOptionPane.showMessageDialog(null,"Succeed");
    	


    	String actSql="SELECT * FROM g17db.tbllistofhistory;";
    	    	ArrayList<String> actKey = new ArrayList<String>();
    		    ArrayList<String> askForActResult = new ArrayList<String>();
    		    askForActResult.add("*");
    		    ArrayList<ArrayList<String>>resultAct =Functions.askFromDB(actSql, actKey, askForActResult);
    	    	   	

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
    		 	actKey.add(this.readernum); 
    		 	actKey.add("Return Book"); 
    		 	actKey.add(LocalDate.now()+""); 
    		 	Functions.askFromDB("INSERT INTO `g17db`.`tbllistofhistory` (`ActivityID`, `ReaderCardNumber`, `Activity`, `Date`) VALUES (?, ?, ?, ?);", actKey);

    }

    /**
     * Enter your CopyID to search for, then showing the data on the screen.
     * @param event -action 
     * @return void
     */
    @FXML
    void click(ActionEvent event) {
    	ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
    	String sql;
    	String enter;
    	enter=BookCopySearchtxt.getText().toString();
    	lblReaderName.clear();
    	LenderNamelbl.clear();
    	
    	
    	if(enter.isEmpty()) {
    		AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage",BookCopySearchtxt.getPromptText()); 
    		//JOptionPane.showMessageDialog(null,BookCopySearchtxt.getPromptText());
    		return;
    	}
    	if(enter.length()!=8 && enter.length()!=6){
    		AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Please enter right Book Copy ID"); 
    		//JOptionPane.showMessageDialog(null,"Please enter right Book Copy ID");
    		return;
    	}
    	if(enter.length()==8) {
    		if (enter.substring(0, 2).equals("BC")&&enter.substring(2).matches("[0-9]+"))
    			this.bookcopynum=enter;
    		else {
    			AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Please enter right Book Copy ID"); 
    			//JOptionPane.showMessageDialog(null,"Please enter right Book Copy ID");
	    		return;
    		}	    			
    	}
    	else {
    		if(enter.matches("[0-9]+"))
    			this.bookcopynum="BC"+enter;
    		else {
    			AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Please enter right Book Copy ID"); 
    			//JOptionPane.showMessageDialog(null,"Please enter right Book Copy ID");
	    		return;
    		}	    		
    	}
    	
    	//bookcopynum is initialize here.
    	sql="SELECT b.CatalogNumber,b.BookName,b.AuthorName,bo.ReturnDate,u.FirstName,u.Lastname,r.CardStatus,bo.BookBorrowID,bo.ReturnDate,r.AmountOfBookDelays,r.ReaderCardNumber,r.MissedReturn,b.AmountAvailable,b.BookID,b.ArchiveStatus\r\n" + 
    			"FROM g17db.tblbookborrow as bo ,g17db.tblbook as b ,g17db.tblbookcopy as bc, g17db.tblreader as r, g17db.tbluser as u\r\n" + 
    			"WHERE bc.BookID=b.BookID AND bc.BookCopyID=bo.BookCopyID AND bo.Status='Active' AND bo.ReaderCardNumber=r.ReaderCardNumber AND r.UserID=u.UserID AND bc.BookCopyID=?;";
    	askForResult.add("CatalogNumber");
    	askForResult.add("BookName");
    	askForResult.add("AuthorName");
    	askForResult.add("ReturnDate");
    	askForResult.add("FirstName");
    	askForResult.add("Lastname");
    	askForResult.add("CardStatus");
    	askForResult.add("BookBorrowID");
    	askForResult.add("ReturnDate");
    	askForResult.add("AmountOfBookDelays");
    	askForResult.add("ReaderCardNumber");
    	askForResult.add("MissedReturn");
    	askForResult.add("AmountAvailable");
    	askForResult.add("BookID");
    	askForResult.add("ArchiveStatus");
    	key.add(this.bookcopynum);
    	resultPageDetails=Functions.askFromDB(sql, key, askForResult);
    	
    	if(resultPageDetails.size()==0){
    		AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Error Massage","Could not found matching result for your search."); 
			//JOptionPane.showMessageDialog(null,"Could not found matching result for your search.");
    		return;
		}
    		
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	txtBookCatalogNum.setText(resultPageDetails.get(0).get(0));
    	txtBookName.setText(resultPageDetails.get(0).get(1));
    	txtAuthorName.setText(resultPageDetails.get(0).get(2));
    	txtReturnDate.setText(resultPageDetails.get(0).get(3));
    	dpkrReturnDate.setValue(LocalDate.now());
    	LenderNamelbl.setText(this.LibrarianName);
    	lblReaderName.setText(resultPageDetails.get(0).get(4) + " " + resultPageDetails.get(0).get(5));
    	this.CardStatus=resultPageDetails.get(0).get(6).trim();
    	this.flag=true;
    	this.bookborrowid = resultPageDetails.get(0).get(7);
    	this.returndateborrow=LocalDate.parse(resultPageDetails.get(0).get(8), formatter);
    	this.amountofdelay=resultPageDetails.get(0).get(9);
    	this.readernum=resultPageDetails.get(0).get(10);
    	this.missed=resultPageDetails.get(0).get(11);
    	this.amountavailible=resultPageDetails.get(0).get(12);
    	this.bookid=resultPageDetails.get(0).get(13);
    	this.archive=resultPageDetails.get(0).get(14);

    }
    /**
     * update the copy inside the database
     * @param 
     * @param 
     * @return void
     */
    private void UpdateCopy() {
		ArrayList<String> key = new ArrayList<String>();
		ArrayList<String> askForResult = new ArrayList<String>();
		String sql="SELECT bo.OrderID\r\n" + 
				"FROM g17db.tblbookorders as bo, g17db.tblbookcopy as bc\r\n" + 
				"WHERE bo.BookID=bc.BookID AND bo.OrderStatus='Waiting' AND bc.BookCopyID=?\r\n" + 
				"ORDER BY bo.OrderID";
		key.add(this.bookcopynum);
		askForResult.add("OrderID");
		resultCopy=Functions.askFromDB(sql, key, askForResult);
		if (resultCopy.size()==0) {
			int amount=Integer.parseInt(amountavailible);
			amount++;
			sql="UPDATE `g17db`.`tblbook` SET `AmountAvailable` = ? WHERE (`BookID` = ?);";
			key.clear();
			key.add(amount+"");
			key.add(this.bookid);
			Functions.askFromDB(sql, key);
			sql="UPDATE `g17db`.`tblbookcopy` SET `Status` = 'Shelf' WHERE (`BookCopyID` = ?);";
			key.clear();
			key.add(this.bookcopynum);
			Functions.askFromDB(sql, key);
			return;
		}
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = new Date();
    	//System.out.println(dateFormat.format(date));
    	//return dateFormat.format(date);
		String nextOrderID=resultCopy.get(0).get(0);
		sql="UPDATE `g17db`.`tblbookorders` SET `OrderStatus` = 'Arrived', `ArrivedDate` = ? WHERE (`OrderID` = ?);";
		key.clear();
		key.add(dateFormat.format(date));
		key.add(nextOrderID);
		Functions.askFromDB(sql, key);
		
	}
    /**
     * to change reader status after return
     * @param 
     * @param 
     * @return void
     */
	private void Unfreeze() {
    	String sqlA="UPDATE g17db.tblreader SET AmountOfBookDelays = ?,CardStatus='Active' WHERE ReaderCardNumber = ?;";
    	String sqlF="UPDATE g17db.tblreader SET AmountOfBookDelays = ? WHERE ReaderCardNumber = ?;";
    	String sqlM="UPDATE g17db.tblreader SET AmountOfBookDelays = ?,CardStatus='Active', MissedReturn='Yes' WHERE ReaderCardNumber = ?;";
    	ArrayList<String> key = new ArrayList<String>();	    	
		if(CardStatus.equals("Active"))
			return;
		int amount=Integer.parseInt(amountofdelay);
		if(returndateborrow.isBefore(LocalDate.now())) {//late
			amount++;
			key.add(amount+"");
			key.add(readernum);
			if(amount>2) 
				Functions.askFromDB(sqlF, key);
			else
				if(missed.equals("No"))
					Functions.askFromDB(sqlM, key);
				else
					Functions.askFromDB(sqlA, key);		
		}
	}

/**
 * Initialize the user. 
 * @param u-User to set
 *
 */
	public void SetUser(String u) {
		ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
    	String sql;
    	//this.u=u;
    	sql = "SELECT u.FirstName , u.Lastname FROM g17db.tbluser as u WHERE  u.UserID = ?;";
    	askForResult.add("FirstName");
    	askForResult.add("Lastname");
    	//key.add(u.getUserID());
    	key.add(u);
    	resultUser = Functions.askFromDB(sql ,key,askForResult);
    	String a = resultUser.get(0).get(0) +" " +resultUser.get(0).get(1);
    	this.LibrarianName = a;
    	//lblReaderName.setText(u.getFirstName() + " " + u.getLastName());
	}



    @FXML
    void initialize() {
        assert BookCopySearchtxt != null : "fx:id=\"BookCopySearchtxt\" was not injected: check your FXML file 'ReturnABookByLibrarianOrManager.fxml'.";
        assert txtBookCatalogNum != null : "fx:id=\"txtBookCatalogNum\" was not injected: check your FXML file 'ReturnABookByLibrarianOrManager.fxml'.";
        assert txtBookName != null : "fx:id=\"txtBookName\" was not injected: check your FXML file 'ReturnABookByLibrarianOrManager.fxml'.";
        assert txtAuthorName != null : "fx:id=\"txtAuthorName\" was not injected: check your FXML file 'ReturnABookByLibrarianOrManager.fxml'.";
        assert txtReturnDate != null : "fx:id=\"txtReturnDate\" was not injected: check your FXML file 'ReturnABookByLibrarianOrManager.fxml'.";
        assert LenderNamelbl != null : "fx:id=\"LenderNamelbl\" was not injected: check your FXML file 'ReturnABookByLibrarianOrManager.fxml'.";
        assert lblReaderName != null : "fx:id=\"lblReaderName\" was not injected: check your FXML file 'ReturnABookByLibrarianOrManager.fxml'.";
        assert btnReturnBook != null : "fx:id=\"btnReturnBook\" was not injected: check your FXML file 'ReturnABookByLibrarianOrManager.fxml'.";
        assert dpkrReturnDate != null : "fx:id=\"dpkrReturnDate\" was not injected: check your FXML file 'ReturnABookByLibrarianOrManager.fxml'.";
        assert BookCopySearchbtn != null : "fx:id=\"BookCopySearchbtn\" was not injected: check your FXML file 'ReturnABookByLibrarianOrManager.fxml'.";

    }
}
