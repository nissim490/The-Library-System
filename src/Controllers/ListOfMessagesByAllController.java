package Controllers;

import com.jfoenix.controls.JFXButton;

import Controllers.ReaderHomepageController.Message;
import common.Functions;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import entity.User;

public class ListOfMessagesByAllController implements Initializable {
	
	private User u;
	private String ReaderCardNumber = "1";
	private String UserIDNumber;
	ArrayList<ArrayList<String>> result;
	ArrayList<ArrayList<String>> resultUser;
	ObservableList<Message> MylistM = FXCollections.observableArrayList();
	ArrayList<Message> lst = new ArrayList<>();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
   
    @FXML
    private TableView<Message> tblMessage;

    @FXML
    private TableColumn<Message, String> tblcSelect;

    @FXML
    private TableColumn<Message, String> tblcStatus;

    @FXML
    private TableColumn<Message, String> tblcSentFrom;

    @FXML
    private TableColumn<Message, String> tblcContent;

    @FXML
    private TableColumn<Message, String> tblcDate;

    @FXML
    private Label Messages;

    @FXML
    private JFXButton btnViewMessage;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnMarkAsRead;

    @FXML
    private JFXButton btnMarkAsUnread;

    @FXML
    private CheckBox SelectAll;

    /**
     * Delete all the checks messages
     * @param event action
     * @return nothing
     */
    @FXML
    void btnDelete(ActionEvent event) {
    	this.lst.clear();
    	ArrayList<String> key = new ArrayList<String>();
    	for(Message t : MylistM) {
    		key.clear();
    		if(t.getSelect().isSelected()) {    			
    			key.add(t.getMessageID());
    			Functions.askFromDB("DELETE FROM g17DB.tblmessages WHERE MessageID = ?;",key);
    		}
    	}
    	if(ReaderCardNumber.equals("1")) {
    		initialM(UserIDNumber,UserIDNumber);
    	}
    	else
    	initialM(ReaderCardNumber);
    }
    /**
     * Mark as read all the checks messages
     * @param event action
     * @return void
     */
    @FXML
    void btnMarkAsRead(ActionEvent event) {
    	this.lst.clear();
    	ArrayList<String> key = new ArrayList<String>();
    	for(Message t : MylistM) {
    	  key.clear();
    	  if(t.getSelect().isSelected()) { 
    		  key.add("Read");	       
    		  key.add(t.getMessageID());
  	  	  	Functions.askFromDB("UPDATE g17DB.tblmessages SET Status = ? WHERE MessageID = ?;" ,key);
    	  }
    	}
    	if(ReaderCardNumber.equals("1")) {
    		initialM(UserIDNumber,UserIDNumber);
    	}
    	else
    	initialM(ReaderCardNumber);
    }
    /**
     * Mark as unread all the checks messages
     * @param event action
     * @return nothing
     */
    
    @FXML
    void btnMarkAsUnread(ActionEvent event) {
    	this.lst.clear();
    	ArrayList<String> key = new ArrayList<String>();
    	for(Message t : MylistM) {
    	  key.clear();
       	  if(t.getSelect().isSelected()) { 
       		  key.add("Unread");	       
       		  key.add(t.getMessageID());
       		  Functions.askFromDB("UPDATE g17DB.tblmessages SET Status = ? WHERE MessageID = ?;" ,key);
       	  }
    	}
    	if(ReaderCardNumber.equals("1")) {
    		initialM(UserIDNumber,UserIDNumber);
    	}
    	else
    	initialM(ReaderCardNumber);
    }
    /**
     * View only one message in popup
     * @param event action
     * @return void
     */
    @FXML
    void btnViewMessage(ActionEvent event) throws IOException {
    	int cnt = 0;
    	Message choose=null;
    	for(Message t : MylistM) {
			  if(t.getSelect().isSelected()) {
				  cnt++;
				  choose=t;
			  }
			  if(cnt>1) {
				  JOptionPane.showMessageDialog(null,"You can choose only 1 Message to read.");
				  return;
			  }
		}
    	if(cnt==0) {
			  JOptionPane.showMessageDialog(null,"Please choose 1 Message to read.");
			  return;
		}
    	//LibraryAssistantUtil.loadWindow(getClass().getResource("/gui/ViewMessagesByAll.fxml"), "Main Page", null);
    	
    	try {
    	Stage window = new Stage();
    	
    	FXMLLoader loader111 = new FXMLLoader();
		loader111.setLocation(getClass().getResource("/gui/ViewMessagesByAll.fxml"));
		Parent viewMessageParent = loader111.load();
		
		Scene viewMessageByAllScene = new Scene(viewMessageParent);
		
		ViewMessagesByAllController controller = loader111.getController();
		controller.showMessage(choose);
		controller.sendController(this);
		
		
		
		window.setScene(viewMessageByAllScene);
		window.show();
		
    	}
    	catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
    }
    
	public void start(Stage primaryStage) {
		/*try {
			//BorderPane root = new BorderPane();
			Parent root= FXMLLoader.load(getClass().getResource("ListOfMessagesByAll.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	/**
	 * Initial the user to load the information
	 * @param userid the user information we need to load all the data
	 * 
	 */
	 public void SetUser(String userid, String permi) {
		  	ArrayList<String> key = new ArrayList<String>();
		    ArrayList<String> askForResult = new ArrayList<String>();
	    	String sql;
	    	//this.u=u;
	    	if(permi.equals("Reader")) {
	    		sql = "SELECT r.ReaderCardNumber FROM g17db.tbluser as u,g17db.tblreader as r WHERE u.UserID=r.UserID AND u.UserID = ?;";
		    	askForResult.add("ReaderCardNumber");
		    	key.add(userid);
		    	//key.add("312576101");
		    	resultUser = Functions.askFromDB(sql ,key,askForResult);
		    	String a = resultUser.get(0).get(0);
		    	this.ReaderCardNumber = a;
		    	 initColM();
		         initialM(ReaderCardNumber);
	    	}
	    	else {
	    		this.UserIDNumber=userid;
	    		initColM();
	    		initialM(UserIDNumber,UserIDNumber);
	    	}
	    	
	   
	    	
	    }
	  /**
	     *init window
	     * @param arg0 s
	     * @param arg1 s
	     *
	     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	

    } 	
	/**
	 * Select all the messages or clear them of the check sign
	 * @param event action
	 * @return void
	 */
	  @FXML
	    void selectAll(ActionEvent event) {
	    	boolean flag = SelectAll.isSelected();
	    	for(Message t : MylistM) {
	    		t.getSelect().setSelected(flag);
	    	}
			tblMessage.setItems(MylistM);	
	    }
	  /**
	     *init table
	     * @param 
	     * @param 
	     * @return void
	     */
	 private void initColM() {
			tblcContent.setCellValueFactory(new PropertyValueFactory<Message,String>("Content"));
			tblcDate.setCellValueFactory(new PropertyValueFactory<Message,String>("ArrivalDate"));
			tblcSentFrom.setCellValueFactory(new PropertyValueFactory<Message,String>("From"));
			tblcStatus.setCellValueFactory(new PropertyValueFactory<Message,String>("Status"));	
			tblcSelect.setCellValueFactory(new PropertyValueFactory<Message,String>("Select"));
		}

	 /**
	  * Initial all the messages of the user
	  * @param readercardnumber loading by using this reader key
	  * 
	  */
		public void initialM(String readercardnumber) {
			tblMessage.getItems().clear();
			MylistM.clear();
	    	SelectAll.setSelected(false);
			int size;
			ArrayList<String> key = new ArrayList<String>();     	
		    ArrayList<String> askForResult = new ArrayList<String>();
		    String sql = "SELECT * FROM g17db.tblmessages as m, g17db.tblreader as r WHERE m.UserID=r.UserID And r.ReaderCardNumber= ?;";
		    key.add(readercardnumber);
		    askForResult.add("*");
		    result=Functions.askFromDB(sql, key, askForResult);
			 size = result.size();
		        try {
		            for(int i=0;i<size;i++) {
		                //String rownum = String.valueOf(i+1);
		                String msgid = result.get(i).get(0);
		                String userid = result.get(i).get(1);
		                String content = result.get(i).get(2);
		                String date = result.get(i).get(3);
		                String from = result.get(i).get(4);
		                String status = result.get(i).get(5);
		                MylistM.add(new Message(msgid,userid, content,from, date, status));       
		            }
		        } catch (Exception ex) {
		            
		        }
		        tblMessage.setItems(MylistM);		 
		        //tblMessage.setOnMouseClicked(e -> {events();});		
		        }

		public void initialM(String useridd,String userid2) {
			tblMessage.getItems().clear();
			MylistM.clear();
	    	SelectAll.setSelected(false);
			int size;
			ArrayList<String> key = new ArrayList<String>();     	
		    ArrayList<String> askForResult = new ArrayList<String>();
		    String sql = "SELECT * FROM g17db.tblmessages as m WHERE m.UserID= ?;";
		    key.add(useridd);
		    askForResult.add("*");
		    result=Functions.askFromDB(sql, key, askForResult);
			 size = result.size();
		        try {
		            for(int i=0;i<size;i++) {
		                //String rownum = String.valueOf(i+1);
		                String msgid = result.get(i).get(0);
		                String userid = result.get(i).get(1);
		                String content = result.get(i).get(2);
		                String date = result.get(i).get(3);
		                String from = result.get(i).get(4);
		                String status = result.get(i).get(5);
		                MylistM.add(new Message(msgid,userid, content,from, date, status));       
		            }
		        } catch (Exception ex) {
		            
		        }
		        tblMessage.setItems(MylistM);		 
		        //tblMessage.setOnMouseClicked(e -> {events();});		
		        }
		private void events() {
			Message tmp;
			int pos = tblMessage.getSelectionModel().getSelectedIndex();
			   // @SuppressWarnings("unchecked")
			  tmp = tblMessage.getItems().get(pos);
		}
}
