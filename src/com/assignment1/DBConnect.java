package com.assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnect {
	static Connection con=null;
	static Statement stmt =null;
	public  Statement dbConnect() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");  
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shapes?useSSL=false","root","admin");  
		stmt=con.createStatement();
		return stmt;
	}
	
	public  void dbClose() throws Exception{
		stmt.close();
		con.close();
	}
}
