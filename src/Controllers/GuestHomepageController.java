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




public class GuestHomepageController extends Application implements Initializable {

	ObservableList<SubBook> MylistSubBook = FXCollections.observableArrayList();


	ArrayList<ArrayList<String>> resultbook;


	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane AnchorPaneTabs;

    @FXML
    private JFXTabPane mainTabPane;

    @FXML
    private Tab WelcomePage;

    @FXML
    private Tab searchesTab;

    @FXML
    private HBox book_info;

    @FXML
    private JFXComboBox<String> cmbxBookSearchTypes;

    @FXML
    private JFXTextField bookIDInput;

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


    /**
  	 * refreshing the table of books
  	 * @param MouseEvent
  	 * 
  	 * return void
  	 */
    @FXML
    void RefreshBookImgbtn(MouseEvent event) {
    	loadBookData();
    }
    /**
  	 * initilizing the combobox
  	 * @param MouseEvent
  	 * 
  	 * return void
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
	 /**loading the book information to the table according to what the user searched
   	 * @param 
   	 * @param ActionEvent
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
            } 
    	}
    	
    }

    /**
  	 * initilizing this window
  	 * @param url d 
  	 * @param rb s
  	 *
  	 */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//list = FXCollections.observableArrayList();
    	//Mylist = FXCollections.observableArrayList();
    	initCol();
    	loadBookData();
    	setBookSearchComboBox();

    }
    
    public AnchorPane getAnchor() {
    	return AnchorPaneTabs;
    }
    /**
  	 * initilizing this combobox
  
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
  	 * initilizing this the tables

  	 */
    private void initCol() {
    	tblBookSearchResults.setEditable(true);
    	tblcolBookRowNum.setCellValueFactory(new PropertyValueFactory<>("RowNum"));
    	tblcolBookName.setCellValueFactory(new PropertyValueFactory<>("BookName"));
    	tblcolBookAuthor.setCellValueFactory(new PropertyValueFactory<>("AuthorName"));
    	tblcolBookSubject.setCellValueFactory(new PropertyValueFactory<>("Subject"));
    	tblcolBookStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
   
    }

   public void start(Stage primaryStage) throws Exception {
     /*	//pStage = primaryStage;
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
 		*/
 	}


   /**
 	 * loading book information to the table
 
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
                    loader.setLocation(getClass().getResource("/gui/BookDetailsByGuest.fxml"));
					root = loader.load();
					BookDetailsByGuestController BookController = loader.getController();
					BookController.loadBook(BookInfo);
					//BookController.loadStage((Stage) AnchorPaneTabs.getScene().getWindow());
					
					//BookController.loadAnchor(AnchorPaneTabs);
					
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
  	 * class for the table of books
 
  	 */
    public static class SubBook {

       /* private final SimpleStringProperty RowNum;
        private final SimpleStringProperty BookName;
        private final SimpleStringProperty AuthorName;
        private final SimpleStringProperty Subject;
        private final SimpleStringProperty ArchiveStatus;*/
        //private CheckBox RowNum;  
        private final String RowNum;
        private final String BookName;
        private final String AuthorName;
        private final String Subject;
        private final String Status;
        private final String BookID;
        
        public SubBook(String rownum,String bookname, String author, String subject, String status, String bookid) {
        //public Book(String bookname, String author, String subject, String status) {
        	/*this.RowNum = new SimpleStringProperty(rownum);
        	this.BookName = new SimpleStringProperty(bookname);
        	this.AuthorName = new SimpleStringProperty(author);
        	this.Subject = new SimpleStringProperty(subject);
        	this.ArchiveStatus = new SimpleStringProperty(status);*/
        	//this.RowNum = new CheckBox();
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
        public void setRowNum(String rownum) {
       // 	this.RowNum = rownum;
        }

        public String getBookName() {
        	return BookName;
        }
        public void setBookName(String bookname) {
     //   	BookName = bookname;
        }
        
        
        public String getAuthorName() {
        	return AuthorName;
        }
        public void setAuthorName(String author) {
      //  	AuthorName = author;
        }
        
  
        public String getSubject() {
        	return Subject;
        }
        public void setSubject(String subject) {
     //   	Subject = subject;
        }
        
        
        public String getStatus() {
        	return Status;
        }
        public void setStatus(String status) {
      //  	Status = status;
        }	
       /* public String getRowNum() {
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
        }*/
       /* public CheckBox getRowNum() {
            return RowNum;
        }
     
        public void setRowNum(CheckBox checkbox) {
            this.RowNum = checkbox;
        }*/
    }
    
}






