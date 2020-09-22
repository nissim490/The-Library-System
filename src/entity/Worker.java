package entity;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import common.Functions;

public class Worker extends User {

	private String WorkerID; 
	private String UserID;
	private String Occupation;
	private String OrganizationalAffiliation;
	private String PhoneNumber;
	/**parameters of class*/
 
	public Worker(String userID, String username, String password, String iD, String firstName, String lastName,
			String email, String addedDate, String permissions, String userStatus, String WorkerID,
			String occupation, String organizationalAffiliation,String PhoneNumber) 
	/**Main constructor of the class*/
	{
		super(userID, username, password, iD, firstName, lastName, email, addedDate, permissions, userStatus);
		WorkerID = WorkerID;
		UserID = userID;
		Occupation = occupation;
		OrganizationalAffiliation = organizationalAffiliation;
		PhoneNumber= PhoneNumber;
	}


		 
	
	public Worker() {}
		/** empty constructor*/
	


	public void UpdateWorkerOrganizationalAffiliationByUserID(String UserID ,String OrganizationalAffiliation)
	/**update the Organizational Affiliation of worker in database by user ID*/
	{
		
		 ArrayList<String> key = new ArrayList<String>();
	      key.add(OrganizationalAffiliation);
	      key.add(UserID);
	      Functions.askFromDB("UPDATE g17db.tblworker SET OrganizationalAffiliation = ? WHERE (`UserID` = ?);" ,key);
		
	}
	public void UpdateWorkerOrganizationalAffiliationByWorkerID(String WorkerID ,String OrganizationalAffiliation)
	/**update the Organizational Affiliation of worker in database by worker ID*/
	{
		
		 ArrayList<String> key = new ArrayList<String>();
	      key.add(OrganizationalAffiliation);
	      key.add(WorkerID);
	      Functions.askFromDB("UPDATE g17db.tblworker SET OrganizationalAffiliation = ? WHERE (`WorkerID` = ?);" ,key);
	} 
	public void UpdateWorkerOccupationByUserID(String UserID ,String Occupation)
	/**update the Occupation of worker in database by user ID*/
	{
		
		 ArrayList<String> key = new ArrayList<String>();
			

	      key.add(Occupation);
	      key.add(UserID);
	  	  Functions.askFromDB("UPDATE g17db.tblworker SET Occupation = ? WHERE UserID = ?;" ,key);
	}
	public void AddNewWorker(String WorkerID ,String UserID ,String Occupation,String PhoneNumber,String OrganizationalAffiliation )
	/**add new worker to database*/
	{
		
		
		 ArrayList<String> key = new ArrayList<String>();

	      key.add(WorkerID);
	      key.add(UserID);
	      key.add(Occupation);;
	      key.add(OrganizationalAffiliation);
	      key.add(PhoneNumber);
	  	  Functions.askFromDB("INSERT INTO g17db.tblworker (WorkerID,UserID,Occupation,OrganizationalAffiliation,PhoneNumber) values (?,?,?,?,?)",key);
		
	}
	public void UpdatePhoneNumberByUserID(String UserID ,String PhoneNumber)
	/**update the phone number of worker in database by user ID*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(PhoneNumber);
	      key.add(UserID);
	  	  Functions.askFromDB("UPDATE g17db.tblworker SET PhoneNumber = ? WHERE UserID = ?;",key);
	}
	public void UpdateWorkerOccupationByWN(String WorkerID, String Occupation)
	/**update the Occupation of worker in database by worker ID*/
	{
		 ArrayList<String> key = new ArrayList<String>();
		

	      key.add(Occupation);
	      key.add(WorkerID);
	  	  Functions.askFromDB("UPDATE g17db.tblworker SET Occupation = ? WHERE WorkerID = ?;" ,key);
	}
    public void ReceiveWorkerInformationByID(String ID) throws Exception
    /**get worker information from database by ID*/
    {
    	
    	ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(ID);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tbluser WHERE ID = ?;",key,askForResult);
		 
    	
    }
	public void ReceiveWorkerInformationByUserID(String UserID) throws Exception
	 /**get worker information from database by user ID*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(UserID);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblworker WHERE UserID = ?;",key,askForResult);
		 
		
	}
	public void ReceiveWorkerInformationByUsername(String Username) throws Exception
	/**get worker information from database by user name*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(Username);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tbluser WHERE Username = ?;",key,askForResult);
		 
		
		
	}
    public void ReceiveWorkerInformationByWN(String WorkerID) throws Exception
    /**get worker information from database by worker number*/
    {
    	
    	
    	ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(WorkerID);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblworker WHERE WorkerID = ?;",key,askForResult);
		 
		
    	 
    }
    public void RemoveWorker(String WorkerID)
    /**remove worker from database*/
    {
    	ArrayList<String> key = new ArrayList<String>();
	    key.add(WorkerID);
	    Functions.askFromDB("DELETE FROM  g17db.tblworker WHERE  (`WorkerID` = ?);",key);
    		
    	 
    }

/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal*/
    
    public String getWorkerID()
    {
    	return WorkerID;
    }
   
	public String getUserID() {
		return UserID;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}


	public String getOccupation() {
		return Occupation;
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
	public void setOccupation(String Occupation) {
		 if(Occupation!="librarian"||Occupation!="libraryManager")		
				this.Occupation = Occupation;
	}
	public void setUserID(String UserID) throws Exception {
		 if(UserID==null)		
			 throw new NullPointerException("User ID is null");
		int number = Integer.parseInt(UserID);
		 if(!Functions.isNumeric(UserID) )	
				 throw new Exception("User ID  must be a number");
		 if(number<0)		
			 throw new Exception("User ID must be greater than or equal to zero");
		
	 
		 this.UserID = UserID;
	 
	}
	public void setWorkerID(String WorkerID) throws Exception {
		 if(WorkerID==null)		
			 throw new NullPointerException("Worker Number is null");
		 if(!Functions.isNumeric(WorkerID) )	
			 throw new Exception("Worker Number  must be a number");
		int number = Integer.parseInt(WorkerID);
		
		 if(number<0)		
			 throw new Exception("Worker Number must be greater than or equal to zero");
		
		 this.WorkerID = WorkerID;
	 
	}
}