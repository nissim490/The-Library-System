package Controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.naming.spi.DirStateFactory.Result;

import common.Functions;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import junit.framework.TestFailure;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton;
import entity.User;

public class LoginController extends Application{
	public static final String ICON_IMAGE_LOC = "/files/icon.png";
	  @FXML
	    private StackPane MainStack;///
    @FXML
    private AnchorPane MainAnchor;
	
    @FXML
    private JFXTextField userFilled;

    @FXML
    private JFXPasswordField passwordFilled;

    @FXML
    private JFXButton handleLoginButtonAction;

    @FXML
    private JFXButton handleCancelButtonAction;


    JFXButton yesButton = new JFXButton("Alright");//
 	JFXButton noButton = new JFXButton("No, Please");//
    @FXML
    void handleCancelButtonAction(ActionEvent event) {
    	((Node)event.getSource()).getScene().getWindow().hide();
    	LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/GuestPanel.fxml"), "Login", null);
    }
    /**
     *seting icon on the window
     * @param stage g
     
     */
    public static void setStageIcon(Stage stage) {
        stage.getIcons().add(new Image(ICON_IMAGE_LOC));
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/login.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
		LibraryAssistantUtil.setStageIcon(primaryStage);
	}

	

	 /**
	   *checking if you can login
	   * @param 
	   * @param 
	   * @return void
	   */
	@FXML
    void handleLoginButtonAction(ActionEvent event)  {
		
    	if(!(userFilled.getText().trim().isEmpty() || passwordFilled.getText().trim().isEmpty()))
    	{  
    		try {
    	      String selectSQL = "SELECT * FROM g17DB.tbluser where Username = ? AND Password = ?;";
    	      ArrayList<String> key = new ArrayList<String>();
    	      ArrayList<String> askForResult = new ArrayList<String>();
    	      key.add(userFilled.getText().trim());
    	      key.add(passwordFilled.getText().trim());
    	      askForResult.add("*");
    	       ArrayList<ArrayList<String>> result = Functions.askFromDB(selectSQL, key, askForResult);
    	    		  
    	    	System.out.println("result"+result);         	  	   	  
    	      	if(result.isEmpty()) {
    	      		userFilled.getStyleClass().add("wrong-credentials");
    	      		passwordFilled.getStyleClass().add("wrong-credentials");
    	      		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","This user doesn't exist");     		
    	      		//System.out.println("This user doesn't exist");
    	      	}
    	      	else {

    		        if(result.get(0).get(8).equals("Librarian")&&result.get(0).get(9).equals("Offline"))
    		        {

    		        	try {
    	    	      		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window

    		            	Stage stage = new Stage(); 
    		                    
    		                FXMLLoader loader = new FXMLLoader();
    		                loader.setLocation(getClass().getResource("/gui/LibrarianPanel.fxml"));
    		                Parent root = loader.load();
    		                User.UpdateUserStatus(result.get(0).get(0) ,"Online");
    		                User me = new User(result.get(0).get(0),result.get(0).get(1),result.get(0).get(2),result.get(0).get(3),result.get(0).get(4),result.get(0).get(5),result.get(0).get(6),result.get(0).get(7),
    		                		result.get(0).get(8),result.get(0).get(9));
    		    			LibrarianPanelController LibrarianController = loader.getController();
    		    			//LibrarianController.setUser(result.get(0));
    		    			LibrarianController.setUser(me);
    		                Scene scene = new Scene(root);
    		                stage.setTitle("Home Page - Librarian");
    		                stage.setScene(scene);
    		                stage.show();
    		                setStageIcon(stage);   		                
              
    		    		} catch (Exception e) {
    		    			// TODO Auto-generated catch block
    		    			e.printStackTrace();
    		    		}  
    		        	
    		        }
    		        else if(result.get(0).get(8).equals("Library Manager")&&result.get(0).get(9).equals("Offline"))
    		        {
    		        	//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/ViewWorkerInformationByManager.fxml"), "Main Page", null);
    		        																 
    		        	try {
    	    	      		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window

    		            	Stage stage = new Stage(); 
    		                    
    		                FXMLLoader loader = new FXMLLoader();
    		                loader.setLocation(getClass().getResource("/gui/LibraryManagerPanel.fxml"));
    		                Parent root = loader.load();
    		                User.UpdateUserStatus(result.get(0).get(0) ,"Online");
    		                User me = new User(result.get(0).get(0),result.get(0).get(1),result.get(0).get(2),result.get(0).get(3),result.get(0).get(4),result.get(0).get(5),result.get(0).get(6),result.get(0).get(7),
    		                		result.get(0).get(8),result.get(0).get(9));
    		                LibraryManagerPanelController HomeManagerController = loader.getController();
    		                //LibraryManagerPanelController HomeManagerController = new LibraryManagerPanelController();
    		    			//HomeManagerController.setUser(result.get(0));
    		                HomeManagerController.setUser(me);
    		                Scene scene = new Scene(root);
    		                stage.setTitle("Home Page - Library Manager");
    		                stage.setScene(scene);
    		                stage.show();
    		                setStageIcon(stage);
    		    		} catch (Exception e) {
    		    			// TODO Auto-generated catch block
    		    			e.printStackTrace();
    		    		} 
    		        	
    		        }
    		        else if(result.get(0).get(8).equals("Reader")&&result.get(0).get(9).equals("Offline"))
    		        {
    		        	
    		        	String selectSQL2 = "SELECT * FROM g17DB.tblreader where UserID = ?;";
    		    	    ArrayList<String> key2 = new ArrayList<String>();
    		    	    ArrayList<String> askForResult2 = new ArrayList<String>();
    		    	    key2.add(result.get(0).get(0));
    		    	    askForResult2.add("CardStatus");
    		    	    String CardStatus = Functions.askFromDB(selectSQL2, key2, askForResult2).get(0).get(0);
    		        	if(CardStatus.equals("Locked")) {
    		        		
    		        		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","Your Account Have Been Locked - You Are Banned From Logging in");
    		        		System.out.println("Your Account Have Been Locked - You Are Banned From Logging in");
    		        	}
    		        	else
    		        	{
    		        		try {
    		    	      		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window

        		            	Stage stage = new Stage(); 
        		                    
        		                FXMLLoader loader = new FXMLLoader();
        		                loader.setLocation(getClass().getResource("/gui/ReaderPanel.fxml"));
        		                Parent root = loader.load();
        		                User.UpdateUserStatus(result.get(0).get(0) ,"Online");
        		                User me = new User(result.get(0).get(0),result.get(0).get(1),result.get(0).get(2),result.get(0).get(3),result.get(0).get(4),result.get(0).get(5),result.get(0).get(6),result.get(0).get(7),
        		                		result.get(0).get(8),result.get(0).get(9));
        		                ReaderPanelController HomeReaderController = loader.getController();
        		                //HomeReaderController.setUser(result.get(0));
        		                HomeReaderController.setUser(me,result.get(0));
        		                Scene scene = new Scene(root);
        		                stage.setTitle("Home Page - Reader");
        		                stage.setScene(scene);
        		                stage.show();
        		                setStageIcon(stage);
        		    		} catch (Exception e) {
        		    			// TODO Auto-generated catch block
        		    			e.printStackTrace();
        		    		} 
        		        	
    		        	}
    		        	
    		        	
    		        }

   	    	    	else AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","This user is already logged in");//System.out.println("This user is already logged in");

    	      	//}
    	      	//else System.out.println("user doesnt exist - this shouldn't happen...");
    	      }
    	      userFilled.clear();
    	      passwordFilled.clear();
    		}
    		catch (Exception e) {
    			// TODO Auto-generated catch block
    			System.out.println("");
    		} 
    	}
    	else
    	{
    		userFilled.getStyleClass().add("wrong-credentials");
      		passwordFilled.getStyleClass().add("wrong-credentials");
      		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","You've forgot to enter your login information");     	
    	}
    }
	
	
	
	
	
	
	
	
	
	
	
    /*@FXML
    void handleLoginButtonAction(ActionEvent event) {
    	
    	if(!(userFilled.getText().trim().isEmpty() || passwordFilled.getText().trim().isEmpty()))
    	{
    		  String selectSQL = "SELECT * FROM g17DB.tbluser where Username = ? AND Password = ?;";
    	      ArrayList<String> key = new ArrayList<String>();
    	      ArrayList<String> askForResult = new ArrayList<String>();
    	      key.add(userFilled.getText().trim());
    	      key.add(passwordFilled.getText().trim());
    	      askForResult.add("*");
    	       ArrayList<ArrayList<String>> result = Functions.askFromDB(selectSQL, key, askForResult);
    	    		  
    	    	if(result.size()>0)
    	    	{   
    	    		ArrayList<String> finalList = result.get(0);
    	    		System.out.println("finalList.get(1)"+finalList.get(1));
    	    		System.out.println("userFilled.getText().trim()"+userFilled.getText().trim());
    	    		System.out.println("finalList.get(2)"+finalList.get(2));
    	    		System.out.println("passwordFilled.getText().trim()"+passwordFilled.getText().trim());
    	    		if(finalList.get(1).equals(userFilled.getText().trim()) && finalList.get(2).equals(passwordFilled.getText().trim()))
            	    {
    	    			LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/main3.fxml"), "Main Page", null);
        	      		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
        		       
        	      		 if(result.get(0).get(8).equals("Reader")&&result.get(0).get(9).equals("Offline"))
         		        {
         		        	System.out.println("readeroffline");
         		        	String selectSQL2 = "SELECT * FROM g17DB.tblreader where UserID = ?;";
         		    	    ArrayList<String> key2 = new ArrayList<String>();
         		    	    ArrayList<String> askForResult2 = new ArrayList<String>();
         		    	    key2.add(result.get(0).get(0));
         		    	    askForResult2.add("CardStatus");
         		    	    String CardStatus = Functions.askFromDB(selectSQL2, key2, askForResult2).get(0).get(0);
         		        	if(CardStatus.equals("Locked")) {
         		        		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","Your Account Have Been Locked - You Are Banned From Logging in");
         		        		System.out.println("Your Account Have Been Locked - You Are Banned From Logging in");
         		        	}
         		        	else
         		        	{
         		        		System.out.println("try to login");
         		        		try {
             		            	Stage stage = new Stage(); 
             		                    
             		                FXMLLoader loader = new FXMLLoader();
             		                loader.setLocation(getClass().getResource("/gui/ReaderPanel.fxml"));
             		                Parent root = loader.load();
             		                User.UpdateUserStatus(result.get(0).get(0) ,"Online");
             		                User me = new User(result.get(0).get(0),result.get(0).get(1),result.get(0).get(2),result.get(0).get(3),result.get(0).get(4),result.get(0).get(5),result.get(0).get(6),result.get(0).get(7),
             		                		result.get(0).get(8),result.get(0).get(9));
             		                ReaderPanelController HomeReaderController = loader.getController();
             		                //HomeReaderController.setUser(result.get(0));
             		                HomeReaderController.setUser(me,result.get(0));
             		                Scene scene = new Scene(root);
             		                stage.setTitle("Home Page - Reader");
             		                stage.setScene(scene);
             		                stage.show();
             		                setStageIcon(stage);
             		    		} catch (Exception e) {
             		    			// TODO Auto-generated catch block
             		    			e.printStackTrace();
             		    		} 
             		        	
         		        	}
         		        	
         		        	
         		        }
            	    }
    	    	}
    	     
    	}*/
    	
	
	
	
	
	
	
	
	
   /* @FXML
    void handleLoginButtonAction(ActionEvent event) {
    	
 
    	
    	if(!(userFilled.getText().trim().isEmpty() || passwordFilled.getText().trim().isEmpty()))
    	{    
    	      String selectSQL = "SELECT * FROM g17DB.tbluser where Username = ? AND Password = ?;";
    	      ArrayList<String> key = new ArrayList<String>();
    	      ArrayList<String> askForResult = new ArrayList<String>();
    	      key.add(userFilled.getText().trim());
    	      key.add(passwordFilled.getText().trim());
    	      askForResult.add("*");
    	       ArrayList<ArrayList<String>> result = Functions.askFromDB(selectSQL, key, askForResult);
    	    		  
    	    	System.out.println("result"+result);         	  	   	  
    	      	if(result.isEmpty()) {
    	      		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","user doesnt exist");
    	      		System.out.println("user doesnt exist");
    	      	}
    	      	else {
    	      		//if(result.get(0).get(1).equals(userFilled.getText().trim()))
        	    	//{
    		        
    		        //LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/main3.fxml"), "Main Page", null);
    		        //MainController MainPage = new MainController();
    		        //testnewguiController MainPage = new testnewguiController();
    	    		//Stage primaryStage = new Stage();
    	    		//MainPage.start(primaryStage);
    	      		
    	      		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
    		        if(result.get(0).get(8).equals("Librarian")&&result.get(0).get(9).equals("Offline"))
    		        {
    		        	System.out.println("librarianOffline");
    		        	try {
    		            	Stage stage = new Stage(); 
    		                    
    		                FXMLLoader loader = new FXMLLoader();
    		                loader.setLocation(getClass().getResource("/gui/LibrarianPanel.fxml"));
    		                Parent root = loader.load();
    		                User.UpdateUserStatus(result.get(0).get(0) ,"Online");
    		                User me = new User(result.get(0).get(0),result.get(0).get(1),result.get(0).get(2),result.get(0).get(3),result.get(0).get(4),result.get(0).get(5),result.get(0).get(6),result.get(0).get(7),
    		                		result.get(0).get(8),result.get(0).get(9));
    		    			LibrarianPanelController LibrarianController = loader.getController();
    		    			//LibrarianController.setUser(result.get(0));
    		    			LibrarianController.setUser(me);
    		                Scene scene = new Scene(root);
    		                stage.setTitle("Home Page - Librarian");
    		                stage.setScene(scene);
    		                stage.show();
    		                setStageIcon(stage);   		                
              
    		    		} catch (Exception e) {
    		    			// TODO Auto-generated catch block
    		    			e.printStackTrace();
    		    		} 
    		        	
    		        }
    		        else if(result.get(0).get(8).equals("Library Manager")&&result.get(0).get(9).equals("Offline"))
    		        {
    		        	//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/ViewWorkerInformationByManager.fxml"), "Main Page", null);
    		        	System.out.println("librarymangeroffline");															 
    		        	try {
    		            	Stage stage = new Stage(); 
    		                    
    		                FXMLLoader loader = new FXMLLoader();
    		                loader.setLocation(getClass().getResource("/gui/LibraryManagerPanel.fxml"));
    		                Parent root = loader.load();
    		                User.UpdateUserStatus(result.get(0).get(0) ,"Online");
    		                User me = new User(result.get(0).get(0),result.get(0).get(1),result.get(0).get(2),result.get(0).get(3),result.get(0).get(4),result.get(0).get(5),result.get(0).get(6),result.get(0).get(7),
    		                		result.get(0).get(8),result.get(0).get(9));
    		                LibraryManagerPanelController HomeManagerController = loader.getController();
    		                //LibraryManagerPanelController HomeManagerController = new LibraryManagerPanelController();
    		    			//HomeManagerController.setUser(result.get(0));
    		                HomeManagerController.setUser(me);
    		                Scene scene = new Scene(root);
    		                stage.setTitle("Home Page - Library Manager");
    		                stage.setScene(scene);
    		                stage.show();
    		                setStageIcon(stage);
    		    		} catch (Exception e) {
    		    			// TODO Auto-generated catch block
    		    			e.printStackTrace();
    		    		} 
    		        	
    		        }
    		        else if(result.get(0).get(8).equals("Reader")&&result.get(0).get(9).equals("Offline"))
    		        {
    		        	System.out.println("readeroffline");
    		        	String selectSQL2 = "SELECT * FROM g17DB.tblreader where UserID = ?;";
    		    	    ArrayList<String> key2 = new ArrayList<String>();
    		    	    ArrayList<String> askForResult2 = new ArrayList<String>();
    		    	    key2.add(result.get(0).get(0));
    		    	    askForResult2.add("CardStatus");
    		    	    String CardStatus = Functions.askFromDB(selectSQL2, key2, askForResult2).get(0).get(0);
    		        	if(CardStatus.equals("Locked")) {
    		        		AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","Your Account Have Been Locked - You Are Banned From Logging in");
    		        		System.out.println("Your Account Have Been Locked - You Are Banned From Logging in");
    		        	}
    		        	else
    		        	{
    		        		System.out.println("try to login");
    		        		try {
        		            	Stage stage = new Stage(); 
        		                    
        		                FXMLLoader loader = new FXMLLoader();
        		                loader.setLocation(getClass().getResource("/gui/ReaderPanel.fxml"));
        		                Parent root = loader.load();
        		                User.UpdateUserStatus(result.get(0).get(0) ,"Online");
        		                User me = new User(result.get(0).get(0),result.get(0).get(1),result.get(0).get(2),result.get(0).get(3),result.get(0).get(4),result.get(0).get(5),result.get(0).get(6),result.get(0).get(7),
        		                		result.get(0).get(8),result.get(0).get(9));
        		                ReaderPanelController HomeReaderController = loader.getController();
        		                //HomeReaderController.setUser(result.get(0));
        		                HomeReaderController.setUser(me,result.get(0));
        		                Scene scene = new Scene(root);
        		                stage.setTitle("Home Page - Reader");
        		                stage.setScene(scene);
        		                stage.show();
        		                setStageIcon(stage);
        		    		} catch (Exception e) {
        		    			// TODO Auto-generated catch block
        		    			e.printStackTrace();
        		    		} 
        		        	
    		        	}
    		        	
    		        	
    		        }

   	    	    	else System.out.println("This user is already logged in");//AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","This user is already logged in");
    		       
    	      	//}
    	      	//else System.out.println("user doesnt exist - this shouldn't happen...");
    	      }
    	      	System.out.println("im clear the filleds");
    	      	userFilled.clear();
    	      	passwordFilled.clear();
    	      	try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}*/
    	
    }