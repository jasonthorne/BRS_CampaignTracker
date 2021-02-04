package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.AirForce;
import model.Campaign;
/*import model.Campaign.CampaignBuilder;*/
import model.Event;
import model.Period;
/*import model.Event.EventBuilder;*/
import model.Period.Block;
import model.Plane.Status;
import model.Player;

/** ?????? ++++++++++++++++++  */

public interface SelectCampaigns {
	
	public static List<Campaign> select(Map<String, Event>nameToEvent) {
		
		List<Campaign>campaigns = new ArrayList<>(); //list for campaigns
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
				
			//statements for selecting campaigns and their players:
			CallableStatement campaignsStatement = connection.prepareCall("{CALL select_campaigns()}");
			CallableStatement playerNamesStatement = connection.prepareCall("{CALL select_player_names(?)}");
			ResultSet campaignsRS = campaignsStatement.executeQuery(); ) { //execute campaigns statement
			
			ResultSet playerNamesRS = null; //player names result set
			
			while(campaignsRS.next()) {
				
				Map<String, Player>nameToPlayer = new HashMap<String, Player>(); //map for players
				
				//set player names statement with campaign id:
				playerNamesStatement.setInt(1, campaignsRS.getInt("campaign_ID")); 
				playerNamesRS = playerNamesStatement.executeQuery(); //execute player names query
				
				while(playerNamesRS.next()) {
					String name = playerNamesRS.getString("name"); //get player name
					nameToPlayer.put(name, new Player(name)); //add player to map
				}
				
				//add campaign to campaigns:
				campaigns.add(new Campaign(
						campaignsRS.getInt("campaign_ID"), //id
						nameToEvent.get(campaignsRS.getString("event_name")), //event 
						new Period( //period
								Block.valueOf(campaignsRS.getString("period_block").toUpperCase()),
								campaignsRS.getInt("period_year")), 
						campaignsRS.getInt("turn"), //turn
						campaignsRS.getTimestamp("date_time"), //created
						campaignsRS.getString("host_name"), //host
						nameToPlayer)); //nameToPlayer
			}
			
		} catch(Exception e) { e.printStackTrace(); }
		////////System.out.println(campaigns); //++++++++++++++++++++++++++
		return campaigns; //return campaigns
	}
}