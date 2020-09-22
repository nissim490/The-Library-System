package Controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import javafx.scene.Node;
import java.util.ArrayList;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


import common.Functions;
import entity.User;



			 
public class LibrarianHomepageController extends Application implements Initializable {
	private User MyUserData;
	ObservableList<SubBook> MylistSubBook = FXCollections.observableArrayList();
	ObservableList<SubReader> MylistSubReader = FXCollections.observableArrayList();
	public static final String ICON_IMAGE_LOC = "/files/icon.png";
	ArrayList<ArrayList<String>> resultbook;
	ArrayList<ArrayList<String>> resultuser;
	ArrayList<ArrayList<String>> resultreader;

	Stage pStage;
	Scene pScene;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneTabs;

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
    private StackPane bookInfoContainer;

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
    private HBox reader_info;

    @FXML
    private JFXComboBox<String> cmbxReaderSearchTypes;

    @FXML
    private JFXTextField memberIDInput;

    @FXML
    private StackPane memberInfoContainer;

    @FXML
    private TableView<SubReader> tblReaderSearchResults;

    @FXML
    private TableColumn<SubReader, String> tblcolReaderRowNum;

    @FXML
    private TableColumn<SubReader, String> tblcolReaderCardNumber;

    @FXML
    private TableColumn<SubReader, String> tblcolReaderFullName;

    @FXML
    private TableColumn<SubReader, String> tblcolReaderStatus;

    

  
	/**
  	 * refreshing information inside the table of books
  	 * @param 
  	 * @param MouseEvent
  	 * @return void
  	 */
    @FXML
    void RefreshBookImgbtn(MouseEvent event) {
    	loadBookData();
    }
    /**
  	 * refreshing information inside the table of readers
  	 * @param 
  	 * @param MouseEvent
  	 * @return void
  	 */
    @FXML
    void RefreshReaderImgbtn(MouseEvent event) {
    	loadReaderData();
    }
    
    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }
    
    
    /**
  	 * init the combobox
  	 * @param 
  	 * @param ActionEvent
  	 * @return void
  	 */
    @FXML
    void cmbxBookSearchTypes(ActionEvent event) {/**set cmbxBookSearchTypes Prompt Text */
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
	 /**loading the book information to the table according to what the user searched
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void loadBookInfo(ActionEvent event) {/** brings in relevant data from the DB and initialization TableSearch*/
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
                            String archive = resultbook.get(i).get(6);
                            cnt++;
                            String bookid = resultbook.get(i).get(5);
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
            } 
    	}
    	
    }

	 /**loading the reader information to the table according to what the user searched
   	 * @param 
   	 * @param ActionEvent
   	 * @return void
   	 */
    @FXML
    void loadMemberInfo(ActionEvent event) {/** brings in relevant data from the DB and initialization TableSearch*/
    	String Fullname;
    	int i,size2,size,cnt=1;
    	if(memberIDInput.getText().trim() != "") {
    		size2 = resultuser.size();
    		size = resultreader.size();
    		
        	switch(cmbxReaderSearchTypes.getValue()) 
            { 
                case "First Name": 
                	cnt=1;
                	MylistSubReader.clear();
                	tblReaderSearchResults.getItems().clear();
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
           					   if(resultuser.get(i).get(1).toUpperCase().contains(memberIDInput.getText().trim().toUpperCase())) {
           						Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
           					   	String rownum = String.valueOf(cnt);
           		                String ReaderCardNumber = resultreader.get(j).get(1);
           		                String CardStatus = resultreader.get(j).get(2);
           		                cnt++;
           		             MylistSubReader.add(new SubReader(rownum, ReaderCardNumber, Fullname, CardStatus)); 
           					   }
           					    
                        }
                    } 
                	  }
                    tblReaderSearchResults.setItems(MylistSubReader);
                    break; 
                case "Last Name": 
                	cnt=1;
                	MylistSubReader.clear();
                	tblReaderSearchResults.getItems().clear();
                
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
           					   if(resultuser.get(i).get(2).toUpperCase().contains(memberIDInput.getText().trim().toUpperCase())) {
           					    Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
           					   	String rownum = String.valueOf(cnt);
           		                String ReaderCardNumber = resultreader.get(j).get(1);
           		                String CardStatus = resultreader.get(j).get(2);
           		                cnt++;
           		             MylistSubReader.add(new SubReader(rownum, ReaderCardNumber, Fullname, CardStatus));
           					   }
                        }
                    }
                	  }
                    tblReaderSearchResults.setItems(MylistSubReader);
                    break; 
                case "ID": 
                	cnt=1;
                	MylistSubReader.clear();
                	tblReaderSearchResults.getItems().clear();
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
           					   if(resultuser.get(i).get(3).toUpperCase().contains(memberIDInput.getText().trim().toUpperCase())) {
           					    Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
           					   	String rownum = String.valueOf(cnt);
           		                String ReaderCardNumber = resultreader.get(j).get(1);
           		                String CardStatus = resultreader.get(j).get(2);
           		                cnt++;
           		             MylistSubReader.add(new SubReader(rownum, ReaderCardNumber, Fullname, CardStatus));
           					   }
                        }
                    }
                	  }
                    tblReaderSearchResults.setItems(MylistSubReader);
                	
                    break; 
                case "Reader Card Number": 
                	cnt=1;
                	MylistSubReader.clear();
                	tblReaderSearchResults.getItems().clear();
                	  for(i = 0;i<size2;i++) {
           			   for(int j=0;j<size;j++) {
           				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
           					   if(resultreader.get(j).get(1).contains(memberIDInput.getText().trim())) {
           					    Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
           					   	String rownum = String.valueOf(cnt);
           		                String ReaderCardNumber = resultreader.get(j).get(1);
           		                String CardStatus = resultreader.get(j).get(2);
           		                cnt++;
           		             MylistSubReader.add(new SubReader(rownum, ReaderCardNumber, Fullname, CardStatus));
           					   }
           				   }
           			   }
                   
                	  } 
                	  
                	  tblReaderSearchResults.setItems(MylistSubReader);
                      break; 
                	  
             }               	
                	  
        }
    }

    /**
   	 * init all window
   	 * @param rb s
   	 * @param url x
   	
   	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {/**initialization*/
    	//list = FXCollections.observableArrayList();
    	//Mylist = FXCollections.observableArrayList();
    	initCol();
    	loadBookData();
    	loadReaderData();
    	setBookSearchComboBox();
    	setReaderSearchComboBox();
    }
   
    public AnchorPane getAnchor() {
    	return AnchorPaneTabs;
    }
    /**
   	 * init combobox
   	 * @param 
   	 * @param void
   	 * @return void
   	 */
    private void setBookSearchComboBox() {/**initialization cmbxBookSearchTypes*/
		
		ArrayList<String> al = new ArrayList<String>();	
		al.add("Book Name");
		al.add("Book Author");
		al.add("Book Subject");
		al.add("Free Text");
		ObservableList<String> CMBXBookSearchlist = FXCollections.observableArrayList(al);	
		cmbxBookSearchTypes.setItems(CMBXBookSearchlist);  	
		}
    /**
   	 * init combobox
   	 * @param 
   	 * @param void
   	 * @return void
   	 */
    private void setReaderSearchComboBox() {/**initialization cmbxReaderSearchTypes*/
		
  		ArrayList<String> al = new ArrayList<String>();	
  		al.add("First Name");
  		al.add("Last Name");
  		al.add("ID");
  		al.add("Reader Card Number");
  		ObservableList<String> CMBXReaderSearchlist = FXCollections.observableArrayList(al);	
  		cmbxReaderSearchTypes.setItems(CMBXReaderSearchlist);  	
  		}
    /**
   	 * init tables
   	 * @param 
   	 * @param void
   	 * @return void
   	 */
    private void initCol() {/**initialization table*/
    	tblBookSearchResults.setEditable(true);
    	tblcolBookRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	tblcolBookAuthor.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
    	tblcolBookSubject.setCellValueFactory(new PropertyValueFactory<>("Subject"));
    	tblcolBookStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
    	////////
    	
    	tblReaderSearchResults.setEditable(true);
    	tblcolReaderRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolReaderCardNumber.setCellValueFactory(new PropertyValueFactory<>("ReaderCardNumber"));
    	tblcolReaderFullName.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
    	tblcolReaderStatus.setCellValueFactory(new PropertyValueFactory<>("CardStatus"));
   
    }

    public void start(Stage primaryStage) throws Exception {

 		/*Parent root = FXMLLoader.load(getClass().getResource("/gui/main2.fxml"));
 		pScene = new Scene(root);
 		pScene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
 		primaryStage.setTitle("Main");
 		primaryStage.setScene(pScene);
 		primaryStage.show();
 		LibraryAssistantUtil.setStageIcon(primaryStage);*/
 		
 	}

    /**
   	 * transfering information to this window
   	 *  
   	 * @param thisUserData d
   	 *  
   	 */
	public void setUser(User thisUserData) {
		this.MyUserData = thisUserData;
	}
    /**
   	 * loading book information to the table
   	 *
   	 *
   	 */
    private void loadBookData() {/** brings in relevant data from the DB and Transfer  information between windows ,opnning window*/
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
                String bookid = resultbook.get(i).get(5);
                String archive = resultbook.get(i).get(6);
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
                    loader.setLocation(getClass().getResource("/gui/BookDetailsByLibrarian.fxml"));
					root = loader.load();
					BookDetailsByLibrarianController BookController = loader.getController();
					BookController.loadBook(BookInfo,MyUserData);
					BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
					
					BookController.loadAnchor(AnchorPaneTabs);
					
	                Scene scene = new Scene(root);
	                stage.setTitle("Book Details");
	                stage.setScene(scene);
	                stage.show();
	                setStageIcon(stage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
             	
             }
         }); 
    }
    /**
   	 * loading reader information to the table
   	 * @param 
   	 * @param void
   	 * @return void
   	 */
        private void loadReaderData()/**brings in relevant data from the DB and Transfer  information between windows ,opnning window*/
        {
        int i, size, size2,cnt=1;
        String Fullname;
	  	ArrayList<String> key1 = new ArrayList<String>();
	  	ArrayList<String> key2 = new ArrayList<String>();
	    ArrayList<String> askForResult1 = new ArrayList<String>();
	    ArrayList<String> askForResult2 = new ArrayList<String>();
	    
        String sql = "SELECT * FROM g17db.tblreader;";
		   askForResult1.add("UserID");
		   askForResult1.add("ReaderCardNumber");
		   askForResult1.add("CardStatus");
		   askForResult1.add("PhoneNumber");
		
		   resultreader = Functions.askFromDB(sql ,key1,askForResult1);
		size = resultreader.size();

		  sql = "SELECT * FROM g17db.tbluser;";
		  askForResult2.add("UserID");
		   askForResult2.add("FirstName");
		   askForResult2.add("Lastname");
		   askForResult2.add("ID");
		   askForResult2.add("Email");
		   askForResult2.add("Username");
		   askForResult2.add("Password");
		
		   resultuser = Functions.askFromDB(sql ,key2,askForResult2);
		   size2 = resultuser.size();
		   for(i = 0;i<size2;i++) {
			   for(int j=0;j<size;j++) {
				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
					    Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
					   	String rownum = String.valueOf(cnt);
		                String ReaderCardNumber = resultreader.get(j).get(1);
		                String CardStatus = resultreader.get(j).get(2);
		                cnt++;
		                MylistSubReader.add(new SubReader(rownum, ReaderCardNumber, Fullname, CardStatus));
				   }
			   }
		   }
		   
		   
		   
     tblReaderSearchResults.setItems(MylistSubReader);
    // tblReaderSearchResults.setOnMouseClicked(e -> {Readerevents();});
     
     
     tblReaderSearchResults.setOnMouseClicked(event -> {
         if (event.getClickCount() == 2 ) {
        	String PhoneNumber = "";
         	String Email = "";
         	String Username = "";
         	String Password = "";
         	String ID = "";
         	String UserID = "";
         	 int pos = tblReaderSearchResults.getSelectionModel().getSelectedIndex();
         	  	// @SuppressWarnings("unchecked")
         	//ArrayList<String> list = 
         	 String ReaderCardNumber = tblReaderSearchResults.getItems().get(pos).getReaderCardNumber();
         	 String Status = tblReaderSearchResults.getItems().get(pos).getCardStatus();
         	 String Fullname2 = tblReaderSearchResults.getItems().get(pos).getFullname();
         	 String arr[] = Fullname2.split(" ", 2);
         	 String firstname = arr[0]; 
         	 String lastname = arr[1];
         	 int size3 = resultreader.size();
         	 int size4 = resultuser.size();
         	 
         	 
         	 for(int k=0;k<size3;k++) {
         		 if(resultreader.get(k).get(1).equals(ReaderCardNumber)) {
         			 UserID = resultreader.get(k).get(0);
         			 PhoneNumber = resultreader.get(k).get(3);
         		 }
         	 }
         	 for(int k=0;k<size4;k++) {
         		 if(resultuser.get(k).get(0).equals(UserID)) {
         			 ID = resultuser.get(k).get(3);
         			 Email = resultuser.get(k).get(4);
         			 Username = resultuser.get(k).get(5);
         			 Password = resultuser.get(k).get(6);
         		 }
         	 }

         	 ArrayList<String> ReaderInfo = new ArrayList<String>();
         	 ReaderInfo.add(firstname);
         	 ReaderInfo.add(lastname);
         	 ReaderInfo.add(ID);
         	 ReaderInfo.add(PhoneNumber);
         	 ReaderInfo.add(Email);
         	 ReaderInfo.add(Username);
         	 ReaderInfo.add(Password);
         	 ReaderInfo.add(ReaderCardNumber);
         	 ReaderInfo.add(Status);
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
                loader.setLocation(getClass().getResource("/gui/ViewReaderPersonalInformationByLibrarian.fxml"));
				root = loader.load();
				ViewReaderPersonalInformationByLibrarianController ViewReaderController = loader.getController();
				ViewReaderController.loadReader(ReaderInfo);
				ViewReaderController.loadUser(MyUserData);
				//BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
				
				//BookController.loadAnchor(AnchorPaneTabs);
				
                Scene scene = new Scene(root);
                stage.setTitle("Reader Personal Information");
                stage.setScene(scene);
                stage.show();
                setStageIcon(stage);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
         	
         	
         	
         }
     });
    }
        /**
       	 * class for the book table
       
       	 */
    public static class SubBook {/**Class especially for table of Book*/

      
        
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
   	 * class for the reader table
   	 
   	 */
    public static class SubReader {/**Class especially for table of Reader*/

        private final SimpleStringProperty RowNum;
        private final SimpleStringProperty ReaderCardNumber;
        private final SimpleStringProperty Fullname;
        private final SimpleStringProperty CardStatus;
  
        
        public SubReader(String rownum,String readerCardNumber, String fullname, String cardStatus) {
        	this.RowNum = new SimpleStringProperty(rownum);
        	this.ReaderCardNumber = new SimpleStringProperty(readerCardNumber);
        	this.Fullname = new SimpleStringProperty(fullname);
         	this.CardStatus = new SimpleStringProperty(cardStatus);

        }
        	
        public String getRowNum() {
        	return RowNum.get();
        }

        public String getReaderCardNumber() {
        	return ReaderCardNumber.get();
        }
    
        public String getFullname() {
        	return Fullname.get();
        }
 
        public String getCardStatus() {
        	return CardStatus.get();
        }    
    }
}






