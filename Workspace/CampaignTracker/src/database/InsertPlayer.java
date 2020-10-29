package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

/**  +++++++++++++ figure out better way of making these. IE have them hidden from anything that doesnt need em! :P  +++++++*/

public interface InsertPlayer {
	
	static void insert(String name, String password) {
		 
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
				
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall(
					"{CALL insert_player(?,?)}");) {
			
			 callableStatement.setString(1, name); //set input with name
	         callableStatement.setString(2, password); //set input with password
	         callableStatement.execute(); //execute statement
			
		}catch(Exception e) { e.printStackTrace(); }
	}
}