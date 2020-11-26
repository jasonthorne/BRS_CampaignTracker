package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public interface InsertCampaign {
	
	static void insert(String eventName, int playerId) {
			
		/////////////////////int statementResult = 0;
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
			
			 //create statement:
			 CallableStatement callableStatement = connection.prepareCall(
					"{CALL insert_campaign(?,?,?)}");) {
			
			 callableStatement.setString(1, eventName); //set input with name
			 callableStatement.setInt(2, playerId); //set input with password
			 
			 
	         callableStatement.setTimestamp(3, new Timestamp(
	        		 Calendar.getInstance().getTimeInMillis())); //set input with time stamp
	        
			
			 /*
			 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 String currentTime = df.format(new Date());
			 Timestamp timestamp = Timestamp.valueOf(currentTime);
			 
			 callableStatement.setTimestamp(3, timestamp); //set input with time stamp*/
	         callableStatement.execute(); //execute statement
	         
	         //holds the value of inserted player id (or 0 if attempted to insert pre-existing name or password)
	         //statementResult = callableStatement.getInt(3); 
			
		}catch(Exception e) { e.printStackTrace(); }
		
		//return result:
		/////////////return statementResult; 
	}

}
