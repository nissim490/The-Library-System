package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import common.Functions;
import entity.User;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.scene.layout.StackPane;

public class LendingABookByLibrarianOrManagerController {
	private ArrayList<String> book = new ArrayList<String>();
	private ArrayList<ArrayList<String>> resultuser;
	private ArrayList<ArrayList<String>> resultreader;
	private ArrayList<ArrayList<String>> ResultBooKcopy;
	private ArrayList<ArrayList<String>> Result;
	ArrayList<String> key = new ArrayList<String>();
	private boolean AvailibleCopies=false;
	private boolean error=false;
	private boolean sucssed=false;
	private String copid;
	private	User user;
	 JFXButton yesButton = new JFXButton("YES, Please");
    @FXML
    private ResourceBundle resources;
    //@FXML
   // private AnchorPane MainAnchor;
    @FXML
    private URL location;
    @FXML
    private StackPane MainStack;
    @FXML
    private AnchorPane LendingABookAnchorPane;

    @FXML
    private DatePicker dpkrTo;

    @FXML
    private Button ConfirmLendingbtn;

    @FXML
    private JFXTextField txtReaderCardNumber;

    @FXML
    private JFXTextField txtReaderName;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtPopularity;

    @FXML
    private JFXTextField txtCopyNum;

    @FXML
    private JFXTextField txtFrom;

    @FXML
    private JFXTextField txtLibrarianName;

    @FXML
    private Label lblMaximumLendingDate;
    /**
  	 * trying to lend this book
  	 * @param ActionEvent
  	 * @return void
  	 */
    @FXML
    void ConfirmLendingbtn(ActionEvent event) {/**A function inserts a row in the borrowing table, checks the parameters you enter, checks times according to preferences and availability, updates the availability of a book copy, the status of the book*/

		String datenow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    	 LocalDate today=LocalDate.now();
    	 
    	LocalDate normal=today.plusWeeks(2);
		LocalDate demanded=today.plusDays(3);
		 int amounutofdigits,numberlength,amountofzero;
		    String newmessageid = "";
		DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		
		if(dpkrTo.getValue().isBefore(today)){
			//JOptionPane.showMessageDialog(null,"Date is incorrect");
			 AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Error Massage","Date is incorrect");  
			error=true;
		}
		if(book.get(7).equals("Demanded")) {
		if(dpkrTo.getValue().isAfter(demanded)){
			//JOptionPane.showMessageDialog(null,"Date is incorrect .");
			AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Error Massage","Date is incorrect"); 
			error=true;
		
		}
		}
			if(dpkrTo.getValue().isAfter(normal)){
				//JOptionPane.showMessageDialog(null,"Date is incorrect .");
				AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Error Massage","Date is incorrect"); 
				error=true;
		}
			
			
			
			boolean sucssedcard=true;
		
			
			
			 ArrayList<String> askForResult2 = new ArrayList<String>();
			 ArrayList<String> askForResult1 = new ArrayList<String>();
				key.clear();
			String sql = "SELECT * FROM g17db.tblreader;";
			 
			   askForResult1.add("ReaderCardNumber");
			   askForResult1.add("UserID");
			   askForResult1.add("CardStatus");
			  
			   resultreader = Functions.askFromDB(sql ,key,askForResult1);
		int	sizetreader = resultreader.size();
		
		
		
		
		key.clear();
		 sql = "SELECT * FROM g17db.tbluser;"; 
		  askForResult2.add("UserID");
		   askForResult2.add("FirstName");
		  int k = 0;
		   resultuser = Functions.askFromDB(sql ,key,askForResult2);
		   int	sizetuser = resultuser.size();
		for(int b=0;b<sizetreader;b++) {
			if(resultreader.get(b).get(0).equals(txtReaderCardNumber.getText())) {
				sucssedcard=false;
				k=b;
				for(int j=0;j<sizetuser;j++) {
					if(resultreader.get(b).get(1).equals(resultuser.get(j).get(0))) {
			
			     if(resultuser.get(j).get(1).toUpperCase().equals(txtReaderName.getText().toUpperCase()))
			    	 
			    	 sucssed=true;
						
			     else  AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Error Massage","Name does not match"); //System.out.println("Name does not match");
			    	 
				}
					
	        }
		  }
			
		}
			if(sucssedcard)
				AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Error Massage","Reader Card Number not exist");//System.out.println("Reader Card Number not exist");
			
			
			if(!error&&AvailibleCopies&&sucssed&&!(resultreader.get(k).get(2).equals("Frozen"))&&!(resultreader.get(k).get(2).equals("Locked"))) {

				String actSql="SELECT * FROM g17db.tbllistofhistory;";
				    	ArrayList<String> actKey = new ArrayList<String>();
					    ArrayList<String> askForActResult = new ArrayList<String>();
					    askForActResult.add("*");
					    ArrayList<ArrayList<String>>resultAct =Functions.askFromDB(actSql, actKey, askForActResult);
				    	   	

					 	   	 int cnt1=1;
					 	   	 int sizelist = resultAct.size();
					 	   	 String lastmessageid = resultAct.get(sizelist-1).get(0); ////Finds the largest number in the table
					 	   	 newmessageid = "A";
					 	   	 	amounutofdigits = lastmessageid.length()-1; //should be 6
					 	   	 	numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1) + 1);
					
					 	   	 	amountofzero = amounutofdigits - numberlength;
					 	   	 for(int b=0;b<amountofzero;b++) {
					 	   		 newmessageid = newmessageid+"0";
					 	   	 }	
					 	   	 newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt1);
				                            actKey.clear();
					 	actKey.add(newmessageid);   	  
					 	actKey.add(txtReaderCardNumber.getText()); 
					 	actKey.add("Lending A Book"); 
					 	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				    	Date date = new Date();
				    	//System.out.println(dateFormat.format(date));
				    	actKey.add(dateFormat.format(date)+"");
					 	//actKey.add(LocalDate.now()+"");ут dateFormat.format(date)
					 	Functions.askFromDB("INSERT INTO `g17db`.`tbllistofhistory` (`ActivityID`, `ReaderCardNumber`, `Activity`, `Date`) VALUES (?, ?, ?, ?);", actKey);
				
				
				 cnt1 = 1;
				key.clear();
				
				 ArrayList<String> askForResultborrow = new ArrayList<String>();
				   sql = "SELECT * FROM g17db.tblbookborrow ORDER BY BookBorrowID ;";
				  askForResultborrow.add("BookBorrowID");
				
				  Result= Functions.askFromDB(sql ,key,askForResultborrow);
			 int size4 = Result.size();
				 lastmessageid = Result.get(size4-1).get(0); 
				newmessageid = "BB";
				amounutofdigits = lastmessageid.length()-2; //should be 6
				numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(2,lastmessageid.length()))+cnt1) + 1);
				
				amountofzero = amounutofdigits - numberlength;
			for(int b=0;b<amountofzero;b++) {
					newmessageid = newmessageid+"0";
				}
			
			
		
			
			
			
			
			
			
				newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(2,lastmessageid.length()))+cnt1);
				key.clear();
				 key.add(newmessageid);	      
			      key.add(copid);	      
			      key.add(txtReaderCardNumber.getText());	      
			      key.add(datenow);	      
			     // DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			    	//dpkrReturnDate.getValue().format(formatters)
			      try {
					//Date dob = new SimpleDateFormat("yyyy/MM/dd").parse(dpkrTo.getValue().toString());
					//System.out.println(dpkrTo.getValue().toString()+" or "+ dpkrTo.getValue()+" or "+new SimpleDateFormat("dd-MM-yyyy").format(dob));
					//key.add(new SimpleDateFormat("dd/MM/yyyy").format(dob));
			    	  DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/uuuu");
			    	  key.add(dpkrTo.getValue().format(formatters));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			      
			      key.add("");	        
			      key.add("Wasnt Sent");	      
			      key.add("No");      
			      key.add("Active");
			      key.add(user.getFirstName()+" "+user.getLastName());	
			      key.add("No");  
			      Functions.askFromDB("INSERT INTO g17DB.tblbookBorrow (`BookBorrowID`, `BookCopyID`, `ReaderCardNumber`, `BorrowingDate`, `ReturnDate`, `ActualReturnDate`, `ReminderStatus`, `BorrowedDelayed`, `Status`, `LenderName`, `BorrowedMissUsed`) VALUES (?,?,?,?,?,?,?,?,?,?,?)",key);
				
			      
			      
			      key.clear();
					

			      key.add("Borrow");
			      key.add(copid);
			  	  Functions.askFromDB("UPDATE g17DB.tblbookcopy SET Status = ? WHERE BookCopyID = ?;" ,key);
				
			  	key.clear();
			  	
			int AmountAvailable=Integer.parseInt(book.get(4))-1;
			      key.add(AmountAvailable+"");
			      key.add(book.get(13));
			  	  Functions.askFromDB("UPDATE g17DB.tblbook SET AmountAvailable = ? WHERE BookID = ?;" ,key);
			  	AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Success Massage","The book lending succeed.");
			  	//JOptionPane.showMessageDialog(null,"The book lending succeed.");
			}
			else if (!AvailibleCopies)
				AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Error Massage","Can't lend this book due to no availiable copies");
				//JOptionPane.showMessageDialog(null,"Can't lend this book due to no availiable copies");
			else if((resultreader.get(k).get(2).equals("Frozen")))
				AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Error Massage","Can't lend this book because this reader is Frozen");
				//JOptionPane.showMessageDialog(null,"Can't lend this book because this reader is Frozen");
			else if((resultreader.get(k).get(2).equals("Locked")))
				AlertMaker.showMaterialDialog(MainStack, null, Arrays.asList(yesButton), "Error Massage","Can't lend this book because this reader is Locked");
				//JOptionPane.showMessageDialog(null,"Can't lend this book because this reader is Locked");

    }

    @FXML
    void initialize() {

    }
    
    /**
  	 * transfering information from other window
  	 * @param book w
  	 * @param user s
  	 *
  	 */
    public void loadBook2(ArrayList<String> book,User user){/**initialization textfild and receiving user data from another screen*/
this.user=user;
		String datenow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    	 LocalDate today=LocalDate.now();
    	 
    	LocalDate normal=today.plusWeeks(2);
		LocalDate demanded=today.plusDays(3);
    	 
    	 dpkrTo.setValue(today);
		
		this.book = book;
		if(book.get(7).equals("Demanded"))
		lblMaximumLendingDate.setText("The Maximum Lending Date \r\n" + 
				"is "+demanded);
		else lblMaximumLendingDate.setText("The Maximum Lending Date \r\n" + 
				"is "+normal);
		
		 ArrayList<String> askForResult = new ArrayList<String>();
    	 ArrayList<String> key = new ArrayList<String>();
		  String sql = "SELECT * FROM g17db.tblbookcopy;";
	
		 askForResult.add("BookCopyID");
	     askForResult.add("BookID");
		 askForResult.add("Status");
		
		 ResultBooKcopy = Functions.askFromDB(sql ,key,askForResult);
		int  sizeBooKcopy = ResultBooKcopy.size();
		
	 	 for(int j=0;j<sizeBooKcopy;j++) {
	 		if(ResultBooKcopy.get(j).get(1).equals(book.get(13))&&ResultBooKcopy.get(j).get(2).equals("Shelf")) {
	 			copid=ResultBooKcopy.get(j).get(0);
	 			  txtCopyNum.setText(ResultBooKcopy.get(j).get(0));
	 		      AvailibleCopies=true;
	 		}
		
	 	 }
		
		
		
	 	if(!AvailibleCopies)  txtCopyNum.setText("No copies available");

		    txtBookName.setText(book.get(1));
		    txtPopularity.setText(book.get(7));

		 
		    txtFrom.setText(datenow);
		    txtLibrarianName.setText(user.getFirstName());
	
		
		
	
	}
    
    
}
