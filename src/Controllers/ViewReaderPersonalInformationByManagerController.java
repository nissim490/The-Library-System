package Controllers;


import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import Controllers.AskForExtentionByReaderController.BorrowedBooks;
import Controllers.ReaderHomepageController.BookCopy;
import Controllers.ReaderHomepageController.BookOrders;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import common.Functions;
import entity.User;
import java.util.Arrays;
            
public class ViewReaderPersonalInformationByManagerController implements Initializable {
	
	private ArrayList<String> reader = new ArrayList<String>();
	private User u;
	private String UserReaderCardNumber;
	//private BorrowedBooks lst = null;
	private BorrowedBooks lst;
	private AnchorPane AnchorPaneTabs;
	
	ObservableList<BorrowedBooks> MylistB = FXCollections.observableArrayList();
	ObservableList<BookOrders> MylistO = FXCollections.observableArrayList();
	ArrayList<ArrayList<String>> resultBorrowed;
	ArrayList<ArrayList<String>> resultOrder;
	ArrayList<ArrayList<String>> resultBook;
	ArrayList<ArrayList<String>> resultUser;
	Stage pStage;
	
	@FXML
    private AnchorPane MainAnchor;//
    @FXML
    private StackPane MainStack;///
    @FXML
    private ResourceBundle resources;
    JFXButton yesButton = new JFXButton("YES, Please");//
    JFXButton okButton = new JFXButton("Alright");

	    @FXML
	    private URL location;

	    @FXML
	    private Tab ReaderPersonalInformationTab;


	  
	    
	    @FXML
	    private JFXTextField lblFirstName;

	    @FXML
	    private JFXTextField lblLastName;

	    @FXML
	    private JFXTextField lblIDNumber;

	    @FXML
	    private JFXTextField lblPhoneNumber;

	    @FXML
	    private JFXTextField lblEmail;

	    @FXML
	    private JFXTextField lblUsername;

	    @FXML
	    private JFXTextField lblPassword;

	    @FXML
	    private JFXTextField lblCardNumber;
	    @FXML
	    private JFXComboBox<String> cmbStatus;
	    


    @FXML
    private JFXButton SaveChanges;

    @FXML
    private Tab BookBorrowsandOrdersTab;

    @FXML
    private JFXButton btnLendThisBook;

    @FXML
    private JFXButton btnExtendBorrowing;

   // @FXML
    //private JFXButton BtnViewAllBorrowedBooks;


    @FXML
    private TableView<BorrowedBooks> tblBorrowedBook;

    @FXML
    private TableColumn<BorrowedBooks, String> tblcolBorrowRowNum;

    @FXML
    private TableColumn<BorrowedBooks, String> tblcolBorrowBookName;

    @FXML
    private TableColumn<BorrowedBooks, String> tblcolBorrowBookAuthor;

    @FXML
    private TableColumn<BorrowedBooks, String> tblcolBorrowLenderName;

    @FXML
    private TableColumn<BorrowedBooks, String> tblcolBorrowBorrowingDate;

    @FXML
    private TableColumn<BorrowedBooks, String> tblcolBorrowReturnDate;

    @FXML
    private TableView<BookOrders> tblOrderBook;

    @FXML
    private TableColumn<BookOrders, String> tblcolOrderRowNum;

    @FXML
    private TableColumn<BookOrders, String> tblcolOrderBookName;

    @FXML
    private TableColumn<BookOrders, String> tblcolOrderBookAuthor;

    @FXML
    private TableColumn<BookOrders, String> tblcolOrderOrderingDate;

    @FXML
    private TableColumn<BookOrders, String> tblcolOrderPlaceOnTheWaitingList;

    @FXML
    private TableColumn<BookOrders, String> tblcolOrderClosestReturn;
    
    @FXML
    private ImageView OrderedBooksRefreshBtn;

    @FXML
    private ImageView ActiveBorrowedBooksRefreshBtn;
    
    @FXML
    private Label LibrarianID;
	private BookOrders lstO;
    
	 /**
     * refresh table
     * @param MouseEvent
     * @return void
     */
    @FXML
    void ActiveBorrowedBooksRefreshBtn(MouseEvent event) {
    	loadDataB();
    }

    /**
     * refresh table
     * @param MouseEvent
     * @return void
     */
    @FXML
    void OrderedBooksRefreshBtn(MouseEvent event) {
    	loadDataO();
    }

    /**
     * save the status of the reader
     * @param event- action
     * @return void
     */
    @FXML
    void SaveChanges(ActionEvent event) {

	  ArrayList<String> key = new ArrayList<String>();
	
	if(!(reader.get(8).equals(cmbStatus.getValue()))) {
		switch(cmbStatus.getValue()) 
        { 
    
            case "Active": 
            	
              key.add("Active");
   		      key.add(reader.get(9));
   		  	  Functions.askFromDB("UPDATE g17DB.tblreader SET CardStatus = ? WHERE UserID = ?;",key);
   		  	  AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Success Massage","Reader Status was changed successfuly");
                break; 
            case "Locked": 
            	  key.add("Locked");
	   		      key.add(reader.get(9));
	   		  	  Functions.askFromDB("UPDATE g17DB.tblreader SET CardStatus = ? WHERE UserID = ?;",key);
	   		  	AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Success Massage","Reader Status was changed successfuly");
                break; 
            case "Frozen": 
            	 key.add("Frozen");
	   		      key.add(reader.get(9));
	   		  	  Functions.askFromDB("UPDATE g17DB.tblreader SET CardStatus = ? WHERE UserID = ?;",key);
	   		  	AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Success Massage","Reader Status was changed successfuly");
                break; 
            case "Inactive": 
            	key.add("Inactive");
	   		      key.add(reader.get(9));
	   		  	  Functions.askFromDB("UPDATE g17DB.tblreader SET CardStatus = ? WHERE UserID = ?;",key);
	   		  	AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(okButton), "Success Massage","Reader Status was changed successfuly");
                break; 
        } 
	  
	  
    }

  }

    /**
     * extend manual the return book date of this borrow
     * @param event - action
     * @return void
     */
    @FXML
    void btnExtendBorrowing(ActionEvent event) {
    	if (lst!=null) { 
    		//((Node)event.getSource()).getScene().getWindow().hide();
    		Stage primaryStage = new Stage();
    		FXMLLoader loader = new FXMLLoader();	    	
    		loader.setLocation(getClass().getResource("/gui/ManualExtensionByLibrarianOrManager.fxml"));
    		try {
    			loader.load();
    	
    		ManualExtensionByLibrarianOrManagerController me = loader.getController();
    		me.SetUser(LibrarianID.getText().trim());
    		me.SetBook(this.lst);	
    		
    		Parent p =loader.getRoot();
    		Scene scene = new Scene(p);
    		//scene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
    		primaryStage.setTitle("Manual Extension");
    		primaryStage.setScene(scene);
    		primaryStage.showAndWait();   
    		}
    		catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	else {
    		System.exit(0);
    	}
    }

    /**
     * if your turn in the waiting list is "Arrived" you can now lend this book.
     * @param event-action
     * @return void
     */
    @FXML
    void btnLendThisBook(ActionEvent event) {
    	String sql="DELETE FROM `g17db`.`tblbookorders` WHERE (`OrderID` = ?);";
    	String sqlD,sqlL;
    	ArrayList<String> book = new ArrayList<String>();
    	ArrayList<String> AskForResult = new ArrayList<String>();
    	ArrayList<String> AskForResultL = new ArrayList<String>();
    	ArrayList<String> keyL = new ArrayList<String>();
    	ArrayList<String> key = new ArrayList<String>();
    	//ArrayList<String> AskForResult = new ArrayList<String>();
    	if(lstO!=null) {
    		if(lstO.getPlaceOnTheWaitingList().equals("Arrived")) {
    			key.add(lstO.getOrderID());
    			
    			try {
    				
    				//need to send <catalog,bookname,author,subject,availiblecopies,existcopies,shelfnum,popularity,editionnum,publisheddate,purchaseddate,tableURL,dicription>
    				sqlD="SELECT b.CatalogNumber,b.BookName,b.AuthorName,b.Subject,b.AmountAvailable,b.ExistingAmount,b.Location,b.Popularity,b.EditionNumber,b.PublishedDate,b.PurchaseDate,b.TableOfContents,b.Description,b.ArchiveStatus,b.BookID\r\n" + 
    						"FROM g17db.tblbookorders as bo,g17db.tblbook as b\r\n" + 
    						"WHERE bo.BookID=b.BookID AND bo.OrderID=?;";
    				AskForResult.add("CatalogNumber");
    				AskForResult.add("BookName");
    				AskForResult.add("AuthorName");
    				AskForResult.add("Subject");
    				AskForResult.add("AmountAvailable");
    				AskForResult.add("ExistingAmount");
    				AskForResult.add("Location");
    				AskForResult.add("Popularity");
    				AskForResult.add("EditionNumber");
    				AskForResult.add("PublishedDate");
    				AskForResult.add("PurchaseDate");
    				AskForResult.add("TableOfContents");
    				AskForResult.add("Description");
    				AskForResult.add("ArchiveStatus");
    				AskForResult.add("BookID");
    				
    				resultBook=Functions.askFromDB(sqlD, key, AskForResult);
    				
    				book.add(resultBook.get(0).get(0));
    				book.add(resultBook.get(0).get(1));
                 	book.add(resultBook.get(0).get(2));
                 	book.add(resultBook.get(0).get(3));
                 	if(resultBook.get(0).get(13).equals("Archived")){
                 		book.add(resultBook.get(0).get(13));
                 		book.add(resultBook.get(0).get(13));
                 	}
                 	else {
                 		book.add(resultBook.get(0).get(4));
                 		book.add(resultBook.get(0).get(5));
                 	}
                 	
                 	book.add(resultBook.get(0).get(6));
                 	book.add(resultBook.get(0).get(7));
                 	book.add(resultBook.get(0).get(8));
                 	book.add(resultBook.get(0).get(9));
                 	book.add(resultBook.get(0).get(10));
                 	book.add(resultBook.get(0).get(11));
                 	book.add(resultBook.get(0).get(12));
                 	book.add(resultBook.get(0).get(14));
                 	
                 	
    				
    				//book=resultBook.get(0);
    				keyL.add(LibrarianID.getText().trim());
    			      AskForResultL.add("*");         	  	   	  
    			      sqlL="SELECT * FROM g17DB.tbluser WHERE ID = ?;";
    			      resultUser=Functions.askFromDB(sqlL,keyL,AskForResultL);
    			      key.clear();
      				key.add(lstO.getOrderID());
    			    Functions.askFromDB(sql, key);
    				String userID=resultUser.get(0).get(0);
    				String username=resultUser.get(0).get(1);
    				String password=resultUser.get(0).get(2);
    				String iD=resultUser.get(0).get(3);
    				String firstName=resultUser.get(0).get(4);
    				String lastName=resultUser.get(0).get(5);
    				String email=resultUser.get(0).get(6);
    				String addedDate=resultUser.get(0).get(7);
    				String permissions=resultUser.get(0).get(8);
    				String userStatus=resultUser.get(0).get(9);
    				
    				 
    				((Node)event.getSource()).getScene().getWindow().hide();
    		    	Parent root;
    				try {
    					FXMLLoader loader = new FXMLLoader();           
    					loader.setLocation(getClass().getResource("/gui/LendingABookByLibrarianOrManager.fxml"));
    					root = loader.load();
    					User user =new User(userID, username, password, iD, firstName, lastName, email, addedDate, permissions, userStatus);
    					
    					LendingABookByLibrarianOrManagerController listOfBorrow = loader.getController();
    					listOfBorrow.loadBook2(book,user); //at LibraryManagerHomepageController
    					 
    					AnchorPaneTabs.getChildren().setAll(root); 
    					((Node)event.getSource()).getScene().getWindow().hide();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}    			
    		}
    		else
    			AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage","Reader turn hasn't arrived yet!");
    	}
    	else
    		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage","Please choose order first.");
    }
    
	public void start(Stage primaryStage) {
		/*try {
			//BorderPane root = new BorderPane();
			Parent root= FXMLLoader.load(getClass().getResource("ViewReaderPersonalInformationByManager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	

	/**
	 * initialize the window.
	 */
	public void initialize() {	
		SetCardStatusComboBox();
		

    }


		private void initColB() {
			tblcolBorrowRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
			tblcolBorrowBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
			tblcolBorrowBookAuthor.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
			tblcolBorrowLenderName.setCellValueFactory(new PropertyValueFactory<>("LenderName"));
			tblcolBorrowBorrowingDate.setCellValueFactory(new PropertyValueFactory<>("BorrowingDate"));
			tblcolBorrowReturnDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
			
			
		}
		private void initColO() {
			tblcolOrderRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
			tblcolOrderBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
			tblcolOrderBookAuthor.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
			tblcolOrderOrderingDate.setCellValueFactory(new PropertyValueFactory<>("OrderingDate"));
			tblcolOrderPlaceOnTheWaitingList.setCellValueFactory(new PropertyValueFactory<>("PlaceOnTheWaitingList"));
			tblcolOrderClosestReturn.setCellValueFactory(new PropertyValueFactory<>("ClosestReturn"));			
		}
		 /**
	     * load data to table borrow
	     * @param void
	     * @return void
	     */
		 private void loadDataB() {
		        int i = 0,size;
		    	MylistB.clear();
			  	ArrayList<String> key = new ArrayList<String>();
			    ArrayList<String> askForResult = new ArrayList<String>();
				  String sql = "SELECT bb.BookBorrowID ,bb.BookCopyID,b.BookName, b.AuthorName,bb.LenderName , bb.BorrowingDate , bb.ReturnDate FROM g17db.tblbookborrow as bb, g17db.tblbook as b, g17db.tblbookcopy as c WHERE bb.BookCopyID=c.BookCopyID AND c.BookID=b.BookID AND bb.ReaderCardNumber = ?;";
				  askForResult.add("BookBorrowID");
				  askForResult.add("BookCopyID");
				  askForResult.add("BookName");
				  askForResult.add("AuthorName");
				  askForResult.add("LenderName");
				  askForResult.add("BorrowingDate");
				  askForResult.add("ReturnDate");
				  key.add(lblCardNumber.getText().trim());
				  //key.add("R10000");
				  resultBorrowed = Functions.askFromDB(sql ,key,askForResult);
				  size = resultBorrowed.size();
		        try {
		            for(i=0;i<size;i++) {
		                String rownum = String.valueOf(i+1);
		                String bookBorrowID = resultBorrowed.get(i).get(0);
		                String bookid = resultBorrowed.get(i).get(1);
		                String bookname = resultBorrowed.get(i).get(2);
		                String author = resultBorrowed.get(i).get(3);
		                String lender = resultBorrowed.get(i).get(4);
		                String borrowdate = resultBorrowed.get(i).get(5);
		                String returndate = resultBorrowed.get(i).get(6);

		                MylistB.add(new BorrowedBooks(rownum,bookBorrowID,bookid, bookname, author, lender, borrowdate,returndate));
		               

		            }
		        } catch (Exception ex) {
		            
		        }
		        tblBorrowedBook.setItems(MylistB);		 
		        tblBorrowedBook.setOnMouseClicked(e -> {events();
		        										if(e.getClickCount()==2)
															btnExtendBorrowing(new ActionEvent(btnExtendBorrowing, btnExtendBorrowing));});		       
		        
		 }
		 /**
		     * load data to table orders
		     * @param void
		     * @return void
		     */
		 private void loadDataO() {
		       /* int i = 0,size;
		        MylistO.clear();
			  	ArrayList<String> keyOrder = new ArrayList<String>();
			    ArrayList<String> askForOrderResult = new ArrayList<String>();
			    String sqlOrder = "SELECT bo.OrderID,bo.BookID,b.BookName,b.AuthorName,bo.OrderDate FROM g17db.tblreader as r, g17db.tbluser as u, g17db.tblbookorders as bo, g17db.tblbook as b WHERE bo.BookID=b.BookID AND bo.ReaderCardNumber = r.ReaderCardNumber And r.UserID = u.UserID AND u.ID = ?;";  
				 // String sqlOrder = "SELECT bo.OrderID,bo.BookID,b.BookName,b.AuthorName,bo.OrderDate FROM g17db.tblbookorders as bo, g17db.tblbook as b WHERE b.ArchiveStatus='Normal' AND bo.BookID=b.BookID AND bo.ReaderCardNumber = ?;";
				  askForOrderResult.add("OrderID");
				  askForOrderResult.add("BookID");				  
				  askForOrderResult.add("BookName");
				  askForOrderResult.add("AuthorName");
				  askForOrderResult.add("OrderDate");
				  keyOrder.add(lblCardNumber.getText().trim());
				  //keyOrder.add("R10000");
				  resultOrder = Functions.askFromDB(sqlOrder ,keyOrder,askForOrderResult);
				  size = resultOrder.size();
		        try {
		            for(i=0;i<size;i++) {
		                String rownum = String.valueOf(i+1);	
		                String orderid=resultOrder.get(i).get(0);
		                String bookname = resultOrder.get(i).get(2);
		                String author = resultOrder.get(i).get(3);
		                String orderingdate = resultOrder.get(i).get(4);
		                //getting from resultOrder		                		                
		                String placeonwaitinglist = BookOrders.PlaceOnWaitingList(resultOrder.get(i).get(0), resultOrder.get(i).get(1))+"";
		                String returndate = BookCopy.GetNextReturnOfBook(resultOrder.get(i).get(1));
		                MylistO.add(new BookOrders(rownum,orderid, bookname, author, orderingdate, placeonwaitinglist,returndate));		               

		            }
		        } catch (Exception ex) {
		            
		        }
		        tblOrderBook.setItems(MylistO);	
		        if(size==0)
		        	btnLendThisBook.setDisable(false);
		        tblOrderBook.setOnMouseClicked(e -> {eventsO();});
		    }*/
		 
			 int i = 0,size;
		        tblOrderBook.getItems().clear();
		        MylistO.clear();
			  	ArrayList<String> keyOrder = new ArrayList<String>();
			    ArrayList<String> askForOrderResult = new ArrayList<String>();
				String sqlOrder = "SELECT bo.OrderID,bo.BookID,b.BookName,b.AuthorName,bo.OrderDate FROM g17db.tblreader as r, g17db.tbluser as u, g17db.tblbookorders as bo, g17db.tblbook as b WHERE bo.BookID=b.BookID AND bo.ReaderCardNumber = r.ReaderCardNumber And r.UserID = u.UserID AND u.ID = ?;";  
			    //String sqlOrder = "SELECT bo.OrderID,bo.BookID,b.BookName,b.AuthorName,bo.OrderDate FROM g17db.tblbookorders as bo, g17db.tblbook as b WHERE bo.BookID=b.BookID AND bo.ReaderCardNumber = ?;";
				  askForOrderResult.add("OrderID");
				  askForOrderResult.add("BookID");				  
				  askForOrderResult.add("BookName");
				  askForOrderResult.add("AuthorName");
				  askForOrderResult.add("OrderDate");
				  //keyOrder.add(this.UserReaderCardNumber);
				  keyOrder.add(this.lblIDNumber.getText());
				  System.out.println("ID int loadorder: "+this.lblIDNumber.getText());
				  resultOrder = Functions.askFromDB(sqlOrder ,keyOrder,askForOrderResult);
				  size = resultOrder.size();
		        try {
		            for(i=0;i<size;i++) {
		                String rownum = String.valueOf(i+1);		                
		                String orderid = resultOrder.get(i).get(0);
		                String bookname = resultOrder.get(i).get(2);
		                String author = resultOrder.get(i).get(3);
		                String orderingdate = resultOrder.get(i).get(4);
		                //getting from resultOrder		                		                
		                String placeonwaitinglist = BookOrders.PlaceOnWaitingList(resultOrder.get(i).get(0), resultOrder.get(i).get(1))+"";
		                String returndate; 
		                		try{
		                			returndate =BookCopy.GetNextReturnOfBook(resultOrder.get(i).get(1));
		                		}
		                		catch (Exception e) {
									returndate="none";
								}
		                MylistO.add(new BookOrders(rownum,orderid, bookname, author, orderingdate, placeonwaitinglist,returndate));		               

		            }
		        } catch (Exception ex) {
		          ex.printStackTrace();  
		        }
		        tblOrderBook.setItems(MylistO);	
		        tblOrderBook.setOnMouseClicked(e -> {eventsO();});
		    }
		 
		 	

		
		
		
		
		
		
		   private void eventsO() {
			   int pos = tblOrderBook.getSelectionModel().getSelectedIndex();
			   // @SuppressWarnings("unchecked")
			   this.lstO = tblOrderBook.getItems().get(pos);
			
		}

 
		private void events() {
			   int pos = tblBorrowedBook.getSelectionModel().getSelectedIndex();
			   // @SuppressWarnings("unchecked")
			   this.lst = tblBorrowedBook.getItems().get(pos);
			   
				   
			   //System.out.println("bookname:"+ list.getLenderName()+ " authorname: "+list.getAuthorName());			
			   
		   }


		 /**
	     * init combobox
	     * @param void
	     * @return void
	     */
	  private void SetCardStatusComboBox() {
			
			ArrayList<String> al = new ArrayList<String>();	
			al.add("Active");
			al.add("Locked");
			al.add("Frozen");
			al.add("Inactive");
			ObservableList<String> CMBXBo = FXCollections.observableArrayList(al);	
			cmbStatus.setItems(CMBXBo);  	
			}
	
	
	/**
	 * load the to the Initial state
	 * @param reader reader the reader we take from him the information
	 * 
	 */
		public void loadReader(ArrayList<String> reader){
			this.reader = reader;
			 lblFirstName.setText(reader.get(0));
			  lblLastName.setText(reader.get(1));
			  lblIDNumber.setText(reader.get(2));
			  lblPhoneNumber.setText(reader.get(3));
			  lblEmail.setText(reader.get(4));
			  lblUsername.setText(reader.get(5));
		      lblPassword.setText(reader.get(6));
			  lblCardNumber.setText(reader.get(7));
			  cmbStatus.setValue(reader.get(8));
			  this.UserReaderCardNumber=reader.get(7);
				initColB();
				initColO();
		    	loadDataB();
		    	loadDataO();
		    	SetCardStatusComboBox();
		}

		/**
		 * Initialize the Librarian on the window
		 * @param myUserData-Librarian user
		 
		 */
		public void loadUser(User myUserData) {

	    	LibrarianID.setText(myUserData.getID());
	    	/*
	    	sql = "SELECT r.ReaderCardNumber FROM g17db.tbluser as u,g17db.tblreader as r WHERE u.UserID=r.UserID AND u.UserID = ?;";
	    	 
	    	askForResult.add("ReaderCardNumber");
	    	key.add(u.getUserID());
	    	resultUser = Functions.askFromDB(sql ,key,askForResult);
	    	String a = resultUser.get(0).get(0);
	    	*/
	    }
		public void loadAnchor(AnchorPane anchorpanetabs){
			this.AnchorPaneTabs = anchorpanetabs;
			
		}

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
			
			
}
