package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

/** select userID from db, based on given name & password.
 *  returns id of user if record is found, or 0 if not */

public interface SelectUserID {
	
	public static int select(String name, String password) {
		
		int statementResult = 0;
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
				
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall(
					"{CALL select_userID(?,?,?)}");) {
			
			 callableStatement.setString(1, name); //set input with player name
	         callableStatement.setString(2, password); //set input with player password
	         callableStatement.registerOutParameter(3, Types.INTEGER); //register the out param (userId)
	         callableStatement.execute(); //execute statement
	        
	         //holds the value of returned player id (or 0 if not found)
	         statementResult = callableStatement.getInt(3); 
				
		}catch(Exception e) { e.printStackTrace(); }
		
		//return result:
		return statementResult; 
	}
}