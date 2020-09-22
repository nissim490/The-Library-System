package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import common.Functions;
import entity.Reader;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class PersonalInformationByReaderController {
	@FXML
    private AnchorPane MainAnchor;//
    @FXML
    private StackPane MainStack;///
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
    private JFXComboBox<String> CBXPhone;

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
    private JFXTextField lblCardStatus;
    @FXML
    private Button edit;
    @FXML
    private JFXButton SaveChanges;
    ArrayList<String> InformationManager = new ArrayList<String>();
	 private ArrayList<ArrayList<String>> Result1;
   private Reader reader;
   private String readerid;
   private Reader readertemp;
   JFXButton yesButton = new JFXButton("YES, Please");//
	JFXButton noButton = new JFXButton("No, Please");//
	 /**
     * By clicking the Changes button,  check the values entered, update the data in the DB  at the end of the editing option
     * @param ActionEvent
     * @return void
     */
   @FXML
   void SaveChanges(ActionEvent event) {/**By clicking the Changes button,  check the values entered, update the data in the DB  at the end of the editing option*/
 
		boolean caenge=false;
    	   	 try {
    	   	
    			
    		
    				
    		
    				
    				if( !(lblEmail.getText().equals(""))) {
    				
    	      
    					readertemp.setEmail(lblEmail.getText());
    					if(!(reader.getEmail().equals(readertemp.getEmail()))) {
    						caenge=true;
    						reader.UpdateUserEmail(readerid, readertemp.getEmail());
    						reader.setEmail(lblEmail.getText());
    	   	} 	 InformationManager.set(6, readertemp.getEmail());
    	   
    				}
    	     
    	   	
    				
    				 
    				if( !(lblPhoneNumber.getText().equals(""))) {
    					 String PhoneNumber= (String) CBXPhone.getValue();
    			  	     PhoneNumber=PhoneNumber+lblPhoneNumber.getText();
    				
    					readertemp.setPhoneNumber(PhoneNumber);
    					
    					if(!(reader.getPhoneNumber().equals(readertemp.getPhoneNumber()))) {
    						reader.UpdateReaderPhoneNumberByUserID(readerid, readertemp.getPhoneNumber());
    						caenge=true;
    						reader.setPhoneNumber(PhoneNumber);
    					}
    						}
    				 System.out.println(caenge);
    				
    				if( !(lblPassword.getText().equals(""))) {
    					readertemp.setPassword(lblPassword.getText());
    					
    					 if(!(reader.getPassword().equals(readertemp.getPassword()))) {
    						 reader.setPassword(lblPassword.getText());
    			    			reader.UpdateUserPassword(readerid, readertemp.getPassword());  
    					InformationManager.set(2, readertemp.getPassword());
    					 	caenge=true;
    					 }
    						}
    				
    				
    				if( !(lblFirstName.getText().equals(""))) {
    					readertemp.setFirstName( lblFirstName.getText());
    					
    		    		if(!(reader.getFirstName().equals(readertemp.getFirstName()))) {
    		    			reader.setFirstName( lblFirstName.getText());
    		    			reader.UpdateUserFirstName(readerid, readertemp.getFirstName());
    		    		InformationManager.set(4, readertemp.getFirstName());
    		    	 	caenge=true;
    		    		}
    						}
    				 System.out.println(caenge);
    				 
    				if(!( lblLastName.getText().equals(""))) {
    			        readertemp.setLastName( lblLastName.getText());
    						if(!(reader.getLastName().equals(readertemp.getLastName()))) {
    			    			reader.UpdateUserLastName(readerid, readertemp.getLastName());
    			    			 reader.setLastName( lblLastName.getText());
    					 	caenge=true;
    						}
    						InformationManager.set(5, readertemp.getLastName());
    						}
    				if(caenge)
    		 			AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), " Massage","Details updated");
                if(!caenge)
                	// System.out.println("  Do not try to save if they do not change anything or leave blank fields");
    	   		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","Do not try to save if they do not change anything or leave blank fields");
    			///Do not leave blank fields 
    	           if(!caenge)
    	           	 System.out.println("  Do not try to save if they do not change anything or leave blank fields");
    	        
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
    	   	 
    	   	     lblPhoneNumber.setEditable(false);
    			  lblEmail.setEditable(false); 	
    			  lblFirstName.setEditable(false); 
    			  lblLastName.setEditable(false); 
    			  lblPassword.setEditable(false);
    			
    			  ReaderPanelController HomeLibrarianController = new ReaderPanelController();
    				HomeLibrarianController.setUser(InformationManager);
    				//transfer information between windows but without opening anything
    	    	
    	    	
    	    	
    	    	
    	    }
   /**
    * init combobox
    * @param void
    * @return void
    */
   private void SetcmbPhoneNumber() {/**Initialize the CBXPhone*/
		
		ArrayList<String> al = new ArrayList<String>();	
		al.add("050");
		al.add("054");
		al.add("052");
		al.add("053");
		ObservableList<String> CMBXPhonelist = FXCollections.observableArrayList(al);	
		CBXPhone.setItems(CMBXPhonelist);  	
		}
   /**
    * By pressing the edit button, you can edit some of the data
    * @param ActionEvent
    * @return void
    */
    	    @FXML
    	    void editac(ActionEvent event) {/***/
    	    	 
    			  lblEmail.setEditable(true); 	 
    			  lblFirstName.setEditable(true); 
    			  lblLastName.setEditable(true); 
    			  lblPassword.setEditable(true);
    			  lblPhoneNumber.setEditable(true);
    	    }
    	    void initialize() {	///Initialize
    				
    		
    	    }
    	    /**
    	     * The function accepts the user data, updates the  textfild
    	     * @param User f
    	    
    	     */
    		public void loadInformationManager(ArrayList<String> User) throws Exception{
    			SetcmbPhoneNumber();
    			readerid="";
    			this.InformationManager = User;
    			reader=new Reader(); 
    			readertemp=new Reader(); 
    			
    	 			  lblFirstName.setText(InformationManager.get(4).toString());
    	 			
    	 			      lblLastName.setText(InformationManager.get(5).toString());
    	 			  
    	 			  lblIDNumber.setText(InformationManager.get(3).toString());
    	 			
    	 			   lblEmail.setText(InformationManager.get(6).toString());
    	 			 
    	 			  readerid=  InformationManager.get(0).toString();
    	 			    lblUsername.setText(InformationManager.get(1).toString());

    	 			    lblPassword.setText(InformationManager.get(2).toString());
    	 			//   lblJoinedDate.setText(InformationManager.get(7).toString());
    	 			   try {
    	 				  reader.setFirstName(InformationManager.get(4).toString());
    	 				
    	 				 reader.setLastName(InformationManager.get(5).toString());
    	 				reader.setEmail(InformationManager.get(6).toString());
    	 				reader.setPassword(InformationManager.get(2).toString());
    	 	 			 //  reader.setAddedDate(InformationManager.get(7).toString());
    	 				} catch (Exception exception) {
    	 					
    	 					System.out.println(exception);
    	 				} 
    	 			 
    				    
    	 		
    	 	 	   	
    			
    			
    			

    		
    	 	
    	 	 
    	 	 
    	 	 
    	 	 
    	 	 
    	 	  ArrayList<String> askForResultreader = new ArrayList<String>();
    			 ArrayList<String> keyreader = new ArrayList<String>();
    			  String sqlreader = "SELECT * FROM g17DB.tblreader ;";

    				 
    			  askForResultreader.add("ReaderCardNumber");	
    			  askForResultreader.add("UserID");
    			  askForResultreader.add("PhoneNumber");
    			  askForResultreader.add("cardStatus");
    		     
    				
    			      Result1=  Functions.askFromDB(sqlreader ,keyreader,askForResultreader);
    			      int sizereader=Result1.size();
    			  	 for(int i=0;i<sizereader;i++)   {
    			  	   if(( Result1.get(i).get(1).equals(readerid))) {
    			  		 System.out.println(Result1.get(i).get(1));
    			  		 
    			  		 
    			 	 CBXPhone.setValue((String) Result1.get(i).get(2).subSequence(0, 3));
    			  	    
    			  		
    		     lblPhoneNumber.setText((String) Result1.get(i).get(2).subSequence(3, 10));

    			   lblCardNumber.setText(Result1.get(i).get(0).toString());

    			   lblCardStatus.setText(Result1.get(i).get(3).toString());
    			 
    			  
    			  
    		     

    		     
    		    
    		

    				   try {
    					   reader.setPhoneNumber(Result1.get(i).get(2).toString());
    					} catch (Exception exception) {
    						
    					  
    						System.out.println(exception);
    					} 
    			  	   }
    	 	  }
    	}
    


}
