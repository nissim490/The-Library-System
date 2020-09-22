package ServerPack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class automation implements Runnable {
	private int time;
	
    public automation(int time) {
		this.time = time;
		System.out.println("the auto run per "+time+" Seconds");
    }
    
    private Connection con = null; 
    
    ArrayList<ArrayList<String>> AUTO1FromBorrowBooksAndReaderToMessagesList(ArrayList<ArrayList<String>> x, ArrayList<ArrayList<String>> y){
    	ArrayList<ArrayList<String>> Z = new ArrayList<ArrayList<String>>();
    	ArrayList<String> tempZ;
   	     ArrayList<String> tempbookusers;
    	if(!(y.size()==0)) {
    		//if(y.get(0).size()>0) {
    			ArrayList<ArrayList<String>> booksofuser = new ArrayList<ArrayList<String>>();
     	   	   int cnt = 1,amounutofdigits,numberlength,amountofzero;
     	   	   String bookname = "",userid = "",messagesid = "",arrivaldate = "",bookid = "",lastmessageid = "",newmessageid = "", booklist = "";
     	   	   
     	   	     ArrayList<String> keyMessages = new ArrayList<String>();
     	   	     String sqlMessages = "SELECT MessageID FROM g17db.tblmessages ORDER BY MessageID;";
     	   	     keyMessages.add(sqlMessages);
     	   	     keyMessages.add("###");
     	   	      
     	   	     ArrayList<ArrayList<String>> resultMessages =  ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyMessages)); 
     	   	     int Messagessize = resultMessages.size();
     	   	     lastmessageid = resultMessages.get(Messagessize-1).get(0);
     	   	   
     	   	   ///////
     	   	       ArrayList<String> keyBookCopy = new ArrayList<String>();
     	   	    String sqlBookCopy = "SELECT BookCopyID,BookID FROM g17db.tblbookcopy;";
     	   	    keyBookCopy.add(sqlBookCopy);
     	   	    keyBookCopy.add("###");
     	   	   
     	   	     ArrayList<ArrayList<String>> resultbookcopy = ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyBookCopy)); 
     	   	     int bookcopysize = resultbookcopy.size();
     	   	     
     	   	     /////////
     	   	      ArrayList<String> keyBook = new ArrayList<String>();
     	   	      String sqlBook = "SELECT BookID,BookName FROM g17db.tblbook;";
     	   	      keyBook.add(sqlBook);
     	   	      keyBook.add("###");
     	   	     
     	   	      ArrayList<ArrayList<String>> resultbook =  ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyBook)); 
     	   	      int booksize = resultbook.size();
     	   	    
     	   	     if(!(x.size()==0)) {
     	   	    	 for(int i=0;i< x.size();i++) {
     	       	    	 for(int j=0;j<bookcopysize;j++) {
     	       	    	       if(x.get(i).get(1).equals(resultbookcopy.get(j).get(0))){
     	       	    	        bookid = resultbookcopy.get(j).get(1);
     	       	    	        break;
     	       	    	       }
     	       	    	  }
     	       	    	  for(int k = 0;k<booksize;k++) {
     	       	    	       if(resultbook.get(k).get(0).equals(bookid)) {
     	       	    	        bookname = resultbook.get(k).get(1);
     	       	    	        break;
     	       	    	       }
     	       	    	  }
     	       	    	     for(int p = 0;p<y.size();p++) {
     	       	    	    	 if(y.get(p).get(0).equals(x.get(i).get(2))) {
     	       	    	    		userid = y.get(p).get(1);
     	       	    	    		
     	       	    	    	 }
     	       	    	     }
     	       	    	     
     	       	    	     
     	       	    	     
     	       	    	     
     	           	    	     newmessageid = "M";
     	           	    	     amounutofdigits = lastmessageid.length()-1; //should be 6
     	           	    	     numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt) + 1);
     	           	    	     amountofzero = amounutofdigits - numberlength;
     	           	    	     for(int b=0;b<amountofzero;b++) {
     	           	    	      newmessageid = newmessageid+"0";
     	           	    	     }
     	           	    	     newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt);
     	       	    	  
     	       	    	     
     	       	    	     tempZ = new ArrayList<String>();
     	       	    	    // tempbookusers = new ArrayList<String>();
     	       	    	     
     	       	    	    	 tempZ.add(newmessageid);
     	       	    	    	 tempZ.add(userid);
     	       	    	    	 tempZ.add("Youve missed the date of "+bookname+" return! Hance your membership has been Frozen.");
     	       	    	    	 tempZ.add(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
     	       	    	    	 tempZ.add("System");
     	       	    	    	 tempZ.add("Unread");
     	       	    	    	 Z.add(tempZ);
     	       	    	    	 cnt++;
     	       	    	    	
     	       	   }  
     	   	     }    			
    	} 	 
   	     return Z;
 }
       
    ArrayList<ArrayList<String>> AUTO2FromBorrowBooksAndReaderToMessagesList(ArrayList<ArrayList<String>> x, ArrayList<ArrayList<String>> y){
    	ArrayList<ArrayList<String>> Z = new ArrayList<ArrayList<String>>();
    	ArrayList<String> tempZ;
   	     ArrayList<String> tempbookusers;
    	if(!(y.size()==0)) {
    		//if(y.get(0).size()>0) {
    			ArrayList<ArrayList<String>> booksofuser = new ArrayList<ArrayList<String>>();
     	   	   int cnt = 1,amounutofdigits,numberlength,amountofzero;
     	   	   String bookname = "",userid = "",messagesid = "",arrivaldate = "",bookid = "",lastmessageid = "",newmessageid = "", booklist = "";
     	   	   
     	   	     ArrayList<String> keyMessages = new ArrayList<String>();
     	   	     String sqlMessages = "SELECT MessageID FROM g17db.tblmessages ORDER BY MessageID;";
     	   	     keyMessages.add(sqlMessages);
     	   	     keyMessages.add("###");
     	   	      
     	   	     ArrayList<ArrayList<String>> resultMessages =  ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyMessages)); 
     	   	     int Messagessize = resultMessages.size();
     	   	     lastmessageid = resultMessages.get(Messagessize-1).get(0);
     	   	   
     	   	   ///////
     	   	       ArrayList<String> keyBookCopy = new ArrayList<String>();
     	   	    String sqlBookCopy = "SELECT BookCopyID,BookID FROM g17db.tblbookcopy;";
     	   	    keyBookCopy.add(sqlBookCopy);
     	   	    keyBookCopy.add("###");
     	   	   
     	   	     ArrayList<ArrayList<String>> resultbookcopy = ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyBookCopy)); 
     	   	     int bookcopysize = resultbookcopy.size();
     	   	     
     	   	     /////////
     	   	      ArrayList<String> keyBook = new ArrayList<String>();
     	   	      String sqlBook = "SELECT BookID,BookName FROM g17db.tblbook;";
     	   	      keyBook.add(sqlBook);
     	   	      keyBook.add("###");
     	   	     
     	   	      ArrayList<ArrayList<String>> resultbook =  ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyBook)); 
     	   	      int booksize = resultbook.size();
     	   	    
     	   	     if(!(x.size()==0)) {
     	   	    	 for(int i=0;i< x.size();i++) {
     	       	    	 for(int j=0;j<bookcopysize;j++) {
     	       	    	       if(x.get(i).get(1).equals(resultbookcopy.get(j).get(0))){
     	       	    	        bookid = resultbookcopy.get(j).get(1);
     	       	    	        break;
     	       	    	       }
     	       	    	  }
     	       	    	  for(int k = 0;k<booksize;k++) {
     	       	    	       if(resultbook.get(k).get(0).equals(bookid)) {
     	       	    	        bookname = resultbook.get(k).get(1);
     	       	    	        break;
     	       	    	       }
     	       	    	  }
     	       	    	     for(int p = 0;p<y.size();p++) {
     	       	    	    	 if(y.get(p).get(0).equals(x.get(i).get(2))) {
     	       	    	    		userid = y.get(p).get(1);
     	       	    	    		
     	       	    	    	 }
     	       	    	     }	         	    	     
     	           	    	     newmessageid = "M";
     	           	    	     amounutofdigits = lastmessageid.length()-1; //should be 6
     	           	    	     numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt) + 1);
     	           	    	     amountofzero = amounutofdigits - numberlength;
     	           	    	     for(int b=0;b<amountofzero;b++) {
     	           	    	      newmessageid = newmessageid+"0";
     	           	    	     }
     	           	    	     newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt);
     	       	    	  
     	       	    	     
     	       	    	     tempZ = new ArrayList<String>();
     	       	    	    // tempbookusers = new ArrayList<String>();
     	       	    	     
     	       	    	    	 tempZ.add(newmessageid);
     	       	    	    	 tempZ.add(userid);
     	       	    	    	 tempZ.add("Dont forget to return the "+bookname+" book to the library! It is due tomorrow.");
     	       	    	    	 tempZ.add(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
     	       	    	    	 tempZ.add("System");
     	       	    	    	 tempZ.add("Unread");
     	       	    	    	 Z.add(tempZ);
     	       	    	    	 cnt++;      	    	    	
     	       	   }  
     	   	     }			
    	}
   	     return Z;
 }
            
    ArrayList<ArrayList<String>> AUTO3FromBorrowBooksAndReaderToMessagesList(ArrayList<ArrayList<String>> x, ArrayList<ArrayList<String>> y){
    	ArrayList<ArrayList<String>> Z = new ArrayList<ArrayList<String>>();
    	ArrayList<String> tempZ;
    	ArrayList<String> tempbookusers;
    	if(!(y.size()==0)) {
    		//if(y.get(0).size()>0) {
    			ArrayList<ArrayList<String>> booksofuser = new ArrayList<ArrayList<String>>();
     	   	   int cnt = 1,amounutofdigits,numberlength,amountofzero;
     	   	   String bookname = "",userid = "",messagesid = "",arrivaldate = "",bookid = "",lastmessageid = "",newmessageid = "", booklist = "";
     	   	   
     	   	     ArrayList<String> keyMessages = new ArrayList<String>();
     	   	     String sqlMessages = "SELECT MessageID FROM g17db.tblmessages ORDER BY MessageID;";
     	   	     keyMessages.add(sqlMessages);
     	   	     keyMessages.add("###");
     	   	      
     	   	     ArrayList<ArrayList<String>> resultMessages =  ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyMessages)); 
     	   	     int Messagessize = resultMessages.size();
     	   	     lastmessageid = resultMessages.get(Messagessize-1).get(0);
     	   	   
     	   	   ///////
     	   	       ArrayList<String> keyBookCopy = new ArrayList<String>();
     	   	    String sqlBookCopy = "SELECT BookCopyID,BookID FROM g17db.tblbookcopy;";
     	   	    keyBookCopy.add(sqlBookCopy);
     	   	    keyBookCopy.add("###");
     	   	   
     	   	     ArrayList<ArrayList<String>> resultbookcopy = ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyBookCopy)); 
     	   	     int bookcopysize = resultbookcopy.size();
     	   	     
     	   	     /////////
     	   	      ArrayList<String> keyBook = new ArrayList<String>();
     	   	      String sqlBook = "SELECT BookID,BookName FROM g17db.tblbook;";
     	   	      keyBook.add(sqlBook);
     	   	      keyBook.add("###");
     	   	     
     	   	      ArrayList<ArrayList<String>> resultbook =  ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyBook)); 
     	   	      int booksize = resultbook.size();
     	   	    
     	   	     if(!(x.size()==0)) {
     	   	    	 for(int i=0;i< x.size();i++) 
     	   	    	 {
     	       	    	 for(int j=0;j<bookcopysize;j++) {
     	       	    	       if(x.get(i).get(1).equals(resultbookcopy.get(j).get(0))){
     	       	    	        bookid = resultbookcopy.get(j).get(1);
     	       	    	        break;
     	       	    	       }
     	       	    	  }
     	       	    	  for(int k = 0;k<booksize;k++) {
     	       	    	       if(resultbook.get(k).get(0).equals(bookid)) {
     	       	    	        bookname = resultbook.get(k).get(1);
     	       	    	        break;
     	       	    	       }
     	       	    	  }
     	       	    	  for(int p = 0;p<y.size();p++) {
     	       	    	    	 if(y.get(p).get(0).equals(x.get(i).get(2))) {
     	       	    	    		userid = y.get(p).get(1);
     	       	    	    		
     	       	    	    	 }
     	       	    	  }
     	       	    	     
     	       	    	     tempZ = new ArrayList<String>();
     	       	    	     tempbookusers = new ArrayList<String>();
     	       	    	     
     	       	    	    tempbookusers.add(userid);
     	       	    	    tempbookusers.add(bookname);
     	       	    	    booksofuser.add(tempbookusers);
     	       	    	    
     	       	   }
     	   	     }
     	   	    	int sizeofbookofusers = booksofuser.size();
	       	    	 Collections.sort(booksofuser, new Comparator<ArrayList<String>>() {    
	       	    	        @Override
	       	    	        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
	       	    	            return o1.get(0).compareTo(o2.get(0));
	       	    	        }               
	       	    	});
	       	    	 String useridPointer = booksofuser.get(0).get(0);
	       	    	 booklist = booksofuser.get(0).get(1);
	       	    	 int i;
	       	    	 for(i = 1;i<sizeofbookofusers;i++) {
	       	    		 if(useridPointer.equals(booksofuser.get(i).get(0))) {
	       	    			 booklist = booklist +", "+booksofuser.get(i).get(1);
	       	    		 }
	       	    		 else
	       	    		 {
	           	    	     tempZ = new ArrayList<>();
	           	    	     newmessageid = "M";
	           	    	     amounutofdigits = lastmessageid.length()-1; //should be 6
	           	    	     numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt) + 1);
	           	    	     amountofzero = amounutofdigits - numberlength;
	           	    	     for(int b=0;b<amountofzero;b++) {
	           	    	      newmessageid = newmessageid+"0";
	           	    	     }
	           	    	     newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt);
	       	    			 
	   	 
	       	    	    	 tempZ.add(newmessageid);
	       	    	    	 tempZ.add(booksofuser.get(i-1).get(0));
	       	    	    	 tempZ.add("Congratulations on your graduation! Please just dont forget to return the following books as soon as possible: return book list "+booklist);
	       	    	    	 tempZ.add(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
	       	    	    	 tempZ.add("System");
	       	    	    	 tempZ.add("Unread");
	       	    	    	 Z.add(tempZ);
	       	    	    	 cnt++;
	       	    			 useridPointer = booksofuser.get(i).get(0);
	       	    	    	 booklist = booksofuser.get(i).get(1);
	       	    		 }
	       	    	 }
	   	    	     tempZ = new ArrayList<>();
	   	    	     newmessageid = "M";
	   	    	     amounutofdigits = lastmessageid.length()-1; //should be 6
	   	    	     numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt) + 1);
	   	    	     amountofzero = amounutofdigits - numberlength;
	   	    	     for(int b=0;b<amountofzero;b++) {
	   	    	      newmessageid = newmessageid+"0";
	   	    	     }
	   	    	     newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt);
	       			 

	       	    	 tempZ.add(newmessageid);
	       	    	 tempZ.add(booksofuser.get(i-1).get(0));
	       	    	 tempZ.add("Congratulations on your graduation! Please just dont forget to return the following books as soon as possible: return book list "+booklist);
	       	    	 tempZ.add(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
	       	    	 tempZ.add("System");
	       	    	 tempZ.add("Unread");
	       	    	 Z.add(tempZ);	   	    	 
     	   	     			
    	}
    	   	 

   	     return Z;
 }
           
    ArrayList<ArrayList<String>> AUTO4FromBorrowBooksAndReaderToMessagesList(ArrayList<ArrayList<String>> y){
    	ArrayList<ArrayList<String>> Z = new ArrayList<ArrayList<String>>();
    	ArrayList<String> tempZ;
    	ArrayList<String> tempbookusers;
    	if(!(y.size()==0)) {
    		//if(y.get(0).size()>0) {
    			ArrayList<ArrayList<String>> booksofuser = new ArrayList<ArrayList<String>>();
     	   	   int cnt = 1,amounutofdigits,numberlength,amountofzero;
     	   	   String bookname = "",userid = "",messagesid = "",arrivaldate = "",bookid = "",lastmessageid = "",newmessageid = "", booklist = "";
     	   	   
     	   	     ArrayList<String> keyMessages = new ArrayList<String>();
     	   	     String sqlMessages = "SELECT MessageID FROM g17db.tblmessages ORDER BY MessageID;";
     	   	     keyMessages.add(sqlMessages);
     	   	     keyMessages.add("###");
     	   	      
     	   	     ArrayList<ArrayList<String>> resultMessages =  ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyMessages)); 
     	   	     int Messagessize = resultMessages.size();
     	   	     lastmessageid = resultMessages.get(Messagessize-1).get(0);
     	   	   
     	   	   ///////
     	   	       ArrayList<String> keyBookCopy = new ArrayList<String>();
     	   	    String sqlBookCopy = "SELECT BookCopyID,BookID FROM g17db.tblbookcopy;";
     	   	    keyBookCopy.add(sqlBookCopy);
     	   	    keyBookCopy.add("###");
     	   	   
     	   	     ArrayList<ArrayList<String>> resultbookcopy = ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyBookCopy)); 
     	   	     int bookcopysize = resultbookcopy.size();
     	   	     
     	   	     /////////
     	   	      ArrayList<String> keyBook = new ArrayList<String>();
     	   	      String sqlBook = "SELECT BookID,BookName FROM g17db.tblbook;";
     	   	      keyBook.add(sqlBook);
     	   	      keyBook.add("###");
     	   	     
     	   	      ArrayList<ArrayList<String>> resultbook =  ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,keyBook)); 
     	   	      int booksize = resultbook.size();
	
	     	   	  for(int i = 0;i<y.size();i++) {
	   	    		 tempZ = new ArrayList<>();
	   	    		 
	   	    		 newmessageid = "M";
	   	    	     amounutofdigits = lastmessageid.length()-1; //should be 6
	   	    	     numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt) + 1);
	   	    	     amountofzero = amounutofdigits - numberlength;
	   	    	     for(int b=0;b<amountofzero;b++) {
	   	    	      newmessageid = newmessageid+"0";
	   	    	     }
	   	    	     newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt);
	   	    		 
	   	    		 userid = y.get(i).get(1);
	   	    		 
		    	    	 tempZ.add(newmessageid);
		    	    	 tempZ.add(userid);
		    	    	 tempZ.add("One of your book orders has arrived. Please come and claim it.");
		    	    	 tempZ.add(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		    	    	 tempZ.add("System");
		    	    	 tempZ.add("Unread");
		    	    	 Z.add(tempZ);
		    	    	 cnt++;
	   	    		}
 			
    	}

   	     return Z;
 }
    
    ArrayList<ArrayList<String>> FromBorrowBooksAndReaderToMessagesList_Stub(ArrayList<ArrayList<String>> borrowbooks , ArrayList<ArrayList<String>> readers){
    	 ArrayList<ArrayList<String>> Messages = new ArrayList<ArrayList<String>>();
    	 ArrayList<String> temp = new ArrayList<String>();
    	 temp.add("M000082");
    	 temp.add("555555555");
    	 temp.add("Remember to return the book secret to the library until the return date");
    	 temp.add("04/11/2019");
    	 temp.add("Liron david");
    	 temp.add("Read");
    	 Messages.add(temp);
		return Messages;
    }
     
    ArrayList<ArrayList<String>> borrowBooksforStatusActive_DelayedNo_ReturnDateSmallFromToday(){
	      ArrayList<ArrayList<String>> ReturnList = new ArrayList<ArrayList<String>>();
		  String today = FormatOFTodayDateYMD();
		  String temp,tempday, tempmonth, tempyear;
    	  ArrayList<String> key = new ArrayList<String>();
	      key.add("SELECT * FROM g17DB.tblbookborrow WHERE Status = ? and BorrowedDelayed = ?;");
   	      key.add("Active");
   	      key.add("No");
   	      key.add("###");
   	   ArrayList<ArrayList<String>> list = ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,key));       
    	//  System.out.println("[borrowBooksforStatusActive_DelayedNo_ReturnDateSmallFromToday:list]"+list);
   	  for(int i=0; i < list.size();i++) {
	    	  tempday =  list.get(i).get(4).substring(0, 2);
	    	  tempmonth= list.get(i).get(4).substring(3,5);
	    	  tempyear = list.get(i).get(4).substring(6,10);  
	    	  temp = tempyear+"/"+tempmonth+"/"+tempday;
	    	  if(checkIfSmallThenToday(temp)) {
	    		   ReturnList.add(list.get(i));
	    	   }
   	  }
   	        
   	  System.out.println("[borrowBooksforStatusActive_DelayedNo_ReturnDateSmallFromToday:ReturnList]"+ReturnList);
   	    return  ReturnList;
   }  

    ArrayList<ArrayList<String>> borrowBooksforStatusInactive_DelayedYes_ActualReturnDateNotNull(){
   	 ArrayList<String> key = new ArrayList<String>();
	      key.add("SELECT * FROM g17DB.tblbookborrow WHERE Status = ? and BorrowedDelayed = ? and ActualReturnDate <> ?;");
  	      key.add("Inactive");
  	      key.add("Yes");
  	      key.add("");
  	      key.add("###");
  	     return ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,key)); 
  } 
    
    ArrayList<ArrayList<String>> FromborrowBooks_ReaderCardNumberToReadersIndfo(ArrayList<ArrayList<String>> ListOfborrowBooks){
 		 ArrayList<ArrayList<String>> ReturnList = new ArrayList<ArrayList<String>>();
 		 ArrayList<String> key = new ArrayList<String>();
     	for(int i =0; i < ListOfborrowBooks.size(); i++) {
    	         key.add("SELECT * FROM g17DB.tblreader WHERE ReaderCardNumber = ?;");
       	     key.add(ListOfborrowBooks.get(i).get(2));
       	     key.add("###");
       	     ReturnList.add(ResultSetToArrayList(mysqlConnection.FuncView(con ,key)));   
       	     key.clear();
     	}	
		List<ArrayList<String>> List = ReturnList.stream().distinct().collect(Collectors.toList());
		ReturnList = (ArrayList<ArrayList<String>>) List;		
 		return ReturnList;
  }  
   
    ArrayList<ArrayList<String>> FrombookordersWithOrderStatusArrivedAndOrderDateIsToday_ReaderCardNumberToReadersIndfo(){
		 ArrayList<ArrayList<String>> firstList = new ArrayList<ArrayList<String>>();
    	 ArrayList<String> firstkey = new ArrayList<String>();
		 firstkey.add("SELECT * FROM g17DB.tblbookorders WHERE OrderStatus = 'Arrived' and OrderDate = ?;");
		 firstkey.add(FormatOFTodayDateDMY());
		 firstkey.add("###");
		 firstList.addAll(ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,firstkey)));

 		 ArrayList<ArrayList<String>> ReturnList = new ArrayList<ArrayList<String>>();
 		 ArrayList<String> key = new ArrayList<String>();
     	for(int i =0; i < firstList.size(); i++) {
    	     key.add("SELECT * FROM g17DB.tblreader WHERE ReaderCardNumber = ?;");
       	     key.add(firstList.get(i).get(3));
       	     key.add("###");
       	     ReturnList.add(ResultSetToArrayList(mysqlConnection.FuncView(con ,key)));   
       	     key.clear();
     	}	
		List<ArrayList<String>> List = ReturnList.stream().distinct().collect(Collectors.toList());
		ReturnList = (ArrayList<ArrayList<String>>) List;		
 		return ReturnList;
  }  
        
    ArrayList<ArrayList<String>> FromMessages_UserIDWithCardStatusActiveToReadersIndfo(ArrayList<ArrayList<String>> ListOfMessages){
 		 ArrayList<ArrayList<String>> ReturnList = new ArrayList<ArrayList<String>>();
 		 ArrayList<String> key = new ArrayList<String>();
     	for(int i =0; i < ListOfMessages.size(); i++) {
    	     key.add("SELECT * FROM g17DB.tblreader WHERE UserID = ? and CardStatus = ?;");
       	     key.add(ListOfMessages.get(i).get(1));
       	     key.add("Active");
       	     key.add("###");
       	     ReturnList.add(ResultSetToArrayList(mysqlConnection.FuncView(con ,key)));   
       	     key.clear();
     	}	
     	List<ArrayList<String>> List = ReturnList.stream().distinct().collect(Collectors.toList());
		ReturnList = (ArrayList<ArrayList<String>>) List;	
 		return ReturnList;
  } 
    
    ArrayList<ArrayList<String>> FromMessages_UserID_ToReadersIndfo(ArrayList<ArrayList<String>> ListOfMessages){
 		 ArrayList<ArrayList<String>> ReturnList = new ArrayList<ArrayList<String>>();
 		 ArrayList<String> key = new ArrayList<String>();
     	for(int i =0; i < ListOfMessages.size(); i++) {
    	     key.add("SELECT * FROM g17DB.tblreader WHERE UserID = ?;");
       	     key.add(ListOfMessages.get(i).get(1));
       	     key.add("###");
       	     ReturnList.add(ResultSetToArrayList(mysqlConnection.FuncView(con ,key)));   
       	     key.clear();
     	}	
     	List<ArrayList<String>> List = ReturnList.stream().distinct().collect(Collectors.toList());
		ReturnList = (ArrayList<ArrayList<String>>) List;	
 		return ReturnList;
  } 
    
    ArrayList<ArrayList<String>> ResultSetTo2x2ArrayList(ResultSet result){
		 ArrayList<ArrayList<String>> ReturnList = new ArrayList<ArrayList<String>>();
 	      ResultSetMetaData meta;
			try {
				meta = result.getMetaData();
				while(result.next()){
			     	 ArrayList<String> Result = new ArrayList<String>();
					for(int i=0; i < meta.getColumnCount();i++){
						Result.add(result.getString(i+1));
						//System.out.println("ResultSetTo2x2ArrayList[Result][i]"+Result);
					}
					ReturnList.add(Result);
			      }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("ResultSetTo2x2ArrayList"+ReturnList);
			return ReturnList;
    }

    ArrayList<String> ResultSetToArrayList(ResultSet result){
     	 ArrayList<String> Result = new ArrayList<String>();
 	      ResultSetMetaData meta;
			try {
				meta = result.getMetaData();
				while(result.next()){
					for(int i=0; i < meta.getColumnCount();i++){
						Result.add(result.getString(i+1));
					}
			      }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("ResultSetToArrayList"+Result);
			return Result;
    }
      
    void AddMessagesByArrayList(ArrayList<ArrayList<String>> ListOfMessages) {
   	 ArrayList<String> key = new ArrayList<String>();
    	for(int i =0; i < ListOfMessages.size(); i++){
    		key.add("INSERT INTO `g17db`.`tblmessages` (`MessageID`, `UserID`, `Content`, `ArrivalDate`, `MessageFrom`, `Status`) VALUES (?,?,?,?,?,?);");


    		for(int j =0; j <ListOfMessages.get(i).size();j++){
    			key.add(ListOfMessages.get(i).get(j));
	    		}
		     	mysqlConnection.FuncUpdate(con,key);
		     	key.clear();
    	}
    }
  

    ArrayList<ArrayList<String>> UpdateReaderAmountofDelaysChangeFromActiveToFrozenMissedReturnToYes_ReturnTheActiveReadersList(ArrayList<ArrayList<String>> ListOfReaders) {
    		 ArrayList<ArrayList<String>> ReturnList = new ArrayList<ArrayList<String>>();
    		 ArrayList<String> key = new ArrayList<String>();
    	    	for(int i =0; i < ListOfReaders.size(); i++){   		
    	    		    if(ListOfReaders.get(i).get(3).equals("Active")) {
    	    		    	key.add("UPDATE `g17db`.`tblreader` SET `CardStatus` = 'Frozen', `AmountOfBookDelays` = ?, `MissedReturn` = 'Yes' WHERE (`ReaderCardNumber` = ?);");
    	    		    	ReturnList.add(ListOfReaders.get(i));
    	    		    }
    	    		    else key.add("UPDATE `g17db`.`tblreader` SET `AmountOfBookDelays` = ?, `MissedReturn` = 'Yes' WHERE (`ReaderCardNumber` = ?);");	 
    	    		    
    	    		    Integer count = Integer.parseInt(ListOfReaders.get(i).get(4))+1;
      	    		    key.add(count.toString());
      	    			key.add(ListOfReaders.get(i).get(0));
    			     	mysqlConnection.FuncUpdate(con,key);
    			     	key.clear();    		      			
    		    		}
				return ReturnList;    	
    	    }
    
    String FormatOFTodayDateYMD(){
         LocalDateTime todayDate = LocalDateTime.now(); 
    	 return todayDate.getYear()+"/"+todayDate.getMonthValue()+"/"+todayDate.getDayOfMonth();
    }
        
    String getNextDayYMD(String Today) {
        String tomrrow = "";
        try {
            Calendar today = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date = format.parse(Today);
            today.setTime(date);
            today.add(Calendar.DAY_OF_YEAR, 1);
            tomrrow = format.format(today.getTime());
        } catch (Exception e) {
            return tomrrow;
        }
        return tomrrow;
    }
       
    boolean checkIfSmallThenToday(String daycheck) {
        try {
            Calendar day = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            Date date = format.parse(daycheck);
            day.setTime(date);//set in the calendar
            
            
            Calendar todayday = Calendar.getInstance();
            String today =  new SimpleDateFormat("yyyy/MM/dd").format(LocalDateTime.now());
            Date todaydate = format.parse(today);
            todayday.setTime(todaydate);//set in the calendar
            
            if(day.before(todayday))return true;
                        
        } catch (Exception e) {
        }
         return false;
    }
       
    String FormatOFTodayDateDMY(){
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	Date date = new Date();
    	//System.out.println(dateFormat.format(date));
    	return dateFormat.format(date);
       // LocalDateTime todayDate = LocalDateTime.now(); 
   	// return todayDate.getDayOfMonth()+"/"+todayDate.getMonthValue()+"/"+todayDate.getYear();
   }
       
    String getNextDayDMY(String Today) {
       String tomrrow = "";
       try {
           Calendar today = Calendar.getInstance();
           DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
           Date date = format.parse(Today);
           today.setTime(date);
           today.add(Calendar.DAY_OF_YEAR, 1);
           tomrrow = format.format(today.getTime());
       } catch (Exception e) {
           return tomrrow;
       }
       return tomrrow;
   }
    
    String getBeforeDayDMY(String Today) {
        String tomrrow = "";
        try {
            Calendar today = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(Today);
            today.setTime(date);
            today.add(Calendar.DAY_OF_YEAR, -1);
            tomrrow = format.format(today.getTime());
        } catch (Exception e) {
            return tomrrow;
        }
        return tomrrow;
    }
    
    ArrayList<ArrayList<String>> borrowBooksforStatusActive_ReminderStatusWasntSent_ReturnDateTomrrow(){
    	 String tomrrow = getNextDayDMY(FormatOFTodayDateDMY());
      	 ArrayList<String> key = new ArrayList<String>();
   	      key.add("SELECT * FROM g17DB.tblbookborrow WHERE Status = ? and ReminderStatus = ? and ReturnDate = ?;");
     	      key.add("Active");
     	      key.add("Wasnt Sent");
     	      key.add(tomrrow);
     	      key.add("###");
     	     return ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,key)); 
     } 
    
    void UpdateBorrowBooksReminderstatusToSent(ArrayList<ArrayList<String>> ListOfborrowBooks) {
		 ArrayList<String> key = new ArrayList<String>();
		 String SQL = "UPDATE `g17db`.`tblbookborrow` SET `ReminderStatus` = 'Sent' WHERE (`BookBorrowID` = ?);";
	    	for(int i =0; i < ListOfborrowBooks.size(); i++){
	    		    key.add(SQL);
 	    			key.add(ListOfborrowBooks.get(i).get(0));
			     	mysqlConnection.FuncUpdate(con,key);
			     	key.clear();    		      			
		    		}    	
	    }
    
    ArrayList<ArrayList<String>> getGraduationAnnouncements(){
    	 ArrayList<String> key = new ArrayList<String>();
	      key.add("SELECT * FROM g17db.tblmessages where MessageFrom = 'Student Management System';");
 	      key.add("###");
 	     return ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,key)); 

    }

    ArrayList<ArrayList<String>> getReaderListThatHaveBooksBorrows_UpdateThereStatusto_Frozen_LockedForEmptyList(ArrayList<ArrayList<String>> ListOfReaders){
		 ArrayList<ArrayList<String>> ReturnList = new ArrayList<ArrayList<String>>();
 		 ArrayList<String> key = new ArrayList<String>();
 		 if(ListOfReaders.size() >0) {
 			 if(ListOfReaders.get(0).size()>0)
 			 {
		     	for(int i =0; i < ListOfReaders.size(); i++) {
		    	     key.add("SELECT * FROM g17DB.tblbookborrow WHERE ReaderCardNumber = ? AND Status = 'Active';");
		       	     key.add(ListOfReaders.get(i).get(0));
		       	     key.add("###");
		       	     ArrayList<ArrayList<String>> temp = ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,key));
		       	     if(temp.size()>0) {
		       	    	 UpdateReaderStatus(ListOfReaders.get(i), "Frozen");
		           	     ReturnList.add(ListOfReaders.get(i));
		       	     }
		       	     else  UpdateReaderStatus(ListOfReaders.get(i), "Locked");
		       	     key.clear();
		     	}	
 			 }
     	}
 		return ReturnList;
    }
         
    ArrayList<ArrayList<String>> getBorrowBooksOfReaderList(ArrayList<ArrayList<String>> ListOfReaders){
    	System.out.println("INSIDEgetBorrowBooksOfReader-ListListOfReaders:"+ListOfReaders);
		 ArrayList<ArrayList<String>> ReturnList = new ArrayList<ArrayList<String>>();
 		 ArrayList<String> key = new ArrayList<String>();
 		 if(ListOfReaders.size() >0) {
 			 if(ListOfReaders.get(0).size()>0)
 			 {
		     	for(int i =0; i < ListOfReaders.size(); i++) {
		    	     key.add("SELECT * FROM g17DB.tblbookborrow WHERE ReaderCardNumber = ?;");
		       	     key.add(ListOfReaders.get(i).get(0));
		       	     key.add("###");
		       	     ArrayList<ArrayList<String>> temp = ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,key));
		       	     if(temp.size()>0) {
		           	     ReturnList.addAll(temp);
		           	     System.out.println("getBorrowBooksOfReaderList in place"+i+"-"+temp);
		       	     }
		       	     key.clear();
		     	}	
 			 }
     	}
 		return ReturnList;
    }
    
    void UpdateReaderStatus(ArrayList<String> ListOfReader , String status){
    	  ArrayList<String> key = new ArrayList<String>();
		  String SQL = "UPDATE `g17db`.`tblreader` SET `CardStatus` = ? WHERE (`ReaderCardNumber` = ?);";
		  key.add(SQL);
		  key.add(status);
    	  key.add(ListOfReader.get(0));
	      mysqlConnection.FuncUpdate(con,key);
	      key.clear();
    }
    
    void deleteLinesFromBookorders(){
    	  ArrayList<String> key = new ArrayList<String>();
	      key.add("SELECT * FROM g17DB.tblbookorders WHERE OrderStatus = ? and ArrivedDate = ?;");
 	      key.add("Arrived");
 	      key.add(getBeforeDayDMY(getBeforeDayDMY(FormatOFTodayDateDMY())));
 	      key.add("###");
 	      ArrayList<ArrayList<String>> listForDelete = ResultSetTo2x2ArrayList(mysqlConnection.FuncView(con ,key)); 
 	      
 	      if(listForDelete.size()>0) {
	 	      for(int i=0; i < listForDelete.size();i++)
	 	      {
		 	      ArrayList<String> Tempkey = new ArrayList<String>();
				  String SQL = "DELETE FROM `g17db`.`tblbookorders` WHERE (`OrderID` = ?);";
				  Tempkey.add(SQL);
				  Tempkey.add(listForDelete.get(i).get(0));
			      mysqlConnection.FuncUpdate(con,Tempkey);
			      Tempkey.clear();    	  
	 	      }
 	      }
 	      else System.out.println("There are no orders to delete");

    	
    }
	
    void UpdateBookBorowDelayedToYes(ArrayList<ArrayList<String>> ListOfborrowBooks){
   	 ArrayList<String> key = new ArrayList<String>();
	 String SQL = "UPDATE `g17db`.`tblbookborrow` SET `BorrowedDelayed` = 'Yes' WHERE (`BookBorrowID` = ?);";
    	for(int i =0; i < ListOfborrowBooks.size(); i++){
    		    key.add(SQL);
	    			key.add(ListOfborrowBooks.get(i).get(0));
		     	mysqlConnection.FuncUpdate(con,key);
		     	key.clear();    		      			
	    		}    	
    }
    
    
	@Override
	public void run() {
		  ///////////////////////////////////////////////////////////////////////////////////////////
    	      con = mysqlConnection.connectToDB();// Direct link to the dataBase for this thread
    	      ArrayList<ArrayList<String>> ListOfborrowBooks = new ArrayList<ArrayList<String>>();
    	      ArrayList<ArrayList<String>> ListOfReaders = new ArrayList<ArrayList<String>>();
    	      ArrayList<ArrayList<String>> ListOfMessages = new ArrayList<ArrayList<String>>();
					while(true) {
					// TODO Auto-generated method stub
				//////////////////////First automation of Delay update and status for readers//////////////////////////////
				ListOfborrowBooks = borrowBooksforStatusActive_DelayedNo_ReturnDateSmallFromToday();
				System.out.println("ListOfborrowBooks:"+ListOfborrowBooks);
				UpdateBookBorowDelayedToYes(ListOfborrowBooks);
				ListOfReaders = FromborrowBooks_ReaderCardNumberToReadersIndfo(ListOfborrowBooks);
				System.out.println("ListOfReaders"+ListOfReaders);
				ListOfReaders = UpdateReaderAmountofDelaysChangeFromActiveToFrozenMissedReturnToYes_ReturnTheActiveReadersList(ListOfReaders);
				System.out.println("NewListOfReaders:OnlyWhatWasActives"+ListOfReaders);
				ListOfborrowBooks = getBorrowBooksOfReaderList(ListOfReaders);//Get the updated list of BorrowBook on the new readers list	
				System.out.println("NewListOfReaders:ListOfborrowBooks"+ListOfborrowBooks);
				System.out.println("[1]ListOfborrowBooks"+ListOfborrowBooks);
				System.out.println("[1]ListOfReaders"+ListOfReaders);
				ListOfMessages = AUTO1FromBorrowBooksAndReaderToMessagesList(ListOfborrowBooks,ListOfReaders);
				System.out.println("[1]ListOfMessages:"+ListOfMessages);
				AddMessagesByArrayList(ListOfMessages);//The appropriate message inserts for the message table
				ListOfborrowBooks.clear(); ListOfReaders.clear();  ListOfMessages.clear();		
				////////////////////////////////////////////////////////////////////////////////////////////////////////////	
				ListOfborrowBooks = borrowBooksforStatusActive_ReminderStatusWasntSent_ReturnDateTomrrow();
				System.out.println("[2]ListOfborrowBooks:"+ListOfborrowBooks);
				UpdateBorrowBooksReminderstatusToSent(ListOfborrowBooks);//Update BorrowBooks Reminder status To Sent
				ListOfReaders = FromborrowBooks_ReaderCardNumberToReadersIndfo(ListOfborrowBooks);
				System.out.println("[2]ListOfReaders"+ListOfReaders);
				ListOfMessages = AUTO2FromBorrowBooksAndReaderToMessagesList(ListOfborrowBooks,ListOfReaders);
				AddMessagesByArrayList(ListOfMessages);
				ListOfborrowBooks.clear(); ListOfReaders.clear();  ListOfMessages.clear();
				////////////////////////////////////////////////////////////////////////////////////////////////////////////	
				ListOfMessages = getGraduationAnnouncements();
				System.out.println("[3]ListOfMessages"+ListOfMessages);
				ListOfReaders = FromMessages_UserID_ToReadersIndfo(ListOfMessages);
				System.out.println("[3]ListOfReaders"+ListOfReaders);
				ListOfReaders = getReaderListThatHaveBooksBorrows_UpdateThereStatusto_Frozen_LockedForEmptyList(ListOfReaders);
				System.out.println("[3]getReaderListThatHaveBooksBorrows_UpdateThereStatusto_Frozen_LockedForEmptyList"+ListOfReaders);//readers that had books in borrows and changed to frozen	
				ListOfborrowBooks = getBorrowBooksOfReaderList(ListOfReaders);
				System.out.println("[3]ListOfborrowBooks:"+ListOfborrowBooks);	
				ListOfMessages = AUTO3FromBorrowBooksAndReaderToMessagesList(ListOfborrowBooks,ListOfReaders);
				System.out.println("[3]ListOfMessages:FromBorrowBooksAndReaderToMessagesList"+ListOfMessages);
				AddMessagesByArrayList(ListOfMessages);
				ListOfborrowBooks.clear(); ListOfReaders.clear();  ListOfMessages.clear();
				////////////////////////////////////////////////////////////////////////////////////////////////////////////
				 deleteLinesFromBookorders();
				 ListOfReaders = FrombookordersWithOrderStatusArrivedAndOrderDateIsToday_ReaderCardNumberToReadersIndfo();
				 ListOfMessages = AUTO4FromBorrowBooksAndReaderToMessagesList(ListOfReaders);
				 System.out.println("[4]ListOfMessages:FromBorrowBooksAndReaderToMessagesList"+ListOfMessages);
				 AddMessagesByArrayList(ListOfMessages);
				 ListOfborrowBooks.clear(); ListOfReaders.clear();  ListOfMessages.clear();
				////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
				try {
						Thread.sleep(time*1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				 }
				}   	  
	    }


