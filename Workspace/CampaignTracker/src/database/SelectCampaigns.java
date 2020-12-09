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
	
	public static List<Campaign> select(int userId) {
		
		List<Campaign>campaigns = new ArrayList<>(); //list for campaigns
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
				
			//create statement:
			CallableStatement statement = connection.prepareCall("{CALL select_campaigns(?)}");) {
			
			statement.setInt(1, userId); //set input with user id:
			ResultSet resultSet = statement.executeQuery(); //execute statement
			
			while(resultSet.next()) {
				
				//create campaign builder:
				CampaignBuilder campaignBuilder = new Campaign.CampaignBuilder(); 
				campaignBuilder.setId(resultSet.getInt("campaign_ID")); //set id
				campaignBuilder.setCreated(resultSet.getTimestamp("date_time")); //set created
				campaignBuilder.setHostName(resultSet.getString("host_name")); //set host name
				
				//set period:
				campaignBuilder.setPeriod((new Period(
						Block.valueOf(resultSet.getString("period_block").toUpperCase()),
						resultSet.getInt("period_year")))); 
				
				//create event builder:
				EventBuilder eventBuilder = new Event.EventBuilder(); 
				eventBuilder.setName(resultSet.getString("event_name")); //set event name
				eventBuilder.setMaxTurns(resultSet.getInt("periods_count")); //set max turns
				campaignBuilder.setEvent(eventBuilder.build()); //add event to campaign builder
				
				//if user was found in campaign:
				if(!resultSet.getString("user_name").equals("N/A")) {
					
					//add player with name to players:
					campaignBuilder.setPlayer(
							new Player.PlayerBuilder() 
								.setName(resultSet.getString("user_name")).build());
				}
				
				//add built campaign to campaigns:
				campaigns.add(campaignBuilder.build());
			}
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return campaigns; //return campaigns
	}
}