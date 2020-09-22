package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import common.Functions;

public class BookCopy {
	private String BookCopyID;
	private String BookID;
	private String Status;
/**parameters of class*/

	/**
	 * 
	 * @param bookCopyID s
	 * @param bookID f
	 * @param status d
	 */ 
	public BookCopy(String bookCopyID, String bookID, String status)
	/**Main constructor of the class*/
	{
		super();
		BookCopyID = bookCopyID;
		BookID = bookID;
		Status = status;
	}

	/**
	 * 
	
	 */ 
	public BookCopy() {}
	/**empty constructor*/
	
	
	/**
	 * 
	 * @param ArchiveStatus s
	 * @param bookID f
	 *
	 */ 
	public static void UpdateBookArchiveStatus(String ArchiveStatus,String bookID)
	/**move book to archive*/
	{
		
		 ArrayList<String> key = new ArrayList<String>();
			

	      key.add(ArchiveStatus);
	      key.add(bookID);
	  	  Functions.askFromDB("UPDATE g17db.tblbook SET ArchiveStatus = ? WHERE BookID = ?;" ,key);
		
	}
	
	/**
	 * 
	 * @param Status s
	 * @param BookCopyID f
	 *
	 */ 
	public static void UpdateBookCopyStatus(String Status,String BookCopyID)
	/**update the status of the book copy in database*/
	{
		ArrayList<String> key = new ArrayList<String>();
		

	      key.add(Status);
	      key.add(BookCopyID);
	  	  Functions.askFromDB("UPDATE g17db.tblbookCopy SET Status = ? WHERE BookCopyID = ?;" ,key);
	}
	
	/**
	 * 
	 
	 * @param BookCopyID f
	 *
	 */ 
    public static void RemoveCopy(String BookCopyID) 
    /**remove specific book copy from database*/
    {
    	ArrayList<String> key = new ArrayList<String>();
	    key.add(BookCopyID);
	    Functions.askFromDB("DELETE FROM  g17db.tblbookCopy WHERE  (`BookCopyID` = ?);",key); 	
    } 
    /**
	 * 
	 
	 * @param BookAuthor f
	 *
	 */ 
	public static void SearchBookByAuthor(String BookAuthor)
	/**search book by author*/
	{
	
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(BookAuthor);
	      askForResult.add("BookID");     
		  Functions.askFromDB("SELECT * FROM g17db.tblbookCopy WHERE BookID = ?;",key,askForResult);
		
	}
	public static void ReceiveBookTableContent(String BookID) throws Exception
	{
		
		ArrayList<String> key = new ArrayList<String>();
 	      ArrayList<String> askForResult = new ArrayList<String>();

 	      key.add(BookID);
 	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblbook WHERE BookID = ?;",key,askForResult);
	
	}
	public static void AddNewCopy(String bookCopyID ,String BookID ,String status )
     /**insert new copy to database*/
	{
		
		 ArrayList<String> key = new ArrayList<String>();

	      key.add(bookCopyID);
	      key.add(BookID);
	      key.add(status);
	  
	      Functions.askFromDB("INSERT INTO g17db.tblbookCopy (`BookCopyID`, `BookID`, `Status`)  VALUES (?,?,?);",key);
	 
	}
	public static void SearchBookBySubject(String Subject) throws Exception
	/**search book by subject*/
	{
		 
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(Subject);
	      askForResult.add("BookID");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblbook WHERE Subject = ?;",key,askForResult);
		
	
		
	}
	public static void SearchBookByName(String BookName) throws Exception
	/**search book by name of book*/
	{
		

		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(BookName);
	      askForResult.add("BookID");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblbook WHERE BookName = ?;",key,askForResult);
		 
		
	}
	public static void SearchBookByDescription(String BookDescription) throws Exception
	/**search book by description*/
	{
		
		ArrayList<String> key = new ArrayList<String>();
	      ArrayList<String> askForResult = new ArrayList<String>();

	      key.add(BookDescription); 
	      askForResult.add("BookID");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblbook WHERE BookDescription = ?;",key,askForResult);
		 
	}

/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal*/ 
	public BookCopy getCopyInformation(String BookCopyID) {
		return this;
	}

	public String getBookCopyID() {
		return BookCopyID;
	}


	public String getBookID() {
		return this.BookID;
	}

	public String getStatus() {
		return Status;
	}

 
public void setStatus(String Status) throws Exception {
		
		 if(Status==null)		
			 throw new NullPointerException("Status is null");
		 if(Status=="Shelf"||Status=="Borrow"||Status=="Removed"||Status=="Missing")		
			 this.Status = Status;
				  
				else throw new Exception("Status can be Shelf or Borrow or Removed or Missing");
		 
	}
public void setBookID(String BookID) throws Exception {
	 if(BookID==null)		
		 throw new NullPointerException("Book Copy ID is null");
		int number = Integer.parseInt(BookID);
		 if(!Functions.isNumeric(BookID) )	
				 throw new Exception("BookID  must be a number");
		 if(number<0)		
			 throw new Exception("BookID must be greater than or equal to zero");
		
	 
		 this.BookID = BookID;
	 
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
}