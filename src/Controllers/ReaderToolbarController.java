package Controllers;


import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.VBox;

public class ReaderToolbarController implements Initializable {


	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private VBox box;

	    @FXML
	    private JFXButton HomepageBTN;

	    @FXML
	    private JFXButton PersonalInformationBTN;

	    @FXML
	    private JFXButton MessagesBTN;

	    @FXML
	    private JFXButton ReaderRegistrationBTN;

	    @FXML
	    private JFXButton LogOutBTN;

	    @FXML
	    void HomepageBTN(ActionEvent event) {

	    }

	    @FXML
	    void LogOutBTN(ActionEvent event) {

	    }

	    @FXML
	    void MessagesBTN(ActionEvent event) {

	    }

	    @FXML
	    void PersonalInformationBTN(ActionEvent event) {

	    }

	    @FXML
	    void ReaderRegistrationBTN(ActionEvent event) {

	    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    

}
