package database;

import java.sql.Connection;

public interface InsertCampaign {
	
	static int insert(int playerId, String eventName) {
			
		int statementResult = 0;
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
			/*	
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall(
					"{CALL insert_player(?,?,?)}");*/) {
			
			/* callableStatement.setString(1, name); //set input with name
	         callableStatement.setString(2, pswrd); //set input with password
	         callableStatement.registerOutParameter(3, Types.INTEGER); //register the out param (playerId)
	         callableStatement.execute(); //execute statement
	         
	         //holds the value of inserted player id (or 0 if attempted to insert pre-existing name or password)
	         statementResult = callableStatement.getInt(3); 
			*/
		}catch(Exception e) { e.printStackTrace(); }
		
		//return result:
		return statementResult; 
	}

}
