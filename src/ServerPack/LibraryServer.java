package ServerPack;
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.awt.Desktop;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import common.Functions;
import common.MyFile;

//import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;

import ocsf.server.*;

public class LibraryServer extends AbstractServer 
{
  //Class variables *************************************************

  final public static int DEFAULT_PORT = 5555;
 // public String messageForGui = null;
  
  //Constructors ****************************************************

  public LibraryServer(int port) 
  {
    super(port);
  }

  //Instance methods ************************************************
  
  public void handleMessageFromClient(Object msg, ConnectionToClient client)
  {
	  System.out.println("[handleMessageFromClient]Message received: " + msg + " from " + client);	  
	  if(msg instanceof ArrayList<?>)
	  {
    	  Connection con = mysqlConnection.connectToDB();
		  ArrayList<String> Result = null;	  
		  ArrayList<String> temp = (ArrayList<String>)msg;
		  
	       if(temp.get(0).equals("AskForConnection"))
		  {
			  temp.remove(0);   
		  }
		  	  
		  else if(temp.get(0).equals("AskForUpdate"))
		  {
			  temp.remove(0);   
			  mysqlConnection.FuncUpdate(con, temp);
		  }
		  
		  else if(temp.get(0).equals("AskForResult")){
			  Result = new ArrayList<String>();
			   temp.remove(0);
			   ResultSet rs = mysqlConnection.FuncView(con, temp);
			   int index = ((ArrayList<String>)msg).indexOf("###");
				 List<String> list = temp.subList(index+1 ,temp.size());
				 System.out.println(list);
			   System.out.println("im here");
			   try {
				ResultSetMetaData meta =  rs.getMetaData();
				if(list.get(0).equals("*"))
				{   while(rs.next()){
					for(int i=0; i < meta.getColumnCount();i++){
						Result.add(rs.getString(i+1));
					}
					 Result.add("#");
			      }
				}
				
				else while(rs.next())   
				   {	for(int i =0;i< list.size();i++)
						{
								Result.add(rs.getString(list.get(i)));
					 	}
				         Result.add("#");
				   }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			   sendToSpecificClient(Result, client);
		  }
	       
		  else if(temp.get(0).equals("AskForFile")) {
			   temp.remove(0);
				  String url= temp.get(0);
					  String lastWord = url.substring(url.lastIndexOf("\\")+1);
					  System.out.println(lastWord);
					  MyFile file= new MyFile(lastWord);
					  String LocalfilePath=url;
						
					  try{
				
						      File newFile = new File (LocalfilePath);
						      		      
						      byte [] mybytearray  = new byte [(int)newFile.length()];
						      FileInputStream fis = new FileInputStream(newFile);
						      BufferedInputStream bis = new BufferedInputStream(fis);			  
						      
						      file.initArray(mybytearray.length);
						      file.setSize(mybytearray.length);
						      
						      bis.read(file.getMybytearray(),0,mybytearray.length);
						      sendToSpecificClient(file,client);		      
						    }
						catch (Exception e) {
							System.out.println("Error send (Files)msg) to Server");
						}
					  
				  }

		  
	  }  
	  
	  
	  else if(msg instanceof MyFile) {
		  System.out.println("im file here");
		  int fileSize =((MyFile)msg).getSize(); 
		  byte[] mybytearray = new byte[fileSize];
		  Functions.msgFile = (MyFile)msg;
		  FileOutputStream fos;

			try {	
				fos = new FileOutputStream("C:\\Users\\Oriel\\eclipse-workspace\\Prototype-G17-13\\src\\PDF\\"+((MyFile)msg).getFileName());
				 BufferedOutputStream bos = new BufferedOutputStream(fos);	   
				  try {
					 
					bos.write(((MyFile)msg).getMybytearray(), 0, fileSize);
					bos.close();
					fos.close();
					 System.out.println("success");
			 
					 Desktop desktop = Desktop.getDesktop();
	        
				        //let's try to open PDF file
				      //  File file = new File("C:\\Users\\1\\Desktop\\dataclient\\"+((MyFile)msg).getFileName());
				      
				        
				       // if(file.exists()) desktop.open(file);
				    }

					
				 catch (IOException e) {
					
					e.printStackTrace();
				}
				  
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		  
		  
	  }
  }
  
  
  public void sendToSpecificClient(Object msg , ConnectionToClient client)
  {
    Thread[] clientThreadList = getClientConnections();

    for (int i=0; i<clientThreadList.length; i++)
    {	
      try
      {
    	  if(((ConnectionToClient)clientThreadList[i]).equals(client)) ((ConnectionToClient)clientThreadList[i]).sendToClient(msg);
      }
      catch (Exception ex) {}
    }
  }
	  
  protected void serverStarted()
  {
    System.out.println
      ("Server listening for connections on port " + getPort());
   // messageForGui = "Server listening for connections on port " + getPort();
  }
  
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
   // messageForGui = "Server has stopped listening for connections.";
  }
  
}
//End of LibraryServer class