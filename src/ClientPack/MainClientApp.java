package ClientPack;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

import Controllers.ConnectToServerController;


public class MainClientApp extends Application{
	public static ClientHandling ClientOfLibrary = null;
	  public static ArrayList<String> message = new ArrayList<String>();


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        launch(args);	
	}

	@Override
	public void start(Stage arg0) throws Exception {	
	 // create StudentFrame
		ConnectToServerController aFrame = new ConnectToServerController();
		aFrame.start(arg0);	
	}
	/*public ArrayList<ArrayList<String>> func(ArrayList<ArrayList<String>> x, ArrayList<ArrayList<String>> y){
		ArrayList<ArrayList<String>> Z = new ArrayList<ArrayList<String>>();
		int cnt = 1,amounutofdigits,numberlength,amountofzero;
		String bookname = "",
			   userid = "",
			   messagesid = "",
			   arrivaldate = "",
			   bookid = "",
			   lastmessageid = "",
			   newmessageid = "";
		
	  	ArrayList<String> keyMessages = new ArrayList<String>();
	    ArrayList<String> askForResultMessages = new ArrayList<String>();

		  String sqlMessages = "SELECT * FROM g17db.tblmessages ORDER BY MessageID;";
		  askForResultMessages.add("MessageID");

		  ArrayList<ArrayList<String>> resultMessages = Functions.askFromDB(sqlMessages ,keyMessages,askForResultMessages);
		  int Messagessize = resultMessages.size();
		  lastmessageid = resultMessages.get(Messagessize-1).get(0);
		
		///////
	  	ArrayList<String> keyBookCopy = new ArrayList<String>();
	    ArrayList<String> askForResultBookCopy = new ArrayList<String>();

		  String sqlBookCopy = "SELECT * FROM g17db.tblbookcopy;";
		  askForResultBookCopy.add("BookCopyID");
		  askForResultBookCopy.add("BookID");

		  ArrayList<ArrayList<String>> resultbookcopy = Functions.askFromDB(sqlBookCopy ,keyBookCopy,askForResultBookCopy);
		  int bookcopysize = resultbookcopy.size();
		  
		  /////////
		  	ArrayList<String> keyBook = new ArrayList<String>();
		    ArrayList<String> askForResultBook = new ArrayList<String>();

			  String sqlBook = "SELECT * FROM g17db.tblbook;";
			  askForResultBook.add("BookID");
			  askForResultBook.add("BookName");

			  ArrayList<ArrayList<String>> resultbook = Functions.askFromDB(sqlBook ,keyBook,askForResultBook);
			  int booksize = resultbook.size();
			  int sizex = x.size();
			  int sizey = y.size();
			  
			  
			 for(int i=0;i<sizex;i++) {
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
				
				userid = y.get(i).get(1);
				
				newmessageid = "M";
				amounutofdigits = lastmessageid.length()-1; //should be 6
				numberlength = (int) (Math.log10(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt) + 1);
				amountofzero = amounutofdigits - numberlength;
				for(int b=0;b<amountofzero;b++) {
					newmessageid = newmessageid+"0";
				}
				newmessageid = newmessageid+(Integer.parseInt(lastmessageid.substring(1,lastmessageid.length()))+cnt);
				
				 Z.get(i).add(newmessageid);
				 Z.get(i).add(userid);
				 Z.get(i).add("You’ve missed the date of "+bookname+" return! Hance your membership has been Frozen.");
				 Z.get(i).add(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
				 Z.get(i).add("System");
				 Z.get(i).add("Unread");
				 cnt++;
			 }

		  return Z;
	}*/
}