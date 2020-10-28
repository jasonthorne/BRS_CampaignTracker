package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public interface InsertPlayer {
	
	static void insert(String name, String password) {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_player(?,?)}"); //create statement
	        callableStatement.setString(1, name); //set input with name
	        callableStatement.setString(2, password); //set input with password
	        try {
		    	callableStatement.execute(); //execute statement
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