package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;

/** inserts a new player to database ++++++++++++++?????????????? */ 
//+++++++++++++++++ IS THIS NEEDED? COuldnt the insert be done at thr end of closing the app?? maybe not, as other players need ot be given the details immediately of a new palyer, for ingame updating!!! 

public interface InsertPlayer {
	
	static void insert(int campaignId, int userId, Timestamp timestamp) {
		
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
			
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall("{CALL insert_player(?,?,?)}");) {
			
			 callableStatement.setInt(1, campaignId); //set input with campaign id
			 callableStatement.setInt(2, userId); //set input with user id
	         callableStatement.setTimestamp(3, timestamp); //set input with time stamp
	         callableStatement.execute(); //execute statement
	        
		}catch(Exception e) { e.printStackTrace(); }
		
		//+++++++++++++++++++UPDATE ALL LOGGED IN CLEINTS THAT THIS PLAYER IS NOW PRESENT IN THIS CAMPAIGN ++++++++++
	}
}