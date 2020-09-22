package ServerPack;

import Controllers.ServerGuiController;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainServerApp extends Application{

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        launch(args);	
	}

	@Override
	public void start(Stage arg0) throws Exception {	
	 // create StudentFrame
		ServerGuiController aFrame = new ServerGuiController();
		aFrame.start(arg0);	
	}
		



}