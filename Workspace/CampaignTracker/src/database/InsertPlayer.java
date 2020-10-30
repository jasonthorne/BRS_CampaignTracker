package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

/**  +++++++++++++ figure out better way of making these. IE have them hidden from anything that doesnt need em! :P  +++++++*/

public interface InsertPlayer {
	
	static int insert(String name, String pswrd) {
		
		int statementResult = 0;
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
				
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall(
					"{CALL insert_player(?,?,?)}");) {
			
			 callableStatement.setString(1, name); //set input with name
	         callableStatement.setString(2, pswrd); //set input with password
	         callableStatement.registerOutParameter(3, Types.INTEGER); //register the out param (playerId)
	         callableStatement.execute(); //execute statement
	         
	         //holds the value of returned player id ////////////////+++++++++reword :P(or 0 if unique column found)
	         statementResult = callableStatement.getInt(3); 
			
		}catch(Exception e) { e.printStackTrace(); }
		
		//return result:
		return statementResult; 
	}
}