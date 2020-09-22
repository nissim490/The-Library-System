package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ServerPack.LibraryServer;
import ServerPack.automation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ServerGuiController{
	public LibraryServer server = null;
	final public static int DEFAULT_PORT = 5555;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnStop;

    @FXML
    private TextArea textConsole;


@FXML
void playFunc(ActionEvent event) {	
	try 
    {   
      int port = DEFAULT_PORT; //Set port to 5555   
      server = new LibraryServer(port);
      server.listen(); //Start listening for connections   
      textConsole.setText("Server Running and listen for clients");
      //Receives a number of seconds per run automation->
      Thread auto = new Thread(new automation(3600));//A thread that runs parallel to the program and is responsible for the automation//
      auto.start();
    }
	  catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }

}

    @FXML
    void stopFunc(ActionEvent event) {
	      textConsole.setText("Server Stop and don't listen for clients");
	      System.exit(0);
    }

	
	 public void start(Stage primaryStage) throws Exception {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerGui.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("Server");
			primaryStage.setScene(scene);
			primaryStage.show();	
			LibraryAssistantUtil.setStageIcon(primaryStage);
		}

}
