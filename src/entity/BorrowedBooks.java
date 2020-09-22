package entity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import common.Functions;

public class BorrowedBooks {
	private String BookBorrowID;
	private String BookCopyID;
	private String ReaderCardNumber;
	private String BorrowingDate;
	private String ReturnDate;
	private String ActualReturnDate;
	private String LenderName;
	private String ReminderStatus;// {Sent, NotSent};
	private String Delayed ;//{Yes,No};
	private String Status ;//{Active,Inactive};
	/**parameters of class*/
	
	
	
	public BorrowedBooks(String bookBorrowID, String bookCopyID, String readerCardNumber, String borrowingDate,
			String returnDate, String actualReturnDate, String lenderName, String reminderStatus, String delayed,
			String status)
	/**Main constructor of the class*/
	{
		super();
		BookBorrowID = bookBorrowID;
		BookCopyID = bookCopyID; 
		ReaderCardNumber = readerCardNumber;
		BorrowingDate = borrowingDate;
		ReturnDate = returnDate;
		ActualReturnDate = actualReturnDate;
		LenderName = lenderName;
		ReminderStatus = reminderStatus;
		Delayed = delayed;
		Status = status; 
	}
	public static void UpdateBorrowedBookDelayedByBookBorrowID(String Delayed,String BookBorrowID)
	/**update the borrowed delayed in database by book borrow ID*/
	{
		ArrayList<String> key = new ArrayList<String>();
		
	      key.add(Delayed);
	      key.add(BookBorrowID);
	      Functions.askFromDB("UPDATE g17db.tblbookborrow SET `BorrowedDelayed` = ? WHERE (`BookBorrowID` = ?);" ,key);
	}
	public static void UpdateBorrowedBookReminderStatusByBookCopyID(String ReminderStatus,String BookCopyID)
	/**update the status of reminder in database by book copy ID*/ 
	{
		
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(ReminderStatus);
	      key.add(BookCopyID);
	  	  Functions.askFromDB("UPDATE g17db.tblbookborrow SET ReminderStatus = ? WHERE BookCopyID = ?;" ,key);
	}
	public static void UpdateBorrowedBookReminderStatusByBookBorrowID(String ReminderStatus,String BookBorrowID)
	/**update the status of reminder in database by book borrow ID */
	{
	
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(ReminderStatus);
	      key.add(BookBorrowID);
	  	  Functions.askFromDB("UPDATE g17db.tblbookborrow SET ReminderStatus = ? WHERE BookBorrowID = ?;" ,key);
	}
	public static void UpdateBorrowedBookLenderNameByBookCopyID(String LenderName,String BookCopyID)
	/**update lender name in database by book copy ID*/
	{
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(LenderName);
	      key.add(BookCopyID);
	  	  Functions.askFromDB("UPDATE g17db.tblbookborrow SET LenderName = ? WHERE BookCopyID = ?;" ,key);
	}
	public static void UpdateBorrowedBookLenderNameByBookBorrowID(String LenderName,String BookBorrowID)
	/**update lender name in database by book borrow ID*/
	{        
		
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(LenderName);
	      key.add(BookBorrowID);
	  	  Functions.askFromDB("UPDATE g17db.tblbookborrow SET LenderName = ? WHERE BookBorrowID = ?;" ,key);
	}
	public static void UpdateBorrowedBookActualReturnDateByBookCopyID(String ActualReturnDate,String BookCopyID)
	/**update the real return date in database by book copy ID*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
				  
	      key.add(ActualReturnDate);	      
	      key.add(BookCopyID);
	      Functions.askFromDB("UPDATE g17db.tblbookBorrow SET ActualReturnDate = ? WHERE (`BookCopyID` = ?);" ,key);
	}
	public static void UpdateBorrowedBookReturnDateByBookCopyID(String ReturnDate,String BookCopyID)
	/**update the return date in database by book copy ID*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
		
		  
	      key.add(ReturnDate);	      
	      key.add(BookCopyID);
	  	  Functions.askFromDB("UPDATE g17db.tblbookborrow SET ReturnDate = ? WHERE BookCopyID = ?;" ,key);
	}
	public static void UpdateBorrowedBookActualReturnDateByBookBorrowID(String ActualReturnDate,String BookBorrowID)
	/**update the real return date in database by book borrow ID*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
				  
	      key.add(ActualReturnDate);	      
	      key.add(BookBorrowID);
	      Functions.askFromDB("UPDATE g1db.tblbookBorrow SET ActualReturnDate = ? WHERE (`BookBorrowID` = ?);" ,key);
	}
	public static void UpdateBorrowedBookReturnDateByBookBorrowID(String ReturnDate,String BookBorrowID)
	/**update the return date in database by book borrow ID*/
	{
		
		 ArrayList<String> key = new ArrayList<String>();
			
		  
	      key.add(ReturnDate);	      
	      key.add(BookBorrowID);
	  	  Functions.askFromDB("UPDATE g17db.tblbookBorrow SET ReturnDate = ? WHERE BookBorrowID = ?;" ,key);
		
	}
	public static void AddNewBorrowedBook(String bookBorrowID, String bookCopyID, String readerCardNumber, String borrowingDate,
			String returnDate, String actualReturnDate, String reminderStatus, String delayed,
			String status, String lenderName)
	/**add new borrow book to table book borrow in database*/
	{
		
		
		 ArrayList<String> key = new ArrayList<String>();
		  
	      key.add(bookBorrowID);	      
	      key.add(bookCopyID);	      
	      key.add(readerCardNumber);	      
	      key.add(borrowingDate);	      
	      key.add(returnDate);	      
	      key.add(actualReturnDate);	        
	      key.add(reminderStatus);	      
	      key.add(delayed);      
	      key.add(status);
	      key.add(lenderName);	
	      Functions.askFromDB("INSERT INTO g17db.tblbookBorrow (`BookBorrowID`, `BookCopyID`, `ReaderCardNumber`, `BorrowingDate`, `ReturnDate`, `ActualReturnDate`, `ReminderStatus`, `Delayed`, `Status`, `LenderName`) VALUES (?,?,?,?,?,?,?,?,?,?)",key);

	  	 
		
	    
	}
	public static void ReceiveBorrowedBookReturnDate(String BookBorrowID)
	/**get return date of borrowed book from database by BookBorrowID*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(BookBorrowID);
	      askForResult.add("ReturnDate");     
		  Functions.askFromDB("SELECT * FROM g17db.tblbookBorrow WHERE BookBorrowID = ?;",key,askForResult);
		
	}
	public static void ReceiveBorrowedBookInformationbyBookBorrowID(String ReaderActive, String BookBorrowID)
	/**get the all borrowed book information from database by specific BookBorrowID*/
	{
	
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(BookBorrowID);
	      askForResult.add("*");     
		  Functions.askFromDB("SELECT * FROM g17db.tblbookborrow WHERE BookBorrowID = ?;",key,askForResult);
	}
		
	public static void RemoveBorrow(String BookBorrowID) 
	/**delete borrow book from database*/
	{
	    	ArrayList<String> key = new ArrayList<String>();
		    key.add(BookBorrowID);
		    Functions.askFromDB("DELETE FROM  g17db.tblbookBorrow WHERE  (`BookBorrowID` = ?);",key);
	    		
	    	 
	    }
	
	public static void ReceiveBorrowedBookInformationbyReaderCardNumber(String ReaderCardNumber) throws Exception
	/**get borrow book information from database by reader card number*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(ReaderCardNumber);
	      askForResult.add("*");      
	      Functions.askFromDB("SELECT * FROM g17db.tblbookBorrow WHERE ReaderCardNumber = ?;",key,askForResult);
		
		 
	}
	
/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal*/
	public String getReminderStatus() {
		return ReminderStatus;
	}
	
	/**
	 * 
	 * @param borrowingDate s
	 */
	public void setBorrowingDate(String borrowingDate) {
		BorrowingDate = borrowingDate;
	}
	


	public String getBookBorrowID() {
		return BookBorrowID;
	}

	public String getBookCopyID() {
		return BookCopyID;
	}

	public String getReaderCardNumber() {
		return ReaderCardNumber;
	}

	public String getBorrowingDate() {
		return BorrowingDate;
	}

	public String getReturnDate() {
		return ReturnDate;
	}

	/**
	 * 
	 * @param ReturnDate d
	 */
public void setReturnDate(String ReturnDate) throws Exception {
	 if(ReturnDate==null)		
		 throw new NullPointerException("Return Date is null");
		if(ReturnDate=="")
			 throw new Exception("Date"+" is Invalid Date format");
		 
		
		if(Functions.validateJavaDate(ReturnDate))
		{	 
			this.ReturnDate = ReturnDate;
		  
		}
	}


	public String getLenderName() {
		return LenderName;
	}






	public String getDelayed() {
		return Delayed;
	}

	

	public String getStatus() {
		return Status;
	}

	/**
	 * 
	 * @param ReaderCardNumber d
	 */
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
    public void setBookBorrowID(String BookBorrowID) throws Exception {
	 if(BookBorrowID==null)		
		 throw new NullPointerException("Book Borrow ID is null");
	 if(!Functions.isNumeric(BookBorrowID) )	
		 throw new Exception("Book Borrow ID must be a number");
		int number = Integer.parseInt(BookBorrowID);
		
		 if(number<0)		
			 throw new Exception("Book Borrow ID must be greater than or equal to zero");
		
		 this.BookBorrowID = BookBorrowID;
	 
	}
	public void setBookCopyID(String BookCopyID) throws Exception {
		 if(BookCopyID==null)		
				throw new NullPointerException("Book Copy ID is null");
		 if(!Functions.isNumeric(BookCopyID) )	
			 throw new Exception("Book Copy ID  must be a number");
		int number = Integer.parseInt(BookCopyID);
		 
		 if(number<0)		
			 throw new Exception("Book Copy ID must be greater than or equal to zero");
		
	 
		 this.BookCopyID = BookCopyID;  
	 
	}
	public void setLenderName(String LenderName) throws Exception {
		if(LenderName==null)		
		 throw new NullPointerException("Lender name is null");
		 if(Functions.isNumeric(LenderName) )	
			 throw new Exception("Existing Amount  must be a string");
		
		  if(LenderName=="")
				 throw new NullPointerException("You can not leave this field empty");
		this.LenderName = LenderName;
		  
		
	}
	public void setReminderStatus(String ReminderStatus) throws Exception {
		 if(ReminderStatus==null)		
				throw new NullPointerException("ReminderStatus is null");
		 if(ReminderStatus!="Sent"||ReminderStatus!="Wasnt Sent")		
			 this.ReminderStatus = ReminderStatus;
				  
				else throw new Exception("Reminde Status can be Sent or Wasnt Sent");
	
		
	}
	public void setDelayed(String Delayed) throws Exception {
		
		 if(Delayed==null)		
				throw new NullPointerException("Delayed is null");
		 if(Delayed!="yes"||Delayed!="no")		
			 this.Delayed = Delayed;
				  
				else throw new Exception("Delayed can be yes or no");
	
		
	}
	public void setStatus(String Status) throws Exception {
		 if(Status==null)		
				throw new NullPointerException("Status is null");
		
		 if(Status!="Active"||Status!="Inactive")		
			 this.Status = Status;
				  
				else throw new Exception("Status can be Active or Inactive");
	
		
	}
}