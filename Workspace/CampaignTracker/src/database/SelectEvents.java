package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

import model.Event;

public interface SelectEvents {
	
public static void /*List<Event>*/ select() {
		
		//int statementResult = 0;
		
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
				
			//create statement:
			CallableStatement callableStatement = connection.prepareCall("{CALL select_events()}");
			//create resultSet executing query:
			ResultSet resultSet = callableStatement.executeQuery();) {
			
			while(resultSet.next()) {
				System.out.println("event name: " + resultSet.getString("name"));
			}
			 ///////callableStatement.setString(1, name); //set input with player name
	         /////////callableStatement.setString(2, pswrd); //set input with player password
	         /////////callableStatement.registerOutParameter(3, Types.INTEGER); //register the out param (playerId)
	         //callableStatement.execute(); //execute statement
	        
	         //holds the value of returned player id (or 0 if not found)
	         //statementResult = callableStatement.getInt(3); 
				
		}catch(Exception e) { e.printStackTrace(); }
		
		//return result:
		///return statementResult; 
	}

}
