package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

/** attempts to insert player to db, using name & password given by user.
 *  if successful, returns the id of newly inserted player (a number > 0).
 *  if not successful (due to pre-existing name or password) then returns 0  */

public interface InsertPlayer {
	
	static int insert(String name, String password) {
		
		int statementResult = 0;
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
				
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall(
					"{CALL insert_player(?,?,?)}");) {
			
			 callableStatement.setString(1, name); //set input with name
	         callableStatement.setString(2, password); //set input with password
	         callableStatement.registerOutParameter(3, Types.INTEGER); //register the out param (playerId)
	         callableStatement.execute(); //execute statement
	         
	         //holds the value of inserted player id (or 0 if attempted to insert pre-existing name or password)
	         statementResult = callableStatement.getInt(3); 
			
		}catch(Exception e) { e.printStackTrace(); }
		
		//return result:
		return statementResult; 
	}
}