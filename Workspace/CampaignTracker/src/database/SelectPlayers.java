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
/*import model.Squadron.SquadronBuilder;*/

public interface SelectPlayers {
	
	public static Map<String, Player> select(int campaignId) {
		
		Map<String, Player>nameToPlayer = new HashMap<String, Player>(); //map for players
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
			//statements for selecting players and their children:
			CallableStatement playersStatement = connection.prepareCall("{CALL select_players(?)}");
			CallableStatement squadronStatement = connection.prepareCall("{CALL select_squadron(?)}");) {
			
			playersStatement.setInt(1, campaignId); //set input with campaignId
			ResultSet playersRS = playersStatement.executeQuery(); //execute statement
			
			//result sets for nested data:
			ResultSet squadronRS = null; //squadron result set
			
			while(playersRS.next()) {
				
				PlayerBuilder playerBuilder = new Player.PlayerBuilder(); //create new player builder
				String playerName = playersRS.getString("name"); //get player name
				playerBuilder.setName(playerName); //set player name
				playerBuilder.setScore(playersRS.getInt("score")); //set player score
				playerBuilder.setIsActive(playersRS.getBoolean("is_active")); //set if active
				playerBuilder.setCreated(playersRS.getTimestamp("created")); //set created
				
				int squadronIdCheck = playersRS.getInt("squadron_ID_check"); //check for squadron id
				
				//if valid squadron id was returned (> 0)
				if(squadronIdCheck > 0) {
					
					//set squadron statement input with squadron id:
					squadronStatement.setInt(1, squadronIdCheck); 
					squadronRS = squadronStatement.executeQuery(); //execute squadron query
					
					while(squadronRS.next()) { 
						
						/*
						SquadronBuilder squadronBuilder = new Squadron.SquadronBuilder(); //create squadron builder
						squadronBuilder.setAirForce(squadronRS.getString("airforce_name")); //set air force name
						squadronBuilder.setSkillPoints(squadronRS.getInt("skill_points")); //set skill points
						*/
						
						String airForce = squadronRS.getString("airforce_name"); //get air force name
						int skillPoints = squadronRS.getInt("skill_points"); //get skill points
					}
					
					
					
					////////////NEEXT DO SQUADRON PILOTS ADDED to Squadron
					//////////tghen logs added to squadron pilots
					//then missions (added to???????)
					
					//finally add squadron to player ++++++++++++
				}
				
				
				/** +++++++++++++++++++++++++++ THOUGHT ABOUT SAFE SETTERS: ++++++++++++++++++*/
				//pass the object in to the setter + the value you want to add to it, then add it inside trhe method & pass back the entered object!!! 
				//walah! :P
				
				//add built player to map:
				nameToPlayer.put(playerName, playerBuilder.build());
			}
				
		} catch(Exception e) { e.printStackTrace(); }
		
		return nameToPlayer; //return map of players
	}
}
