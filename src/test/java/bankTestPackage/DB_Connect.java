package bankTestPackage;

import java.sql.*;

import org.testng.annotations.Test;

public class DB_Connect {

	String dbUrl ="jdbc:mysql://localhost:3306/bank_project";
	String username ="Admin";
	String password ="12345";
	@Test
	public void databaseconnect() throws SQLException, ClassNotFoundException
	{
		Connection con = DriverManager.getConnection(dbUrl,"root","Raja#123");
	//	Class.forName("com.mysql.cj.jdbc.Driver");
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery("select * from customer");
		//= stmt.executeQuery(query);							
        
 		// While Loop to iterate through all data and print results		
		while (rs.next()){
	        		String custno = rs.getString(1);								        
                    String custname = rs.getString(2);					                               
                    String custAdd = rs.getString(3);
                    System. out.println(custno+"  "+custname+" "+custAdd);		
            }		
			 // closing DB Connection		
			con.close();	
	}
}
