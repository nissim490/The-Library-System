package Controllers;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;

import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import common.Functions;
import entity.User;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;




public class BookDetailsByLibrarianController implements Initializable{
	private ArrayList<String> book = new ArrayList<String>();
	private ArrayList<ArrayList<String>> ResultbooksForArchive;
	private ArrayList<ArrayList<String>> Resultbookid;
	private Stage pStage;
	private AnchorPane AnchorPaneTabs;
	private String TableURL = "";
	User user;
	
	  private Desktop desktop = Desktop.getDesktop();
	    final FileChooser fileChooser = new FileChooser();
	
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
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnLendThisBook;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton ArchiveBtn;
    
    @FXML
    private JFXButton btnBorrowingBookList;

    /**
     * make the book status "Archived" and make effect on all his copies.
     * @param event action
     *  * @return void
     */
    @FXML
    void ArchiveBtn(ActionEvent event) {
    	String sqlGetCopies="SELECT *\r\n" + 
    			"FROM g17db.tblbook as b,g17db.tblbookcopy as bc\r\n" + 
    			"WHERE b.BookID=bc.BookID AND b.BookName=? AND b.AuthorName=?;";
    	ArrayList<String> keyGetCopies = new ArrayList<String>();
    	ArrayList<String> keys = new ArrayList<String>();
    	ArrayList<String> askForGetCopies = new ArrayList<String>();
    	ArrayList<ArrayList<String>> delO;
    	keyGetCopies.add(book.get(1));
    	keyGetCopies.add(book.get(2));
    	askForGetCopies.add("BookCopyID");
    	askForGetCopies.add("BookID");
    	ResultbooksForArchive=Functions.askFromDB(sqlGetCopies, keyGetCopies, askForGetCopies);
    	int size=ResultbooksForArchive.size();
    	String str="UPDATE g17db.tblbook SET ArchiveStatus = 'Archived' WHERE BookID = ?;";
    	System.out.println(str);
    	keys.add(ResultbooksForArchive.get(0).get(1));
    	Functions.askFromDB(str, keys);
    	keys.clear();
    	for(int i=0;i<size;i++) {
    		keys.clear();
    		str="UPDATE g17db.tblbookcopy SET Status = 'Removed' WHERE BookCopyID = ?;";
    		keys.add(ResultbooksForArchive.get(i).get(0));
    		Functions.askFromDB(str, keys);
    	}
    	keyGetCopies.clear();
    	askForGetCopies.clear();
    	sqlGetCopies="SELECT OrderID FROM g17db.tblbookorders WHERE BookID=?;";
    	keyGetCopies.add(ResultbooksForArchive.get(0).get(1));
    	askForGetCopies.add("OrderID");
    	System.out.println("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
    	try {
    	delO=Functions.askFromDB(sqlGetCopies, keyGetCopies, askForGetCopies);
    	size=delO.size();
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		delO=null;
    		size=0;
    		}
    	System.out.println("11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
    	for(int i=0;i<size;i++) {
        	System.out.println("11111111166666611111111111111111111111111111111111111111111111111111111111");
    		keys.clear();
    		System.out.println(delO.get(i).get(0));
    		str="DELETE FROM `g17db`.`tblbookorders` WHERE (`OrderID` = ?);";
    		keys.add(delO.get(i).get(0));
    		Functions.askFromDB(str, keys);
    	}

    	//ArrayList<String> r = ResultbooksForArchive;
    	
    }

    /**
     * view the table of content of the book
     * @param event action
     * @return void
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
		    	//Process p = new ProcessBuilder("explorer.exe", "/select,C:\\directory\\selectedFile").start();
		    	
		    	
		    	
		    	
		    	
		    	/* File file = fileChooser.showOpenDialog(pStage);
		         if (file != null) {
		             openFile(file);
		             try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		            	   String line = null;
		            	   while ((line = br.readLine()) != null) {
		            	       System.out.println(line);
		            	   }
		            	}
	
	}
    /**
     * cancel the edit and return to last state.
     * @param event action
     */
	  /**
     * button to cancel the editing
     * @param event action
     * @return void
     */
    @FXML
    void btnCancel(ActionEvent event) {
    	unSetEdit();
    }
    
    /**
     * set the window to view
     
     */
    public void unSetEdit() {
    	btnBorrowingBookList.setVisible(true);
    	btnEdit.setVisible(true);
    	btnLendThisBook.setVisible(true);
    	ArchiveBtn.setVisible(true);
    	btnCancel.setVisible(false);
    	btnSave.setVisible(false);
    	txtBookCatalogNum.setEditable(false);
		txtBookName.setEditable(false);
		txtAuthorName.setEditable(false);
		TxtSubject.setEditable(false);
		txtAvailibleCopies.setEditable(false);
		txtExistingCopies.setEditable(false);
		txtShelfNum.setEditable(false);
		txtPopularity.setEditable(false);
		txtEditionNumber.setEditable(false);
		txtPublishDate.setEditable(false);; 
		txtPurchasedDate.setEditable(false);
		txtDescription.setEditable(false);
    }
    /**
     * making the window ready to set new info
 
     */
    public void setEdit() {
    	btnBorrowingBookList.setVisible(false);
    	btnEdit.setVisible(false);
    	btnLendThisBook.setVisible(false);
    	ArchiveBtn.setVisible(false);
    	btnCancel.setVisible(true);
    	btnSave.setVisible(true);
    	txtBookCatalogNum.setEditable(true);
		txtBookName.setEditable(true);
		txtAuthorName.setEditable(true);
		TxtSubject.setEditable(true);
		txtAvailibleCopies.setEditable(true);
		txtExistingCopies.setEditable(true);
		txtShelfNum.setEditable(true);
		txtPopularity.setEditable(true);
		txtEditionNumber.setEditable(true);
		txtPublishDate.setEditable(true);; 
		txtPurchasedDate.setEditable(true);
		txtDescription.setEditable(true);
    }
    /**
     * updating the info inside the fields
     * @param event action
     * @return void
     */
    @FXML
    void btnEdit(ActionEvent event) {
    	book.clear();
    	book.add(txtBookCatalogNum.getText());
    	book.add(txtBookName.getText());
    	book.add(txtAuthorName.getText());
    	book.add(TxtSubject.getText());
    	book.add(txtAvailibleCopies.getText());
    	book.add(txtExistingCopies.getText());
    	book.add(txtShelfNum.getText());
    	book.add(txtPopularity.getText());
    	book.add(txtEditionNumber.getText());
    	book.add(txtPublishDate.getText());
    	book.add(txtDescription.getText());
    	
    	
    	String sqlGetbookid="SELECT *\r\n" + 
    			"FROM g17db.tblbook as b,g17db.tblbookcopy as bc\r\n" + 
    			"WHERE b.BookID=bc.BookID AND b.BookName=? AND b.AuthorName=?;";
    	ArrayList<String> keyGetCopies = new ArrayList<String>();
    	ArrayList<String> askForGetCopies = new ArrayList<String>();
    	keyGetCopies.add(book.get(1));
    	keyGetCopies.add(book.get(2));
    	askForGetCopies.add("BookID");
    	Resultbookid=Functions.askFromDB(sqlGetbookid, keyGetCopies, askForGetCopies);
    	
    	
    	setEdit();
    	
    }
    /**
     * Lend this book for specific reader.
     * @param event action
     * @return nothing
     */
    @FXML
    void btnLendThisBook(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/LendingABookByLibrarianOrManager.fxml"));
			root = loader.load();
			LendingABookByLibrarianOrManagerController listOfBorrow = loader.getController();
			listOfBorrow.loadBook2(book,user); //at LibraryManagerHomepageController
			 
			AnchorPaneTabs.getChildren().setAll(root); 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * Save the changes of the book
     * @param event action
     * @return void
     */
    @FXML
    void btnSave(ActionEvent event) {
    	ArrayList<String> get =new ArrayList<>();
    	ArrayList<String> key =new ArrayList<>();
    	ArrayList<String> askForResult =new ArrayList<>();
    	String sql;
    	boolean flag=false;
    	get.add(txtBookCatalogNum.getText());
    	get.add(txtBookName.getText());
    	get.add(txtAuthorName.getText());
    	get.add(TxtSubject.getText());
    	get.add(txtAvailibleCopies.getText());
    	get.add(txtExistingCopies.getText());
    	get.add(txtShelfNum.getText());
    	get.add(txtPopularity.getText());
    	get.add(txtEditionNumber.getText());
    	get.add(txtPublishDate.getText());
    	get.add(txtDescription.getText());
    	for(int i=0;i<get.size();i++)
    		if ((get.get(i).equals(""))||!("Normal Demanded".contains(txtPopularity.getText().trim()))) 
    		{
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
    			txtDescription.setText(book.get(11));
    			unSetEdit();
    			return;
    		}
    	sql="UPDATE `g17db`.`tblbook` SET ";
    	int cnt=0;
    	for(int i=0;i<get.size();i++)
    		if (!get.get(i).equals(book.get(i))) 
    		{
    			
    			if(cnt>0&&i<get.size()-1)
    				sql+=",";
    			if(i==0)
    				sql+="CatalogNumber='"+txtBookCatalogNum.getText().trim()+"'";
    			if(i==1)
    				sql+="BookName='"+txtBookName.getText().trim()+"'";
    			if(i==2)
    				sql+="CatalogNumber='"+txtAuthorName.getText().trim()+"'";
    			if(i==3)
    				sql+="CatalogNumber='"+TxtSubject.getText().trim()+"'";
    			if(i==4)
    				sql+="AmountAvailable='"+txtAvailibleCopies.getText().trim()+"'";
    			if(i==5)
    				sql+="ExistingAmount='"+txtExistingCopies.getText().trim()+"'";
    			if(i==6)
    				sql+="Location='"+txtShelfNum.getText().trim()+"'";
    			if(i==7)
    				sql+="Popularity='"+txtPopularity.getText().trim()+"'";
    			if(i==8)
    				sql+="EditionNumber='"+txtEditionNumber.getText().trim()+"'";
    			if(i==9)
    				sql+="PublishedDate='"+txtPublishDate.getText().trim()+"'";
    			if(i==10)
    				sql+="PurchaseDate='"+txtPurchasedDate.getText().trim()+"'";
    			if(i==11)
    				sql+="Description='"+txtDescription.getText().trim()+"'";
    			cnt++;
    		}
    
    				sql+=" WHERE BookID=?;";
    		key.add(Resultbookid.get(0).get(0));
    		Functions.askFromDB(sql, key);
    		unSetEdit();
    	}
    			
    
    /**
     * view borrow books list
     * @param event action
     * return void
     */
    @FXML
    void btnBorrowingBookList(ActionEvent event) {
    
    	((Node)event.getSource()).getScene().getWindow().hide();
    	Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/ListOfBorrowedBooks.fxml"));
			root = loader.load();
			ListOfBorrowedBooksController listOfBorrow = loader.getController();
			listOfBorrow.preload(book.get(1), book.get(13));
			
			AnchorPaneTabs.getChildren().setAll(root); 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
		
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {	


	    }
		/**
		 * Initialize the data for the screen
		 * @param book the book to view
		 * @param user the user info
		 * return void
		 */
		public void loadBook(ArrayList<String> book,User user){
			this.book = book;
			this.user = user;
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
		
		public void loadStage(Stage mainstage){
			this.pStage = mainstage;
			
		}
		public void loadAnchor(AnchorPane anchorpanetabs){
			this.AnchorPaneTabs = anchorpanetabs;
			
		}
		
		
}
