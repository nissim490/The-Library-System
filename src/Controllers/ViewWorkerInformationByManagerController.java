package Controllers;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import common.Functions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.util.Arrays;

public class ViewWorkerInformationByManagerController implements Initializable {
	
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
	    private JFXTextField lblWorkerNumber;

	    @FXML
	    private JFXTextField lblOccupation;

	    @FXML
	    private JFXTextField lblJoinedDate;

	    @FXML
	    private JFXTextField lblOragnizationalAffiliation;
	    
	    @FXML
	    private StackPane MainStack;
	    
		@FXML
	    private AnchorPane MainAnchor;//

	    @FXML
	    private JFXComboBox<String> lblPermission;
	    @FXML
	    private JFXButton SavePermission;
	    JFXButton yesButton = new JFXButton("YES, Please");//

	private ArrayList<String> worker = new ArrayList<String>();
	  ArrayList<String> key = new ArrayList<String>();
	  /**
		 * save the changed premissions on click
		 * @param ActionEvent
		 * @return void
		 */
    @FXML
    void SavePermission(ActionEvent event) {/**The function updates the Permission and does input tests*/
    	if(!(worker.get(9).equals(lblPermission.getValue()))) {
    		switch(lblPermission.getValue()) 
            {  
        
              
                case "Library Manager":  
                	  key.add("Library Manager");
    	   		      key.add(worker.get(10));
    	   		   Functions.askFromDB("UPDATE g17DB.tbluser SET Permissions = ? WHERE UserID = ?;" ,key);
    	   		JOptionPane.showMessageDialog(null,"This worker Permissions were saved");
    	   		 //AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Success Massage","This worker Permissions were saved");  
                    break; 
                case "Librarian": 
                	 key.add("Librarian");
    	   		      key.add(worker.get(10));
    	   		   Functions.askFromDB("UPDATE g17DB.tbluser SET Permissions = ? WHERE UserID = ?;" ,key);
    	   		JOptionPane.showMessageDialog(null,"This worker Permissions were saved");
    	   		//AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Success Massage","This worker Permissions were saved");
                    break; 
              
            } 
    	    
    	  
        }
    	key.clear();
    	
    }
	public void start(Stage primaryStage) {
		/*try {
			//BorderPane root = new BorderPane();
			Parent root= FXMLLoader.load(getClass().getResource("ViewWorkerInformationByManager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	/**
	 * Initialize the lblPermission
	 * @param void
	 * @return void
	 */
	  private void SetPermissionComboBox() {/***/
			
			ArrayList<String> al = new ArrayList<String>();	
			
			al.add("Library Manager");
			al.add("Librarian");
			
			ObservableList<String> CMBXBookSearchlist = FXCollections.observableArrayList(al);	
			lblPermission.setItems(CMBXBookSearchlist);  	
			}
		/**
		 * Initialize comobox and window
		 * @param arg0  s
		 * @param arg1 w
		 * 
		 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	/**initialization*/
		SetPermissionComboBox();
    }
	/**
	 * Transfer user information between windows and set the textfild
	 * @param worker f
	 * 
	 */
	public void loadworker(ArrayList<String> worker){/**Transfer user information between windows and set the textfild*/
		this.worker = worker; 
		 lblFirstName.setText(worker.get(0));
		  lblLastName.setText(worker.get(1));
		  lblIDNumber.setText(worker.get(2));
		
		  lblEmail.setText(worker.get(3));
		  lblUsername.setText(worker.get(4));
	      lblPassword.setText(worker.get(5));
		  lblWorkerNumber.setText(worker.get(6));
		  
		  lblJoinedDate.setText(worker.get(11));
		  
		  lblOccupation.setText(worker.get(7));
		  lblOragnizationalAffiliation.setText(worker.get(8));
		  lblPermission.setValue(worker.get(9));
		  
	}
}