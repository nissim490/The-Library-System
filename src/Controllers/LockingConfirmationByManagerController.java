package Controllers;

import com.jfoenix.controls.JFXButton;

import Controllers.LockingConfirmationByManagerController.Sublock;

import common.Functions;
import entity.User;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class LockingConfirmationByManagerController implements Initializable {
	
    @FXML
    private CheckBox Select;

    @FXML
    private AnchorPane MainAnchor;//
    @FXML
    private StackPane MainStack;///

    @FXML
    private TableColumn<Sublock, String> tblcSelect;
	@FXML
    private TableView<Sublock> tblreader;
	@FXML
    private TableColumn<Sublock, String> AmountOfBookDelays;
    @FXML
    private TableColumn<Sublock, String> tblcolCardNumber;

    @FXML
    private TableColumn<Sublock, String>  tblcolFullName;

    @FXML
    private TableColumn<Sublock, String>  tblcolJoinedDate;

    
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    private	User UserInfo;
   
    @FXML
    private JFXButton ConfirmLockBtn;
	ObservableList<Sublock> MylistSubReader = FXCollections.observableArrayList();
	 private ArrayList<ArrayList<String>> resultreader;
	 private ArrayList<ArrayList<String>> resultuser;
	ArrayList<String> key = new ArrayList<String>();
	ArrayList<String> keymessage = new ArrayList<String>();
	ArrayList<String> keyreader = new ArrayList<String>();
	ArrayList<String> keyuser = new ArrayList<String>();
	private ArrayList<String> MyUser = new ArrayList<String>();
    private ArrayList<ArrayList<String>> Result;
	private ArrayList<String> askForResultreader = new ArrayList<String>();
	private ArrayList<String> askForResultuser = new ArrayList<String>();
	private ArrayList<String> reader = new ArrayList<String>();
	 JFXButton yesButton = new JFXButton("YES, Please");//
		JFXButton noButton = new JFXButton("No, Please");//
		  /**
	     *The function takes data, checks it and updates the changes in the DB and sends a message to the locked reader
	     * @param 
	     * @param ActionEvent
	     * @return void
	     */
    @FXML
    void ConfirmLockBtn(ActionEvent event) {/**The function takes data, checks it and updates the changes in the DB and sends a message to the locked reader*/
    	
    	//AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), " Massage","the details have been updated and a message was sent");
    
    	int sizeReader=MylistSubReader.size();
    	 JFXButton yesButton = new JFXButton("YES, Please");//
			JFXButton noButton = new JFXButton("No, Please");//
    	 
    	
    	 ArrayList<String> askForResultmessage = new ArrayList<String>();
		 System.out.println(2);
		 ArrayList<String> keymessage = new ArrayList<String>();
		  String sqlmessage = "SELECT * FROM g17db.tblmessages ORDER BY MessageID ;";
		  askForResultmessage.add("MessageID");
		
		  Result= Functions.askFromDB(sqlmessage ,keymessage,askForResultmessage);
	 int size4 = Result.size();
	 
   
 	int cnt1 = 1;
    String lastmessageid = Result.get(size4-1).get(0);
    
    int amounutofdigits,numberlength,amountofzero;
    String newmessageid = "";
 
    String userid="";
 
 boolean select=false;
	 for(int s=0;s<sizeReader;s++) {
		 
		if (MylistSubReader.get(s).getSelect().isSelected()) {
			String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
			
			select=true;
	      key.add("Locked");
	      key.add(MylistSubReader.get(s).getReaderCardNumber());
	    Functions.askFromDB("UPDATE g17DB.tblreader SET CardStatus = ? WHERE ReaderCardNumber = ?;",key);
		 System.out.println( mydate);
	 
		  key.clear();
	
		  int	size = resultreader.size();
		  for(int s1=0;s1<size;s1++) {
			  if(resultreader.get(s1).get(1).equals((MylistSubReader.get(s).getReaderCardNumber()))) {
				  
				   userid=resultreader.get(s1).get(0);
				   
				  
				  
			  }
		  }
	
			newmessageid = "M";
			amounutofdigits = lastmessageid.length()-1; //should be 6
			numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1) + 1);
			amountofzero = amounutofdigits - numberlength;
			for(int b=0;b<amountofzero;b++) {
				newmessageid = newmessageid+"0";
			}
			newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1);

			  keymessage.clear();
			 // loadData();
		keymessage.add(newmessageid);	      
	      keymessage.add(userid);	      
	      keymessage.add("Since you've missed returning a book 3 or more times your Membership was suspended - Locked.");	      
	      keymessage.add(mydate);	      
	      keymessage.add("Manager");	      
	      keymessage.add("Unread"); 
	    
	      Functions.askFromDB("INSERT INTO g17DB.tblmessages (`MessageID`, `UserID`, `Content`, `ArrivalDate`, `MessageFrom`, `Status`) VALUES (?,?,?,?,?,?)",keymessage);
	      keymessage.clear();
	      cnt1++;
	     
    		}
    		
    		
    		
    }
	 if(select)
	 {AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), " Massage","The lock successfully updated");}
	
    } 
    
	  /**
     *Once CheckBox is pressed, all CheckBox is pressed
     * @param 
     * @param ActionEvent
     * @return void
     */
    @FXML
    void selectAll(ActionEvent event) {/**Once CheckBox is pressed, all CheckBox is pressed*/
    	boolean flag = Select.isSelected();
    	for(Sublock t : MylistSubReader) {
    		t.getSelect().setSelected(flag);
    	}
    	tblreader.setItems(MylistSubReader);	
    }

    
	public void start(Stage primaryStage) {
		/*try {
			//BorderPane root = new BorderPane();
			Parent root= FXMLLoader.load(getClass().getResource("LockingConfirmationByManager.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
	  /**
     *Initialize the tables here, initialize and extract information from the DB
     * @param arg1 d
     * @param arg0 s
     * 
     */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	/**Initialize the tables here, initialize and extract information from the DB*/
		initCol();
		
		loadData();
		

    }
	
	private void loadData() {
		String sql;
    	int cnt=1;
    	MylistSubReader.clear();
    	//tblreader.getItems().clear();
    	   sql = "SELECT * FROM g17db.tblreader;";
		   askForResultreader.add("UserID");
		   askForResultreader.add("ReaderCardNumber");
		   askForResultreader.add("CardStatus");
		   askForResultreader.add("PhoneNumber");
		   askForResultreader.add("AmountOfBookDelays");
		   resultreader = Functions.askFromDB(sql ,keyreader,askForResultreader);
	int	size = resultreader.size();

		  sql = "SELECT * FROM g17db.tbluser;"; 
		  askForResultuser.add("UserID");
		   askForResultuser.add("FirstName");
		   askForResultuser.add("Lastname");
		   askForResultuser.add("ID");
		   askForResultuser.add("Email");
		   askForResultuser.add("Username");
		   askForResultuser.add("Password");
		   askForResultuser.add("AddedDate"); 
		   resultuser = Functions.askFromDB(sql ,keyuser,askForResultuser);
		  int size2 = resultuser.size();
		   for(int i = 0;i<size2;i++) {
			   for(int j=0;j<size;j++) {
				   if(resultuser.get(i).get(0).equals(resultreader.get(j).get(0))) {
					   String  Fullname = (resultuser.get(i).get(1)+" "+resultuser.get(i).get(2));
					   	String rownum = String.valueOf(cnt);
		                String ReaderCardNumber = resultreader.get(j).get(1);
		                String CardStatus = resultreader.get(j).get(2);
		                String AddedDate = resultuser.get(i).get(7);
		                String AmountOfBookDelays =  resultreader.get(j).get(4);
		                cnt++;
		              
		            int a= Integer.parseInt(AmountOfBookDelays);
		                if(!(CardStatus.equals("Locked"))&&((a>=3)))
		                MylistSubReader.add(new Sublock(ReaderCardNumber, Fullname, AddedDate,AmountOfBookDelays));
				   }
			   }
		   }
		   
    	
		   tblreader.setItems(MylistSubReader);
	}
	  /**
     *transerfing information
     * @param MyUserData f
     *
     */
	public void loadReader22(User MyUserData){/*Updating user data*/
	
		this.UserInfo=MyUserData;
		
		 
	}
	  /**
     *init tables
     * @param User
     * @param 
     * @return void
     */
  private void initCol() {	/*Initialize the tables*/
		  
		  tblreader.setEditable(true);
		  tblcSelect.setCellValueFactory(new PropertyValueFactory<>("Select"));
	    	tblcolCardNumber.setCellValueFactory(new PropertyValueFactory<>("ReaderCardNumber"));
	    	tblcolFullName.setCellValueFactory(new PropertyValueFactory<>("Fullname"));
	    	tblcolJoinedDate.setCellValueFactory(new PropertyValueFactory<>("JoinedDate")); 
	    	AmountOfBookDelays.setCellValueFactory(new PropertyValueFactory<>("AmountOfBookDelays")); 
		  
		  
	  }
  /**
   *class for lock table
 
   */
	   public static class Sublock {/**Special class for receiving a lock certificate*/

		   private CheckBox Select;
	        private final SimpleStringProperty ReaderCardNumber;
	        private final SimpleStringProperty Fullname;
	        private final SimpleStringProperty JoinedDate;
	        private final SimpleStringProperty  AmountOfBookDelays;
	        //private CheckBox RowNum;   
	        
	        public Sublock(String readerCardNumber, String fullname, String joinedDate,String amountOfBookDelays) {
	        
	   
	        	this.ReaderCardNumber = new SimpleStringProperty(readerCardNumber);
	        	this.Fullname = new SimpleStringProperty(fullname);
	         	this.JoinedDate = new SimpleStringProperty(joinedDate);
	        	this.AmountOfBookDelays = new SimpleStringProperty(amountOfBookDelays);
	        	this.Select = new CheckBox();
	        }
	        public String getJoinedDate() {
	        	return JoinedDate.get();
	        }
	        public void setJoinedDate(String joinedDate) {
	        	JoinedDate.set(joinedDate);
	        }
	        	
	       public CheckBox getSelect() {
	    	   return this.Select;
	       }
	       
	       public void setSelect(CheckBox c) {
	    	   this.Select=c;
	       }

	        public String getReaderCardNumber() {
	        	return ReaderCardNumber.get();
	        }
	        public void setReaderCardNumber(String readerCardNumber) {
	        	ReaderCardNumber.set(readerCardNumber);
	        }
	        
	        
	        public String getFullname() {
	        	return Fullname.get();
	        }
	        public void setFullname(String fullname) {
	        	Fullname.set(fullname);
	        }
	        
	      
	        public String getAmountOfBookDelays() {
	        	return AmountOfBookDelays.get();
	        }
	        public void setAmountOfBookDelays(String amountOfBookDelays) {
	        	  AmountOfBookDelays.set(amountOfBookDelays);
	        }
	       
	      
	    }
	    

}

