package ClientPack;

import ocsf.client.*;
import common.*;

import java.awt.Desktop;
import java.io.*;
import java.util.*;  

public class ClientHandling extends AbstractClient
{

  //Constructors ****************************************************

  public ClientHandling(String host, int port) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    openConnection();
  }

  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  synchronized (Functions.lock) {
	  System.out.println("[handlemessagefromserver]msg: "+msg);
	  
	  if(msg instanceof ArrayList)Functions.msg = (ArrayList<String>) msg;
	  
	  
	  else if(msg instanceof MyFile) {
		  System.out.println("im file here");
		  int fileSize =((MyFile)msg).getSize(); 
		  byte[] mybytearray = new byte[fileSize];
		  Functions.msgFile = (MyFile)msg;
		  FileOutputStream fos;

			try {
				String URL = System.getProperty("user.home") + "\\Desktop\\";
				fos = new FileOutputStream(URL+((MyFile)msg).getFileName());
				 BufferedOutputStream bos = new BufferedOutputStream(fos);	   
				  try {
					 
					bos.write(((MyFile)msg).getMybytearray(), 0, fileSize);
					bos.close();
					fos.close();
					 System.out.println("success");
					 Desktop desktop = Desktop.getDesktop();
	        
				        //let's try to open PDF file
				        File file = new File(URL+((MyFile)msg).getFileName());
				        if(file.exists()) desktop.open(file);
				    }

					
				 catch (IOException e) {
					
					e.printStackTrace();
				}
				  
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
		  
		  
	  }

      Functions.lock.notify();
      }
  }

  
  
  
 
  
  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  public void handleMessageFromClientUI(Object message)  
  {
    try
    {
    	//System.out.println("[handleMessageFromClientUI]message: "+message);
    	//System.out.println("handleMessageFromClientUI "+Thread.activeCount());
    	sendToServer(message);
    	//message.clear();

    }
    catch(IOException e)
    {
      //clientUI.display("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /**
   * This method terminates the client.
   */
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
     //UpdateStatusController.ConnectionFlag  = false;
   // System.exit(0);
  }
}
//End of ClientHandling class
