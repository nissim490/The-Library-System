package entity;

import java.util.ArrayList;

import common.Functions;

public class BookOrders {

	private String BookID;
	private String OrderID;
	private String Date;
	private String ReaderCardNumber; 
	/**parameters of class*/
	
	public BookOrders(String bookID, String orderID, String date, String readerCardNumber) 
	/**Main constructor of the class*/
	{
		super();
		BookID = bookID;
		OrderID = orderID;
		Date = date;
		ReaderCardNumber = readerCardNumber;
	}

	 public static void RemoveOrder(String OrderID)
	 /**remove order of book from database*/
	 {
	    	ArrayList<String> key = new ArrayList<String>();
		    key.add(OrderID);
		    Functions.askFromDB("DELETE FROM  g17db.tblbookorders WHERE  (`OrderID` = ?);",key); 	
	    }
	 
	public static void AddNewOrder(String BookID , String OrderID ,String Date , String ReaderCardNumber)
	 /**add new order of book to database*/
	{
		 ArrayList<String> key = new ArrayList<String>();
	      key.add(OrderID);
	      key.add(BookID);
	      key.add(Date);
	      key.add(ReaderCardNumber);
	      Functions.askFromDB("INSERT INTO g17db.tblbookorders (`OrderID`, `BookID`, `Date`, `ReaderCardNumber`) VALUES (?,?,?,?)",key);
	}
	
	public static void ReceiveBookOrdersInformation(String ReaderCardNumber) throws Exception
	/**get orders information of specific reader from database by reader card number*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(ReaderCardNumber);
	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblbookorders WHERE ReaderCardNumber = ?;",key,askForResult);
		 	
	}

	
/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal*/
	public String getBookID() {
		return this.BookID;
	}

	public String getOrderID() {
		 return OrderID;
	}


	public String getDate() {
		 return Date;
	}


	public String getReaderCardNumber() {

		 return ReaderCardNumber;

	}

	
	
	
public void setDate(String Date) throws Exception {
		
		if(Date=="")
			 throw new Exception("Date"+" is Invalid Date format");
		 
		
		if(Functions.validateJavaDate(Date))
		{	 
			this.Date = Date;
		 
		}
	}

public void setBookID(String BookID) throws Exception {
	 if(BookID==null)		
		 throw new NullPointerException("Book ID is null");
	 if(!Functions.isNumeric(BookID) )	
		 throw new Exception("BookID  must be a number");
		int number = Integer.parseInt(BookID);
		
		 if(number<0)		
			 throw new Exception("BookID must be greater than or equal to zero");
		
	 
		 this.BookID = BookID;
	 
	}
public void setOrderID(String OrderID) throws Exception {
	 if(OrderID==null)		
		 throw new NullPointerException("Order ID is null");
	 
	 if(!Functions.isNumeric(OrderID) )	
		 throw new Exception("Order ID  must be a number");
	int number = Integer.parseInt(OrderID);
	 
	 if(number<0)		
		 throw new Exception("Order ID must be greater than or equal to zero");
	
 
	 this.OrderID = OrderID;
 
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
}