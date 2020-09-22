package Controllers;


import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import common.Functions;
import entity.Reader;
import entity.User;

public class AddNewReaderByLibrarianOrManagerController extends Application implements Initializable {
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastName;

    @FXML
    private JFXTextField txtIDNumber;

    @FXML
    private JFXComboBox<String> cmbPhoneNumber;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXTextField lblEmail;

    @FXML
    private JFXTextField lblUsername;

    @FXML
    private JFXButton btnCheckAvailability;

    @FXML
    private JFXPasswordField lblPassword;

    @FXML
    private JFXPasswordField lblRePassword;
    @FXML
    private AnchorPane MainAnchor;//
    @FXML
    private StackPane MainStack;///
    @FXML
    private JFXButton btnSaveReader;
    private ArrayList<ArrayList<String>> Result1;
    private ArrayList<ArrayList<String>> Resultreader;
    private Reader reader;
    JFXButton yesButton = new JFXButton("YES, Please");//
	JFXButton noButton = new JFXButton("No, Please");//
	 
    private  boolean name=false;
    private  boolean check=false;//
    /**
     * adding new reader after clicking this button
     * @param ActionEvent
     * @return void
     */
    @FXML
    void btnSaveReader(ActionEvent event) throws Exception {/**A button to click on it will add a user and a reader  And also checks the parameters entered*/
    	
    	
    	 ArrayList<String> askForResultreader = new ArrayList<String>();

		 ArrayList<String> keyreader = new ArrayList<String>();
		  String sqlreader = "SELECT * FROM g17db.tblreader;";
			
			
			 askForResultreader.add("ReaderCardNumber");
			  Resultreader= Functions.askFromDB(sqlreader ,keyreader,askForResultreader); 
    	 int size = Result1.size();
    	 int sizereader = Resultreader.size();
    	 if(  !(txtFirstName.getText().equals(""))&&!(txtLastName.getText().equals(""))&&!(txtIDNumber.getText().equals(""))&&!(lblPassword.getText().equals(""))&&!(lblRePassword.getText().equals(""))&&!(cmbPhoneNumber.isArmed())&&!(txtPhoneNumber.getText().equals("")))   {   
    	
    	if(CheckAvailability()&&check) {
    	
    	
    		
    		int numidmax = 0;
   	   	 for(int j=0;j<size;j++) {
   	   		String strmax = Result1.get(j).get(0);
   	   	    strmax = strmax.replaceAll("[^0-9]", "");
   	   	 numidmax= Integer.parseInt(strmax);
   	   		
   	   	 for(int i=0;i<size;i++)   {
   	   		String str = Result1.get(i).get(0);
   	   	    str = str.replaceAll("[^0-9]", "");
   	   	 int num= Integer.parseInt(str);
   	   		if(num>numidmax)
   	   			numidmax=num;
   	   		 
   	   		 
   	   	 }
   	   	 }
   	 	numidmax++;
   	 String strreaderforint = "" + numidmax;
   	 reader.setUserID(strreaderforint);
   	 int cnt1=1;
   	 int sizelist = Resultreader.size();
		String lastmessageid = Resultreader.get(sizelist-1).get(0); ////Finds the largest number in the table
		String newmessageid = "R";
	int	amounutofdigits = lastmessageid.length()-1; //should be 6
	int	numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1) + 1);
		
	int	amountofzero = amounutofdigits - numberlength;
	for(int b=0;b<amountofzero;b++) {
			newmessageid = newmessageid+"0";
		}
	newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1);
	
   	 
   	try {
   	 reader.setUsername(lblUsername.getText()); 
   	 reader.setFirstName(txtFirstName.getText());

     reader.setLastName(txtLastName.getText());

     reader.setID(txtIDNumber.getText()); 
 
     String PhoneNumber= (String) cmbPhoneNumber.getValue();
     PhoneNumber=PhoneNumber+txtPhoneNumber.getText();
     reader.setPhoneNumber(PhoneNumber);  

     reader.setEmail( lblEmail.getText()); 
     
     

     String Password= lblPassword.getText();

     String RePassword= lblRePassword.getText();
    
     if(Password.equals(RePassword))
     {
 	              
     	 reader.setPassword(lblPassword.getText()); 
     	String datenow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    
    	 System.out.println(datenow);
     	 reader.AddNewUser(reader.getUserID(), reader.getUsername(),reader.getPassword(), reader.getID(), reader.getFirstName(), reader.getLastName(), reader.getEmail(), datenow, "Reader", "Offline");
   
     	 reader.AddNewReader(reader.getUserID(),newmessageid , reader.getPhoneNumber(),"Active" , "0", "0", "No");
     	AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","the details have been updated");
     	name=false;
     }
     		 
     	 
     else 	 	AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage", "Passwords do not match"); //System.out.println("Passwords do not match");


	} 
   	catch(NullPointerException exception)
	{
		
		System.out.println(exception);
		
	}
	catch(Exception exception)
	{
		String s =exception.toString().substring(20);
   		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage",s);
		System.out.println(exception);
		
	}
   
  
    
    	}
    	
    	
    	
    	 }
    	  else 		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage", "The all fild must be filled"); //System.out.println("The all fild must be filled");
    	 
    
    }
    /**
     * Initializing cmbPhoneNumber
     * @param none
     * @return void
     */
	  private void SetcmbPhoneNumber() {/**Initializing cmbPhoneNumber*/
			
			ArrayList<String> al = new ArrayList<String>();	
			al.add("050");
			al.add("054");
			al.add("052");
			al.add("053");
			ObservableList<String> CMBXBookSearchlist = FXCollections.observableArrayList(al);	
			cmbPhoneNumber.setItems(CMBXBookSearchlist);  	
			}
    @FXML
    /**
     * check the availablility of this user name(not used already)
     * @param ActionEvent
     * @return void
     */
  void btnCheckAvailability(ActionEvent event) {
    	check=true;
    	name=CheckAvailability();
     }
    boolean CheckAvailability() {/**Checks whether the inserted username is available*/
    	
    	  
 		 ArrayList<String> askForResult = new ArrayList<String>();
 		 ArrayList<String> key = new ArrayList<String>();
 		  String sql = "SELECT * FROM g17db.tbluser;";

 			 System.out.println(1);
 	     askForResult.add("UserID"); 
 	     askForResult.add("Username");

 		  Result1 = Functions.askFromDB(sql ,key,askForResult);
    	
   	 int size = Result1.size();
 	   	 String Username= lblUsername.getText();
 	   	 
 	   	 
 	   for(int i=0;i<size;i++)   {
	   if(( Result1.get(i).get(1).equals(Username))) {
		   AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage", "Username already exists, select a new user name");//  System.out.println("Username already exists, select a new user name");  
		return  false;
 	   	
	   }
	  
	   }
 	  btnSaveReader.setVisible(true);;
 	  return  true;
    }
    
   
	public void start(Stage primaryStage) {
		/*try {
			//BorderPane root = new BorderPane();
			Parent root= FXMLLoader.load(getClass().getResource("/gui/AddNewReaderByLibrarianOrManager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	
	   /**
     * initialization variables and cmbPhoneNumber
     * @param arg0 s
     * @param arg1 s
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	/**initialization variables and cmbPhoneNumber*/
		reader=new Reader(); 	
		SetcmbPhoneNumber();
		btnSaveReader.setVisible(false);;
    }
}



	
	
