package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.AirForce;
import model.Campaign;
import model.Campaign.CampaignBuilder;
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
				
				//create campaign builder:
				/*CampaignBuilder campaignBuilder = new Campaign.CampaignBuilder(); 
				campaignBuilder.setId(campaignsRS.getInt("campaign_ID")); //set id
				campaignBuilder.setTurn(campaignsRS.getInt("turn")); //set turn
				campaignBuilder.setCreated(campaignsRS.getTimestamp("date_time")); //set created
				campaignBuilder.setHost(campaignsRS.getString("host_name")); //set host name
				
				//set period:
				campaignBuilder.setPeriod((new Period(
						Block.valueOf(campaignsRS.getString("period_block").toUpperCase()),
						campaignsRS.getInt("period_year"))));*/
				
				int campaignId = campaignsRS.getInt("campaign_ID"); //get id
				int turn = campaignsRS.getInt("turn"); //get turn
				Timestamp created = campaignsRS.getTimestamp("date_time"); //get created
				String host = campaignsRS.getString("host_name"); //get host name
				
				//get period:
				Period period = new Period(
						Block.valueOf(campaignsRS.getString("period_block").toUpperCase()),
						campaignsRS.getInt("period_year")); 
				
				//create event builder: //+++++++++++++++++++++++++++++ALL OF THIS could be gotten from nameToEvent!!! (it just isnt added in Campaigns controller when the cmapi)
				
				//++++++++++use getEvent() here ++++++++++++++++++++++++++++++++++++++
				//////////////////EventBuilder eventBuilder = new Event.EventBuilder();
				String eventName = campaignsRS.getString("event_name"); //get event name
				/////////////eventBuilder.setName(eventName); //set event name
				///////////////eventBuilder.setStartPeriod(nameToEvent.get(eventName).getStartPeriod()); //set start period
				Period startPeriod = nameToEvent.get(eventName).getStartPeriod(); //get start period
				//////////////eventBuilder.setEndPeriod(nameToEvent.get(eventName).getEndPeriod()); //set end period
				Period endPeriod = nameToEvent.get(eventName).getEndPeriod(); //get end period
				////////////////eventBuilder.setMaxTurns(campaignsRS.getInt("periods_count")); //set max turns
				///////////////eventBuilder.setMaxTurns(nameToEvent.get(eventName).getMaxTurns()); //set max turns ++++++++
				int maxTurns = nameToEvent.get(eventName).getMaxTurns(); //get max turns
				/////////////eventBuilder.setAirForces(nameToEvent.get(eventName).getAirForces()); //set air forces
				List<AirForce>airForces = nameToEvent.get(eventName).getAirForces(); //get air forces
				
				/////campaignBuilder.setEvent(eventBuilder.build()); //add event to campaign builder
				//////////campaignBuilder.setEvent(new Event(eventName, startPeriod, endPeriod, maxTurns, airForces)); //add event to campaign builder
				Event event = new Event(eventName, startPeriod, endPeriod, maxTurns, airForces); //create event
				//++++++++++++HAVE THIS BE multilined and passed directly into the constructor ++++++++++++++++++++++++
				
				//set player names statement input with campaign id:
				playerNamesStatement.setInt(1, campaignsRS.getInt("campaign_ID")); 
				playerNamesRS = playerNamesStatement.executeQuery(); //execute player names query
				
				
				Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //map for players involved
				
				while(playerNamesRS.next()) {
					///////add player with user name to campaign:
					////////////campaignBuilder.setPlayer(playerNamesRS.getString("name"));
					
					String playerName = playerNamesRS.getString("name"); //get player name
					
					//add player with name to map:
					nameToPlayer.putIfAbsent(playerName, new Player(playerName));
				}
				
				//add built campaign to campaigns:
				////////////campaigns.add(campaignBuilder.build());
				//add campaign to campaigns:
				campaigns.add(new Campaign(campaignId, event, period, turn, created, host, nameToPlayer));
			}
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return campaigns; //return campaigns
	}
}