package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controllers.ReaderHomepageController.Message;
import common.Functions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ViewMessagesByAllController implements Initializable{
	
	private String getMessageID;
	private String userID;
	private Scene cont;
	private ListOfMessagesByAllController lom;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane PaneMain;

    @FXML
    private JFXTextField txtSendFrom;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextArea txtContent;

    @FXML
    private JFXButton BackBtn;

    /**
     * returning to the list of messages window.
     * @param event - action
     * @throws IOException
     * @return void
     */
    @FXML
    void BackBtn(ActionEvent event) throws IOException {
    	ArrayList<String> key = new ArrayList<String>();
    	key.clear();
	    key.add("Read");	       
	    key.add(this.getMessageID);
	  	Functions.askFromDB("UPDATE g17DB.tblmessages SET Status = ? WHERE MessageID = ?;" ,key);
	  	key.clear();
	  	 ArrayList<String> askForResult = new ArrayList<String>();
	  	String sql;
	  	sql = "SELECT * FROM g17db.tbluser WHERE UserID= ?;";
	  	askForResult.add("Permissions");
    	key.add(userID);
    	//key.add("312576101");
    	ArrayList<ArrayList<String>> resultUser = Functions.askFromDB(sql ,key,askForResult);
	  	
	  
		lom.SetUser(userID,resultUser.get(0).get(0));

    	Stage stage = (Stage)BackBtn.getScene().getWindow();
    	stage.close();

    }

    @FXML
    void initialize() {
    	
    }
    /**
     * transfering formation
     * @param controller e
    
     */
    public void SetScene(Scene controller) {
    	this.cont=controller;
    }
    
    
     /**
      *  show the Message on the screen.
      * @param m -Message
      */
    public void showMessage(Message m) {
    	this.getMessageID=m.getMessageID();
    	txtDate.setText(m.getArrivalDate());
    	txtSendFrom.setText(m.getFrom());
    	txtContent.setText(m.getContent());
    	this.userID=m.getUserID();
    }

    /**
     * Initialize the screen
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtContent.setEditable(false);		
	}

	/**
	 * 
	 * @param listOfMessagesByAllController - the controller from list Of Messages By All Controller
	 */
	public void sendController(ListOfMessagesByAllController listOfMessagesByAllController) {
		this.lom=listOfMessagesByAllController;		
	}
}
