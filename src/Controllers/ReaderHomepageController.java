package Controllers;

import com.jfoenix.controls.JFXComboBox;
import Controllers.AskForExtentionByReaderController.BorrowedBooks;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import Controllers.ReaderHomepageController.BookCopy;
import Controllers.ReaderHomepageController.BookOrders;
//import Controllers.ReaderHomepageController2.BookOrders;
import java.util.Arrays;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javafx.scene.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import common.Functions;
import entity.User;


public class ReaderHomepageController extends Application implements Initializable {
	private User MyUserData;
	ObservableList<SubBook> MylistSubBook = FXCollections.observableArrayList();
	private String UserReaderCardNumber = "R10011";
	private BorrowedBooks lst;
	
	ObservableList<BorrowedBooks> MylistB = FXCollections.observableArrayList();
	ObservableList<BookOrders> MylistO = FXCollections.observableArrayList();
	ArrayList<ArrayList<String>> resultBorrowed;
	ArrayList<ArrayList<String>> resultOrder;
	//ArrayList<ArrayList<String>> resultBook;
	ArrayList<ArrayList<String>> resultUser;
	Stage pStage;
	ArrayList<ArrayList<String>> resultbook;

	Scene pScene;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneTabs;
    
    private StackPane MainStack;///
    @FXML

    JFXButton yesButton = new JFXButton("YES, Please");//
	JFXButton noButton = new JFXButton("No, Please");//

    @FXML
    private JFXTabPane mainTabPane;

    @FXML
    private Tab searchesTab;

    @FXML
    private HBox book_info;

    @FXML
    private JFXComboBox<String> cmbxBookSearchTypes;

    @FXML
    private JFXTextField bookIDInput;
    @FXML
    private ImageView RefreshBookImgbtn;
    @FXML
    private ImageView refreshtblBorrow;
    @FXML
    private ImageView refreshtblOrder;

    @FXML
    private TableView<SubBook> tblBookSearchResults;

    @FXML
    private TableColumn<SubBook, String> tblcolBookRowNum;

    @FXML
    private TableColumn<SubBook, String> tblcolBookName;

    @FXML
    private TableColumn<SubBook, String> tblcolBookAuthor;

    @FXML
    private TableColumn<SubBook, String> tblcolBookSubject;

    @FXML
    private TableColumn<SubBook, String> tblcolBookStatus;

    @FXML
    private Tab BookBorrowsandOrdersTab;

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
    private JFXButton CancelOrderBtn;

    @FXML
    private JFXButton AskForExtensionBtn;
    
    @FXML
    private Label ID;
	private BookOrders lstO;
   
    /**
     * ask for extension from the library server,
     * returns a message as feedback
     * @param event-action
     */
    @FXML
    void AskForExtensionBtn(ActionEvent event) {
    	if (lst!=null) { 
    		//((Node)event.getSource()).getScene().getWindow().hide();
    		
    		try {
    			Stage primaryStage = new Stage();
        		FXMLLoader loader = new FXMLLoader();	    	
        		loader.setLocation(getClass().getResource("/gui/AskForExtentionByReader.fxml"));
    			loader.load();
    			AskForExtentionByReaderController afe = loader.getController();
        		afe.SetUser(this.ID.getText().trim());
        		afe.SetBook(this.lst);	
        		
        		Parent p =loader.getRoot();
        		Scene scene = new Scene(p);
        		//scene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
        		primaryStage.setTitle("Ask For Extention");
        		primaryStage.setScene(scene);
        		primaryStage.showAndWait();  
        		LibraryAssistantUtil.setStageIcon(primaryStage);
 
        			    		
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    		//AskForExtentionByReaderController afe = new AskForExtentionByReaderController();
    		
    		/*try {
    			afe.start(primaryStage);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		*/
    	}
    	else {
    		//AlertMaker.showMaterialDialog(MainStack, AnchorPaneTabs, Arrays.asList(yesButton), "Error Massage","Please choose a book from the table.");
    		JOptionPane.showMessageDialog(null, "Please choose a book from the table.");
    	}
    }

/**
 * Cancel a specific order from the table
 * @param event action
 * @return void
 */
    @FXML
    void CancelOrderBtn(ActionEvent event) {
    	if(lstO==null)
    		return;
    	ArrayList<String> key=new ArrayList<>();
    	key.add(lstO.getOrderID());
		Functions.askFromDB("DELETE FROM `g17db`.`tblbookorders` WHERE OrderID =?;", key);
		try {
		AlertMaker.showMaterialDialog(MainStack, AnchorPaneTabs, Arrays.asList(yesButton), "Error Massage","Order delete");
		//JOptionPane.showMessageDialog(null, "Order delete");
		}
		catch (Exception e) {
			System.out.println("Error Massage Order delete");
		}
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
		 	actKey.add("Cancel Order"); 
		 	actKey.add(LocalDate.now()+""); 
		 	Functions.askFromDB("INSERT INTO `g17db`.`tbllistofhistory` (`ActivityID`, `ReaderCardNumber`, `Activity`, `Date`) VALUES (?, ?, ?, ?);", actKey);

    }
 
    @FXML
    void RefreshBookImgbtn(MouseEvent event) {
    	MylistB.clear();
	
    	loadBookData();
    }
    @FXML
    void refreshtblOrder(MouseEvent event) {
    	MylistO.clear();
    	loadOrderedData();
    }
    @FXML
    void refreshtblBorrow(MouseEvent event) {
    	MylistB.clear();
    	loadBorrowedData();
    }
   /**
    * Choose the type of your search.
    * @param event-action
    * @return void
    */
    @FXML
    void cmbxBookSearchTypes(ActionEvent event) {
    	switch(cmbxBookSearchTypes.getValue()) 
        { 
            case "Book Name": 
            	bookIDInput.setPromptText("i.e: For Cluck's Sake!");
                break; 
            case "Book Author": 
            	bookIDInput.setPromptText("i.e: Author1 OR Author1, Authr2,..");
                break; 
            case "Book Subject": 
            	bookIDInput.setPromptText("i.e: Crime OR Food OR Humor...");
                break; 
            case "Free Text": 
            	bookIDInput.setPromptText("i.e: lord voldemort, best seller...");
                break; 
        } 
    	
    }
    /**
     * Try to find the book details by the search type,
     * showing the results on the screen.
     * @param event-action
     * @return void
     */
    @FXML
    void loadBookInfo(ActionEvent event) {
    	int i,size,cnt=1;
    	if(bookIDInput.getText().trim() != "") {
    		size = resultbook.size();
        	switch(cmbxBookSearchTypes.getValue()) 
            { 
                case "Book Name": 
                	MylistSubBook.clear();
                	tblBookSearchResults.getItems().clear();
                    for(i=0;i<size;i++) {               
                        String bookname = resultbook.get(i).get(0);
                        if(bookname.toUpperCase().contains(bookIDInput.getText().trim().toUpperCase())) {
                            String rownum = String.valueOf(cnt);
                            String author = resultbook.get(i).get(1);
                            String subject = resultbook.get(i).get(2);
                            String status = resultbook.get(i).get(3);
                            cnt++;
                            String bookid = resultbook.get(i).get(5);
                            String archive = resultbook.get(i).get(6);
                            if(Integer.parseInt(status)!=0 && archive.equals("Normal")) status = "Available";
                            else status = "Unavailable";

                            MylistSubBook.add(new SubBook(rownum, bookname, author, subject, status, bookid));
                        }
                    }
                    tblBookSearchResults.setItems(MylistSubBook);
                    break; 
                case "Book Author": 
                	MylistSubBook.clear();
                	tblBookSearchResults.getItems().clear();
                	//StringBuilder stringBuilder = new StringBuilder();
                	//for(i=0;i<size;i++) {
                	//	stringBuilder.append(resultbook.get(i).get(1)+", ");
                	//}
             
                	//String finalString = stringBuilder.toString();
                    for(i=0;i<size;i++) {               
                    	String author = resultbook.get(i).get(1);
                    	if(author.toUpperCase().contains(bookIDInput.getText().trim().toUpperCase())) { //need to split to tokkens... 
                            String rownum = String.valueOf(cnt);
                            String bookname = resultbook.get(i).get(0);
                            String subject = resultbook.get(i).get(2);
                            String status = resultbook.get(i).get(3);
                            cnt++;
                            String bookid = resultbook.get(i).get(5);
                            String archive = resultbook.get(i).get(6);
                            if(Integer.parseInt(status)!=0 && archive.equals("Normal")) status = "Available";
                            else status = "Unavailable";

                            MylistSubBook.add(new SubBook(rownum, bookname, author, subject, status, bookid));
                        }
                    }
                    tblBookSearchResults.setItems(MylistSubBook);
                    break; 
                case "Book Subject": 
                	MylistSubBook.clear();
                	tblBookSearchResults.getItems().clear();
                    for(i=0;i<size;i++) {               
                        String subject = resultbook.get(i).get(2);
                        if(subject.toUpperCase().contains(bookIDInput.getText().trim().toUpperCase())) {
                            String rownum = String.valueOf(cnt);
                            String author = resultbook.get(i).get(1);
                            String bookname = resultbook.get(i).get(0);
                            String status = resultbook.get(i).get(3);
                            cnt++;
                            String bookid = resultbook.get(i).get(5);
                            String archive = resultbook.get(i).get(6);
                            if(Integer.parseInt(status)!=0 && archive.equals("Normal")) status = "Available";
                            else status = "Unavailable";

                            MylistSubBook.add(new SubBook(rownum, bookname, author, subject, status, bookid));
                        }
                    }
                    tblBookSearchResults.setItems(MylistSubBook);
                    break; 
                case "Free Text": 
                	MylistSubBook.clear();
                	tblBookSearchResults.getItems().clear();
                    for(i=0;i<size;i++) {               
                    	String description = resultbook.get(i).get(4);
                        if(description.toUpperCase().contains(bookIDInput.getText().trim().toUpperCase())) {
                            String rownum = String.valueOf(cnt);
                            String bookname = resultbook.get(i).get(0);
                            String author = resultbook.get(i).get(1);
                            String subject = resultbook.get(i).get(2);
                            String status = resultbook.get(i).get(3);
                            cnt++;
                            String bookid = resultbook.get(i).get(5);
                            String archive = resultbook.get(i).get(6);
                            if(Integer.parseInt(status)!=0 && archive.equals("Normal")) status = "Available";
                            else status = "Unavailable";

                            MylistSubBook.add(new SubBook(rownum, bookname, author, subject, status, bookid));
                        }
                    }
                    tblBookSearchResults.setItems(MylistSubBook);
                    break; 
                    default: JOptionPane.showMessageDialog(null, "Choose something from the Search Combobox.");
            } 
    	}
    	
    }

    /**
     * init the screen
     * @param rb f 
     * @param url g
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }
    public AnchorPane getAnchor() {
    	return AnchorPaneTabs;
    }
    /**
     * init combo box
  
     */
    private void setBookSearchComboBox() {
		
		ArrayList<String> al = new ArrayList<String>();	
		al.add("Book Name");
		al.add("Book Author");
		al.add("Book Subject");
		al.add("Free Text");
		ObservableList<String> CMBXBookSearchlist = FXCollections.observableArrayList(al);	
		cmbxBookSearchTypes.setItems(CMBXBookSearchlist);  	
		}
    /**
     * init tables
     * @param void
     * @return void
     */
    private void initCol() {
    	tblBookSearchResults.setEditable(true);
    	tblcolBookRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	tblcolBookAuthor.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
    	tblcolBookSubject.setCellValueFactory(new PropertyValueFactory<>("Subject"));
    	tblcolBookStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
    
    	////////////////////
    	tblcolBorrowRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
		tblcolBorrowBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		tblcolBorrowBookAuthor.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
		tblcolBorrowLenderName.setCellValueFactory(new PropertyValueFactory<>("LenderName"));
		tblcolBorrowBorrowingDate.setCellValueFactory(new PropertyValueFactory<>("BorrowingDate"));
		tblcolBorrowReturnDate.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
		/////////
		

		tblcolOrderRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
		tblcolOrderBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
		tblcolOrderBookAuthor.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
		tblcolOrderOrderingDate.setCellValueFactory(new PropertyValueFactory<>("OrderingDate"));
		tblcolOrderPlaceOnTheWaitingList.setCellValueFactory(new PropertyValueFactory<>("PlaceOnTheWaitingList"));
		tblcolOrderClosestReturn.setCellValueFactory(new PropertyValueFactory<>("ClosestReturn"));	
		
		
    }

    public void start(Stage primaryStage) throws Exception {
    	//pStage = primaryStage;
    	//ToolbarController tb = new ToolbarController();
    	//tb.loadStage((Stage) rootPane.getScene().getWindow());
    	//this.pStage = primaryStage;
 		Parent root = FXMLLoader.load(getClass().getResource("/gui/main2.fxml"));
 		pScene = new Scene(root);
 		pScene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
 		primaryStage.setTitle("Main");
 		primaryStage.setScene(pScene);
 		primaryStage.show();
 		LibraryAssistantUtil.setStageIcon(primaryStage);
 		
 	}
    /**
     * Initialize the user. 
     * @param thisUserData gg
     *
     */
    public void setUser(User thisUserData) {
    	this.MyUserData = thisUserData;

    	this.ID.setText(MyUserData.getID());
    	//System.out.println("ID int setuser: "+this.ID.getText());
    	
    	initCol();
    	loadBookData();
    	loadBorrowedData();
    	loadOrderedData();
    	setBookSearchComboBox();
    }
    /**
     * load book informations in table
  
     */
    private void loadBookData() {
    	int i = 0,size;

    	MylistSubBook.clear();
	  	ArrayList<String> key = new ArrayList<String>();

	    ArrayList<String> askForResult = new ArrayList<String>();

		  String sql = "SELECT * FROM g17db.tblbook;";
		  askForResult.add("BookName");
		  askForResult.add("AuthorName");
		  askForResult.add("Subject");
		  askForResult.add("AmountAvailable");
		  askForResult.add("Description");
		  askForResult.add("BookID");
		  askForResult.add("ArchiveStatus");
		  askForResult.add("CatalogNumber");
		  askForResult.add("PublishedDate");
		  askForResult.add("PurchaseDate");
		  askForResult.add("TableOfContents");
		  askForResult.add("ExistingAmount");
		  askForResult.add("Location");
		  askForResult.add("Popularity");
		  askForResult.add("EditionNumber");
	
		  resultbook = Functions.askFromDB(sql ,key,askForResult);
		  size = resultbook.size();
        try {
            for(i=0;i<size;i++) {
                String rownum = String.valueOf(i+1);
                String bookname = resultbook.get(i).get(0);
                String author = resultbook.get(i).get(1);
                String subject = resultbook.get(i).get(2);
                String status = resultbook.get(i).get(3);
                String archive = resultbook.get(i).get(6);
                String bookid = resultbook.get(i).get(5);
                if(Integer.parseInt(status)!=0 && archive.equals("Normal")) status = "Available";
                else status = "Unavailable";

                MylistSubBook.add(new SubBook(rownum, bookname, author, subject, status, bookid));
               // MylistSubBook.add(new Book(bookname, author, subject, status));

            }
        } catch (Exception ex) {
            
        }
        
        tblBookSearchResults.setItems(MylistSubBook);
       
        tblBookSearchResults.setOnMouseClicked(event -> {
        	if (event.getClickCount() == 2 ) {

             	 int pos = tblBookSearchResults.getSelectionModel().getSelectedIndex();
             	  	// @SuppressWarnings("unchecked")
             	//ArrayList<String> list = 
             	 String BookID = tblBookSearchResults.getItems().get(pos).getBookID();
             	 String BookName = tblBookSearchResults.getItems().get(pos).getBookName();
             	 String AuthorName = tblBookSearchResults.getItems().get(pos).getAuthorName();
             	 String Subject = tblBookSearchResults.getItems().get(pos).getSubject();
             	String CatalogNumber = "";
             	String Description = "";
             	String PublishedDate = "";
             	String PurchaseDate = "";
             	String AmountAvailable = "";
             	String ExistingAmount = "";
             	String ArchiveStatus = "";
             	String Location = "";
             	String Popularity = "";
             	String TableOfContents = "";
             	String EditionNumber = "";
             	
             	int size5 = resultbook.size();
             	 for(int s=0;s<size5;s++) {
             		 if(resultbook.get(s).get(5).equals(BookID)) {

             			  CatalogNumber = resultbook.get(s).get(7);
             		      Description = resultbook.get(s).get(4);
             		      PublishedDate = resultbook.get(s).get(8);
             		      PurchaseDate = resultbook.get(s).get(9);
             		      AmountAvailable = resultbook.get(s).get(3);
             		      ExistingAmount = resultbook.get(s).get(11);
             		      ArchiveStatus = resultbook.get(s).get(6);
             		      Location = resultbook.get(s).get(12);
             		      Popularity = resultbook.get(s).get(13);
             		      TableOfContents = resultbook.get(s).get(10);
             		     EditionNumber = resultbook.get(s).get(14);
             		 }
             	 }


             	 ArrayList<String> BookInfo = new ArrayList<String>();
             	BookInfo.add(CatalogNumber);
             	BookInfo.add(BookName);
             	BookInfo.add(AuthorName);
             	BookInfo.add(Subject);
             	//BookInfo.add(AmountAvailable);
             	if(ArchiveStatus.equals("Archived")||ArchiveStatus.equals("Frozen")){
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
             	
             	 //System.out.println(CatalogNumber+" "+BookName+" "+AuthorName+" "+Subject+" "+AmountAvailable+" "+ExistingAmount+" "+ArchiveStatus+" "+Location+" "+Popularity+" "+
             		//	EditionNumber+" "+PublishedDate+" "+PurchaseDate+" "+TableOfContents+" "+Description+" ");
             
             	
             	try {
                	Stage stage = new Stage(); 
                    Parent root;                   
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/gui/BookDetailsByReader.fxml"));
					root = loader.load();
					BookDetailsByReaderController BookController = loader.getController();
					BookController.loadBook(BookInfo);
					BookController.setUser(MyUserData);
					BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
					
					BookController.loadAnchor(AnchorPaneTabs);
					
	                Scene scene = new Scene(root);
	                stage.setTitle("Book Details");
	                stage.setScene(scene);
	                stage.show();
	                LibraryAssistantUtil.setStageIcon(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
             	
             }
         }); 
    }
    /**
     * load borrowed book information inside the table
     * @param void
     * @return void
     */
    private void loadBorrowedData() {
        int i = 0,size;
    	MylistB.clear();
	  	ArrayList<String> key = new ArrayList<String>();
	    ArrayList<String> askForResult = new ArrayList<String>();
		String sql = "SELECT bb.BookCopyID,b.BookName, b.AuthorName, bb.BorrowingDate , bb.ReturnDate ,bb.LenderName,bb.BookBorrowID FROM g17db.tblreader as r, g17db.tbluser as u, g17db.tblbookborrow as bb, g17db.tblbook as b, g17db.tblbookcopy as c WHERE bb.BookCopyID=c.BookCopyID AND c.BookID=b.BookID AND bb.ReaderCardNumber = r.ReaderCardNumber And r.UserID = u.UserID AND u.ID = ?;";  
	    //String sql = "SELECT bb.BookCopyID,b.BookName, b.AuthorName, bb.BorrowingDate , bb.ReturnDate ,bb.LenderName,bb.BookBorrowID FROM g17db.tblbookborrow as bb, g17db.tblbook as b, g17db.tblbookcopy as c WHERE bb.BookCopyID=c.BookCopyID AND c.BookID=b.BookID AND bb.ReaderCardNumber = ?;";
		  askForResult.add("BookCopyID");
		  askForResult.add("BookName");
		  askForResult.add("AuthorName");
		  askForResult.add("LenderName");
		  askForResult.add("BorrowingDate");
		  askForResult.add("ReturnDate");
		  askForResult.add("BookBorrowID");
		  //key.add(this.UserReaderCardNumber);
		  key.add(this.ID.getText());
		  System.out.println("ID int loadborrow: "+this.ID.getText());
		  resultBorrowed = Functions.askFromDB(sql ,key,askForResult);
		  size = resultBorrowed.size();
        try {
            for(i=0;i<size;i++) {
                String rownum = String.valueOf(i+1);
                String bookid = resultBorrowed.get(i).get(0);
                String bookname = resultBorrowed.get(i).get(1);
                String author = resultBorrowed.get(i).get(2);
                String lender = resultBorrowed.get(i).get(3);
                String borrowdate = resultBorrowed.get(i).get(4);
                String returndate = resultBorrowed.get(i).get(5);
                String borroeid = resultBorrowed.get(i).get(6);

                MylistB.add(new BorrowedBooks(rownum,borroeid,bookid, bookname, author, lender, borrowdate,returndate));
               

            }
        } catch (Exception ex) {
            
        }
        tblBorrowedBook.setItems(MylistB);		 
        tblBorrowedBook.setOnMouseClicked(e -> {events();
        										if(e.getClickCount()==2)
													AskForExtensionBtn(new ActionEvent(AskForExtensionBtn,AskForExtensionBtn));});		       
        
 }
    /**
     * init the order information in the table
     * @param void
     * @return void
     */
 private void loadOrderedData() {
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
		  keyOrder.add(this.ID.getText());
		  System.out.println("ID int loadorder: "+this.ID.getText());
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
	   try {
	   	this.lstO = tblOrderBook.getItems().get(pos);}
	   catch (Exception e) {
			this.lstO=null;
	}
	
}


private void events() {
	   int pos = tblBorrowedBook.getSelectionModel().getSelectedIndex();
	  try {
	   this.lst = tblBorrowedBook.getItems().get(pos);}
	  catch (Exception e) {
		this.lst=null;
		  // TODO: handle exception
	}
	   
		   
	   //System.out.println("bookname:"+ list.getLenderName()+ " authorname: "+list.getAuthorName());			
	   
   }





/**
 * class for book copy
 *
 */
   public static class BookCopy {

	   	private final SimpleStringProperty RowNum;
        private final SimpleStringProperty BookCopyID;
        private final SimpleStringProperty BookID;
        		        
        public BookCopy(String rownum,String BookCopyID,String BookID) {
        	this.RowNum = new SimpleStringProperty(rownum);
           	this.BookCopyID = new SimpleStringProperty(BookCopyID);
        	this.BookID = new SimpleStringProperty(BookID);
        }
        	
        public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);
        }
        
        public String getBookCopyID() {
        	return BookCopyID.get();
        }
        public void setBookCopyID(String bookcopyid) {
        	BookCopyID.set(bookcopyid);
        }

        public String getBookID() {
        	return BookID.get();
        }
        public void setBookID(String bookid) {
        	BookID.set(bookid);
        }  
        
        public static String GetNextReturnOfBook(String BookID) {
        	ArrayList<ArrayList<String>> copyresult;
        	ArrayList<String> myresult= new ArrayList<String>();
        	int size=0;
        	ArrayList<String> keycopy = new ArrayList<String>();     	
		    ArrayList<String> askForCopyResult = new ArrayList<String>();
		    String sqlcopy = "SELECT b.ReturnDate FROM g17db.tblbookborrow as b, g17db.tblbookcopy as c WHERE b.Status = 'Active' And b.BookCopyID=c.BookCopyID And c.BookID=?;";
		    askForCopyResult.add("ReturnDate");
		    keycopy.add(BookID);
		    copyresult=Functions.askFromDB(sqlcopy, keycopy, askForCopyResult);
		    size=copyresult.size();
		    for(int i=0;i<size;i++) {
		    	myresult.add(copyresult.get(0).get(0));
		    }
		    //sort of date
		    Collections.sort(myresult, new Comparator<String>() {
		        DateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		        @Override
		        public int compare(String o1, String o2) {
		            try {
		                return f.parse(o1).compareTo(f.parse(o2));
		            } catch (Exception e) {
		                throw new IllegalArgumentException(e);
		            }
		        }
		    });
		    return myresult.get(0);
        }
    }
   /**
    * class for messages
    *
    */
   public static class Message {

       private CheckBox Select;
       private final SimpleStringProperty MessageID;
       private final SimpleStringProperty UserID;
       private final SimpleStringProperty Content;
       private final SimpleStringProperty ArrivalDate;
       private final SimpleStringProperty From;
       private final SimpleStringProperty Status;
       //private CheckBox RowNum;  
       
       public Message(String messageid,String user, String content,String from, String date, String status) 
       {
       	this.MessageID = new SimpleStringProperty(messageid);
       	this.UserID = new SimpleStringProperty(user);
       	this.Content = new SimpleStringProperty(content);
       	this.ArrivalDate = new SimpleStringProperty(date);
       	this.From = new SimpleStringProperty(from);
       	this.Status = new SimpleStringProperty(status);
       	this.Select = new CheckBox();
       }
       	
       public CheckBox getSelect() {
       	return Select;
       }
       public void setSelect(CheckBox c) {
       	this.Select = c;
       }

       public String getMessageID() {
       	return MessageID.get();
       }
       public void setMessageID(String mid) {
       	MessageID.set(mid);
       }
       
       public String getUserID() {
       	return UserID.get();
       }
       public void setUserID(String userid) {
       	UserID.set(userid);
       }
       
       public String getContent() {
       	return Content.get();
       }
       public void setContent(String cont) {
       	Content.set(cont);
       }
       
       public String getArrivalDate() {
       	return ArrivalDate.get();
       }
       public void setArrivalDate(String date) {
       	ArrivalDate.set(date);
       }
       
       public String getStatus() {
       	return Status.get();
       }
       public void setStatus(String status) {
       	Status.set(status);
       }
       
       public String getFrom() {
       	return From.get();
       }
       public void setFrom(String from) {
       	From.set(from);
       }  
   
  }
   /**
    * class for book table
    
    */
    public static class SubBook {

        private final String RowNum;
        private final String BookName;
        private final String AuthorName;
        private final String Subject;
        private final String Status;
        private final String BookID;
        
        public SubBook(String rownum,String bookname, String author, String subject, String status, String bookid) {
        	this.RowNum = new String(rownum);
        	this.BookName = new String(bookname);
        	this.AuthorName = new String(author);
        	this.Subject = new String(subject);
        	this.Status = new String(status);
        	this.BookID = new String(bookid);
        }
        public String getBookID() {
        	return BookID;
        }

        public String getRowNum() {
        	return RowNum;
        }


        public String getBookName() {
        	return BookName;
        }

        
        
        public String getAuthorName() {
        	return AuthorName;
        }

        
  
        public String getSubject() {
        	return Subject;
        }

        
        public String getStatus() {
        	return Status;
        }
    }
    /**
     * class book
     *
     */
    public static class Book {

        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty BookID;
        private final SimpleStringProperty BookName;
        private final SimpleStringProperty AuthorName;
        private final SimpleStringProperty Subject;
        private final SimpleStringProperty ArchiveStatus;
        //private CheckBox RowNum;  
        
        public Book(String rownum,String bookid,String bookname, String author, String subject, String status) {
        //public Book(String bookname, String author, String subject, String status) {
        	this.RowNum = new SimpleStringProperty(rownum);
        	this.BookID = new SimpleStringProperty(bookid);
        	this.BookName = new SimpleStringProperty(bookname);
        	this.AuthorName = new SimpleStringProperty(author);
        	this.Subject = new SimpleStringProperty(subject);
        	this.ArchiveStatus = new SimpleStringProperty(status);
        	//this.RowNum = new CheckBox();
        }
        	
        public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);
        }
        
        public String getBookID() {
        	return BookID.get();
        }
        public void setBookID(String bookid) {
        	BookID.set(bookid);
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
        
  
        public String getSubject() {
        	return Subject.get();
        }
        public void setSubject(String subject) {
        	Subject.set(subject);
        }
        
        
        public String getArchiveStatus() {
        	return ArchiveStatus.get();
        }
        public void setArchiveStatus(String status) {
        	ArchiveStatus.set(status);
        }


    }
  
   
        	
    /**
     * class book orders in the table
     *
     * 
     */
   public static class BookOrders {

        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty BookName;
        private final SimpleStringProperty OrderID;
        private final SimpleStringProperty AuthorName;
        private final SimpleStringProperty OrderingDate;
        private final SimpleStringProperty PlaceOnTheWaitingList;
        private final SimpleStringProperty ClosestReturn;
        //private CheckBox RowNum;  

        
        public BookOrders(String rownum,String orderid,String bookname, String author, String orderdate, String place, String close) {
        	this.RowNum = new SimpleStringProperty(rownum);
        	this.BookName = new SimpleStringProperty(bookname);
        	this.OrderID = new SimpleStringProperty(orderid);
        	this.AuthorName = new SimpleStringProperty(author);
        	this.OrderingDate = new SimpleStringProperty(orderdate);
        	this.PlaceOnTheWaitingList = new SimpleStringProperty(place);
        	this.ClosestReturn = new SimpleStringProperty(close);		        
        }
        	
        public String getRowNum() {
        	return RowNum.get();
        }
        public void setRowNum(String rownum) {
        	RowNum.set(rownum);
        }

        public String getOrderID() {
        	return OrderID.get();
        }
        public void setOrderID(String orderid) {
        	OrderID.set(orderid);
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
        
  
        public String getOrderingDate() {
        	return OrderingDate.get();
        }
        public void setOrderingDate(String order) {
        	OrderingDate.set(order);
        }
        
        
        public String getPlaceOnTheWaitingList() {
        	return PlaceOnTheWaitingList.get();
        }
        public void setPlaceOnTheWaitingList(String place) {
        	PlaceOnTheWaitingList.set(place);
        }
        
        public String getClosestReturn() {
        	return ClosestReturn.get();
        }
        public void setClosestReturn(String close) {
        	ClosestReturn.set(close);
        }
        /**
         * to get the place on the waiting list
         * @param BookID f
         * @param OrderID f
         * @return String 
         */
        public static String PlaceOnWaitingList(String OrderID,String BookID) {
        	ArrayList<ArrayList<String>> myresult;
        	ArrayList<String> myresult1= new ArrayList<String>();
        	int ans=0,size=0;
        	ArrayList<String> key = new ArrayList<String>();
		    ArrayList<String> askForResult = new ArrayList<String>();
		    String sql = "SELECT OrderID FROM g17db.tblbookorders WHERE BookID = ?;";
		    askForResult.add("OrderID");
		    key.add(BookID);
		    myresult=Functions.askFromDB(sql, key, askForResult);
		    size=myresult.size();
		    for(int i=0;i<size;i++) {
		    	myresult1.add(myresult.get(i).get(0));
		    }
		    Collections.sort(myresult1, new Comparator<String>() {
	            @Override
	            public int compare(String s1, String s2) {
	                return s1.compareToIgnoreCase(s2);
	            }
	        });
		    for(int i=0;i<size;i++) {
		    	if(!myresult1.get(i).equals(OrderID))
		    		ans++;
		    	else
		    		break;				    	
		    }
		    if (ans == 0)
		    	return "Arrived";
		  return ans+"";
        }
    }
}






