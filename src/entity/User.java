package entity;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import common.Functions;
import entity.*;

public class User {

	private String UserID;
	private String Username;
	private String Password;
	private String ID;
	private String FirstName;
	private String LastName;
	private String Email;
	private String AddedDate;
	private String Permissions ;//{reader,librarian,libraryManager};
	private String UserStatus;// {Online,Offline};
	public User() { 
		
	}
	
	public User(String userID, String username, String password, String iD, String firstName, String lastName,
			String email, String addedDate, String permissions, String userStatus) {
		super();
		UserID = userID;
		Username = username;
		Password = password;
		ID = iD;
		FirstName = firstName;
		LastName = lastName;
		Email = email;
		AddedDate = addedDate;
		Permissions = permissions;
		UserStatus = userStatus;
	}
	

	/**
	 * 
	 * @param Permissions f
	 * @param UserID s

	 */
	public static void UpdateUserPermissions(String UserID ,String Permissions){
		
		
		 ArrayList<String> key = new ArrayList<String>();
			

	      key.add(Permissions);
	      key.add(UserID);
	  	  Functions.askFromDB("UPDATE g17DB.tbluser SET Permissions = ? WHERE UserID = ?;" ,key);
	}
	public static void UpdateUserEmail(String UserID ,String Email){
		 ArrayList<String> key = new ArrayList<String>();
	      key.add(Email);
	      key.add(UserID);
	  	  Functions.askFromDB("UPDATE g17DB.tbluser SET Email = ? WHERE UserID = ?;" ,key);
	}
	public static void UpdateUserLastName(String UserID ,String LastName){
			
		 ArrayList<String> key = new ArrayList<String>();
			

	      key.add(LastName);
	      key.add(UserID);
	  	  Functions.askFromDB("UPDATE g17DB.tbluser SET LastName = ? WHERE UserID = ?;" ,key);
		
		
	}
	public static void UpdateUserFirstName(String UserID ,String FirstName){
		
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(FirstName);
	      key.add(UserID);
	  	  Functions.askFromDB("UPDATE g17DB.tbluser SET FirstName = ? WHERE UserID = ?;" ,key);
	}
	public static void UpdateUserPassword(String UserID ,String Password){
	ArrayList<String> key = new ArrayList<String>();
	

    key.add(Password);
    key.add(UserID);
	  Functions.askFromDB("UPDATE g17DB.tbluser SET Password = ? WHERE UserID = ?;" ,key);
	}
	public static void UpdateUserStatus(String UserID ,String NewStatus){
	ArrayList<String> key = new ArrayList<String>();

    key.add(NewStatus);
    key.add(UserID);
	  Functions.askFromDB("UPDATE g17DB.tbluser SET UserStatus = ? WHERE UserID = ?;" ,key);
	}
	
	public static void AddNewUser(String UserID , String Username , String Password,String ID ,String FirstName,String LastName , String Email , String AddedDate , String Permissions ,  String UserStatus ){
		  
		  ArrayList<String> key = new ArrayList<String>();

	      key.add(UserID);
	      key.add(Username); 
	      key.add(Password);
	      key.add(ID);
	      key.add(FirstName);
	      key.add(LastName); 
	      key.add(Email);
	      key.add(AddedDate);
	      key.add(Permissions);
	      key.add(UserStatus);
	      Functions.askFromDB("INSERT INTO g17DB.tbluser  (`UserID`, `Username`, `Password`, `ID`, `FirstName`, `Lastname`, `Email`, `AddedDate`, `Permissions`, `UserStatus`) values (?,?,?,?,?,?,?,?,?,?)",key);
	  	
	}
	public static void RemoveUser(String UserID) {
		ArrayList<String> key = new ArrayList<String>();
	    key.add(UserID);
	    Functions.askFromDB("DELETE FROM  g17DB.tbluser WHERE  (`UserID` = ?);",key);
    		
	}
	public static void ReceiveUserInformationByUsername(String Username) throws Exception{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(Username);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17DB.tbluser WHERE Username = ?;",key,askForResult);
		 
	}
	public static void ReceiveUserInformationByUserID(String UserID) throws Exception{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(UserID);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17DB.tbluser WHERE UserID = ?;",key,askForResult);
		 
		
	}
	

	/**
	 * 

	 * @param ID
	 */
	public void setID(String ID) throws Exception {
		if(ID==null)		
			 throw new NullPointerException(" UserID is null");
		if( Functions.Checkid(ID))
		 this.ID = ID;
		
	} 
	public void setAddedDate(String addedDate) throws Exception {
		 if(addedDate==null)		
			 throw new NullPointerException("added Date is null");
		if(addedDate=="")
			 throw new Exception("Date"+" is Invalid Date format");
		 
		
		if(Functions.validateJavaDate(addedDate))
		{	 
			AddedDate = addedDate;
		 
		}
	}


	public String getUsername() {
		return Username; 
	}

	/**
	 * 
	 * @param LastName d
	 */
	public void setLastName(String LastName) throws Exception {
		if(LastName==null)		
		 throw new NullPointerException("Last name is null");
		 char[] chars = LastName.toCharArray();
		 for (char c : chars) {
		        if(!Character.isLetter(c)) {
		        	 throw new Exception("Last name is must be leters");
		         
		        }
		    }
		this.LastName = LastName;
		  
		
	}

	public String getPassword() {
		return Password;
	}

	/**
	 * 
	 * @param Password d
	 
	 */
	public void setPassword(String Password) throws Exception {
		 
		  if(Password==null)		
				 throw new NullPointerException("Password  is null");
	               
		  if(Password.length()<8)
				 throw new Exception("The Password should be a combination of 8 letters or numbers");
		                
		  this.Password = Password;
            
		}

	public String getID() {
		return ID;
	}

	public String getFirstName() {
		return FirstName;
	}

	/**
	 * 
	 * @param FirstName s
	 */
	public void setFirstName(String FirstName) throws Exception {
		if(FirstName==null)		
		 throw new NullPointerException("First name is null");
		char[] chars = FirstName.toCharArray();
		 for (char c : chars) {
		        if(!Character.isLetter(c)) {
		        	 throw new Exception("First name is must be leters");
		         
		        }
		    }
		this.FirstName = FirstName;
		  
		
	}

	public String getLastName() {
		return LastName;
	}


	
	

	public String getEmail() {
		return Email;
	}

	/**
	 * 
	 * @param Email g
	 */
	public void setEmail(String Email) {
		 
		  if(Email==null)		
				 throw new NullPointerException("Email  is null");
		  this.Email = Email;
		}

	public String getAddedDate() {
		return AddedDate;
	}

	public String getPermissions() {
		return Permissions;
	}


	

	public String getUserStatus() {
		return UserStatus;
	}

	/**
	 * 
	 * @param Permissions d
	 */
	public void setPermissions(String Permissions) throws Exception {
		if(Permissions==null)		
			 throw new NullPointerException(" ID is null");
		 if(Permissions=="reader"||Permissions=="librarian"||Permissions=="libraryManager")		
				this.Permissions = Permissions;
				  
				else throw new Exception("Permissions can be librarian or libraryManager or reader ");
	}
	public void setUserStatus(String UserStatus) throws Exception {
		 if(UserStatus==null)		
			 throw new NullPointerException(" UserStatus is null");
		 if(UserStatus=="Online"||UserStatus=="Offline")		
				this.UserStatus = UserStatus;
				  
				else throw new Exception("User Status can be Online or Offline");
	}
	public void setUserID(String UserID) throws Exception {
		 if(UserID==null)		
			 throw new NullPointerException(" User ID is null");
		 if(!Functions.isNumeric(UserID) )	
			 throw new Exception(" User ID  must be a number");
		int number = Integer.parseInt(UserID);
		
		 if(number<0)		
			 throw new Exception(" User ID must be greater than or equal to zero");
		
	 
		 this.ID = ID;
	 
	}


	public String getUserID() {
		return UserID;
	}


	public void setUsername(String username) {
		if(username==null)		
			 throw new NullPointerException("User name is null");
			this.Username = username;
	}
}