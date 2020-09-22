package Controllers;


import com.jfoenix.controls.JFXComboBox;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import javafx.scene.Node;
import java.util.ArrayList;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;


import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import entity.User;
import javafx.scene.input.KeyEvent;
import common.Functions;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.event.EventHandler;


public class LibrarianPanelController {
	
	private int drawerstatus;
	private int logstatus = 0;
	private int homestatus = 0;
	private int personalstatus = 0;
	private int messagestatus = 0;
	private int readerstatus = 0;
	private int recievestatus = 0;
	private int borrowstatus = 0;
	private int addstatus = 0;
	
	private User MyUserData;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane MainAnchor;

    @FXML
    private StackPane rootPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private AnchorPane AnchorPaneTabs;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private Label ShowUsername;

 
    /**
   	 *init drawer
   	 * @param 
   	 * @param void
   	 * @return void
   	 */
    @FXML
    void initialize() {
    	
		  
    	initDrawer();

    }
    /**
   	 * geting this window stage
   	 * @param
   	 * @param 
   	 * @return Stage
   	 */
    private Stage getStage() {
        return (Stage) rootPane.getScene().getWindow();
    }
 	 /** change this window
   	 
   	 */
    public void changeAnchor() {
    	AnchorPane root;
    	try {
    		
    		root = FXMLLoader.load(getClass().getResource("/gui/ListOfBorrowedBooks.fxml"));
			this.AnchorPaneTabs.getChildren().setAll(root); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//MainPage.getAnchor().getChildren().setAll(root);;
    	
    	
    	//return AnchorPaneTabs;
    }
    
   /* public void start(Stage primaryStage) throws Exception {
    	//pStage = primaryStage;
    	//ToolbarController tb = new ToolbarController();
    	//tb.loadStage((Stage) rootPane.getScene().getWindow());
    	//this.pStage = primaryStage;
 		Parent root = FXMLLoader.load(getClass().getResource("/gui/main.fxml"));
 		pScene = new Scene(root);
 		pScene.getStylesheets().add(getClass().getResource("/gui/dark-theme.css").toExternalForm());
 		primaryStage.setTitle("Main");
 		primaryStage.setScene(pScene);
 		primaryStage.show();
 		LibraryAssistantUtil.setStageIcon(primaryStage);
 		
 	}*/
	 /** transfer information to this window
   	 * @param thisUserData d
   	 *
   	 */
    public void setUser(User thisUserData) {
    	this.MyUserData = thisUserData;
    	ShowUsername.setText(MyUserData.getUsername());
    	
     	Parent root;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/gui/LibrarianHomepage.fxml"));
			root = loader.load();
			LibrarianHomepageController HomepageLibrarian = loader.getController();
			//User me = new User(MyUserData.getUserID(),MyUserData.getUsername(),MyUserData.getPassword(),MyUserData.getID(),MyUserData.getFirstName()
			//		,MyUserData.getLastName(),MyUserData.getEmail(),MyUserData.getAddedDate(),MyUserData.getPermissions(),MyUserData.getUserStatus());
			//HomepageManager.setUser(me);
			HomepageLibrarian.setUser(MyUserData);
			
			AnchorPaneTabs.getChildren().setAll(root); 

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	
    }
	 /** init drawer
   	 * @param 
   	 * @param 
   	 * @return void
   	 */
private void initDrawer() {
    	
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/LibrarianToolbar.fxml"));
            VBox toolbar = loader.load();
            drawer.setSidePane(toolbar);
            
            for(Node node : toolbar.getChildren()){
            	if(node.getAccessibleText() != null){
            		
            		MainAnchor.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            			final KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.DIGIT1,KeyCombination.CONTROL_DOWN);
            			final KeyCombination keyComb2 = new KeyCodeCombination(KeyCode.DIGIT2,KeyCombination.CONTROL_DOWN);
            			final KeyCombination keyComb3 = new KeyCodeCombination(KeyCode.DIGIT3,KeyCombination.CONTROL_DOWN);
            			final KeyCombination keyComb4 = new KeyCodeCombination(KeyCode.DIGIT4,KeyCombination.CONTROL_DOWN);
            			final KeyCombination keyComb5 = new KeyCodeCombination(KeyCode.DIGIT5,KeyCombination.CONTROL_DOWN);
            			final KeyCombination keyComb6 = new KeyCodeCombination(KeyCode.DIGIT6,KeyCombination.CONTROL_DOWN);
            			final KeyCombination keyComb7 = new KeyCodeCombination(KeyCode.DIGIT7,KeyCombination.CONTROL_DOWN);
            			final KeyCombination keyComb8 = new KeyCodeCombination(KeyCode.DIGIT8,KeyCombination.CONTROL_DOWN);
            			//final KeyCombination keyComb9 = new KeyCodeCombination(KeyCode.DIGIT9,KeyCombination.CONTROL_DOWN);
                        public void handle(KeyEvent ke) {
                         AnchorPane root;
                         
                         if(drawerstatus==1) {
                        	 if(keyComb1.match(ke)) {
                        		 for(Node node3 : toolbar.getChildren()){
                       				if(node3.getAccessibleText().equals("Homepage")) {
                       					node3.setStyle("-fx-border-color: #FFFF8D");
                       				}
                       				else {
                       					node3.setStyle("-fx-border-color: transparent"); 
                       				}
                     				  
                     			 }
                        		 try {
          							ke.consume();
          							if(homestatus%8==0) {
          								homestatus++;
          								FXMLLoader loader2 = new FXMLLoader();
            							loader2.setLocation(getClass().getResource("/gui/LibrarianHomepage.fxml"));
            							root = loader2.load(); 
            							LibrarianHomepageController HomepageManager = loader2.getController();
            							//User me = new User(MyUserData.getUserID(),MyUserData.getUsername(),MyUserData.getPassword(),MyUserData.getID(),MyUserData.getFirstName()
            							//		,MyUserData.getLastName(),MyUserData.getEmail(),MyUserData.getAddedDate(),MyUserData.getPermissions(),MyUserData.getUserStatus());
            							//HomepageManager.setUser(me);
            							HomepageManager.setUser(MyUserData);
            							
            							AnchorPaneTabs.getChildren().setAll(root); 
          							}
          							else {
          								homestatus++;
          							}
          							
          							
          						} catch (IOException e2) {
          							// TODO Auto-generated catch block
          							e2.printStackTrace();
          						}
                        	 }
                        	 
             					
                        	
             				
             						if(keyComb2.match(ke)) {
             							for(Node node3 : toolbar.getChildren()){
                               				if(node3.getAccessibleText().equals("PersonalInformation")) {
                               					node3.setStyle("-fx-border-color: #FFFF8D");
                               				}
                               				else {
                               					node3.setStyle("-fx-border-color: transparent"); 
                               				}
                             				  
                             			 }
             							try {
                     						ke.consume();
                     						if(personalstatus%8==0) {
                     							personalstatus++;
                     							FXMLLoader loader2 = new FXMLLoader();
                     							loader2.setLocation(getClass().getResource("/gui/PersonalInformationByLibrarian.fxml"));
                     							root = loader2.load();
                     							PersonalInformationByLibrarianOrManagerController listOfBorrow = loader2.getController();
                     							try {
                     								listOfBorrow.loadInformationManager(MyUserData);
                     								AnchorPaneTabs.getChildren().setAll(root); 
                     							} catch (Exception e1) {
                     								// TODO Auto-generated catch block
                     								e1.printStackTrace();
                     							}
                     							
                     						}
                     						personalstatus++;
             							
             							
             							
             						} catch (IOException e1) {
             							// TODO Auto-generated catch block
             							e1.printStackTrace();
             						}//transfer information between windows from the toolbar+opening them*/
                               	 }
             					
             					
             							
             					if(keyComb3.match(ke)) {
             						for(Node node3 : toolbar.getChildren()){
                           				if(node3.getAccessibleText().equals("Messages")) {
                           					node3.setStyle("-fx-border-color: #FFFF8D");
                           				}
                           				else {
                           					node3.setStyle("-fx-border-color: transparent"); 
                           				}
                         				  
                         			 }
             						try {
                 						ke.consume();
                 						if(messagestatus%8==0) {
                 							messagestatus++;
                 							FXMLLoader loader2 = new FXMLLoader();
                     						loader2.setLocation(getClass().getResource("/gui/ListOfMessagesByAll.fxml"));
                     						root = loader2.load();
                     						ListOfMessagesByAllController listOfBorrow = loader2.getController();
                     						listOfBorrow.SetUser(MyUserData.getUserID(),MyUserData.getPermissions());
                     						
                     						AnchorPaneTabs.getChildren().setAll(root); 
                 							
                 						}
                 						messagestatus++;
                 						

                 					} catch (IOException e2) {
                 						// TODO Auto-generated catch block
                 						e2.printStackTrace();
                 					}
                           	 }
             					
             					
             					if(keyComb4.match(ke)) {
             						for(Node node3 : toolbar.getChildren()){
                           				if(node3.getAccessibleText().equals("ReaderRegistration")) {
                           					node3.setStyle("-fx-border-color: #FFFF8D");
                           				}
                           				else {
                           					node3.setStyle("-fx-border-color: transparent"); 
                           				}
                         				  
                         			 }
             						try {
                 						ke.consume();
                 						if(readerstatus%8==0) {
                 							readerstatus++;
                 							root = FXMLLoader.load(getClass().getResource("/gui/AddNewReaderByLibrarianOrManager.fxml"));
                     						AnchorPaneTabs.getChildren().setAll(root); 
                 							
                 						}readerstatus++;
                 						
                 					}
                 					catch(IOException ex){
                 						
                 					}
                           	 }
             					
             					
             					
             					if(keyComb5.match(ke)) {
             						for(Node node3 : toolbar.getChildren()){
                           				if(node3.getAccessibleText().equals("ReceiveReturnBook")) {
                           					node3.setStyle("-fx-border-color: #FFFF8D");
                           				}
                           				else {
                           					node3.setStyle("-fx-border-color: transparent"); 
                           				}
                         				  
                         			 }
             						try {
                 						ke.consume();
                 						if(recievestatus%8==0) {
                 							recievestatus++;
                 							FXMLLoader loader22 = new FXMLLoader();
                     						loader22.setLocation((getClass().getResource("/gui/ReturnABookByLibrarianOrManager.fxml")));
                     						root=loader22.load();
                     						ReturnABookByLibrarianOrManagerController r= loader22.getController();
                     						r.SetUser(MyUserData.getUserID());
                     						AnchorPaneTabs.getChildren().setAll(root); 
                 							
                 						}recievestatus++;
                 						
                 					}
                 					catch(IOException ex){
                 						ex.printStackTrace();
                 					}
                           	 }
             					
             					
             			
             					if(keyComb6.match(ke)) {
             						for(Node node3 : toolbar.getChildren()){
                           				if(node3.getAccessibleText().equals("BorrowedBookList")) {
                           					node3.setStyle("-fx-border-color: #FFFF8D");
                           				}
                           				else {
                           					node3.setStyle("-fx-border-color: transparent"); 
                           				}
                         				  
                         			 }
             						ke.consume();
                 					if(borrowstatus%8==0) {
                 						borrowstatus++;
                 						changeAnchor();
             							
                 					}borrowstatus++;
                           	 }
             					
             					
             				
             					if(keyComb7.match(ke)) {
             						for(Node node3 : toolbar.getChildren()){
                           				if(node3.getAccessibleText().equals("AddNewBook")) {
                           					node3.setStyle("-fx-border-color: #FFFF8D");
                           				}
                           				else {
                           					node3.setStyle("-fx-border-color: transparent"); 
                           				}
                         				  
                         			 }
             						try {
                 						
             							ke.consume();
             							if(addstatus%8==0) {
             								addstatus++;
             								root = FXMLLoader.load(getClass().getResource("/gui/AddNewBookByLibrarianOrManager.fxml"));
                     						AnchorPaneTabs.getChildren().setAll(root); 
             							}addstatus++;

             					}
             					catch(IOException ex){
             						
             					}
                           	 }
             						
             					
             					if(keyComb8.match(ke)) {
             						for(Node node3 : toolbar.getChildren()){
                           				if(node3.getAccessibleText().equals("LogOut")) {
                           					node3.setStyle("-fx-border-color: #FFFF8D");
                           				}
                           				else {
                           					node3.setStyle("-fx-border-color: transparent"); 
                           				}
                         				  
                         			 }
             						ke.consume();
                 					if(logstatus==0) {
                 						logstatus++;
                 						User.UpdateUserStatus(MyUserData.getUserID() ,"Offline");
                     					getStage().close();
                     					LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/GuestPanel.fxml"), "Welcome Page", null);
                 					}
                           	 }
             					
             					
             			
                         }
                			       
                            
                        }
                    });
            		
            		
            		
            		node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            			AnchorPane root;
            			for(Node node2 : toolbar.getChildren()){
            				
           				 node2.setStyle("-fx-border-color: transparent"); 
           			 }
           			
           			 node.setStyle("-fx-border-color: #FFFF8D");
            			switch (node.getAccessibleText()){
            				case "Homepage":
            					//Parent root;
        						try {
        							FXMLLoader loader2 = new FXMLLoader();
        							loader2.setLocation(getClass().getResource("/gui/LibrarianHomepage.fxml"));
        							root = loader2.load(); 
        							LibrarianHomepageController HomepageManager = loader2.getController();
        							//User me = new User(MyUserData.getUserID(),MyUserData.getUsername(),MyUserData.getPassword(),MyUserData.getID(),MyUserData.getFirstName()
        							//		,MyUserData.getLastName(),MyUserData.getEmail(),MyUserData.getAddedDate(),MyUserData.getPermissions(),MyUserData.getUserStatus());
        							//HomepageManager.setUser(me);
        							HomepageManager.setUser(MyUserData);
        							
        							AnchorPaneTabs.getChildren().setAll(root); 

        						} catch (IOException e2) {
        							// TODO Auto-generated catch block
        							e2.printStackTrace();
        						}
            					
            					
            					
            					
            					break;
            				case "PersonalInformation":
            				
            					
            					try {
        							FXMLLoader loader2 = new FXMLLoader();
        							loader2.setLocation(getClass().getResource("/gui/PersonalInformationByLibrarian.fxml"));
        							root = loader2.load();
        							PersonalInformationByLibrarianOrManagerController listOfBorrow = loader2.getController();
        							try {
        								listOfBorrow.loadInformationManager(MyUserData);
        								AnchorPaneTabs.getChildren().setAll(root); 
        							} catch (Exception e1) {
        								// TODO Auto-generated catch block
        								e1.printStackTrace();
        							}
        							
        							
        						} catch (IOException e1) {
        							// TODO Auto-generated catch block
        							e1.printStackTrace();
        						}//transfer information between windows from the toolbar+opening them*/
                							

            					//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/PersonalInformationByLibrarianOrManager.fxml"), "Personal Information", null);
            					break;
            				case "Messages":
            					try {
            						FXMLLoader loader2 = new FXMLLoader();
            						loader2.setLocation(getClass().getResource("/gui/ListOfMessagesByAll.fxml"));
            						root = loader2.load();
            						ListOfMessagesByAllController listOfBorrow = loader2.getController();
            						listOfBorrow.SetUser(MyUserData.getUserID(),MyUserData.getPermissions());
            						
            						AnchorPaneTabs.getChildren().setAll(root); 

            					} catch (IOException e2) {
            						// TODO Auto-generated catch block
            						e2.printStackTrace();
            					}
            					//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/ListOfMessagesByAll.fxml"), "List Of Messages", null);
            					break;
            				case "ReaderRegistration":
            					try {
            						root = FXMLLoader.load(getClass().getResource("/gui/AddNewReaderByLibrarianOrManager.fxml"));
            						AnchorPaneTabs.getChildren().setAll(root); 
            					}
            					catch(IOException ex){
            						
            					}
            					//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/AddNewReaderByLibrarianOrManager.fxml"), "Reader Registration", null);
            					break;

            				case "ReceiveReturnBook":
            					
            					try {
            						FXMLLoader loader22 = new FXMLLoader();
            						loader22.setLocation((getClass().getResource("/gui/ReturnABookByLibrarianOrManager.fxml")));
            						root=loader22.load();
            						ReturnABookByLibrarianOrManagerController r= loader22.getController();
            						r.SetUser(MyUserData.getUserID());
            						AnchorPaneTabs.getChildren().setAll(root); 
            					}
            					catch(IOException ex){
            						ex.printStackTrace();
            					}
            					//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/ReturnABookByLibrarianOrManager.fxml"), "Receive Return Book", null);
            					break;
            				case "BorrowedBookList":
            					//try {
            						//root = FXMLLoader.load(getClass().getResource("/gui/ListOfBorrowedBooks.fxml"));
            						//AnchorPaneTabs.getChildren().setAll(root);  
            					changeAnchor();
            					//}
            				//	catch(IOException ex){
            						
            					//}
                				
            					break;
            				case "AddNewBook":
            						
            					try {
            						root = FXMLLoader.load(getClass().getResource("/gui/AddNewBookByLibrarianOrManager.fxml"));
            						AnchorPaneTabs.getChildren().setAll(root);  
            					}
            					catch(IOException ex){
            						
            					}
            					
            					
            					//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/AddNewBookByLibrarianOrManager.fxml"), "Add New Book", null);
            					break;

            				case "LogOut":
            					User.UpdateUserStatus(MyUserData.getUserID() ,"Offline");
            					getStage().close();
            					LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/GuestPanel.fxml"), "Login", null);
            					break;

            			}
            			

            		});

            	}

            }
            
            
            
        } catch (IOException ex) {
            //Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        HamburgerSlideCloseTransition task = new HamburgerSlideCloseTransition(hamburger);
        task.setRate(-1);
        MainAnchor.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.TAB) {
                	drawer.toggle();
                	
                    ke.consume(); // <-- stops passing the event to next node
                }
            }
        });
        hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
            drawer.toggle();
        });
        drawer.setOnDrawerOpening((event) -> {
        	drawerstatus = 1;
            task.setRate(task.getRate() * -1);
            task.play();
            drawer.toFront();
        });
        drawer.setOnDrawerClosed((event) -> {
        	drawerstatus = 0;
            drawer.toBack();
            task.setRate(task.getRate() * -1);
            task.play();
        });
    }

}
