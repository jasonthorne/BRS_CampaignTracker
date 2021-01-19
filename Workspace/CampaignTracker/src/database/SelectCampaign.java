package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import model.Campaign;
import model.Campaign.CampaignBuilder;

public interface SelectCampaign {

	public static Campaign select(Campaign campaign) {
		
		//create campaign builder:
		CampaignBuilder campaignBuilder = new Campaign.CampaignBuilder(); 
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
			//statements for selecting campaign and it's children:
			CallableStatement campaignStatement = connection.prepareCall("{CALL select_campaign(?)}");
			CallableStatement playersStatement = connection.prepareCall("{CALL select_players(?)}");) {
			
			campaignStatement.setInt(1, campaign.getId()); //set input with name
			ResultSet campaignRS = campaignStatement.executeQuery(); //execute statement
			
			//result sets for nested data:
			ResultSet playersRS = null; //players result set
			
			if(campaignRS.next()) {
				
				//add pulled data from existing campaign:
				
				
			}else{
				//throw exception as more than 1 result was returned:
				throw new Exception(); } 
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return campaignBuilder.build(); //return campaign
	}
}
