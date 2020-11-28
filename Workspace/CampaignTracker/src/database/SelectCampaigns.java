package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import model.Campaign;
import model.Campaign.CampaignBuilder;
import model.Event;
import model.Period;
import model.Event.EventBuilder;
import model.Period.Block;
import model.Plane.Status;
import model.Player;

public interface SelectCampaigns {
	
	public static List<Campaign> select() {
		
		List<Campaign>campaigns = new ArrayList<Campaign>(); //list for campaigns
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
			//statements for selecting campaigns and their players:
			CallableStatement campaignsStatement = connection.prepareCall("{CALL select_campaigns()}");
			CallableStatement playersStatement = connection.prepareCall("{CALL select_campaign_players(?)}");	
			ResultSet campaignsRS = campaignsStatement.executeQuery(); //execute campaigns statement
			) {
			
			ResultSet playersRS = null;
			
			while(campaignsRS.next()) {
				
				//create campaign builder:
				CampaignBuilder campaignBuilder = new Campaign.CampaignBuilder(); 
				campaignBuilder.setId(campaignsRS.getInt("campaign_ID")); //set id
				campaignBuilder.setCreated(campaignsRS.getTimestamp("date_time")); //set created
				campaignBuilder.setHostName(campaignsRS.getString("host_name")); //set host name
				
				//set period:
				campaignBuilder.setPeriod((new Period(
						Block.valueOf(campaignsRS.getString("period_block").toUpperCase()),
						campaignsRS.getInt("period_year")))); 
				
				//create event builder:
				EventBuilder eventBuilder = new Event.EventBuilder(); 
				eventBuilder.setName(campaignsRS.getString("event_name")); //set event name
				eventBuilder.setMaxTurns(campaignsRS.getInt("periods_count")); //set max turns
				campaignBuilder.setEvent(eventBuilder.build()); //add event to campaign builder
				
				//create map for campaign players:
				Map<String, Player>playerNameToPlayer = new TreeMap<String, Player>();
				
				//set players statement input with campaign id:
				playersStatement.setInt(1, campaignsRS.getInt("campaign_ID")); 
				playersRS = playersStatement.executeQuery(); //execute players query
				
				while(playersRS.next()) {
					//playersRS.ge
				}
				
			}
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return campaigns;
	}
}