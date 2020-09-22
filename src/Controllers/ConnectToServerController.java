package Controllers;

import java.io.IOException;
import java.util.ArrayList;
import ClientPack.ClientHandling;
import ClientPack.MainClientApp;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConnectToServerController extends Application{
	final public static int DEFAULT_PORT = 5555;
	public ArrayList<String> message = new ArrayList<String>();
	public static boolean ConnectionFlag= false;
    @FXML
    private TextField textFieldserverIP;

    @FXML
    private Button btnConnectToServer;

    @FXML
    private Button btnHomePage;

    @FXML
    private TextArea textForStatusInfo;

    
    @FXML
    void homePagePress(ActionEvent event) throws Exception {
    	if(ConnectionFlag == true)
    	{
    		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
    		
    		//HomeGuestController homeguest = new HomeGuestController();
    		//Stage primaryStage = new Stage();
    		//homeguest.start(primaryStage);
    		
    		try {
            	Stage stage = new Stage(); 
                    
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/gui/GuestPanel.fxml"));
                //loader.setLocation(getClass().getResource("/gui/LendingABookByLibrarianOrManager.fxml"));
                
                Parent root = loader.load();
                Scene scene = new Scene(root);
                stage.setTitle("Home Page - Guest");
                stage.setScene(scene);
                stage.show();
                LibraryAssistantUtil.setStageIcon(stage);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
    		
    		
    	}
    }
    
    public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ConnectToServer.fxml"));
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("/gui/UpdateStatus.css").toExternalForm());
		primaryStage.setTitle("Connect To Server");
		primaryStage.setScene(scene);
		primaryStage.show();	
		LibraryAssistantUtil.setStageIcon(primaryStage);
	}
    
    @FXML
    void getConnectbtn(ActionEvent event) {
    	System.out.println("Connect to ip");		
     	String ip = "localhost";
   		if(textFieldserverIP.getText() != "localhost")  ip = textFieldserverIP.getText();
   	    System.out.println("my ip is "+ip);
   	    
   	    
   	    try{
   	    	MainClientApp.ClientOfLibrary = new ClientHandling(ip, DEFAULT_PORT);
   	    	message.add("AskForConnection");
	   	    System.out.println("getConnectbtn send this message: "+message);
	   	    MainClientApp.ClientOfLibrary.handleMessageFromClientUI(message);
	   	    textForStatusInfo.setText("Connection\nSucceeded");
	   	    ConnectionFlag= true;
   	    }
   	    
   	     catch(IOException exception) {
   	    	textForStatusInfo.setText("Connection\nFailed");	
     		ConnectionFlag= false;
   	     }

    }

}
