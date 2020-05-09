package com.brs.statuses;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

import com.brs.ConnectDB;
import com.brs.Insert;

public interface InsertStatuses {
	
	static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_status(?)}"); //create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
			System.out.println("------------------");
			System.out.println("Statuses:\n"); 
			
			Arrays.asList(Status.values()).forEach((status)->{ //forEach status in list of statuses:
				try {
					callableStatement.setString(1, status.toString()); //set input param
					callableStatement.execute(); //execute statement
					System.out.println("Inserted: " + callableStatement.getString(1)); //print response
				}catch(Exception e) { e.printStackTrace(); }
			});
				
		 }catch(Exception e) { e.printStackTrace(); }
		 finally {
			 if (connection != null) {	//finally, try close connection:
				 try {
					 connection.close(); 
				 } catch (SQLException e) { e.printStackTrace(); } 
			 }
		 }
	};
}
