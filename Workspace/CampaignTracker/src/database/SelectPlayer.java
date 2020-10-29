package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import model.Player;

public interface SelectPlayer {
	
	public static void select(Player player) {
		
		System.out.println(player.getName() + " " + player.getPswrd());
		
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
				
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall(
					"{CALL select_player(?,?,?)}");) {
			
			 callableStatement.setString(1, player.getName()); //set input with player name
	         callableStatement.setString(2, player.getPswrd()); //set input with player password
	         callableStatement.registerOutParameter(3, Types.INTEGER); //register the OUT param (playerId)
	         callableStatement.execute(); //execute statement
	        
	         int statementResult = callableStatement.getInt(3); //get the value of the OUT param
				
	         System.out.println("the result is: " + statementResult);
			
		}catch(Exception e) { e.printStackTrace(); }
		
	}
	

}
