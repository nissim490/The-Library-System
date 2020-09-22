package Controllers;

import com.jfoenix.controls.JFXButton;


import common.Functions;
import entity.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ActivitiesHistoryByReaderController {///////////////////////////////////////A class that displays the history of the reader's actions

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane ActivitiesHistoryAnchorPane;

    @FXML
    private TableView<SubActivitiesHistory> tblActivitiesHistory;

    @FXML
    private TableColumn<SubActivitiesHistory, String> tblcolRowNum;

    @FXML
    private TableColumn<SubActivitiesHistory, String>tblcolActivity;

    @FXML
    private TableColumn<SubActivitiesHistory, String> tblcolDate;

   
    @FXML
    private Label lblActivityHistory;

    @FXML
    private TableView<SubBorrow> tblOldBorrowingHistory;


    @FXML
    private TableColumn<SubBorrow, String> tblcolOldRowNum;

    @FXML
    private TableColumn<SubBorrow, String> tblcolOldBookName;

    @FXML
    private TableColumn<SubBorrow, String> tblcolOldBookAuthor;
 
    @FXML
    private TableColumn<SubBorrow, String> tblcolOldBorrowingDate;

    @FXML
    private TableColumn<SubBorrow, String> tblcolOldReturnDate;

    @FXML
    private TableColumn<SubBorrow, String> tblcolOldActualReturnDate;

    @FXML
    private TableColumn<SubBorrow, String> tblcolOldDelayed;

    @FXML
    private TableColumn<SubBorrow, String> tblcolOldLibrarianName;


    @FXML
    private Label lblActivityHistory1;

    @FXML
    private JFXButton btnViewOldBorrowedBookInfo;

    @FXML
    private TableView<SublDelayedBorrows> tblDelayedBorrows;

    @FXML
    private TableColumn<SublDelayedBorrows, String> tblcolDelayedRowNum;

    @FXML
    private TableColumn<SublDelayedBorrows, String>tblcolDelayedBookName;

    @FXML
    private TableColumn<SublDelayedBorrows, String> tblcolDelayedBookAuthor;

    @FXML
    private TableColumn<SublDelayedBorrows, String> tblcolDelayedBorrowingDate;

    @FXML
    private TableColumn<SublDelayedBorrows, String> tblcolDelayedReturnDate;

   // @FXML
  //  private TableColumn<SublDelayedBorrows, String> tblcolDelayedActualReturnDate;
    @FXML
    private TableColumn<SublDelayedBorrows, String> tblcolDelayedLibrarianName;

    @FXML
    private Label lblActivityHistory2;

    @FXML
    private JFXButton btnViewDelayedBookInfo;

    @FXML
    private TableView<SubBorrow> tblMissUsedBorrows;

    @FXML
    private TableColumn<SubBorrow, String> tblcolMissRowNum;

    @FXML
    private TableColumn<SubBorrow, String> tblcolMissBookName;

    @FXML
    private TableColumn<SubBorrow, String> tblcolMissBookAuthor;

    @FXML
    private TableColumn<SubBorrow, String> tblcolMissBorrowingDate;

    @FXML
    private TableColumn<SubBorrow, String> tblcolMissReturnDate;

    @FXML
    private TableColumn<SubBorrow, String> tblcolMissActualReturnDate;

    @FXML
    private TableColumn<SubBorrow, String> tblcolMissDelayed;

    @FXML
    private TableColumn<SubBorrow, String> tblcolMissLibrarianName;

    @FXML
    private Label lblActivityHistory3;

    @FXML
    private JFXButton btnViewMissUsedBookInfo;
    private AnchorPane AnchorPaneTabs;
    User UserInfo;//User logged in now 
    ObservableList<SubBorrow> MylistSubBorrow4= FXCollections.observableArrayList();//Mylist to table
    ObservableList<SublDelayedBorrows> MylistSublDelayedBorrows= FXCollections.observableArrayList();//Mylist to table tbl Delayed Borrows
    ObservableList<SubBorrow> MylistSubBorrow= FXCollections.observableArrayList();//Mylist to table Miss Used Borrows
    ObservableList<SubActivitiesHistory> MylistSubActivitiesHistory= FXCollections.observableArrayList();//Mylist to table Activities History
	ArrayList<ArrayList<String>> resultbook;//A list containing information about the book from the DB
	ArrayList<ArrayList<String>> resultBorrow;//A list containing information about the book Borrow from the DB
	ArrayList<ArrayList<String>> resultbookcopy;//A list containing information about the book copy from the DB
	ArrayList<ArrayList<String>> resultreader;//A list containing information about the reader from the DB
	ArrayList<ArrayList<String>> resulthistory;
	
	public void loadAnchor(AnchorPane anchorpanetabs){
		this.AnchorPaneTabs = anchorpanetabs;
		
	}
	
	@FXML
    void btnViewOldBorrowedBookInfo(ActionEvent event) {/**bootun to View Old BorrowedBookInfo*/
		int pos = tblOldBorrowingHistory.getSelectionModel().getSelectedIndex();//row position
 	 String BookID = tblOldBorrowingHistory.getItems().get(pos).getBookID();
 	 String BookName = tblOldBorrowingHistory.getItems().get(pos).getBookName();
 	
 	String CatalogNumber = "";
 	String Description = "";
 	String PublishedDate = "";
 	String PurchaseDate = "";//////initialization strings
 	String AmountAvailable = "";
 	String ExistingAmount = "";
 	String ArchiveStatus = "";
 	String Location = "";
 	String Popularity = "";
 	String TableOfContents = "";
 	String EditionNumber = "";
 	 String AuthorName = "";
	 	 String Subject =  "";
 	int size5 = resultbook.size();
 	 for(int s=0;s<size5;s++) {
 		 if(resultbook.get(s).get(0).equals(BookID)) {

 			  CatalogNumber = resultbook.get(s).get(7);
 		      Description = resultbook.get(s).get(5);
 		      PublishedDate = resultbook.get(s).get(8);
 		      PurchaseDate = resultbook.get(s).get(9);
 		      AmountAvailable = resultbook.get(s).get(4);
 		      ExistingAmount = resultbook.get(s).get(11); ////// set strings 
 		      ArchiveStatus = resultbook.get(s).get(6);
 		      Location = resultbook.get(s).get(12);
 		      Popularity = resultbook.get(s).get(13);
 		      TableOfContents = resultbook.get(s).get(10);
 		     EditionNumber = resultbook.get(s).get(14);
 		     AuthorName = resultbook.get(s).get(3);
 		 	  Subject = resultbook.get(s).get(2);
 		 }
 	 }
 	 

 	 ArrayList<String> BookInfo = new ArrayList<String>();
 	BookInfo.add(CatalogNumber);
 	BookInfo.add(BookName);
 	BookInfo.add(AuthorName);
 	BookInfo.add(Subject);
 	BookInfo.add(AmountAvailable);
 	if(ArchiveStatus.equals("Archived")){
 		BookInfo.add(ArchiveStatus);
 	}
 	else BookInfo.add(ExistingAmount);
 	
 	BookInfo.add(Location);////Load strings 
 	BookInfo.add(Popularity);
 	BookInfo.add(EditionNumber);
 	BookInfo.add(PublishedDate);
 	BookInfo.add(PurchaseDate);
 	BookInfo.add(TableOfContents);
 	BookInfo.add(Description);
 	BookInfo.add(BookID);
 	
 	 //System.out.println(CatalogNumber+" "+BookName+" "+AuthorName+" "+Subject+" "+AmountAvailable+" "+ExistingAmount+" "+ArchiveStatus+" "+Location+" "+Popularity+" "+
 		//	EditionNumber+" "+PublishedDate+" "+PurchaseDate+" "+TableOfContents+" "+Description+" ");
 
 	
 	try {
    	Stage stage = new Stage(); 
        Parent root;                   
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/BookDetailsByReader.fxml"));
		root = loader.load();
		BookDetailsByReaderController BookController = loader.getController();//Move a book's information to another window
		BookController.loadBook(BookInfo);
		//BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
		
		BookController.loadAnchor(AnchorPaneTabs);
		
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
 	
    }
	/**
     * viewing the info on this delayed book
     * @param event-action
     * @return void
     */
    @FXML
    void btnViewDelayedBookInfo(ActionEvent event) {/**bootun to View Delayed Book Information*/
    	int pos = tblDelayedBorrows.getSelectionModel().getSelectedIndex();//row position
 	  	// @SuppressWarnings("unchecked")
 	//ArrayList<String> list = 
 	 String BookID = tblDelayedBorrows.getItems().get(pos).getBookID();
 	 String BookName = tblDelayedBorrows.getItems().get(pos).getBookName();
 	
 	String CatalogNumber = "";
 	String Description = "";
 	String PublishedDate = "";
 	String PurchaseDate = "";
 	String AmountAvailable = "";
 	String ExistingAmount = "";
 	String ArchiveStatus = "";
 	String Location = "";//////initialization strings
 	String Popularity = "";
 	String TableOfContents = "";
 	String EditionNumber = "";
 	 String AuthorName = "";
	 	 String Subject =  "";
 	int size5 = resultbook.size();
 	 for(int s=0;s<size5;s++) {
 		 if(resultbook.get(s).get(0).equals(BookID)) {

 			  CatalogNumber = resultbook.get(s).get(7);
 		      Description = resultbook.get(s).get(5);
 		      PublishedDate = resultbook.get(s).get(8);
 		      PurchaseDate = resultbook.get(s).get(9);
 		      AmountAvailable = resultbook.get(s).get(4);////// set strings
 		      ExistingAmount = resultbook.get(s).get(11);
 		      ArchiveStatus = resultbook.get(s).get(6);
 		      Location = resultbook.get(s).get(12);
 		      Popularity = resultbook.get(s).get(13);
 		      TableOfContents = resultbook.get(s).get(10);
 		     EditionNumber = resultbook.get(s).get(14);
 		     AuthorName = resultbook.get(s).get(3);////// set strings
 		 	  Subject = resultbook.get(s).get(2);
 		 }
 	 }
 	 

 	 ArrayList<String> BookInfo = new ArrayList<String>();
 	BookInfo.add(CatalogNumber);
 	BookInfo.add(BookName);
 	BookInfo.add(AuthorName);
 	BookInfo.add(Subject);////Load strings 
 	BookInfo.add(AmountAvailable);
 	if(ArchiveStatus.equals("Archived")){
 		BookInfo.add(ArchiveStatus);
 	}
 	else BookInfo.add(ExistingAmount);
 	
 	BookInfo.add(Location);
 	BookInfo.add(Popularity);
 	BookInfo.add(EditionNumber);
 	BookInfo.add(PublishedDate);
 	BookInfo.add(PurchaseDate);
 	BookInfo.add(TableOfContents);
 	BookInfo.add(Description);
 	BookInfo.add(BookID);
 	
 	 //System.out.println(CatalogNumber+" "+BookName+" "+AuthorName+" "+Subject+" "+AmountAvailable+" "+ExistingAmount+" "+ArchiveStatus+" "+Location+" "+Popularity+" "+
 		//	EditionNumber+" "+PublishedDate+" "+PurchaseDate+" "+TableOfContents+" "+Description+" ");
 
 	
 	try {
    	Stage stage = new Stage(); 
        Parent root;                   
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/gui/BookDetailsByReader.fxml"));
		root = loader.load();
		BookDetailsByReaderController BookController = loader.getController(); //Move a book's information to another window
		BookController.loadBook(BookInfo);
		//BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
		
		BookController.loadAnchor(AnchorPaneTabs);
		
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
 	
 }
    
    /**
     * viewing the info on this miss used book
     * @param event-action
     * @return void
     */
    @FXML
    void btnViewMissUsedBookInfo(ActionEvent event) {
    	int pos = tblMissUsedBorrows.getSelectionModel().getSelectedIndex();/**bootun to View Delayed Book Information*/
 	  	// @SuppressWarnings("unchecked")
 	//ArrayList<String> list = 
 	 String BookID = tblMissUsedBorrows.getItems().get(pos).getBookID();
 	 String BookName = tblMissUsedBorrows.getItems().get(pos).getBookName();
 	
 	String CatalogNumber = "";
 	String Description = "";
 	String PublishedDate = "";
 	String PurchaseDate = "";
 	String AmountAvailable = "";
 	String ExistingAmount = "";///initialization strings
 	String ArchiveStatus = "";
 	String Location = "";
 	String Popularity = "";
 	String TableOfContents = "";
 	String EditionNumber = "";
 	 String AuthorName = "";
	 	 String Subject =  "";
 	int size5 = resultbook.size();
 	 for(int s=0;s<size5;s++) {
 		 if(resultbook.get(s).get(0).equals(BookID)) {

 			  CatalogNumber = resultbook.get(s).get(7);
 		      Description = resultbook.get(s).get(5);
 		      PublishedDate = resultbook.get(s).get(8);
 		      PurchaseDate = resultbook.get(s).get(9);////// set strings
 		      AmountAvailable = resultbook.get(s).get(4);
 		      ExistingAmount = resultbook.get(s).get(11);
 		      ArchiveStatus = resultbook.get(s).get(6);
 		      Location = resultbook.get(s).get(12);
 		      Popularity = resultbook.get(s).get(13);
 		      TableOfContents = resultbook.get(s).get(10);
 		     EditionNumber = resultbook.get(s).get(14);
 		     AuthorName = resultbook.get(s).get(3);////// set strings
 		 	  Subject = resultbook.get(s).get(2);
 		 }
 	 }
 	 ArrayList<String> BookInfo = new ArrayList<String>();
  	BookInfo.add(CatalogNumber);
  	BookInfo.add(BookName);
  	BookInfo.add(AuthorName);
  	BookInfo.add(Subject);
  	BookInfo.add(AmountAvailable);
  	if(ArchiveStatus.equals("Archived")){
  		BookInfo.add(ArchiveStatus);
  	}
  	else BookInfo.add(ExistingAmount);
  	
  	BookInfo.add(Location);
  	BookInfo.add(Popularity);
  	BookInfo.add(EditionNumber);
  	BookInfo.add(PublishedDate);
  	BookInfo.add(PurchaseDate);////Load strings 
  	BookInfo.add(TableOfContents);
  	BookInfo.add(Description);
  	BookInfo.add(BookID);
  	
  	 //System.out.println(CatalogNumber+" "+BookName+" "+AuthorName+" "+Subject+" "+AmountAvailable+" "+ExistingAmount+" "+ArchiveStatus+" "+Location+" "+Popularity+" "+
  		//	EditionNumber+" "+PublishedDate+" "+PurchaseDate+" "+TableOfContents+" "+Description+" ");
  
  	
  	try {
     	Stage stage = new Stage(); 
         Parent root;                   
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource("/gui/BookDetailsByReader.fxml"));
 		root = loader.load();
 		BookDetailsByReaderController BookController = loader.getController();/**Move a book's information to another window*/
 		//BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
 		
 		BookController.loadAnchor(AnchorPaneTabs);
 		
         Scene scene = new Scene(root);
         stage.setScene(scene);
         stage.show();
 	} catch (Exception e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 	}  

    }
    

    @FXML
    void initialize() {

    }
    
    
   
    /**
     * loading data to the tables
     * @param none
     * @return void
     */
    
    private void loadData() {/**The function accepts the user data, updates the  textfild and brings in relevant data from the DB*/
    	String ReaderCardNumber="";
      	ArrayList<String> askForResult5 = new ArrayList<String>();
    	ArrayList<String> askForResult4 = new ArrayList<String>();
    	ArrayList<String> askForResult3 = new ArrayList<String>();
    	ArrayList<String> askForResult = new ArrayList<String>();/**Lists that contain the information we want from DB*/
    	 ArrayList<String> askForResult2 = new ArrayList<String>();
    	 ArrayList<String> key = new ArrayList<String>();
 
   	  key.clear();
   	String 	sql = "SELECT * FROM g17db.tblreader;";
   	   askForResult4.add("UserID");
	   askForResult4.add("ReaderCardNumber");//information reader we want from DB
	   askForResult4.add("AmountOfBookDelays");
	   resultreader = Functions.askFromDB(sql ,key,askForResult4);
int	sizereader = resultreader.size();
key.clear();
   	  
    	   sql = "SELECT * FROM g17db.tblbookborrow;";
    	 askForResult2.add("BookBorrowID");	      
    	 askForResult2.add("BookCopyID");	      
    	 askForResult2.add("ReaderCardNumber");	      
    	 askForResult2.add("BorrowingDate");	      
    	 askForResult2.add("ReturnDate");	      
    	 askForResult2.add("ActualReturnDate");  ///information bookborrows we want from DB
    	 askForResult2.add("BorrowedDelayed");	      
    	 askForResult2.add("LenderName");
    	 askForResult2.add("Status");
    	 askForResult2.add("BorrowedMissUsed");
	
		  resultBorrow = Functions.askFromDB(sql ,key,askForResult2);
			int  sizeBorrow = resultBorrow.size();//size of the list 
			
			
			
			
			  key.clear();
			    
				 sql = "SELECT * FROM g17db.tblbookcopy;";
			 	 askForResult3.add("BookCopyID");	
				  askForResult3.add("BookID");
				  
				  
			
				  resultbookcopy = Functions.askFromDB(sql ,key,askForResult3);
				int  sizebookcopy = resultbookcopy.size();
    	
		  key.clear();
    
		 sql = "SELECT * FROM g17db.tblbook;";
	 	 askForResult.add("BookID");	
		  askForResult.add("BookName");///information books we want from DB
		  askForResult.add("Subject");

		
			  askForResult.add("AuthorName");
			  askForResult.add("AmountAvailable");
			  askForResult.add("Description");
			  askForResult.add("ArchiveStatus");///information books we want from DB
			  askForResult.add("CatalogNumber");
			  askForResult.add("PublishedDate");
			  askForResult.add("PurchaseDate");
			  askForResult.add("TableOfContents");
			  askForResult.add("ExistingAmount");
			  askForResult.add("Location");
			  askForResult.add("Popularity");
			  askForResult.add("EditionNumber");
	
		  resultbook = Functions.askFromDB(sql ,key,askForResult);
		  for(int i=0;i<sizereader;i++) {
			  if(resultreader.get(i).get(0).equals(UserInfo.getUserID()))
			  	ReaderCardNumber=resultreader.get(i).get(1);
			  	
			  }
		  int cnt=1;
		int  size = resultbook.size();
        try {
            for(int i=0;i<sizeBorrow;i++) {
            	for(int c=0;c<sizebookcopy;c++) {
            		for(int j=0;j<size;j++) {
 				   if((resultBorrow.get(i).get(1).equals(resultbookcopy.get(c).get(0)))&&(resultbookcopy.get(c).get(1).equals(resultbook.get(j).get(0))&&((resultBorrow.get(i).get(2).equals(ReaderCardNumber))&&((resultBorrow.get(i).get(8).equals("Inactive")))))) {
                String rownum = String.valueOf(cnt);
                String bookname = resultbook.get(j).get(1);
                String author = resultbook.get(j).get(2);
                String bookBorrowID = resultBorrow.get(i).get(0);
                String readerCardNumber = resultBorrow.get(i).get(2);
                String bookid = resultBorrow.get(i).get(1);//Enter information for lists to initialize the table
                String borrowingDate = resultBorrow.get(i).get(3);
                String returnDate = resultBorrow.get(i).get(4);
                String actualReturnDate = resultBorrow.get(i).get(5);
                String delayed = resultBorrow.get(i).get(6);
                String librarianName = resultBorrow.get(i).get(7);
cnt++;
                MylistSubBorrow.add(new SubBorrow(rownum, bookname, author, borrowingDate, returnDate, actualReturnDate, delayed, librarianName,resultbook.get(j).get(0),resultbook.get(j).get(2)));
 				     }
 				   }
            	}
          
            }
    
        } 
        
        
  	
        
        catch (Exception ex) {
            
        }
        
        tblOldBorrowingHistory.setItems(MylistSubBorrow);
        
        
        key.clear();
 	 	sql = "SELECT * FROM g17db.tbllistofhistory;";
 	  
	   askForResult5.add("ReaderCardNumber");
	 askForResult5.add("Activity");
	   askForResult5.add("Date");
	   resulthistory = Functions.askFromDB(sql ,key,askForResult5);
int	sizehistory = resulthistory.size();
 
cnt=1;

	for(int j=0;j<sizehistory;j++) {
	   if(resulthistory.get(j).get(0).equals(ReaderCardNumber)) {
String rownum = String.valueOf(cnt);
String Activity = resulthistory.get(j).get(1);
String Date = resulthistory.get(j).get(2);


cnt++;
MylistSubActivitiesHistory.add(new SubActivitiesHistory(rownum, Activity, Date));
	     }
	   }
        
	tblActivitiesHistory.setItems(MylistSubActivitiesHistory);//set table
	cnt=1;
	 for(int i=0;i<sizeBorrow;i++) {
     	for(int c=0;c<sizebookcopy;c++) {
     		for(int j=0;j<size;j++) {
			   if((resultBorrow.get(i).get(1).equals(resultbookcopy.get(c).get(0)))&&(resultbookcopy.get(c).get(1).equals(resultbook.get(j).get(0))&&((resultBorrow.get(i).get(2).equals(ReaderCardNumber))&&((resultBorrow.get(i).get(8).equals("Active"))&&((resultBorrow.get(i).get(6).equals("Yes"))))))) {
         String rownum = String.valueOf(cnt);
         String bookname = resultbook.get(j).get(1);
         String author = resultbook.get(j).get(2);
         String bookBorrowID = resultBorrow.get(i).get(0);//Enter information for lists to initialize the table
         String readerCardNumber = resultBorrow.get(i).get(2);
         String bookid = resultBorrow.get(i).get(1);
         String borrowingDate = resultBorrow.get(i).get(3);
         String returnDate = resultBorrow.get(i).get(4);
         String actualReturnDate = resultBorrow.get(i).get(5);
         String librarianName = resultBorrow.get(i).get(7);
cnt++;
         MylistSublDelayedBorrows.add(new SublDelayedBorrows(rownum, bookname, author, borrowingDate, returnDate, librarianName,resultbook.get(j).get(0),resultbook.get(j).get(2)));
			     }
			   }
     	}
   
     }

	 tblDelayedBorrows.setItems(MylistSublDelayedBorrows);//set table
        
        
	 
	 
	 
	  cnt=1;
			  size = resultbook.size();
	        
	            for(int i=0;i<sizeBorrow;i++) {
	            	for(int c=0;c<sizebookcopy;c++) {
	            		for(int j=0;j<size;j++) {
	 				   if((resultBorrow.get(i).get(1).equals(resultbookcopy.get(c).get(0)))&&(resultbookcopy.get(c).get(1).equals(resultbook.get(j).get(0))&&((resultBorrow.get(i).get(2).equals(ReaderCardNumber))&&((resultBorrow.get(i).get(8).equals("Inactive"))&&((resultBorrow.get(i).get(9).equals("yes"))))))) {
	                String rownum = String.valueOf(cnt);
	                String bookname = resultbook.get(j).get(1);
	                String author = resultbook.get(j).get(2);
	                String bookBorrowID = resultBorrow.get(i).get(0);
	                String readerCardNumber = resultBorrow.get(i).get(2);
	                String bookid = resultBorrow.get(i).get(1);
	                String borrowingDate = resultBorrow.get(i).get(3);//Enter information for lists to initialize the table
	                String returnDate = resultBorrow.get(i).get(4);
	                String actualReturnDate = resultBorrow.get(i).get(5);
	                String delayed = resultBorrow.get(i).get(6);
	                String librarianName = resultBorrow.get(i).get(7);
	cnt++;
	MylistSubBorrow4.add(new SubBorrow(rownum, bookname, author, borrowingDate, returnDate, actualReturnDate, delayed, librarianName,resultbook.get(j).get(0),resultbook.get(j).get(2)));
	 				     }
	 				   }
	            	}
	          
	            }
	            tblMissUsedBorrows.setItems(MylistSubBorrow4);
    	
    }
    /**
     * initialization Tables
     * @param none
     * @return void
     */
    private void initCol() {/**initialization Tables*/
////////////////////////////////////////////Table Old Borrowing History
    	tblOldBorrowingHistory.setEditable(true);
    	tblcolOldRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolOldBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	tblcolOldBookAuthor.setCellValueFactory(new PropertyValueFactory<>("BookAuthor"));
    	tblcolOldBorrowingDate.setCellValueFactory(new PropertyValueFactory<>("BorrowingDate"));//initialization Column Old Borrowing History
    	tblcolOldReturnDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
    	tblcolOldActualReturnDate.setCellValueFactory(new PropertyValueFactory<>("ActualReturnDate"));
    	tblcolOldDelayed.setCellValueFactory(new PropertyValueFactory<>("Delayed"));
    	tblcolOldLibrarianName.setCellValueFactory(new PropertyValueFactory<>("LibrarianName"));
    	////////////////////////////////////////////Table Miss Used Borrows
    	
    	    tblMissUsedBorrows.setEditable(true);
    	    tblcolMissRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	    tblcolMissBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	    tblcolMissBookAuthor.setCellValueFactory(new PropertyValueFactory<>("BookAuthor"));
    	    tblcolMissBorrowingDate.setCellValueFactory(new PropertyValueFactory<>("BorrowingDate"));//initialization Column Miss Used Borrows
    	    tblcolMissReturnDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
    	    tblcolMissActualReturnDate.setCellValueFactory(new PropertyValueFactory<>("ActualReturnDate"));
    	    tblcolMissDelayed.setCellValueFactory(new PropertyValueFactory<>("Delayed"));
    	    tblcolMissLibrarianName.setCellValueFactory(new PropertyValueFactory<>("LibrarianName"));
    	/////////////////////////////////////////Table Delayed Borrows
    	tblDelayedBorrows.setEditable(true);
    	tblcolDelayedRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolDelayedBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	tblcolDelayedBookAuthor.setCellValueFactory(new PropertyValueFactory<>("BookAuthor"));
    	tblcolDelayedBorrowingDate.setCellValueFactory(new PropertyValueFactory<>("BorrowingDate"));
    	tblcolDelayedReturnDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
    	//tblcolDelayedActualReturnDate.setCellValueFactory(new PropertyValueFactory<>("ActualReturnDate"));//initialization Column Delayed Borrows
    	
    	tblcolDelayedLibrarianName.setCellValueFactory(new PropertyValueFactory<>("LibrarianName"));
    	/////////////////////////////////////////////Table Activities History
    	tblActivitiesHistory.setEditable(true);
    	tblcolRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolActivity.setCellValueFactory(new PropertyValueFactory<>("Activity"));
    	tblcolDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
    	
    }
 
   
   

   


    
    
    
    /**
     * Transfer user information between windows
     * @param reader d
     * 
     */
    public void loadreader(User reader) {/**Transfer user information between windows*/
    	this.UserInfo=reader;
    	initCol();
    	loadData();
    
    }

   
  

  

    /**
     * creating class for tables
  
     */
    
    public static class SubBorrow {/**Class especially for table*/

        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty BookName;
        private final SimpleStringProperty BookAuthor;
        private final SimpleStringProperty BorrowingDate;
        private final SimpleStringProperty ReturnDate;
        private final SimpleStringProperty ActualReturnDate;
        private final SimpleStringProperty Delayed;
        private final SimpleStringProperty LibrarianName;
        private final SimpleStringProperty BookID;
        private final SimpleStringProperty Subject;
    
       
        public SubBorrow(String rownum,String bookName, String authorName, String borrowingDate,String returnDate,String actualReturnDate, String delayed, String librarianName,String bookID,  String subject) {//constructor
        	   //////////////////////constructor////////////////////////////////////////////////////
        	this.RowNum = new SimpleStringProperty(rownum);
        	this.BookName = new SimpleStringProperty(bookName);
        	this.BookAuthor = new SimpleStringProperty(authorName);
         	this.BorrowingDate = new SimpleStringProperty(borrowingDate);
         	this.ReturnDate = new SimpleStringProperty(returnDate);
        	this.ActualReturnDate = new SimpleStringProperty(actualReturnDate);
        	this.Delayed = new SimpleStringProperty(delayed);
        	this.LibrarianName = new SimpleStringProperty(librarianName);
        	this.BookID = new SimpleStringProperty(bookID);
        	this.Subject = new SimpleStringProperty(subject);
      
        	
        	
        }
        public String getBookID() {
        	return BookID.get();
        }
        public void setBookID(String bookID) {
        	BookID.set(bookID);
        }
        public String getSubject() {
        	return Subject.get();
        }
        public void setSubject(String subject) {
        	this.Subject.set(subject);
        }
        public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);
        }

        public String getBookName() {
        	return BookName.get();
        }
        public void setBookName(String bookName) {//Getters and setters
        	BookName.set(bookName);
        }
        
        
        public String getBookAuthor() {
        	return BookAuthor.get();
        }
        public void setBookAuthor(String authorName) {
        	BookAuthor.set(authorName);
        }
        
  
        public String getBorrowingDate() {
        	return BorrowingDate.get();
        }
        public void setBorrowingDate(String borrowingDate) {
        	this.BorrowingDate.set(borrowingDate);//Getters and setters
        }
        
        public String getReturnDate() {
        	return ReturnDate.get();
        }
        public void setReturnDate(String returnDate) {
        	ReturnDate.set(returnDate);
        }

        public String getActualReturnDate() {
        	return ActualReturnDate.get();
        }
        public void setActualReturnDate(String actualReturnDate) {
        	ActualReturnDate.set(actualReturnDate);
        }
        
        
        public String getDelayed() {
        	return Delayed.get();
        }
        public void setDelayed(String delayed) {
        	Delayed.set(delayed);
        }
        
  
        public String getLibrarianName() {
        	return LibrarianName.get();
        }
        public void setLibrarianName(String librarianName) {
        	this.LibrarianName.set(librarianName);
        }
        
    }
    /**
     * creating call for tables
    
     */
    public static class SubActivitiesHistory {/**Class especially for table of Activities History*/

        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty Activity;
        private final SimpleStringProperty Date;
       
       
         
        
        public SubActivitiesHistory(String rownum,String activity, String date)  {
        	   //////////////////////constructor////////////////////////////////////////////////////
        	this.RowNum = new SimpleStringProperty(rownum);
        	this.Activity = new SimpleStringProperty(activity);
        	this.Date = new SimpleStringProperty(date);
        	
        	
      
        	
        }
       
        public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);//Getters and setters
        }

        public String getActivity() {
        	return Activity.get();
        }
        public void setBActivity(String activity) {
        	Activity.set(activity);
        }
        
        
        public String getDate() {
        	return Date.get();
        }
        public void setDate(String date) {
        	Date.set(date);
        }
        
  
        
        
    } /**
     * creating call for tables
  
     */
    public static class SublDelayedBorrows {/**Class especially for table of Delayed Borrows*/

        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty BookName;
        private final SimpleStringProperty BookAuthor;
        private final SimpleStringProperty BorrowingDate;
        private final SimpleStringProperty ReturnDate;
       // private final SimpleStringProperty ActualReturnDate;
        private final SimpleStringProperty LibrarianName;
        private final SimpleStringProperty BookID;
        private final SimpleStringProperty Subject;
      
        public SublDelayedBorrows(String rownum,String bookName, String authorName, String borrowingDate,String returnDate,  String librarianName,String bookID,  String subject) {
        //////////////////////constructor////////////////////////////////////////////////////
        	this.RowNum = new SimpleStringProperty(rownum);
        	this.BookName = new SimpleStringProperty(bookName);
        	this.BookAuthor = new SimpleStringProperty(authorName);
         	this.BorrowingDate = new SimpleStringProperty(borrowingDate);
         	this.ReturnDate = new SimpleStringProperty(returnDate);
        	//this.ActualReturnDate = new SimpleStringProperty(actualReturnDate);
        	this.BookID = new SimpleStringProperty(bookID);
        	this.Subject = new SimpleStringProperty(subject);
        	this.LibrarianName = new SimpleStringProperty(librarianName);
        	
      
        	
        }
        	
        
        public String getBookID() {
        	return BookID.get();
        }
        public void setBookID(String bookID) {
        	BookID.set(bookID);
        }
        
        
       
        
  
        public String getSubject() {
        	return Subject.get();
        }
        public void setSubject(String subject) {
        	this.Subject.set(subject);
        }
        
        
        
        
        public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);//Getters and setters
        }

        public String getBookName() {
        	return BookName.get();
        }
        public void setBookName(String bookName) {
        	BookName.set(bookName);
        }
        
        
        public String getBookAuthor() {
        	return BookAuthor.get();
        }
        public void setBookAuthor(String authorName) {
        	BookAuthor.set(authorName);
        }
        
  
        public String getBorrowingDate() {
        	return BorrowingDate.get();
        }
        public void setBorrowingDate(String borrowingDate) {//Getters and setters
        	this.BorrowingDate.set(borrowingDate);
        }
        
        public String getReturnDate() {
        	return ReturnDate.get();
        }//Getters and setters
        public void setReturnDate(String returnDate) {
        	ReturnDate.set(returnDate);
        }

        //public String getActualReturnDate() {
        //	return ActualReturnDate.get();
        //}
       // public void setActualReturnDate(String actualReturnDate) {
       // 	ActualReturnDate.set(actualReturnDate);
       // }
        
        
       
        
  
        public String getLibrarianName() {
        	return LibrarianName.get();
        }//Getters and setters
        public void setLibrarianName(String librarianName) {
        	this.LibrarianName.set(librarianName);
        }
        
    }
    
    
}
