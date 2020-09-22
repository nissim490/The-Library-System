package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class LibrarianToolbarController {

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
    private JFXButton ReceiveReturnBookBTN;

    @FXML
    private JFXButton BorrowedBookListBTN;

    @FXML
    private JFXButton AddNewBookBTN;

    @FXML
    private JFXButton LogOutBTN;

    @FXML
    void AddNewBookBTN(ActionEvent event) {

    }

    @FXML
    void BorrowedBookListBTN(ActionEvent event) {

    }

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

    @FXML
    void ReceiveReturnBookBTN(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert box != null : "fx:id=\"box\" was not injected: check your FXML file 'LibrarianToolbar.fxml'.";
        assert HomepageBTN != null : "fx:id=\"HomepageBTN\" was not injected: check your FXML file 'LibrarianToolbar.fxml'.";
        assert PersonalInformationBTN != null : "fx:id=\"PersonalInformationBTN\" was not injected: check your FXML file 'LibrarianToolbar.fxml'.";
        assert MessagesBTN != null : "fx:id=\"MessagesBTN\" was not injected: check your FXML file 'LibrarianToolbar.fxml'.";
        assert ReaderRegistrationBTN != null : "fx:id=\"ReaderRegistrationBTN\" was not injected: check your FXML file 'LibrarianToolbar.fxml'.";
        assert ReceiveReturnBookBTN != null : "fx:id=\"ReceiveReturnBookBTN\" was not injected: check your FXML file 'LibrarianToolbar.fxml'.";
        assert BorrowedBookListBTN != null : "fx:id=\"BorrowedBookListBTN\" was not injected: check your FXML file 'LibrarianToolbar.fxml'.";
        assert AddNewBookBTN != null : "fx:id=\"AddNewBookBTN\" was not injected: check your FXML file 'LibrarianToolbar.fxml'.";
        assert LogOutBTN != null : "fx:id=\"LogOutBTN\" was not injected: check your FXML file 'LibrarianToolbar.fxml'.";

    }
}
