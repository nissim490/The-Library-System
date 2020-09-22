package entity;

import java.util.ArrayList;

import common.Functions;

public class ReaderStatistic {

	private String AmountOfActiveReaders;
	private String AmountOfInactiveReaders;
	private String AmountOfFrozenReaders;
	private String AmountOfDelays;
	/**parameters of class*/

	
	public ReaderStatistic(String amountOfActiveReaders, String amountOfInactiveReaders, String amountOfFrozenReaders,
			String amountOfDelays)
	/**Main constructor of the class*/
	{
		super();
		AmountOfActiveReaders = amountOfActiveReaders;
		AmountOfInactiveReaders = amountOfInactiveReaders;
		AmountOfFrozenReaders = amountOfFrozenReaders;
		AmountOfDelays = amountOfDelays;
	}


	
	public static void ReceiveDelayedBorrowedBookInformation(String ReaderCardNumber) throws Exception
	/**get reader delayed borrowed book from database by reader card number*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(ReaderCardNumber);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblborrowedBook WHERE ReaderCardNumber = ?;",key,askForResult);
		 
		
		
	}
	
/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal*/

	public String getAmountOfInactiveReaders() {
		return AmountOfInactiveReaders;
	}

	public String getAmountOfActiveReaders() {
		return AmountOfActiveReaders;
	}


	public String getAmountOfFrozenReaders() {
		return AmountOfFrozenReaders;
	}

	public String getAmountOfDelays() {
		return AmountOfDelays;
	}


	public void setAmountOfActiveReaders(String AmountOfActiveReaders) throws Exception {
		 if(AmountOfActiveReaders==null)		
			 throw new NullPointerException("Amount Of Active Readers  is null");
		 if(!Functions.isNumeric(AmountOfActiveReaders) )	
			 throw new Exception("Amount Of Active Readers must be a number");
		int number = Integer.parseInt(AmountOfActiveReaders);
		
		 if(number<0)		
			 throw new Exception("Amount Of Active Readers must be greater than or equal to zero");
		
		 this.AmountOfActiveReaders = AmountOfActiveReaders;
		
	 
	}
	public void setAmountOfInactiveReaders(String AmountOfInactiveReaders) throws Exception {
		 if(AmountOfInactiveReaders==null)		
			 throw new NullPointerException("Amount Of Inactive Readers  is null");
		 if(!Functions.isNumeric(AmountOfInactiveReaders) )	
			 throw new Exception("Amount Of Inactive Readers must be a number");
		int number = Integer.parseInt(AmountOfInactiveReaders);
		 
		 if(number<0)		
			 throw new Exception("Amount Of Inactive Readers must be greater than or equal to zero");
		
		 this.AmountOfInactiveReaders = AmountOfInactiveReaders;
		
	 
	}
	public void setAmountOfFrozenReaders(String AmountOfFrozenReaders) throws Exception {
		 if(AmountOfFrozenReaders==null)		
			 throw new NullPointerException("Amount Of Frozen Readers  is null");if(!Functions.isNumeric(AmountOfFrozenReaders) )	
				 throw new Exception("Amount Of Frozen Readers must be a number");
		int number = Integer.parseInt(AmountOfFrozenReaders);
		 
		 if(number<0)		
			 throw new Exception("Amount Of Frozen Readers must be greater than or equal to zero");
		
		 this.AmountOfFrozenReaders = AmountOfFrozenReaders;
		
	 
	}
	public void setAmountOfDelays(String AmountOfDelays) throws Exception {
		 if(AmountOfDelays==null)		
			 throw new NullPointerException("Amount Of Delays is null"); 
		 if(!Functions.isNumeric(AmountOfDelays) )	
				 throw new Exception("Amount Of Delays must be a number");
		int number = Integer.parseInt(AmountOfDelays);
		
		 if(number<0)		
			 throw new Exception("Amount Of Delays must be greater than or equal to zero");
		
		 this.AmountOfDelays = AmountOfDelays;
		
	 
	}
}