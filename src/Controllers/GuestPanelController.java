package Controllers;



import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Node;

public class GuestPanelController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane MainAnchor;

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private AnchorPane AnchorPaneTabs;

    @FXML
    private JFXButton LoginBtn;

    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }
    /**
  	 * opening the loging window at click
  	 * @param ActionEvent
  	 * @return void
  	 */
    @FXML
    void LoginBtn(ActionEvent event) {
    	//((Node)event.getSource()).getScene().getWindow().hide();
    	getStage().close();
    	LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/login.fxml"), "Log in", null);
    }

    @FXML
    void handleAboutMenu(ActionEvent event) {

    }

    @FXML
    void handleMenuClose(ActionEvent event) {

    }

    @FXML
    void handleMenuSettings(ActionEvent event) {

    }

    /**
  	 * initilizing this window
  	 * @param void
  	 * @return void
  	 */
    @FXML
    void initialize() {
    	AnchorPane root;
    	try {
			root = FXMLLoader.load(getClass().getResource("/gui/GuestHomepage.fxml"));
			AnchorPaneTabs.getChildren().setAll(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
 

}
