package com.brs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

import com.brs.planes.Plane;

public interface InsertPlayer_TEST {
	
	static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			
			String name = "Bob";
			String password = "password";
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_player(?,?)}"); //create statement
	        callableStatement.setString(1, name); 
	        callableStatement.setString(2, password); 
	       
	        try {
		    	callableStatement.execute(); //execute statement //++++++++++++++++++++++++++++++++++
			}catch(Exception e) { e.printStackTrace(); }
	
				
		 }catch(Exception e) { e.printStackTrace(); }
		 finally {
			 if (connection != null) {	//finally, try close connection:
				 try {
					 connection.close(); 
				 } catch (SQLException e) { e.printStackTrace(); } 
			 }
		 }
	}

}
