package com.brs.events;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

import com.brs.ConnectDB;

public interface InsertEvents {
	
	static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_event(?)}"); //create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
			Arrays.asList(Event.values()).forEach((event)->{ //forEach event in list of events:
				try {
					callableStatement.setString(1, event.toString()); //set input param
					callableStatement.execute(); //execute statement
					System.out.println(callableStatement.getString(1)); //print response
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
	}
}
