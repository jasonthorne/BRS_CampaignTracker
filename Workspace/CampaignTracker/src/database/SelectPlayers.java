package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Map;

import model.Campaign;
import model.Campaign.CampaignBuilder;
import model.Player;

public interface SelectPlayers {
	
	public static Map<String, Player> select(Map<String, Player>nameToPlayer, int campaignId) {
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
			//statements for selecting players and their children:
			CallableStatement playersStatement = connection.prepareCall("{CALL select_players(?)}");
			/*CallableStatement playersStatement = connection.prepareCall("{CALL select_players(?)}");*/) {
			
			playersStatement.setInt(1, campaignId); //set input with campaignId
			ResultSet playersRS = playersStatement.executeQuery(); //execute statement
			
			//result sets for nested data:
			/////ResultSet playersRS = null; //players result set
			
			while(playersRS.next()) {  
				
				
				
			}
				
		} catch(Exception e) { e.printStackTrace(); }
		
		return nameToPlayer; //return campaign
	}
}
