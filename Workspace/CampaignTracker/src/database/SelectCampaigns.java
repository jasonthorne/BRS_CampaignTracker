package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Campaign;

public interface SelectCampaigns {
	
	public static List<Campaign> select() {
		
		List<Campaign>campaigns = new ArrayList<Campaign>(); //list for campaigns
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
				//statements for selecting campaigns and their children:
				CallableStatement campaignsStatement = connection.prepareCall("{CALL select_campaigns()}");
				/*CallableStatement periodsStatement = connection.prepareCall("{CALL select_event_periods(?)}");	
				CallableStatement airForcesStatement = connection.prepareCall("{CALL select_event_airforces(?)}");
				CallableStatement planesStatement = connection.prepareCall("{CALL select_airforce_planes(?)}");*/
				CallableStatement availabilitiesStatement = connection.prepareCall("{CALL select_plane_availabilities(?,?)}");
				ResultSet campaignsRS = campaignsStatement.executeQuery();) { //execute campaigns statement
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return campaigns;
	}
}