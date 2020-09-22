package entity;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import common.Functions;

public class Messages {
 
	private String MessageID;
	private String UserID;
	private String Content;
	private String ArrivalDate;
	private String From;
	private String Status;//{Read,Unread};
	/**parameters of class*/

	
	public Messages(String messageID, String userID, String content, String arrivalDate, String from, String status) 
	/**Main constructor of the class*/
	{
		super();
		MessageID = messageID;
		UserID = userID;
		Content = content;
		ArrivalDate = arrivalDate;
		From = from;
		Status = status;
	}
	

	public static void UpdateMessageStatus(String MessageID, String status)
	/**update the status of message in database*/
	{
		
		 ArrayList<String> key = new ArrayList<String>();
	      key.add(status);	       
	      key.add(MessageID);
	  	  Functions.askFromDB("UPDATE g17db.tblmessage SET status = ? WHERE MessageID = ?;" ,key);
	}
	public static void RemoveMessage(String MessageID)
	/**remove message from database*/
	{
		ArrayList<String> key = new ArrayList<String>();
	    key.add(MessageID);
	    Functions.askFromDB("DELETE FROM  g17db.tblmessage WHERE  (`MessageID` = ?);",key);	
	}
	public static void ReceiveMessageInformationByUserID(String UserID) throws Exception
	/**get information of all message of specific user from database*/
	{
		

    	ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(UserID); 
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblmessage  WHERE UserID = ?;",key,askForResult);
		 
		
	}
	public static void ReceiveMessageInformationByMessageID(String MessageID) throws Exception
	/**get information of message from database by MessageID*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(MessageID); 
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblmessage WHERE MessageID = ?;",key,askForResult);

		
	}
	public static void AddNewMessage(String messageID,String UserID, String Content,String arrivalDate, String From,String status) 
	/**add new message to database*/
	{
		
		 ArrayList<String> key = new ArrayList<String>();
		  
	      key.add(messageID);	      
	      key.add(UserID);	      
	      key.add(Content);	      
	      key.add(arrivalDate);	      
	      key.add(From);	      
	      key.add(status); 
	    
	      Functions.askFromDB("INSERT INTO g17db.tblmessage (`MessageID`, `UserID`, `Content`, `ArrivalDate`, `From`, `Status`) VALUES (?,?,?,?,?,?)",key);

	}

	/**
	 * 
	 * @param MessageID
	 */
	
/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal*/
	public String getMessageID() {
	return MessageID;
	}

	public String getUserID() {
		return UserID;
	}

	public String getContent() {
		return Content;
	}

	public String getArrivalDate() {
		return ArrivalDate;
	}

	public String getFrom() {
		return From;
	}
	
	public String getStatus() {
		return Status;
	}

	/**
	 * 
	 * @param MessageID d
	 */
	public void setMessageID(String MessageID) throws Exception {
		 if(MessageID==null)		
			 throw new NullPointerException("Message ID is null");
		 if(!Functions.isNumeric(MessageID) )	
			 throw new Exception("Message ID  must be a number");
		int number = Integer.parseInt(MessageID);
		
		 if(number<0)		
			 throw new Exception("Message ID must be greater than or equal to zero");
		
	 
		 this.MessageID = MessageID;
	 
	}
	public void setUserID(String UserID) throws Exception {
		 if(UserID==null)		
			 throw new NullPointerException("User ID is null");
		 if(!Functions.isNumeric(UserID) )	
			 throw new Exception("User ID  must be a number");
		int number = Integer.parseInt(UserID);
		 if(!Functions.isNumeric(UserID) )	
				 throw new Exception("User ID  must be a number");
		 if(number<0)		
			 throw new Exception("User ID must be greater than or equal to zero");
		
	 
		 this.UserID = UserID;
	 
	}
	public void setStatus(String Status) throws Exception {
		if(Status==null)		
			 throw new NullPointerException("Status is null");
		
		 if(Status!="Read"||Status!="Unread")		
			 this.Status = Status;
				  
				else throw new Exception("Status can be Read or Unread");
	
		
	}
	public void setFrom(String From) throws Exception {
		if(From==null)		
		 throw new NullPointerException("From is null");
		 if(From == "")
			 throw new Exception("You can not leave this field empty");
		this.From = From;
		  
		
	}

	public void setContent(String Content) throws Exception {
		 if(Content==null)		
			 throw new NullPointerException("The Content is null");
		if(Content == " ")
			 throw new Exception("The content of the message is empty");
		 this.Content = Content;
	}
	
	
	public void setArrivalDate(String ArrivalDate) throws Exception {
		 if(ArrivalDate==null)		
			 throw new NullPointerException("Date is null");
		if(ArrivalDate=="")
			 throw new Exception("Date"+" is Invalid Date format");
		 
		
		if(Functions.validateJavaDate(ArrivalDate))
		{	 
			this.ArrivalDate = ArrivalDate;
		 
		}
	}
	
	
}