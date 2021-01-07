package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

import model.Campaign;

/** inserts a new campaign to database, returning its id for local storage */

public interface InsertCampaign {
	
	static int insert(String eventName, int userId, Timestamp timestamp) {
		
		int campaignId = 0;
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
			
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall(
					"{CALL insert_campaign(?,?,?,?)}");) {
			
			 callableStatement.setString(1, eventName); //set input with event name
			 callableStatement.setInt(2, userId); //set input with user id
	         callableStatement.setTimestamp(3, timestamp); //set input with time stamp
	         callableStatement.registerOutParameter(4, Types.INTEGER); //register the out param (campaignId)
	         callableStatement.execute(); //execute statement
	         
	         //get id of inserted campaign:
	         campaignId = callableStatement.getInt(4);
	      
		}catch(Exception e) { e.printStackTrace(); }
		
		return campaignId; //return campaign id
	}
}