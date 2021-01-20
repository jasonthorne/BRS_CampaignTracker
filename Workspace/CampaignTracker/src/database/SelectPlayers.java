package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import model.Campaign;
import model.Campaign.CampaignBuilder;
import model.Player;
import model.Player.PlayerBuilder;
import model.Squadron;
import model.Squadron.SquadronBuilder;

public interface SelectPlayers {
	
	public static Map<String, Player> select(/*Map<String, Player>nameToPlayer,*/ int campaignId) {
		
		Map<String, Player>nameToPlayer = new HashMap<String, Player>(); //map for players
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
			//statements for selecting players and their children:
			CallableStatement playersStatement = connection.prepareCall("{CALL select_players(?)}");
			/*CallableStatement playersStatement = connection.prepareCall("{CALL select_players(?)}");*/) {
			
			playersStatement.setInt(1, campaignId); //set input with campaignId
			ResultSet playersRS = playersStatement.executeQuery(); //execute statement
			
			//result sets for nested data:
			/////ResultSet playersRS = null; //players result set
			
			while(playersRS.next()) {  
				
				PlayerBuilder playerBuilder = new Player.PlayerBuilder(); //create new player builder
				playerBuilder.setName(playersRS.getString("player_name")); //set player name
				playerBuilder.setScore(playersRS.getInt("player_score")); //set player score
				playerBuilder.setIsActive(playersRS.getBoolean("player_is_active")); //set if active
				playerBuilder.setCreated(playersRS.getTimestamp("player_created")); //set created
				
				SquadronBuilder squadronBuilder = new Squadron.SquadronBuilder(); //create squadron builder
				
			}
				
		} catch(Exception e) { e.printStackTrace(); }
		
		return nameToPlayer; //return campaign
	}
}
