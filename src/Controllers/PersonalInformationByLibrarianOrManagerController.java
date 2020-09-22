package Controllers;


import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import common.Functions;
import entity.Reader;
import entity.User;
import entity.Worker;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class PersonalInformationByLibrarianOrManagerController extends Application implements Initializable {
	
    @FXML
    private ResourceBundle resources;
 
    @FXML
    private URL location;
    
    @FXML
    private AnchorPane PersonalInfomationAnchorPane;

    @FXML
    private JFXTextField lblFirstName;

    @FXML
    private JFXTextField lblLastName;

    @FXML
    private JFXTextField lblIDNumber;

    @FXML
    private AnchorPane MainAnchor;//
    @FXML
    private StackPane MainStack;///

    @FXML
    private JFXTextField lblEmail;

    @FXML
    private JFXTextField lblUsername;

    @FXML
    private JFXTextField lblPassword;

    @FXML
    private JFXTextField lblWorkerNumber;

    @FXML
    private JFXTextField lblOccupation;

    @FXML
    private JFXTextField lblJoinedDate;

    @FXML
    private JFXTextField lblOragnizationalAffiliation;

    @FXML
    private JFXButton SaveChangesBtb;
    @FXML
    private JFXButton edit;
    JFXButton yesButton = new JFXButton("YES, Please");//
	JFXButton noButton = new JFXButton("No, Please");//
    private Worker worker;
    private Worker workertemp;
    private String userid;
    User UserInfo;
    private ArrayList<ArrayList<String>> Result1;
	private ArrayList<ArrayList<String>> InformationManager;
	/**
  	 * giving the option to be able to edit the fields
  	 * @param 
  	 * @param ActionEvent
  	 * @return void
  	 */
	  @FXML
	    void editinformtion(ActionEvent event) {/**By pressing the edit button, you can edit some of the data*/
		  
		  
		  lblEmail.setEditable(true); 	
		  lblFirstName.setEditable(true); 
		  lblLastName.setEditable(true); 
		  lblPassword.setEditable(true);
		 
	 
		 
	

	    }
		/**
	  	 * save the edited information
	  	 * @param 
	  	 * @param ActionEvent
	  	 * @return void
	  	 */
    @FXML
    void SaveChangesBtb(ActionEvent event) {/**By clicking the Changes button,  check the values entered, update the data in the DB  at the end of the editing option*/
 
    	
	
    
    	 try {
    	
			boolean caenge=false;
	
			
			
			
			
			if( !(lblEmail.getText().equals(""))) {
       
        workertemp.setEmail(lblEmail.getText());
    	if(!(worker.getEmail().equals(workertemp.getEmail()))) {
        worker.UpdateUserEmail(userid, workertemp.getEmail());
        worker.setEmail(lblEmail.getText());
    	caenge=true;
    	}
			}
      
		
			
			if( !(lblPassword.getText().equals(""))) {
				 workertemp.setPassword(lblPassword.getText());
				 if(!(worker.getPassword().equals(workertemp.getPassword()))) {
		    			worker.UpdateUserPassword(userid, workertemp.getPassword());  
				 	caenge=true;
				 	 worker.setPassword(lblPassword.getText());
				 }
				
					}
		
       
			
			if( !(lblFirstName.getText().equals(""))) {
				workertemp.setFirstName( lblFirstName.getText());
	    		if(!(worker.getFirstName().equals(workertemp.getFirstName()))) {
	    			worker.UpdateUserFirstName(userid, workertemp.getFirstName());
	    	 	caenge=true;
	    		worker.setFirstName( lblFirstName.getText());
					}
			}
			if(!( lblLastName.getText().equals(""))) {
		        workertemp.setLastName( lblLastName.getText());
					if(!(worker.getLastName().equals(workertemp.getLastName()))) {
		    			worker.UpdateUserLastName(userid, workertemp.getLastName());
				 	caenge=true;
				 	  worker.setLastName( lblLastName.getText());
					}
			}
			if(caenge)
				AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), " Massage","Details updated");
        if(!caenge)
        	// System.out.println("  Do not try to save if they do not change anything or leave blank fields");
   		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","Do not try to save if they do not change anything or leave blank fields");
         
			///Do not leave blank fields
    		} 	
    	 catch (NullPointerException exception) {
    		 System.out.println("Do not leave blank fields");
    		
 		}
    	
    		catch (Exception exception) {
    			String s =exception.toString().substring(20);
		   		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage",s);
    			System.out.println(exception);
    		}
    	 
    	
		  lblEmail.setEditable(false); 	
		  lblFirstName.setEditable(false); 
		  lblLastName.setEditable(false); 
		  lblPassword.setEditable(false);
		
    	    }
    	
 
    

	public void start(Stage primaryStage) {
		/*try {
			//BorderPane root = new BorderPane();
			Parent root= FXMLLoader.load(getClass().getResource("PersonalInformationByLibrarianOrManager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	/**
  	 * initiliztion this window
  	 * @param arg0 f
  	 * @param arg1 g
  	 * 
  	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	/**Initialize*/
		worker=new Worker(); 
		workertemp=new Worker(); 
		
		
    }
	
	/**
  	 * transfering information to this window
  	 * @param MyUserData f
  	 
  	 */
	public void loadInformationManager(User MyUserData) throws Exception{/**The function accepts the user data, updates the  textfild and brings in relevant data from the DB*/
		
		this.UserInfo=MyUserData;
		userid="";
		 ArrayList<String> askForResult = new ArrayList<String>();
 		 ArrayList<String> key = new ArrayList<String>();
 		  String sql = "SELECT * FROM g17DB.tbluser ;";

 		
 			askForResult.add("UserID");
 			askForResult.add("Username"); 
 			askForResult.add("Password");
 			askForResult.add("ID");
 			askForResult.add("FirstName");
 			askForResult.add("LastName");
 			askForResult.add("Email");
 			askForResult.add("AddedDate");
 			askForResult.add("Permissions");
 			askForResult.add("UserStatus");
 			
 	   

 	    this.InformationManager =  Functions.askFromDB(sql ,key,askForResult);
 	    int size=InformationManager.size();
 	 for(int i=0;i<size;i++)   {
 	   if(( InformationManager.get(i).get(1).equals(MyUserData.getUsername()))) {
 			  
 			   
 			   
 			  lblFirstName.setText(InformationManager.get(i).get(4).toString());
 			
 			      lblLastName.setText(InformationManager.get(i).get(5).toString());
 			  
 			  lblIDNumber.setText(InformationManager.get(i).get(3).toString());
 			
 			   lblEmail.setText(InformationManager.get(i).get(6).toString());
 			 
 			  userid=  InformationManager.get(i).get(0).toString();
 			    lblUsername.setText(InformationManager.get(i).get(1).toString());

 			    lblPassword.setText(InformationManager.get(i).get(2).toString());
 			   lblJoinedDate.setText(InformationManager.get(i).get(7).toString());
 			   try {
 				  worker.setFirstName(InformationManager.get(i).get(4).toString());
 			
 	 			   worker.setLastName(InformationManager.get(i).get(5).toString());
 	 			  worker.setEmail(InformationManager.get(i).get(6).toString());
 	 			  worker.setPassword(InformationManager.get(i).get(2).toString());
 	 			 //  worker.setAddedDate(InformationManager.get(i).get(7).toString());
 				} catch (Exception exception) {
 					
 					System.out.println(exception);
 				} 
 			 
			    
 		
 	 	   	
 		   }
 	
		
		
		
		
	
 	  }
 	 
 	 
 	 
 	
 	 
 	  ArrayList<String> askForResultworker = new ArrayList<String>();
		 ArrayList<String> keyworker = new ArrayList<String>();
		  String sqlworker = "SELECT * FROM g17DB.tblworker ;";

			 System.out.println(1);
			 askForResultworker.add("WorkerID");
			 askForResultworker.add("UserID");
			 askForResultworker.add("Occupation");
			 askForResultworker.add("OrganizationalAffilation");
			
			 System.out.println(userid);
		      Result1=  Functions.askFromDB(sqlworker ,keyworker,askForResultworker);
		      int sizeworker=Result1.size();
		  	 for(int i=0;i<sizeworker;i++)   {
		  	   if(( Result1.get(i).get(1).equals(userid))) {
		  	
	     lblWorkerNumber.setText(Result1.get(i).get(0).toString());

		   lblOccupation.setText(Result1.get(i).get(2).toString());
		 
		   lblOragnizationalAffiliation.setText(Result1.get(i).get(3).toString());
		
	      
	    
	

		  	   }
 	  }
}
}