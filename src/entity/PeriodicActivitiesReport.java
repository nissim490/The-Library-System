
package entity;

import java.util.ArrayList;

import common.Functions;

public class PeriodicActivitiesReport extends ReaderStatistic {
	private String PeriodicActivitiesReportID;
	private String AmountOfBorrowedBookCopies;
	private String AmountOfActiveReaders;
	private String AmountOfFrozenReaders;
	private String AmountOfDelays;
	private String AmountOfInactiveReaders;
	private String AmounOfLockedReaders;
	/**parameters of class*/
	
	public PeriodicActivitiesReport(String amountOfActiveReaders, String amountOfInactiveReaders,
			String amountOfFrozenReaders, String amountOfDelays, String periodicActivitiesReportID,
			String amountOfBorrowedBookCopies,String amounOfLockedReaders) 
	/**Main constructor of the class*/
	{
		super(amountOfActiveReaders, amountOfInactiveReaders, amountOfFrozenReaders, amountOfDelays);
		PeriodicActivitiesReportID = periodicActivitiesReportID;
		AmountOfBorrowedBookCopies = amountOfBorrowedBookCopies;
		AmounOfLockedReaders = amounOfLockedReaders;
	}


	public static void AddNewPeriodicActivitiesReport(String amountOfActiveReaders, String amountOfInactiveReaders,
			String amountOfFrozenReaders, String amountOfDelays, String periodicActivitiesReportID,
			String amountOfBorrowedBookCopies,String amounOfLockedReaders)
	/**add new periodic activities report to database*/
	{

		 ArrayList<String> key = new ArrayList<String>();
		 key.add("p000003");
		 key.add("22");
	      key.add("33");      
	      key.add("44");	      
	      key.add("0");	     
	      key.add("1");
	      key.add("1");	      
	      
	  	  Functions.askFromDB("INSERT INTO g17db.tblperiodicActivitiesReport  (`PeriodicActivitiesReportID`, `AmountOfBorrowedBookCopies`, `AmountOfActiveReaders`, `AmountOfInactiveReaders`, `AmountOfFrozenReaders`, `AmounOfLockedReaders`, `AmountOfDelays`) values (?,?,?,?,?,?,?)",key);
	}
		

	public static void ReceivePeriodicActivitiesReportInformation(String PeriodicActivitiesReportID) throws Exception
	/**get periodic activities report information from database by PeriodicActivitiesReportID*/
	{
	
    	ArrayList<String> key = new ArrayList<String>();
	     ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(PeriodicActivitiesReportID);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblperiodicActivitiesReport WHERE PeriodicActivitiesReportID = ?;",key,askForResult);
		  
	  	
		 
	}
	public static void RemovePeriodicActivitiesReport(String PeriodicActivitiesReportID)
	/**remove periodic activities report from database*/
	{
		ArrayList<String> key = new ArrayList<String>();
	    key.add(PeriodicActivitiesReportID);
	    Functions.askFromDB("DELETE FROM  g17db.tblperiodicActivitiesReport WHERE  (`PeriodicActivitiesReportID` = ?);",key);
	}


/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal*/
	public String getPeriodicActivitiesReportID() {
		return PeriodicActivitiesReportID;
	}
	
	
	public String getAmountOfBorrowedBookCopies() {
		return AmountOfBorrowedBookCopies;
	}

	/**
	 * 
	 * @param AmountOfBorrowedBookCopies f
	 */
	public void setAmountOfBorrowedBookCopies(String AmountOfBorrowedBookCopies) throws Exception {
		 if(AmountOfBorrowedBookCopies!=null)		
			 throw new NullPointerException("Amount Of Borrowed Book Copies  is null");
		 if(!Functions.isNumeric(AmountOfBorrowedBookCopies) )	
			 throw new Exception("Amount Of Borrowed Book Copies must be a number");
		int number = Integer.parseInt(AmountOfBorrowedBookCopies);
		
		 if(number<0)		
			 throw new Exception("Amount Of Borrowed Book Copies must be greater than or equal to zero");
		
		 this.AmountOfBorrowedBookCopies = AmountOfBorrowedBookCopies;
		
	 
	}
	public void setPeriodicActivitiesReportID(String PeriodicActivitiesReportID) throws Exception {
		 if(PeriodicActivitiesReportID==null)		
			 throw new NullPointerException("Periodic Activities Report ID is null");
		 if(!Functions.isNumeric(PeriodicActivitiesReportID) )	
			 throw new Exception("Periodic Activities Report ID  must be a number");
		int number = Integer.parseInt(PeriodicActivitiesReportID);
		
		 if(number<0)		
			 throw new Exception("Periodic Activities Report ID must be greater than or equal to zero");
		
	 
		 this.PeriodicActivitiesReportID = PeriodicActivitiesReportID;
	 
	}

}