package common;
import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.SimpleDateFormat;
//import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import ClientPack.MainClientApp;
//import sun.print.resources.serviceui;
public class Functions {
	final static String DATE_FORMAT = "dd-MM-yyyy";
	public static Integer lock = 1;
	public static ArrayList<String> msg = null;
	public static MyFile msgFile = null;
	
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;   
	  }  
	  return true;  
	}
	
	public static boolean Checkid(String id) throws Exception 
	 {
      
        int sum, bikoret;
        
      
        
        if(id.length()==9)
        {    
            sum = idVerification(id);
            bikoret = Character.getNumericValue(id.charAt(8));
        
            if((sum+bikoret)%10==0) {
               
            return true;
            }
            else {
           	 throw new Exception("The ID is ilegal!!");
           
             
            }
        }
        else
        {
          
            throw new Exception("An Israeli ID must contain 9 numbers. ");
    
        }
        
    }
    
    public static int idVerification(String id) {
        String a;
        int sum=0, i, j, num;
        
        for(i=0; i<8; i++)
        {
            if(i==0 || i%2==0)
            {
                num = Character.getNumericValue(id.charAt(i));
                sum = sum+num;
            }
            else
            {
                num = Character.getNumericValue(id.charAt(i));
                if((num*2)>10)
                {
                    a = Integer.toString(num*2);
                    for(j=0; j<2; j++)
                    {
                        num = Character.getNumericValue(a.charAt(j));
                        sum = sum+num;
                    }
                }
                else
                    sum = sum+(num*2);
            }
        }
        
        return sum;
    }
    
    public static boolean validateJavaDate(String strDate) throws Exception
    {
 	/* Check if date is 'null' */
 	if (strDate.trim().equals(""))
 	{
 	    return true;
 	} 
 	/* Date is not 'null' */
 	else
 	{
 	    /*
 	     * Set preferred date format,
 	     * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
 	    SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
 	    sdfrmt.setLenient(false);
 	    /* Create Date object
 	     * parse the string into date 
              */
 	    try
 	    {
 	    	 
 	        Date javaDate = sdfrmt.parse(strDate); 
 	       
 	    }
 	    /* Date format is invalid */
 	    catch (Exception e)
 	    {
 	    	
 	    	 throw new Exception("Date"+" is Invalid Date format");
 	       
 	       
 	    }
 	    /* Return true if date format is valid */
 		
 	    return true;
 	}
    }
	
    
     
    
    public static ArrayList<ArrayList<String>> askFromDB(String sql , ArrayList<String> key,ArrayList<String> askForResult) {
    	
    	Thread t = new Thread(new Runnable() {	
			@Override
			public void run() {
				synchronized (lock) {
				 if(sql.toUpperCase().contains("SELECT"))
		    	  {
		    	  key.add(0, "AskForResult");
		    	  key.add(1, sql);
		    	  ArrayList<String> str = new ArrayList<String>();
		    	  str.addAll(key);
		      	  str.add("###");
		      	  str.addAll(askForResult);
		    	  MainClientApp.ClientOfLibrary.handleMessageFromClientUI(str);
		    	  try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	  } else
					try {
						throw new Exception("try to get a result from d.b without select");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
    	}
		});	
    	t.start();
    	try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ArrayList<ArrayList<String>> ReturnList = new ArrayList<ArrayList<String>>();
  	    ArrayList<String> perRun = new ArrayList<String>();
    	for(int i =0; i < msg.size();i++){
    		
    		if(!msg.get(i).equals("#"))perRun.add(msg.get(i));
    		else {
    			System.out.println("temp:"+perRun);
    			ArrayList<String> temp = new ArrayList<String>();
    			temp.addAll(perRun);
    			ReturnList.add(temp);
    			perRun.clear();
    		}
    	}
    	System.out.println(ReturnList);
		return ReturnList;
    	
    	 
    }
    
    
    
 public static void askFromFILE(String URL){	
    	Thread t = new Thread(new Runnable() {	
			@Override
			public void run() {
				
				synchronized (lock) {
			  	  ArrayList<String> str = new ArrayList<String>();
			  	  str.add(0, "AskForFile");
			  	 str.add(1, URL);
			  	  MainClientApp.ClientOfLibrary.handleMessageFromClientUI(str); 	
				}
			}
    	});
    	t.start();
    	try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
 
 
 public static void UplodeFILE(File file){	
 	Thread t = new Thread(new Runnable() {	
			@Override
			public void run() {
				
				synchronized (lock) {		
					
					
					  String url = file.getPath();
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
						  	  MainClientApp.ClientOfLibrary.handleMessageFromClientUI(file); 	
						    }
						catch (Exception e) {
							System.out.println("Error send (Files)msg) to Server");
						}
					  
				  }
					
				}
			
 	});
 	t.start();
 	try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
    
    public static void askFromDB(String sql , ArrayList<String> key){
    	
    	Thread t = new Thread(new Runnable() {	
			@Override
			public void run() {
				
				synchronized (lock) {
    	
			  	  key.add(0, "AskForUpdate");
			  	  key.add(1, sql);
			  	  ArrayList<String> str = new ArrayList<String>();
			  	  str.addAll(key);
			  	  MainClientApp.ClientOfLibrary.handleMessageFromClientUI(str); 	
				}
			}
    	});
    	t.start();
    	try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
}
