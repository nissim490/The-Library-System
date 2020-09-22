package Controllers;


import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import Controllers.AlertMaker;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;
import javax.swing.JFileChooser;
import javafx.stage.FileChooser;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
//import sun.util.calendar.BaseCalendar.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTextArea;
import common.Functions;
import entity.Book;
import entity.BookCopy;
import entity.User;

public class AddNewBookByLibrarianOrManagerController extends Application implements Initializable {
	private User MyUserData;
	ObservableList<String> Mylist = FXCollections.observableArrayList();
	private ArrayList<ArrayList<String>> ResultBooKcopy;
	private ArrayList<ArrayList<String>> Result;
	private ArrayList<ArrayList<String>> Resultbook;
	     private Book book;
	     private BookCopy bookcopy; 
	     private boolean a;
	     JFXButton yesButton = new JFXButton("YES, Please");//
			JFXButton okButton = new JFXButton("Alright");//
			 
	 @FXML
	    private ResourceBundle resources;
 
	    @FXML
	    private URL location;
	    @FXML
		private Label TableOCURL;
	    @FXML
	    private AnchorPane AddNewBookPane;
	    @FXML
	    private AnchorPane MainAnchor;//
	    @FXML
	    private StackPane MainStack;///
	    @FXML
	    private JFXTextArea txtDescription;
	    
	    @FXML
	    private JFXTextField txtBookCatalogNum;

	    @FXML
	    private JFXTextField txtBookName;

	    @FXML
	    private JFXTextField txtAuthorName;

	    @FXML
	    private CheckComboBox<String> cbxSubject;

	    @FXML
	    private JFXComboBox<String> cmbPopularity;

	    @FXML
	    private JFXButton BrowseForPDFBtn;

	    @FXML
	    private JFXTextField txtAvailibleCopies;

	    @FXML
	    private JFXTextField txtShelfNum;

	    @FXML
	    private JFXTextField txtEditionNumber;

	    @FXML
	    private JFXDatePicker DPKPurchaseDate;

	    @FXML
	    private JFXDatePicker DPKPublishedDate;

	    @FXML
	    private JFXButton AddBookBtn;

	    /**
	     * adding a book after pressing this button
	     * @param ActionEvent
	     * @return void
	     */
	    @FXML
	    void AddBookBtn(ActionEvent event) throws Exception  {/**A button to click on it will add a book and a copy of a book And also checks the parameters entered*/
	    	  boolean Exists = true; 
	    	  boolean examination= false;
	    	Mylist.add("[]");
		    	int sizebook ;
		   
		    	if((!(cmbPopularity.getValue()==null)&& !(cbxSubject.getCheckModel().getCheckedItems().isEmpty())&&!(txtEditionNumber.getText().equals(""))&&!(txtBookName.getText().equals(""))&&!(txtShelfNum.getText().equals(""))&&!(txtAuthorName.getText().equals(""))&&!(txtBookCatalogNum.getText().equals(""))&&!(txtDescription.getText().equals("")))) {
			if(! ((DPKPurchaseDate.getValue()==null||DPKPublishedDate.getValue()==null)))	{
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Checks if one of the fields is empty//////////////////////////////////////////////////////
				
				 ArrayList<String> askForResultBooKcopy = new ArrayList<String>();
				 ArrayList<String> keyBooKcopy = new ArrayList<String>();
				 String sqlkeyBooKcopy = "SELECT * FROM g17db.tblbookcopy;";
			
			
					 askForResultBooKcopy.add("BookCopyID");
				
			     ResultBooKcopy = Functions.askFromDB(sqlkeyBooKcopy ,keyBooKcopy,askForResultBooKcopy);
				int  sizeBooKcopy = ResultBooKcopy.size();
				
				
				
	    	 ArrayList<String> askForResult = new ArrayList<String>();
	    	 ArrayList<String> key = new ArrayList<String>();
			  String sql = "SELECT * FROM g17db.tblbook;";
		
			
		      askForResult.add("BookID");
			  askForResult.add("BookName");
			  askForResult.add("AuthorName");
			  askForResult.add("CatalogNumber");
			  askForResult.add("EditionNumber");
		
			  
	    	 Resultbook = Functions.askFromDB(sql ,key,askForResult); 
			 sizebook = Resultbook.size();
			
		    
	    	 String strAmountAvailable =txtAvailibleCopies.getText();
	    	 strAmountAvailable = strAmountAvailable.replaceAll("[^0-9]", "");
	    	
	   	      if (strAmountAvailable.equals(""))
	   	    	examination=true;
	    	
	   	   if(examination==true)
			   	AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage", "Available amount must be a number");
	    	
	    	book.setBookName(txtBookName.getText());
	   	
	   	 try { 
	   		 book.setAmountAvailable(txtAvailibleCopies.getText());
		    	// book.setCatalogNum(txtBookCatalogNum.getText()); 
	    	book.setAuthorName(txtAuthorName.getText());
			} 
	   	catch(Exception exception)
			{String s =exception.toString().substring(20);
	   		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage",s);
				System.out.println(exception); 
				examination=true;
			
			}	
	    	
	   	 	   	 int cnt1=1;
	   	 int sizelist = Resultbook.size();
			String lastmessageid = Resultbook.get(sizelist-1).get(0); ////Finds the largest number in the table
			String newmessageid = "B";
		int	amounutofdigits = lastmessageid.length()-1; //should be 6
		int	numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1) + 1);
			
		int	amountofzero = amounutofdigits - numberlength;
		for(int b=0;b<amountofzero;b++) {
				newmessageid = newmessageid+"0";
			}
		newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1);
		
	   	 
		
		
		
		 int cnt2=1;
	   	 sizeBooKcopy = ResultBooKcopy.size();
			String lastmessageid1 = ResultBooKcopy.get(sizeBooKcopy-1).get(0); 
			String newmessageid1 = "BC";
		int	amounutofdigits1 = lastmessageid1.length()-2; //should be 6
		int	numberlength1 = (int) (Math.log10(Integer.parseInt(lastmessageid1.substring(2,lastmessageid1.length()))+cnt2) + 1);
			
		int	amountofzero1 = amounutofdigits1 - numberlength1;
		for(int b=0;b<amountofzero1;b++) {
				newmessageid1 = newmessageid1+"0";
			}
		newmessageid1 = newmessageid1+(Integer.parseInt(lastmessageid1.substring(2,lastmessageid1.length()))+cnt2);

	    	book.setAmountAvailable(txtAvailibleCopies.getText());
	
	   ObservableList<String> subject  =  cbxSubject.getCheckModel().getCheckedItems();
	   String Popularity = cmbPopularity.getValue();
	   String ShelfNum = txtShelfNum.getText();
	   String EditionNumber = txtEditionNumber.getText();
	   String Description = txtDescription.getText();
	   String PurchaseDate = DPKPurchaseDate.getValue().toString();
	   String  PublishedDate= DPKPublishedDate.getValue().toString();
	   
	  for(int i=0;i<sizebook;i++)              
		  if((Resultbook.get(i).get(4).equals(EditionNumber))&&((Resultbook.get(i).get(3).equals(txtBookCatalogNum.getText()))&&((Resultbook.get(i).get(2).toUpperCase().equals(book.getAuthorName().toUpperCase()))&&((Resultbook.get(i).get(1).toUpperCase().equals(book.getBookName().toUpperCase()))))) )
			  Exists=false;
			  
	  if( !Exists)
		   	AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage", "This book already exists in the system");
	
	  if((Exists==true)&&(examination==false)) {/**Checks whether another book exists and whether correct parameters have been inserted*/
			 int sizecopy=Integer.parseInt(strAmountAvailable);
				  book.AddNewBook(newmessageid, book.getBookName(), book.getAuthorName(), subject, Description , Popularity, book.getAmountAvailable(), book.getAmountAvailable(), ShelfNum, "Normal",PurchaseDate,PublishedDate,txtBookCatalogNum.getText(),TableOCURL.getText(),EditionNumber);
				  for(int i = 0;i<sizecopy;i++) {
					  bookcopy.AddNewCopy(newmessageid1,newmessageid,"Shelf");
					cnt2++;
						 lastmessageid1 = ResultBooKcopy.get(sizeBooKcopy-1).get(0); 
						 newmessageid1 = "BC";
						amounutofdigits1 = lastmessageid1.length()-2; //should be 6
						numberlength1 = (int) (Math.log10(Integer.parseInt(lastmessageid1.substring(2,lastmessageid1.length()))+cnt2) + 1);////Finds the largest number in the table
						
						amountofzero1 = amounutofdigits1 - numberlength1;
					for(int b=0;b<amountofzero1;b++) {
							newmessageid1 = newmessageid1+"0";
						}
					newmessageid1 = newmessageid1+(Integer.parseInt(lastmessageid1.substring(2,lastmessageid1.length()))+cnt2);
				   	

				  }
				  String subject1 ="";
				  String subject2 =",";
				  for(int i=0;i<subject.size();i++)
					if(i==0)
						subject1=subject1+subject.get(i);
					else		 
						subject1=subject1+subject2+subject.get(i);
				  
				
	             	 ArrayList<String> BookInfo = new ArrayList<String>();
	              	BookInfo.add(txtBookCatalogNum.getText());
	              	BookInfo.add(book.getBookName());
	              	BookInfo.add(book.getAuthorName());
	              	BookInfo.add(subject1);
	              	BookInfo.add(book.getAmountAvailable());
	              	BookInfo.add(book.getAmountAvailable());
	              	
	              	BookInfo.add(ShelfNum);
	              	BookInfo.add(Popularity);
	              	BookInfo.add(EditionNumber);
	              	BookInfo.add(PublishedDate);
	              	BookInfo.add(PurchaseDate);
	              	BookInfo.add(TableOCURL.getText());
	              	BookInfo.add(Description);
	              	BookInfo.add(newmessageid);
	              	
			    	try {
			    		Stage stage = new Stage(); 
	                    Parent root;                   
	                    FXMLLoader loader = new FXMLLoader();
	                    loader.setLocation(getClass().getResource("/gui/BookDetailsByLibrarian.fxml"));
						root = loader.load();
						BookDetailsByLibrarianController BookController = loader.getController();
						BookController.loadBook(BookInfo, MyUserData);
						
						BookController.loadAnchor(AddNewBookPane);/**Transferring data to display the book*/
						
		                Scene scene = new Scene(root);
		                stage.setTitle("Book Details");
		                stage.setScene(scene);
		                stage.show();
		                LibraryAssistantUtil.setStageIcon(stage);
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				  
			  }
			 
				
					// System.out.println("exsit");
			  
			  
			}
			  else AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage", "The dates must be filled");
					 //System.out.println("The dates must be filled");
		    	}
		    	else
		    		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(okButton), "Error Massage", "The all fild must be filled");
		    		// System.out.println("The all fild must be filled");
		    		
			
	   
	  }
	   
	    /**
	     * loading PDF when clicking
	     * @param ActionEvent
	     * @return void
	     */
	    @FXML
	    void BrowseForPDFBtn(ActionEvent event) {
	
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				TableOCURL.setText(selectedFile.getName());
				System.out.print(1+" "+TableOCURL.getText());
				System.out.print(selectedFile.getName());
	    	   Functions.UplodeFILE(selectedFile);
			}
	    }
		
	public void start(Stage primaryStage) {
		/*try {
			//BorderPane root = new BorderPane();
			Parent root= FXMLLoader.load(getClass().getResource("/gui/AddNewBookByLibrarianOrManager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	
	/**
     * initializing
     * @param arg0 d
     * @param arg1 d
     *
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	/**Initializing*/
		setchekSubject() ;
		setcmbPopularity();
		 book = new Book();
		 bookcopy = new BookCopy();
    }
	/**
     * Initializing cbxSubject
  
     * 
     */
	public void setchekSubject() {/**Initializing cbxSubject*/
		
		ArrayList<String> al = new ArrayList<String>();	
		al.add("Biography");
		al.add("Travel");
		al.add("Children");
		al.add("Crime");
		al.add("Education");
		al.add("Historical Fiction");
		al.add("Food");
		al.add("History");
		al.add("Humor");
		al.add("Medical");
		al.add("Prose");
		al.add("Science Fiction");
		al.add("Sport");
		al.add("Education & Medical");
		al.add("Prose & Crime");
		ObservableList<String> CMBXBookSearchlist = FXCollections.observableArrayList(al);	
		cbxSubject.getItems().addAll(CMBXBookSearchlist);
    
	
	}
	/**
     * transfering information between windows
     * @param result s 
     * 
     */
	public void loadReader(ArrayList<ArrayList<String>> result) {/**Receiving user data from another screen*/
		
		this.Result=result;
	}
	/**
     * Initializing cmbPopularity
    
     
     */
	public void setcmbPopularity() {/**Initializing cmbPopularity*/

		ArrayList<String> al = new ArrayList<String>();	
		al.add("Normal");
		al.add("Demanded");
		ObservableList<String> CMBXBookSearchlist = FXCollections.observableArrayList(al);	
		cmbPopularity.setItems( CMBXBookSearchlist);  	
		
		
}

}

