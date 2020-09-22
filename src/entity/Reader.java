package entity;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import common.Functions;

public class Reader extends User {

	private String ReaderCardNumber;
	private String UserID;
	private String PhoneNumber;
	private String CardStatus;// {Active,Inactive,Frozen,Locked};
	private String AmountOfBookDelays;
	private String AmountOfBookMissUse;
	private String MissedReturn;
	/**parameters of class*/
	
 	public Reader() {}
	/**empty constructor*/



	public Reader(String userID, String username, String password, String iD, String firstName, String lastName,
			String email, String addedDate, String permissions, String userStatus, String readerCardNumber, String phoneNumber, String cardStatus, String amountOfBookDelays,
			String amountOfBookMissUse,String amountOfBookMissedReturn) 
	/**Main constructor of the class*/
	{
		
		super(userID, username, password, iD, firstName, lastName, email, addedDate, permissions, userStatus);
		ReaderCardNumber = readerCardNumber;
		UserID = userID;
		PhoneNumber = phoneNumber;
		CardStatus = cardStatus;
		AmountOfBookDelays = amountOfBookDelays;
		AmountOfBookMissUse = amountOfBookMissUse;
		MissedReturn=amountOfBookMissedReturn;
	}

	
    public void UpdateReaderBookMissUseByUserID(String AmountOfBookMissUse ,String UserID)
  /**update the amount of reader book missed used in the database by user ID specific*/
    {
		
		ArrayList<String> key = new ArrayList<String>();
		
 
	      key.add(AmountOfBookMissUse);
	      key.add(UserID);
	  	  Functions.askFromDB("UPDATE g17db.tblreader SET AmountOfBookMissUse = ? WHERE UserID = ?;",key);
	 
	}
	public void UpdateReaderBookDelaysByUserID(String AmountOfBookDelays ,String UserID)
	  /**update the amount of reader book delays in the database by user ID specific*/
	{	
		ArrayList<String> key = new ArrayList<String>();
		
	      key.add(AmountOfBookDelays);
	      key.add(UserID);
	  	  Functions.askFromDB("UPDATE g17db.tblreader SET AmountOfBookDelays = ? WHERE UserID = ?;",key);
	}
	public void UpdateReaderBookMissUseByRCN(String AmountOfBookMissUse ,String ReaderCardNumber)
	  /**update the amount of reader book missed used in the database by reader card number specific*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(AmountOfBookMissUse);
	      key.add(ReaderCardNumber);
	  	  Functions.askFromDB("UPDATE g17db.tblreader SET AmountOfBookMissUse = ? WHERE ReaderCardNumber = ?;",key);
		
	}
	public void UpdateReaderBookDelaysByRCN(String AmountOfBookDelays ,String ReaderCardNumber)
	/**update the amount of reader book delays in the database by reader card number specific*/
	{	
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(AmountOfBookDelays);
	      key.add(ReaderCardNumber);
	  	  Functions.askFromDB("UPDATE g17db.tblreader SET AmountOfBookDelays = ? WHERE ReaderCardNumber = ?;",key);
	}
	public void UpdateReaderPhoneNumberByUserID(String UserID ,String PhoneNumber)
	/**update the reader phone number in the database by user ID*/
	{
			
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(PhoneNumber);
	      key.add(UserID);
	  	  Functions.askFromDB("UPDATE g17db.tblreader SET PhoneNumber = ? WHERE UserID = ?;",key);
	}
	public void UpdateReaderPhoneNumberByRCN(String PhoneNumber ,String ReaderCardNumber)
	/**update the reader phone number in the database by reader card number*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(PhoneNumber);
	      key.add(ReaderCardNumber);
	  	  Functions.askFromDB("UPDATE g17db.tblreader SET PhoneNumber = ? WHERE ReaderCardNumber = ?;",key);
	}
	public void UpdateReaderCardStatusByUserID(String UserID ,String Status)
	/**update the reader card status in the database by user ID*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(Status);
	      key.add(UserID);
	  	  Functions.askFromDB("UPDATE g17db.tblreader SET Status = ? WHERE UserID = ?;",key);
	}
	public void UpdateReaderCardStatusByRCN(String Status ,String ReaderCardNumber)
	/**update the reader card status in the database by reader card number*/
	{

		ArrayList<String> key = new ArrayList<String>();
		
	      key.add(Status);
	      key.add(ReaderCardNumber);
	  	  Functions.askFromDB("UPDATE g17db.tblreader SET Status = ? WHERE ReaderCardNumber = ?;",key);
	}
	public void AddNewReader(String UserID, String ReaderCardNumber, String PhoneNumber, String cardStatus, String amountOfBookDelays,
			String amountOfBookMissUse,String amountOfBookMissedReturn)
	/**add new reader to database*/
	{
	
		ArrayList<String> key = new ArrayList<String>();
	     key.add(ReaderCardNumber);	
	      key.add(UserID);
	      key.add(PhoneNumber);
	      key.add(cardStatus);
	      key.add(amountOfBookDelays);  
	      key.add(amountOfBookMissUse);
	      key.add(amountOfBookMissedReturn);
	           
	     
	      Functions.askFromDB("INSERT INTO g17db.tblreader  (`ReaderCardNumber`, `UserID`, `PhoneNumber`, `CardStatus`, `AmountOfBookDelays`, `AmountOfBookMissUse`, `MissedReturn`) VALUES (?,?,?,?,?,?,?);",key);

		
	}
	public void ReceiveReaderInformationByUserID(String UserID) throws Exception
	/**get reader information from database by user ID*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(UserID);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblreader WHERE UserID = ?;",key,askForResult);
		 
		
		
	}
	public void ReceiveReaderInformationByUsername(String Username) throws Exception
	/**get reader information from database by user name*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(Username);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tbluser WHERE Username = ?;",key,askForResult);
		 
		
		
	}
	public void ReceiveReaderInformationByID(String ID) throws Exception
	/**get reader information from database by ID*/
	{
		
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(ID);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tbluser WHERE ID = ?;",key,askForResult);
		 
		
	}
	public void ReceiveReaderInformationByRCN(String ReaderCardNumber) throws Exception
	/**get reader information from database by reader card number*/
	{
		
		
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(ReaderCardNumber);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblreader WHERE ReaderCardNumber = ?;",key,askForResult);
		 
		
	}
	public void RemoveReader(String ReaderCardNumber) 
	/**remove reader from database*/
	{
		ArrayList<String> key = new ArrayList<String>();
	    key.add(ReaderCardNumber);
	    Functions.askFromDB("DELETE FROM  g17db.tblreader WHERE  (`ReaderCardNumber` = ?);",key);
    		
	}

	
	
/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal*/
	public String getReaderCardNumber() {
		return ReaderCardNumber;
	}

	
	public String getUserID() {
		return UserID;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	
	public String getCardStatus() {
		return CardStatus;
	}	


	public String getAmountOfBookDelays() {
		return AmountOfBookDelays;
	}

	
	public String getAmountOfBookMissUse() {
		return AmountOfBookMissUse;
	}

	

	public void setReaderCardNumber(String ReaderCardNumber) throws Exception {
		 if(ReaderCardNumber==null)		
			 throw new NullPointerException("Reader CardNumber is null");
		 if(!Functions.isNumeric(ReaderCardNumber) )	
			 throw new Exception("Reader CardNumber  must be a number");
		int number = Integer.parseInt(ReaderCardNumber);
		
		 if(number<0)		
			 throw new Exception("Reader CardNumber must be greater than or equal to zero");
		
	 
		 this.ReaderCardNumber = ReaderCardNumber;
	 
	}
	public void setAmountOfBookDelays(String AmountOfBookDelays) throws Exception {
		 if(AmountOfBookDelays==null)		
			 throw new NullPointerException("Amount Of Book Delays  is null");
		 if(!Functions.isNumeric(AmountOfBookDelays) )	
			 throw new Exception("Amount Of Book Delays must be a number");
		int number = Integer.parseInt(AmountOfBookDelays);
		
		 if(number<0)		
			 throw new Exception("Amount Of Book Delays must be greater than or equal to zero");
		
		 this.AmountOfBookDelays = AmountOfBookDelays;
	
	}
	public void setAmountOefBookMissUse(String AmountOfBookMissUse) throws Exception {
		 if(AmountOfBookMissUse==null)		
			 throw new NullPointerException("Amount Of Book Miss Use  is null");
		 if(!Functions.isNumeric(AmountOfBookMissUse) )	
			 throw new Exception("Amount Of Book Miss Use must be a number");
		int number = Integer.parseInt(AmountOfBookMissUse);
		 
		 if(number<0)		
			 throw new Exception("Amount Of Book Miss Use must be greater than or equal to zero");
		
		 this.AmountOfBookMissUse = AmountOfBookMissUse;
		
	 
	}
	public void setUserID(String UserID) throws Exception {
		 if(UserID==null)		
			 throw new NullPointerException("User ID is null");
		 if(!Functions.isNumeric(UserID) )	
			 throw new Exception("User ID  must be a number");
		int number = Integer.parseInt(UserID);
		
		 if(number<0)		
			 throw new Exception("User ID must be greater than or equal to zero");
		
	 
		 this.UserID = UserID;
	 
	}
	public void setCardStatus(String CardStatus) throws Exception {
		
		 if(CardStatus==null)		
			 throw new NullPointerException("Card Status is null");
		 if(CardStatus!="Active"||CardStatus!="Frozen"||CardStatus!="Locked"||CardStatus!="Inactive")		
			 this.CardStatus = CardStatus;
				  
				else throw new Exception("Card Status can be Active or Locked or Frozen or Inactive");
	
		
	}
    public void setPhoneNumber(String PhoneNumber) throws Exception {
		 if(PhoneNumber==null)		
			 throw new NullPointerException("Phone Number is null");
		 if(!Functions.isNumeric(PhoneNumber) )	
			 throw new Exception("Phone Number  must be a number");
		 try {
		int number = Integer.parseInt(PhoneNumber);
		 if(number<0)		
			 throw new Exception("Phone Number must be greater than or equal to zero");
		 }
		 catch(NumberFormatException exception)
			{
				System.out.println("Phone Number must be a number");;
				
			}
		
		  if(PhoneNumber.length()<10||PhoneNumber.length()>10)
				 throw new Exception("Phone number is not valid ");
		
	 
		 this.PhoneNumber = PhoneNumber;
	 
	}
}