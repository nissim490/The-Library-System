package entity;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import common.Functions;
import entity.*;
import javafx.collections.ObservableList;

import static java.lang.System.*;
import static org.junit.Assert.assertTrue;

public class Book {
	private String CatalogNumber;
	private String TableOfContents;
	private String EditionNumber;
	private String BookID;
	private String BookName;
	private String AuthorName;
	private String Subject;
	private String Description;
	private String Popularity;// {Normal ,Demanded};
	private String PurchaseDate ;
	private String PublishedDate;
	private String ExistingAmount;
	private String AmountAvailable;
	private String Location;
	private String ArchiveStatus ;//{Archived,Frozen,Normal};
	/**parameters of class
	 * @param  bookID e
	 * @param bookName w
	 * @param authorName d 
	 * @param subject d
	 * @param description f
	 * @param popularity dd
	 * @param existingAmount ddd
	 * @param amountAvailable  ff
	 * @param location ss
	 * @param archiveStatus s
	 * @param purchaseDate a
	 * @param publishedDate j
	 * 
	*/
	public Book(String bookID, String bookName, String authorName, String subject, String description,
			String popularity, String existingAmount, String amountAvailable, String location, String archiveStatus,String purchaseDate,String publishedDate) 
	/**Main constructor of the class*/
	{
		super();
		BookID = bookID;
		BookName = bookName;
		AuthorName = authorName;
		Subject = subject;
		Description = description;
		Popularity = popularity;
		ExistingAmount = existingAmount;
		AmountAvailable = amountAvailable;
		Location = location;
		ArchiveStatus = archiveStatus;
		PurchaseDate=purchaseDate;
		PublishedDate=publishedDate;
		
	} 

	
	public Book() {}
	/**empty constructor*/

		public static void  AddNewBook(String bookID, String bookName, String authorName, ObservableList<String> subject, String description,
			 String popularity, String existingAmount, String amountAvailable, String location, String archiveStatus,String purchaseDate,String publishedDate,String catalogNumber,String tableOfContents,String editionNumber) {
		/**initialize the parameters and insert new book to database*/ 
		 String subject1 ="";
		 String subject2 =",";
		 ArrayList<String> key = new ArrayList<String>();
		 for(int i=0;i<subject.size();i++)
			 if(i==0)
			 subject1=subject1+subject.get(i);
			 else		 
				 subject1=subject1+subject2+subject.get(i);
		 
	      key.add(bookID);
	      key.add(bookName);
	      key.add(authorName);
	      key.add(subject1);
	      key.add(description);
	      key.add(popularity);
	      key.add(existingAmount);
	      key.add(amountAvailable);
	      key.add(location);
	      key.add(archiveStatus);
	      key.add(purchaseDate);
	      key.add(publishedDate); 
	      key.add(catalogNumber); 
	      key.add(tableOfContents); 
	      key.add(editionNumber); 
	      Functions.askFromDB("INSERT INTO g17db.tblbook (`BookID`, `BookName`, `AuthorName`, `Subject`, `Description`, `Popularity`, `ExistingAmount`, `AmountAvailable`, `Location`, `ArchiveStatus`, `PurchaseDate`, `PublishedDate`, `CatalogNumber`, `TableOfContents`, `EditionNumber`)    VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);",key);
	  
	  
		 
	 }
	public static void RemoveBook(String BookID) {
    /**remove book from the database*/
		 ArrayList<String> key = new ArrayList<String>();
		    key.add("B000020");
		    Functions.askFromDB("DELETE FROM  g17db.tblbook WHERE  (`BookID` = ?);",key);
		 
		 
	 }
	public static void UpdateBookArchiveStatus(String ArchiveStatus,String bookID){
	/**insert book to archive of library and update in database*/
		 ArrayList<String> key = new ArrayList<String>();
			
		 
	      key.add(ArchiveStatus);
	      key.add(bookID);
	  	  Functions.askFromDB("UPDATE g17db.tblbook SET ArchiveStatus = ? WHERE BookID = ?;" ,key);
		
	}
	public static void UpdateBookShelfLocation(String Location,String bookID){
		/**update the location of the book in the shelf library in database*/
		 ArrayList<String> key = new ArrayList<String>();
		
	      key.add(Location);
	      key.add(bookID);
	  	  Functions.askFromDB("UPDATE g17db.tblbook SET Location = ? WHERE BookID = ?;" ,key);
		
	}
	public static void UpdateBookPopularity(String newPopularity,String bookID){
		/**update the popularity of the book in the database*/

		
		  ArrayList<String> key = new ArrayList<String>();
		  
	      key.add(newPopularity);
	      key.add(bookID);
	  	  Functions.askFromDB("UPDATE g17db.tblbook SET Popularity = ? WHERE BookID = ?;" ,key);
		
		
		
		
	}
	public static void UpdateBookExistingAmount(String ExistingAmount,String bookID){
		 /**update the existing amount of specific book in database*/
			 ArrayList<String> key = new ArrayList<String>();
			  
			
		      key.add(ExistingAmount);
		      key.add(bookID);
		  	  Functions.askFromDB("UPDATE g17db.tblbook SET ExistingAmount = ? WHERE BookID = ?;" ,key);
	 }
	public static void UpdateBookAmountAvailable(String AmountAvailable,String bookID){
		/**update the amount of the available books in the library in database*/
			 ArrayList<String> key = new ArrayList<String>();
			  
			
		      key.add(AmountAvailable);
		      key.add(bookID);
		  	  Functions.askFromDB("UPDATE g17db.tblbook SET AmountAvailable = ? WHERE BookID = ?;" ,key);
	}
	/**get from database from the books table the all information of specific book 
	 * @param BookID s
	 * */
		public static void ReceiveBookInformationByBookID(String BookID) throws Exception{
			
   	      ArrayList<String> key = new ArrayList<String>();
   	      ArrayList<String> askForResult = new ArrayList<String>();
  
   	      key.add(BookID);
   	      askForResult.add("*");         	  	   	  
		  Functions.askFromDB("SELECT * FROM g17db.tblbook WHERE BookID = ?;",key,askForResult);
		 
	}
	


/**Get and Set functions for all the parameters of book ,the Set functions checks if the input is legal */
	public String getBookID() {
		return this.BookID;
	}
	/**
	 * 
	 * @param AmountAvailable  d
	 */

	public void setAmountAvailable(String AmountAvailable) throws Exception {
		 if(AmountAvailable==null)		
			 throw new NullPointerException("Amount Available  is null");
			try {
		int number = Integer.parseInt(AmountAvailable);
		 if(!Functions.isNumeric(AmountAvailable) )	
				 throw new NumberFormatException("Vailable Amount  must be a number");
		 if(number<0)		
			 throw new Exception("Vailable Amount must be greater than or equal to zero");
			}
			catch(NumberFormatException exception)
			{
				System.out.println("Vailable Amount  must be a number");;
				
			}
		 this.AmountAvailable = AmountAvailable;
		
	 
	}
public void setAuthorName(String AuthorName) throws Exception {
		
	
		if(AuthorName==null)		
			 throw new NullPointerException("Author name is null");
		 char[] chars = AuthorName.toCharArray();
		 for (char c : chars) {
		        if(!Character.isLetter(c)) {
		        	 throw new Exception("Author name is must be leters");
		         
		        }
		    }
		
		if(AuthorName=="")
			 throw new NullPointerException("You can not leave this field empty");
		this.AuthorName = AuthorName;
	}

public void setCatalogNum(String catalogNum) throws Exception {
	 if(catalogNum==null)		
		 throw new NullPointerException("Book catalog Number is null");
	 try {
	int number = Integer.parseInt(catalogNum);
	 if(!Functions.isNumeric(catalogNum) )	
			 throw new NumberFormatException("Book catalog Number  must be a number");
	 if(number<0)		
		 throw new Exception("Book ID must be greater than or equal to zero");
	
			}
			catch(NumberFormatException exception)
			{
				System.out.println("Book catalog Number  must be a number");;
				
			}
	 this.CatalogNumber = catalogNum;

}
	public void setBookID(String BookID) throws Exception {
		 if(BookID==null)		
			 throw new NullPointerException("Book ID is null");
		 try {
		int number = Integer.parseInt(BookID);
		 if(!Functions.isNumeric(BookID) )	
				 throw new NumberFormatException("Book ID  must be a number");
		 if(number<0)		
			 throw new Exception("Book ID must be greater than or equal to zero");
		
				}
				catch(NumberFormatException exception)
				{
					System.out.println("Book ID  must be a number");;
					
				}
		 this.BookID = BookID;
	 
	}
	public String getBookName() {
		return BookName;
	}
	
	/**
	 * 
	 * @param BookName d
	 */
	public void setBookName(String BookName) {
		if(BookName==null)		
		 throw new NullPointerException("Book name is null");
		if(BookName=="")
			 throw new NullPointerException("You can not leave this field empty");
		this.BookName = BookName;
		  
		
	}

	public String getAuthorName() {
		return AuthorName;
		
	}


	
	public String getSubject() {
		return Subject;
	}
	

	public void setSubject(String Subject) {
		if(Subject==null)		  
			 throw new NullPointerException("Subject  is null");
		if(Subject=="")
			 throw new NullPointerException("You can not leave this field empty");
		this.Subject = Subject;
	}

	public String getDescription() {
		return Description;
	}



	public void setDescription(String Description) {
	 
	  if(Description==null)		
			 throw new NullPointerException("Description  is null");
	  if(Description=="")
			 throw new NullPointerException("You can not leave this field empty");
	  this.Description = Description;
	}

	
	public String getPopularity() {
		return Popularity;
	}



	public void setPopularity(String Popularity) throws Exception {
		 if(Popularity!="Normal"||Popularity!="Demanded")		
				this.Popularity = Popularity;
				  
				else throw new Exception("Popularity can be Demanded or Normal");
	}

	public String getExistingAmount() {
		return ExistingAmount;
	}


  

	public void setExistingAmount(String ExistingAmount) throws Exception {
		if(ExistingAmount==null)
			 throw new NullPointerException("Existing Amount name is null");
		try {
		int number = Integer.parseInt(ExistingAmount);


		 if(!Functions.isNumeric(ExistingAmount) )
				 throw new NumberFormatException("Existing Amount  must be a number");
		 if(number<0)
			 throw new Exception("Existing Amount  must be  greater than or equal to zero");
		}
		catch(NumberFormatException exception)
		{
			System.out.println("Existing Amount  must be a number");;

		}
		 this.ExistingAmount = ExistingAmount;
	}

	public String getAmountAvailable() { 
		return AmountAvailable;
	}



	
	public String getCatalogNum() {
		return CatalogNumber;
	}


	public String getTableOfContents() {
		return TableOfContents;
	}


	public String getEditionNumber() {
		return EditionNumber;
	}


	public String getLocation() {
		return Location;
	}


	/**
	 * 
	 * @param Location d
	 */
	public void setLocation(String Location) {
		
		if(Location==null)		
			throw new NullPointerException("Location name is null");
		this.Location = Location;
		
	}
	

	public String getArchiveStatus() {
		return ArchiveStatus;
	}


 
	public void setArchiveStatus(String ArchiveStatus) throws Exception {
		
		
		 if(ArchiveStatus!="Archived"||ArchiveStatus!="Frozen"||ArchiveStatus!="Normal")		
			 this.ArchiveStatus = ArchiveStatus;
				  
				else throw new Exception("Popularity can be Demanded or Normal");

	}

}