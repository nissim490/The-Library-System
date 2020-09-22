package Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class LibraryManagerToolbarController implements Initializable {


    @FXML
    void AddNewBookBTN(ActionEvent event) throws Exception {
    	//(pStage).getScene().getWindow().hide();
    	//pScene.getWindow().hide();
    	//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/AddNewBookByLibrarianOrManager.fxml"), "Add New Book", null);
    	//pStage.close();
    	
    	//pStage.setScene(Pscene);
    	//pStage.show();
    }

    @FXML
    void BorrowedBookListBTN(ActionEvent event) {
    		//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/AddNewReaderByLibrarianOrManager.fxml"), "Reader Registration", null);
    }

    @FXML
    void HomepageBTN(ActionEvent event) {
    		//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/AddNewReaderByLibrarianOrManager.fxml"), "Reader Registration", null);
    }

    @FXML
    void LockingConfirmationBTN(ActionEvent event) {
    		//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/LockingConfirmationByManager.fxml"), "Locking Confirmation", null);
    }

    @FXML
    void LogOutBTN(ActionEvent event) {
    		//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/AddNewReaderByLibrarianOrManager.fxml"), "Reader Registration", null);
    }

    @FXML
    void MessagesBTN(ActionEvent event) {
    		//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/ListOfMessagesByAll.fxml"), "List Of Messages", null);
    }

    @FXML
    void PersonalInformationBTN(ActionEvent event) {
		//Pane root = FXMLLoader.load(getClass().getResource("/gui/PersonalInformationByLibrarianOrManager.fxml"));
		//panelView.getChildren().setAll(root); 
    		//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/PersonalInformationByLibrarianOrManager.fxml"), "Personal Information", null);
    }

    @FXML
    void ReaderRegistrationBTN(ActionEvent event) {
    		//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/AddNewReaderByLibrarianOrManager.fxml"), "Reader Registration", null);
    }

    @FXML
    void ReceiveReturnBookBTN(ActionEvent event) {
    		//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/ReturnABookByLibrarianOrManager.fxml"), "Receive Return Book", null);
    }
	
	
	
	/*
    private BookReturnCallback callback;

    public void setBookReturnCallback(BookReturnCallback callback) {
        this.callback = callback;
    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    

}
