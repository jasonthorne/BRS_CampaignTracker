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
				playerBuilder.setName(playersRS.getString("name")); //set player name
				playerBuilder.setScore(playersRS.getInt("score")); //set player score
				playerBuilder.setIsActive(playersRS.getBoolean("is_active")); //set if active
				playerBuilder.setCreated(playersRS.getTimestamp("created")); //set created
				
				
				/*
				SquadronBuilder squadronBuilder = new Squadron.SquadronBuilder(); //create squadron builder
				squadronBuilder.setSkillPoints(playersRS.getInt("squadron_skill_points")); //set skill points
				/////int squadronId = playersRS.getInt("squadron_ID"); //get squadron id
				
				int airForceIdCheck = playersRS.getInt("squadron_airforceID"); //get air force id
				
				//if player has selected an air force:
				if(airForceIdCheck > 0) {
					squadronBuilder.setAirForce(playersRS.getString("airforce_name")); //set air force name
					
					//if so then there COULD be a squadron +++++++++++++++
					
					
					
				}*/
				
				int squadronIdCheck = playersRS.getInt("squadron_ID_check"); //check for squadron id
				
				//if valid squadron id was returned (> 0)
				if(squadronIdCheck > 0) {
					
					//////squadronBuilder.setAirForce(playersRS.getString("airforce_name")); //set air force name
					
					//if so then there COULD be a squadron +++++++++++++++
					
					
					
				}
				
				
				/** +++++++++++++++++++++++++++ THOUGHT ABOUT SAFE SETTERS: ++++++++++++++++++*/
				//pass the object in to the setter + the value you want to add to it, then add it inside trhe method & pass back the entered object!!! 
				//walah! :P
				
			}
				
		} catch(Exception e) { e.printStackTrace(); }
		
		return nameToPlayer; //return campaign
	}
}
