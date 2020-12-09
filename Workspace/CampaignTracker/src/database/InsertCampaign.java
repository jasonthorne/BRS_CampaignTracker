package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

import model.Campaign;

public interface InsertCampaign {
	
	static void insert(Campaign campaign, int userId) {
			
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
			
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall(
					"{CALL insert_campaign(?,?,?)}");) {
			
			 callableStatement.setString(1, campaign.getEventName()); //set input with event name
			 callableStatement.setInt(2, userId); //set input with user id
	         callableStatement.setTimestamp(3, campaign.getCreated()); //set input with time stamp
	         callableStatement.execute(); //execute statement
	      
		}catch(Exception e) { e.printStackTrace(); }
	}
}