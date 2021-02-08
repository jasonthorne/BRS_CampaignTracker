package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;

/** inserts a new player to database ++++++++++++++?????????????? */ 
//+++++++++++++++++ IS THIS NEEDED? COuldnt the insert be done at thr end of closing the app?? maybe not, as other players need ot be given the details immediately of a new palyer, for ingame updating!!! 

public interface InsertPlayer {
	
	static void insert(int campaignId, int userId, Timestamp timestamp) {
		
		//////int playerId = 0;
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
			
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall("{CALL insert_player(?,?,?)}");) {
			/*
			 callableStatement.setString(1, eventName); //set input with event name
			 callableStatement.setInt(2, userId); //set input with user id
	         callableStatement.setTimestamp(3, timestamp); //set input with time stamp
	         callableStatement.registerOutParameter(4, Types.INTEGER); //register the out param (campaignId)
	         callableStatement.execute(); //execute statement
	         */
			
	         //get id of inserted campaign:
	         campaignId = callableStatement.getInt(4);
	      
		}catch(Exception e) { e.printStackTrace(); }
		
		///return campaignId; //return campaign id
		
		//+++++++++++++++++++UPDATE ALL LOGGED IN CLEINTS THAT THIS PLAYER IS NOW PRESENT IN THIS CAMPAIGN ++++++++++
	}

}
