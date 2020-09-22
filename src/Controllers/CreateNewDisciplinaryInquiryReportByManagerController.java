package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import common.Functions;
import entity.DisciplinaryInquiryReport;
import entity.User;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class CreateNewDisciplinaryInquiryReportByManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BtnConfirmReport;

    @FXML
    private JFXTextField txtReaderName;

    @FXML
    private JFXTextField txtReaderCardNumber;

    @FXML
    private JFXTextField txtMangerName;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextArea txtDescription;
    @FXML
    private AnchorPane MainAnchor;//
    @FXML
    private StackPane MainStack;///
    @FXML
    private JFXComboBox<String> cmbVerdict;
    private  User user;
    /**
	 * create the report after clicking(and checking it)
	 * @param ActionEvent
	 * 
	 * return void
	 */
    @FXML
    void BtnConfirmReport(ActionEvent event) {
    	ArrayList<ArrayList<String>> resultreader;
    	ArrayList<ArrayList<String>> resultreport;
    	ArrayList<ArrayList<String>> resultuser;
    	ArrayList<ArrayList<String>> resultworker;
    	 String Verdict = "";
    	String Fullname= "";
    	boolean v=false;
    	 JFXButton yesButton = new JFXButton("YES, Please");//
			JFXButton noButton = new JFXButton("No, Please");//
			 
      		if((!(cmbVerdict.getValue()==null)&&!(txtDescription.getText().equals(""))&&!(txtReaderName.getText().equals(""))&&!(txtReaderCardNumber.getText().equals(""))&&!(txtMangerName.getText().equals(""))&&!(txtDate.getText().equals("")))) {

      		
    	   Verdict =(String) cmbVerdict.getValue();
 
    	   
    	 	int sizereport , sizereader, sizeuser;
         
      	  	ArrayList<String> key = new ArrayList<String>();
	        ArrayList<String> askForResult = new ArrayList<String>();

              String sql = "SELECT * FROM g17db.tblreader;";
      		   askForResult.add("ReaderCardNumber");
      		   askForResult.add("AmountOfBookDelays");
      		     askForResult.add("UserID");

      	    resultreader = Functions.askFromDB(sql ,key,askForResult);
      	  sizereader = resultreader.size();   
       key.clear();
        ArrayList<String> askForResult2 = new ArrayList<String>();

      sql = "SELECT * FROM g17db.tblworker;";
         askForResult2.add("UserID");
  		   askForResult2.add("WorkerID");
  		 
  		 resultworker = Functions.askFromDB(sql ,key,askForResult2);
  	 int sizerworker = resultworker.size();   
       	
          	ArrayList<String> keyreport = new ArrayList<String>();
        	ArrayList<String> askForresultreport = new ArrayList<String>();

            String sqlreport = "SELECT * FROM g17db.tbldisciplinaryinquiryreport;";
            askForresultreport.add("DisciplinaryInquiryReportID");
            resultreport = Functions.askFromDB(sqlreport ,keyreport,askForresultreport);
            sizereport = resultreport.size();
            
            String workerid="";
            for(int i = 0;i<sizerworker;i++) {
            	
            	if((resultworker.get(i).get(0).equals(user.getUserID())))
            	 workerid=resultworker.get(i).get(1);
            	
            }
           ArrayList<String> keyuser = new ArrayList<String>();
           ArrayList<String> askForResultuser = new ArrayList<String>();
    		  String sqluser = "SELECT * FROM g17db.tbluser;"; 
     		   askForResultuser.add("UserID");
     		   askForResultuser.add("FirstName");
     		   askForResultuser.add("LastName");
     	
     		boolean card =false;
     		   resultuser = Functions.askFromDB(sqluser ,keyuser ,askForResultuser);
     		   sizeuser= resultuser.size();
     		   
      		  for(int i = 0;i<sizeuser;i++) {
      			   for(int j=0;j<sizereader;j++) {
      				   if((resultuser.get(i).get(0).equals(resultreader.get(j).get(2))&&(resultreader.get(j).get(0).equals(txtReaderCardNumber.getText())))){
      					    Fullname = resultuser.get(i).get(1);
      					  card =true;
      					   
      					    
      				   }
      				  
      			   }
      		   }     		
      		  if(!card)
      			AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","The Card Number not exist");
      	int	cnt2=1;
      		  
    
      		int size5 = resultreport.size();
		String	 lastmessageid1 = resultreport.get(size5-1).get(0); 
			String newmessageid1 = "DIR";
		int	amounutofdigits1 = lastmessageid1.length()-3; //should be 6
		
		int	numberlength1 = (int) (Math.log10(Integer.parseInt(lastmessageid1.substring(3,lastmessageid1.length()))+cnt2) + 1);

			int amountofzero1 = amounutofdigits1 - numberlength1;
		for(int b=0;b<amountofzero1;b++) {
				newmessageid1 = newmessageid1+"0";
			} 
		
		newmessageid1 = newmessageid1+(Integer.parseInt(lastmessageid1.substring(3,lastmessageid1.length()))+cnt2);
	
		 for(int i=0;i<sizereader;i++)   {
    		   if(( Fullname.equals(txtReaderName.getText()))) {
    			   v=true;
    				System.out.println("OK");	
    		   } 
    		   else AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","The name does not match");
    	  }
		 
      		  if(v) {
   
      	   		
      		  
      		  
   		
   	 
     DisciplinaryInquiryReport.AddNewDisciplinaryInquiryReport(newmessageid1, txtReaderCardNumber.getText(), workerid, txtDescription.getText(), Verdict, txtDate.getText(),"0");
      		  }
      		  else System.out.println("not OK");	
      	
   	 
      	 
      }
      		 else AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","need to complet all field");
      		
      		
      		 if(v) {AlertMaker.showMaterialDialog(MainStack, MainAnchor, Arrays.asList(yesButton), "Error Massage","Report successfully updated");}/////Report successfully updated
    }
    /**
	 * initializing the combobox
	 * @param ActionEvent
	 * 
	 * @return void
	 */
    @FXML
    void initialize() {
    
		SetcmbVerdictComboBox();
    }
    /**
	 * transfering the user infromation from the last window to this one
	 * @param User
	 * 
	 * return void
	 */
    void load(User user) {
    	String datenow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    	txtMangerName.setText(user.getFirstName()); 
 	   txtDate.setText(datenow);
    	this.user=user;
    	
    }
    /**
  	 * initilizing the combobox
  	 * @param User
  	 * 
  	 * return void
  	 */
  private void SetcmbVerdictComboBox() {
		
		ArrayList<String> al = new ArrayList<String>();	
		al.add("Active");
		al.add("Locked");
		al.add("Frozen");
		ObservableList<String> VerdictComboBox = FXCollections.observableArrayList(al);	
		cmbVerdict.setItems(VerdictComboBox);  	
		}
}
