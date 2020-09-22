
package entity;

import java.util.ArrayList;

import common.Functions;

public class ListOfHistory {
	
	private String ActivityID;
	private String ReaderCardNumber;
	private String Activity;
	private String Date;
	/**parameters of class*/
	
	public ListOfHistory(String activityID, String readerCardNumber, String activity, String date)
	/**Main constructor of the class*/
	{
		super();
		ActivityID = activityID;
		ReaderCardNumber = readerCardNumber;
		Activity = activity;
		Date = date;
	}
	
	public static void AddNewActivityHistory(String activityID, String readerCardNumber, String activity, String date)
	/**add new disciplinary Activity history to database*/ 
	{

		 ArrayList<String> key = new ArrayList<String>();		  
	      key.add(activityID);	      
	      key.add(readerCardNumber);	      
	      key.add(activity);	      
	      key.add(date);
	  	  Functions.askFromDB("INSERT INTO g17db.tbllistofhistory (`ActivityID`, `ReaderCardNumber`, `Activity`, `Date` values  (?,?,?,?);",key);
	}
	public static void RemoveActivity(String ActivityID) 
	/**remove activity from database*/
	{
		ArrayList<String> key = new ArrayList<String>();
	    key.add(ActivityID);
	    Functions.askFromDB("DELETE FROM g17db.tbllistofhistory WHERE  (`ActivityID` = ?);",key);	
	}	
	
/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal*/
	public void setActivityID(String activityID) throws Exception  {
	
			 if(activityID==null)		
				 throw new NullPointerException("activity ID is null");
			 if(!Functions.isNumeric(activityID) )	
				 throw new Exception("activity ID  must be a number");
			int number = Integer.parseInt(activityID);
			 
			 if(number<0)		
				 throw new Exception("activity ID must be greater than or equal to zero");
			
		 
			 this.ActivityID = activityID;
		 
		 
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

	public void setActivity(String activity) {
		Activity = activity;
	}

	public void setDate(String Date) throws Exception {
		 if(Date==null)		
			 throw new NullPointerException("Date is null");
		if(Date=="")
			 throw new Exception("Date"+" is Invalid Date format");
		 
		
		if(Functions.validateJavaDate(Date))
		{	 
			this.Date = Date;
		 
		}
	}
	public String getActivityID() {
		return ActivityID;
	}

	

	public String getReaderCardNumber() {
		return ReaderCardNumber;
	}

	
	public String getActivity() {
		return Activity;
	}

	

	public String getDate() {
		return Date;
	}

	
}