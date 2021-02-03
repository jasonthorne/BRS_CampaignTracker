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
import model.Campaign.CampaignBuilder;
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
				
				/*
				//create campaign builder:
				CampaignBuilder campaignBuilder = new Campaign.CampaignBuilder(); 
				campaignBuilder.setId(campaignsRS.getInt("campaign_ID")); //set id
				campaignBuilder.setTurn(campaignsRS.getInt("turn")); //set turn
				campaignBuilder.setCreated(campaignsRS.getTimestamp("date_time")); //set created
				campaignBuilder.setHost(campaignsRS.getString("host_name")); //set host name
				
				//set period:
				campaignBuilder.setPeriod((new Period(
						Block.valueOf(campaignsRS.getString("period_block").toUpperCase()),
						campaignsRS.getInt("period_year"))));
				
				//set event using nameToEvent:
				campaignBuilder.setEvent(nameToEvent.get(campaignsRS.getString("event_name")));*/
				
				
				int id = campaignsRS.getInt("campaign_ID"); //get id
				Event event = nameToEvent.get(campaignsRS.getString("event_name")); //get event
				
				//get period:
				Period period = new Period(
						Block.valueOf(campaignsRS.getString("period_block").toUpperCase()),
						campaignsRS.getInt("period_year"));
				
				int turn = campaignsRS.getInt("turn"); //get turn
				Timestamp created = (campaignsRS.getTimestamp("date_time")); //get created
				String host = campaignsRS.getString("host_name"); //get host name
				
				//=============================================
				//map for players:
				Map<String, Player>nameToPlayer = new HashMap<String, Player>();
				//=============================================
				
				//set player names statement input with campaign id:
				playerNamesStatement.setInt(1, campaignsRS.getInt("campaign_ID")); 
				playerNamesRS = playerNamesStatement.executeQuery(); //execute player names query
				
				while(playerNamesRS.next()) {
					//add player with name to campaign:
					////////////campaignBuilder.setPlayer(playerNamesRS.getString("name"));
					
					//==================
					//get name of player:
					String name = playerNamesRS.getString("name");
					nameToPlayer.put(name, new Player(name)); //add player to map
					//====================
					
				}
				
				
				
				//===========================================
				
				
				
				//add campaign to campaigns:
				campaigns.add(new Campaign(id, event, period, turn, created, host, nameToPlayer));
				
				//get event using nameToEvent:
				//Event event = nameToEvent.get(campaignsRS.getString("event_name"));
				
				
			
				//===========================================
				
				
				
				
				
				
				//add built campaign to campaigns:
				////////////campaigns.add(campaignBuilder.build());
			}
			
		} catch(Exception e) { e.printStackTrace(); }
		System.out.println(campaigns);
		return campaigns; //return campaigns
	}
}