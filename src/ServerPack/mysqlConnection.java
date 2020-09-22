package ServerPack;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ClientPack.*;


public class mysqlConnection {

	
	//unchecked yet

	 public static Connection connectToDB()
	 	{
				try 
				{
		            Class.forName("com.mysql.jdbc.Driver").newInstance();
		        } catch (Exception ex) {/* handle the error*/}
		        
		        try 
		        {
		            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/g17db","root","orielgilo6");
		            //Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
		            System.out.println("SQL connection succeed");
		            //createTableCourses(conn);
		            return conn;
		     	} catch (SQLException ex) 
		     	    {/* handle any errors*/
		            System.out.println("SQLException: " + ex.getMessage());
		            System.out.println("SQLState: " + ex.getSQLState());
		            System.out.println("VendorError: " + ex.getErrorCode());
		            }
		        return null;
		}

	 
	 @SuppressWarnings("unchecked")
		public static ResultSet FuncView(Connection con ,Object msg)
		 {
	     int index = ((ArrayList<String>)msg).indexOf("###");
		 List<String> list = ((ArrayList<String>) msg).subList(0, index);
		 System.out.println(list);
			 PreparedStatement stmt;
			 ResultSet rs = null;
				try {
					
					String selectSQL = list.get(0);
					stmt = con.prepareStatement(selectSQL);
					for(int i =1;i< list.size();i++)
					{
							stmt.setString(i, list.get(i));
					}
					 rs = stmt.executeQuery();
					
				} catch (SQLException e) {	e.printStackTrace();}
					return rs;	
		 }
	 
	 
	 @SuppressWarnings("unchecked")
	public static void FuncUpdate(Connection con ,Object msg)
	 {	 
		 List<String> list = ((ArrayList<String>) msg);
		 System.out.println(list);
		 PreparedStatement stmt;
			try {
				
				String selectSQL = list.get(0);
				stmt = con.prepareStatement(selectSQL);
				for(int i =1;i< list.size();i++)
				{
						stmt.setString(i, list.get(i));
				}
				stmt.executeUpdate();
				System.out.println("update done");
				
			} catch (SQLException e) {	e.printStackTrace();}
	 }
		
}
