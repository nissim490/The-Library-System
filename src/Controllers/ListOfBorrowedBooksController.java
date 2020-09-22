package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import common.Functions;
import entity.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import javafx.event.ActionEvent;



	public class ListOfBorrowedBooksController implements Initializable {
		
		ObservableList<SubBorrowedBook> Mylist = FXCollections.observableArrayList();
		ArrayList<ArrayList<String>> resultbookborrow;
		ArrayList<ArrayList<String>> resultbookcopy;
		ArrayList<ArrayList<String>> readerResult;
		ArrayList<ArrayList<String>> userResult;
		ArrayList<ArrayList<String>> bookResult;
		private User MyUserData;
		
		Stage pStage;
		public String bookname = "";
        public String bookid = "";
        
	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;


	    @FXML
	    private AnchorPane AnchorPaneTabs;
	    
	    @FXML
	    private Pane WindowBorrowedBooks;
	    
	    @FXML
	    private JFXButton btnViewReaderDetails;

	    @FXML
	    private TableView<SubBorrowedBook> tblBorrowedBooks;

	    @FXML
	    private TableColumn<SubBorrowedBook, String> tblcRowNum;

	    @FXML
	    private TableColumn<SubBorrowedBook, String> tblcBookName;

	    @FXML
	    private TableColumn<SubBorrowedBook, String> tblcBookCopyId;

	    @FXML
	    private TableColumn<SubBorrowedBook, String> tblcReaderName;

	    @FXML
	    private TableColumn<SubBorrowedBook, String> tblcReaderCardNumber;

	    @FXML
	    private TableColumn<SubBorrowedBook, String> tblcBorrowingDate;
	    
	    @FXML
	    private TableColumn<SubBorrowedBook, String> tblcReturnDate;

	    @FXML
	    private TableColumn<SubBorrowedBook, String> tblcLibrarianName;
	    
	    @FXML
	    private JFXButton btnViewBookDetails;
		 /**view the selected book information
		 * @param 
		 * @param ActionEvent
		 * @return void
		 */
	    @FXML
	    void btnViewBookDetails(ActionEvent event) {

	    	int i, booksize, bookcopysize,cnt=1;
        	 int pos = tblBorrowedBooks.getSelectionModel().getSelectedIndex();
        	 String BookCopyID = tblBorrowedBooks.getItems().get(pos).getBookCopyID();
        	 String CatalogNumber = ""; 
        	 String PublishedDate = "";
        	 String PurchaseDate = ""; 
        	 String TableOfContents = "";
        	 String ExistingAmount = ""; 
        	 String Location = "";
        	 String Popularity = ""; 
        	 String EditionNumber = "";
	    	String BookName = "";
	    	String AuthorName = "";
         	String Subject = "";
         	String AmountAvailable = "";
         	String Description = "";
         	String BookID = "";
         	String ArchiveStatus = "";

         	  	// @SuppressWarnings("unchecked")
         	//ArrayList<String> list = 
 	 
         	
         	if(!(bookname.equals(""))) {
    		  	ArrayList<String> keybook = new ArrayList<String>();
    		  	ArrayList<String> keybookcopy = new ArrayList<String>();
    		    ArrayList<String> askForResultbook = new ArrayList<String>();
    		    ArrayList<String> askForResultbookcopy = new ArrayList<String>();

         		
         		
         		String sqlbookcopy = "SELECT * FROM g17db.tblbookcopy;";
         		askForResultbookcopy.add("BookCopyID");
         		askForResultbookcopy.add("BookID");
				  
				  String sqlbook = "SELECT * FROM g17db.tblbook;";
				  askForResultbook.add("BookID");
				  askForResultbook.add("BookName");
				  askForResultbook.add("AuthorName");
				  askForResultbook.add("Subject");
				  askForResultbook.add("AmountAvailable");
				  askForResultbook.add("Description");
				  askForResultbook.add("ArchiveStatus");
				  askForResultbook.add("CatalogNumber");
				  askForResultbook.add("PublishedDate");
				  askForResultbook.add("PurchaseDate");
				  askForResultbook.add("TableOfContents");
				  askForResultbook.add("ExistingAmount");
				  askForResultbook.add("Location");
				  askForResultbook.add("Popularity");
				  askForResultbook.add("EditionNumber");
				  

				  bookResult = Functions.askFromDB(sqlbook, keybook, askForResultbook);
				  resultbookcopy = Functions.askFromDB(sqlbookcopy, keybookcopy, askForResultbookcopy);

         	}
         	 	 
         	booksize = bookResult.size();
         	bookcopysize = resultbookcopy.size();
         	 
         	 
         	 for(i=0;i<bookcopysize;i++) {
         		 if(resultbookcopy.get(i).get(0).equals(BookCopyID)) {
         			 BookID = resultbookcopy.get(i).get(1);
         			 break;
         		 }
         	 }
         	 for(int k=0;k<booksize;k++) {
         		 if(bookResult.get(k).get(0).equals(BookID)) {
         			BookName = bookResult.get(k).get(1);
         			AuthorName = bookResult.get(k).get(2);
         			Subject = bookResult.get(k).get(3);
         			AmountAvailable = bookResult.get(k).get(4);
         			Description = bookResult.get(k).get(5);
         			ArchiveStatus = bookResult.get(k).get(6);   
         			CatalogNumber = bookResult.get(k).get(7);
         			PublishedDate = bookResult.get(k).get(8);
         			PurchaseDate = bookResult.get(k).get(9);
         			TableOfContents = bookResult.get(k).get(10);
         			ExistingAmount = bookResult.get(k).get(11);
         			Location = bookResult.get(k).get(12); 
         			Popularity = bookResult.get(k).get(13);
         			EditionNumber = bookResult.get(k).get(14); 
         			break;
         		 }
         	 }

 
         	 
         	 ArrayList<String> BookInfo = new ArrayList<String>();
         	BookInfo.add(CatalogNumber);
         	BookInfo.add(BookName);
         	BookInfo.add(AuthorName);
         	BookInfo.add(Subject);
         	if(ArchiveStatus.equals("Archived")){
         		BookInfo.add(ArchiveStatus);
         		BookInfo.add(ArchiveStatus);
         	}
         	else {
         		BookInfo.add(AmountAvailable);
         		BookInfo.add(ExistingAmount);
         	}
         	
         	BookInfo.add(Location);
         	BookInfo.add(Popularity);
         	BookInfo.add(EditionNumber);
         	BookInfo.add(PublishedDate);
         	BookInfo.add(PurchaseDate);
         	BookInfo.add(TableOfContents);
         	BookInfo.add(Description);
         	BookInfo.add(BookID);
         	 //System.out.println(firstname+" "+lastname+" "+ID+" "+PhoneNumber+" "+Email+" "+Username+" "+Password+" "+ReaderCardNumber+" "+Status+" ");

         	  
         	 //MainAnchor.getScene().getWindow().hide();
         	//((Node)Readerevents.getSource()).getScene().getWindow().hide(); //hiding primary window
         	try {
            	Stage stage = new Stage(); 
                Parent root;                   
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/gui/BookDetailsByLibrarian.fxml"));
				root = loader.load();
				BookDetailsByLibrarianController BookController = loader.getController();
				BookController.loadBook(BookInfo, MyUserData);
				BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
				
				BookController.loadAnchor(AnchorPaneTabs);
				
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    
	    	
	    }
		 /**transefer information
			 * 
			 * @param thisUserData f
			 * 
			 */
	    public void setUser(User thisUserData) {///// brings in relevant data from the DB and Transfer  information between windows ,opnning window
        	this.MyUserData = thisUserData;
        }
		 /**view the selected reader information
		 * @param 
		 * @param ActionEvent
		 * @return void
		 */
	    @FXML
	    void btnViewReaderDetails(ActionEvent event) {
	    	
	    	int i, readersize, usersize,cnt=1;
	    	String PhoneNumber = "";
	    	String CardStatus = "";
         	String Email = "";
         	String Username = "";
         	String Password = "";
         	String ID = "";
         	String UserID = "";
         	 int pos = tblBorrowedBooks.getSelectionModel().getSelectedIndex();
         	  	// @SuppressWarnings("unchecked")
         	//ArrayList<String> list = 
         	 String ReaderCardNumber = tblBorrowedBooks.getItems().get(pos).getReaderCardNumber();
         	 String Firstname = ""; 
         	 String Lastname = "";
      	 
         	 readersize = readerResult.size();
         	 usersize = userResult.size();
         	 
         	 
         	 for(int k=0;k<readersize;k++) {
         		 if(readerResult.get(k).get(0).equals(ReaderCardNumber)) {
         			 UserID = readerResult.get(k).get(1);
         			 CardStatus = readerResult.get(k).get(2);
         			 PhoneNumber = readerResult.get(k).get(3);
         			 break;
         		 }
         	 }
         	 for(int k=0;k<usersize;k++) {
         		 if(userResult.get(k).get(0).equals(UserID)) {
         			Firstname = userResult.get(k).get(1);
         			Lastname = userResult.get(k).get(2);
         			ID = userResult.get(k).get(3);
         			 Email = userResult.get(k).get(4);
         			 Username = userResult.get(k).get(5);
         			 Password = userResult.get(k).get(6);   			 
         			 
         		 }
         	 }

         	 ArrayList<String> ReaderInfo = new ArrayList<String>();
         	 ReaderInfo.add(Firstname);
         	 ReaderInfo.add(Lastname);
         	 ReaderInfo.add(ID);
         	 ReaderInfo.add(PhoneNumber);
         	 ReaderInfo.add(Email);
         	 ReaderInfo.add(Username);
         	 ReaderInfo.add(Password);
         	 ReaderInfo.add(ReaderCardNumber);
         	 ReaderInfo.add(CardStatus);
         	 ReaderInfo.add(UserID);
         	 //System.out.println(firstname+" "+lastname+" "+ID+" "+PhoneNumber+" "+Email+" "+Username+" "+Password+" "+ReaderCardNumber+" "+Status+" ");

         	  
         	 //MainAnchor.getScene().getWindow().hide();
         	//((Node)Readerevents.getSource()).getScene().getWindow().hide(); //hiding primary window
         	/*Stage primaryStage = new Stage();
         	FXMLLoader loader = new FXMLLoader();
         	AnchorPane root;
         	try {
         		root = loader.load(getClass().getResource("/gui/ViewReaderPersonalInformationByManager.fxml").openStream());
         		ViewReaderPersonalInformationByManagerController ViewReaderController = loader.getController();
         		
         		ViewReaderController.loadReader(ReaderInfo);
         		Scene ReaderScene = new Scene(root);			
         		ReaderScene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
         		primaryStage.setScene(ReaderScene);		
         		primaryStage.show();
         	} catch (IOException e) {
         		// TODO Auto-generated catch block
         		e.printStackTrace();
         	}*/
         	
         	
         	try {
            	Stage stage = new Stage(); 
                Parent root;                   
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/gui/ViewReaderPersonalInformationByManager.fxml"));
				root = loader.load();
				ViewReaderPersonalInformationByManagerController ViewReaderController = loader.getController();
				ViewReaderController.loadReader(ReaderInfo);
				//ViewReaderController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
				
				//BookController.loadAnchor(AnchorPaneTabs);
				
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
         	
         	
         	
         	
	    	
	    }
	  

		 /**init window
			 * @param arg1 d
			 * @param arg0 s
			 * 
			 */
		public void initialize(URL arg0, ResourceBundle arg1) {	
			 initCol();
			 loadData();
	    }
		 /**init tables
			 * @param 
			 * @param 
			 * @return void
			 */
	    private void initCol() {
	    	tblBorrowedBooks.setEditable(true);
	    	tblcRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
	    	tblcBookCopyId.setCellValueFactory(new PropertyValueFactory<>("BookCopyID"));
	    	tblcBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
	    	tblcReaderCardNumber.setCellValueFactory(new PropertyValueFactory<>("ReaderCardNumber"));
	    	tblcReaderName.setCellValueFactory(new PropertyValueFactory<>("ReaderName"));
	    	tblcBorrowingDate.setCellValueFactory(new PropertyValueFactory<>("BorrowingDate"));
	    	tblcReturnDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
	    	tblcLibrarianName.setCellValueFactory(new PropertyValueFactory<>("LibrarianName"));
	    }
		 /**preloading table data
		 * @param bname s
		 * @param bid d
		 * 
		 */
	    public void preload(String bname, String bid) {
	    	this.bookname = bname;
	        this.bookid = bid;
	        initCol();
			loadData();
	        
	    }
		 /**loading table data
	
		 */
	    private void loadData() {
	    	
	        int i,j,k,m,size,size2,size3,size4,size5=0,cnt = 1;
	       String sql2;
	    	Mylist.clear();
		  	ArrayList<String> key = new ArrayList<String>();
		  	ArrayList<String> key2 = new ArrayList<String>();
		  	ArrayList<String> key3 = new ArrayList<String>();
		  	ArrayList<String> key4 = new ArrayList<String>();
		  	ArrayList<String> key5 = new ArrayList<String>();
		    ArrayList<String> askForResult = new ArrayList<String>();
		    ArrayList<String> askForResult2 = new ArrayList<String>();
		    ArrayList<String> askForResult3 = new ArrayList<String>();
		    ArrayList<String> askForResult4 = new ArrayList<String>();
		    ArrayList<String> askForResult5 = new ArrayList<String>();
			  String sql = "SELECT * FROM g17db.tblbookborrow;";
			  askForResult.add("BookCopyID");
			  askForResult.add("ReaderCardNumber");
			  askForResult.add("BorrowingDate");
			  askForResult.add("ReturnDate");
			  askForResult.add("LenderName");
			  resultbookborrow = Functions.askFromDB(sql, key, askForResult);
			  size = resultbookborrow.size();
			  if(this.bookid.equals("")) {
				  sql2 = "SELECT * FROM g17db.tblbookcopy;";
				  askForResult2.add("BookCopyID");
				  askForResult2.add("BookID");
				  
				  String sql5 = "SELECT * FROM g17db.tblbook;";
				  askForResult5.add("BookID");
				  askForResult5.add("BookName");
				  askForResult5.add("AuthorName");
				  askForResult5.add("Subject");
				  askForResult5.add("AmountAvailable");
				  askForResult5.add("Description");
				  askForResult5.add("ArchiveStatus");
				  askForResult5.add("CatalogNumber");
				  askForResult5.add("PublishedDate");
				  askForResult5.add("PurchaseDate");
				  askForResult5.add("TableOfContents");
				  askForResult5.add("ExistingAmount");
				  askForResult5.add("Location");
				  askForResult5.add("Popularity");
				  askForResult5.add("EditionNumber");
				  

				  bookResult = Functions.askFromDB(sql5, key5, askForResult5);
				  size5 = bookResult.size();
  
			  }
			  else {
				  sql2 = "SELECT * FROM g17db.tblbookcopy where BookID = ?;";
				  key2.add(this.bookid);
				  askForResult2.add("BookCopyID");
			  }
			  
			  
			  resultbookcopy = Functions.askFromDB(sql2, key2, askForResult2);
			  size2 = resultbookcopy.size();
			  
			  String sql3 = "SELECT * FROM g17db.tblreader;";
			  askForResult3.add("ReaderCardNumber");
			  askForResult3.add("UserID");
			  askForResult3.add("CardStatus");
			  askForResult3.add("PhoneNumber");


			  
			  
			  readerResult = Functions.askFromDB(sql3, key3, askForResult3);
			  size3 = readerResult.size();
			  
			  String sql4 = "SELECT * FROM g17db.tbluser;";
			  askForResult4.add("UserID");
			  askForResult4.add("FirstName");
			  askForResult4.add("LastName");
    		   askForResult4.add("ID");
    		   askForResult4.add("Email");
    		   askForResult4.add("Username");
    		   askForResult4.add("Password");
			  
			  
			  userResult = Functions.askFromDB(sql4, key4, askForResult4);
			  size4 = userResult.size();
	    		
			  String bookname = "";
			  String rownum = "";
			  String bookcopyid = "";
			  if(!this.bookname.equals("")) {
				  bookname = this.bookname;
			  }
			  
              String readercardnumber = "";
              String readername = "";
              String borrowingdate = "";
              String returndate = "";
              String librarianname = "";
              String bookid = "";
              String userid = "";
			  if(this.bookname.equals("")) {
				  for(j=0;j<size;j++) {
					    rownum = String.valueOf(cnt);
          				bookcopyid = resultbookborrow.get(j).get(0);
	                	readercardnumber = resultbookborrow.get(j).get(1);
	                	borrowingdate = resultbookborrow.get(j).get(2);
	                	returndate = resultbookborrow.get(j).get(3);
	                	librarianname = resultbookborrow.get(j).get(4);
	                	cnt++;
	                	
	                	for(i=0;i<size2;i++) {
	                		if(resultbookcopy.get(i).get(0).equals(bookcopyid)) {
	                			bookid = resultbookcopy.get(i).get(1);
	                			break;
	                		}
	                	}
	                	for(i=0;i<size5;i++) {
	                		if(bookResult.get(i).get(0).equals(bookid)) {
	                			bookname = bookResult.get(i).get(1);
	                			break;
	                		}
	                	}
	                	
	                	
	                	for(k=0;k<size3;k++) {
	                		if(readercardnumber.equals(readerResult.get(k).get(0))) {
	                			userid = readerResult.get(k).get(1);
	                			
	                			for(m=0;m<size4;m++) {
			                		if(userid.equals(userResult.get(m).get(0))) {
			                			readername = (userResult.get(m).get(1)+" "+userResult.get(m).get(2));
			                			break;
			                		}
			                	}
	                			break;
	
	                		}
	                	}
	                	Mylist.add(new SubBorrowedBook(rownum, bookcopyid, bookname, readercardnumber, readername, borrowingdate, returndate, librarianname));
	                	
				  }
			  }
			  else {
				  for(i=0;i<size2;i++) {
		            	for(j=0;j<size;j++) {
		            		if(resultbookcopy.get(i).get(0).equals(resultbookborrow.get(j).get(0)))
			            	{
		            			rownum = String.valueOf(cnt);
		            			bookcopyid = resultbookborrow.get(j).get(0);
			                	readercardnumber = resultbookborrow.get(j).get(1);
			                	borrowingdate = resultbookborrow.get(j).get(2);
			                	returndate = resultbookborrow.get(j).get(3);
			                	librarianname = resultbookborrow.get(j).get(4);
			                	cnt++;

			                	
			                	
			                	for(k=0;k<size3;k++) {
			                		if(readercardnumber.equals(readerResult.get(k).get(0))) {
			                			userid = readerResult.get(k).get(1);
			                			
			                			for(m=0;m<size4;m++) {
					                		if(userid.equals(userResult.get(m).get(0))) {
					                			readername = (userResult.get(m).get(1)+" "+userResult.get(m).get(2));
					                		}
					                	}
			
			                		}
			                	}
		
			                	Mylist.add(new SubBorrowedBook(rownum, bookcopyid, bookname, readercardnumber, readername, borrowingdate, returndate, librarianname));

			            	}
		            	}
						
		            }
			  }
	            
	            tblBorrowedBooks.setItems(Mylist);
	    }
		 /**class for the borrow table
			
			 */
	    public static class SubBorrowedBook {

	        private final SimpleStringProperty RowNum;
	        private final SimpleStringProperty BookCopyID;
	        private final SimpleStringProperty BookName;
	        private final SimpleStringProperty ReaderCardNumber;
	        private final SimpleStringProperty ReaderName;
	        private final SimpleStringProperty BorrowingDate;
	        private final SimpleStringProperty ReturnDate;
	        private final SimpleStringProperty LibrarianName;
	
	        
	        public SubBorrowedBook(String rownum,String bookcopyid, String bookname, String readercardnumber, String readername, String borrowingdate, String returndate,  String librarianname) {
	        	this.RowNum = new SimpleStringProperty(rownum);
	        	this.BookCopyID = new SimpleStringProperty(bookcopyid);
	        	this.BookName = new SimpleStringProperty(bookname);
	        	this.ReaderCardNumber = new SimpleStringProperty(readercardnumber);
	        	this.ReaderName = new SimpleStringProperty(readername);
	        	this.BorrowingDate = new SimpleStringProperty(borrowingdate);
	        	this.ReturnDate = new SimpleStringProperty(returndate);
	        	this.LibrarianName = new SimpleStringProperty(librarianname);
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
	        public void setBookName(String bookname) {
	        	BookName.set(bookname);
	        }
	        
	        public String getBookCopyID() {
	        	return BookCopyID.get();
	        }
	        public void setBookCopyID(String bookcopyid) {
	        	BookCopyID.set(bookcopyid);
	        }
	        
	        public String getReaderName() {
	        	return ReaderName.get();
	        }
	        public void setReaderName(String readername) {
	        	ReaderName.set(readername);
	        }
	         
	        public String getReaderCardNumber() {
	        	return ReaderCardNumber.get();
	        }
	        public void setReaderCardNumber(String readercardnumber) {
	        	ReaderCardNumber.set(readercardnumber);
	        }
	        
	        public String getBorrowingDate() {
	        	return BorrowingDate.get();
	        }
	        public void setBorrowingDate(String borrowingdate) {
	        	BorrowingDate.set(borrowingdate);
	        }
	         
	        public String getReturnDate() {
	        	return ReturnDate.get();
	        }
	        public void setReturnDate(String returndate) {
	        	ReturnDate.set(returndate);
	        }
	        
	        public String getLibrarianName() {
	        	return LibrarianName.get();
	        }
	        public void setLibrarianName(String librarianname) {
	        	LibrarianName.set(librarianname);
	        }
	    }
}
