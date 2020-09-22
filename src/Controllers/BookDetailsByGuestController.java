package Controllers;

import javafx.fxml.Initializable;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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




public class BookDetailsByGuestController implements Initializable{
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
