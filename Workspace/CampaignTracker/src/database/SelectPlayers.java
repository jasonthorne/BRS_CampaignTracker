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
			//statements for selecting campaign and it's children:
			CallableStatement campaignStatement = connection.prepareCall("{CALL select_players(?)}");
			/*CallableStatement playersStatement = connection.prepareCall("{CALL select_players(?)}");*/) {
			
			////campaignStatement.setInt(1, campaign.getId()); //set input with name
			ResultSet campaignRS = campaignStatement.executeQuery(); //execute statement
			
			//result sets for nested data:
			ResultSet playersRS = null; //players result set
			
			while(campaignRS.next()) {  
				
				//grab data known already to passed campaign+++++++++++++++++
				
			}
				
		} catch(Exception e) { e.printStackTrace(); }
		
		return nameToPlayer; //return campaign
	}
}
