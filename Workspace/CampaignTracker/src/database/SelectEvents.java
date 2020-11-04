package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Event.EventBuilder;

public interface SelectEvents {
	
public static List<Event> select() {
	
	//++++++++++++++++++++
	//https://www.roseindia.net/jdbc/Jdbc-nested-result-set.shtml#:~:text=The%20JDBC%20Nested%20Result%20Set,is%20the%20simplest%20join%20algorithm.
		
		List<Event>events = new ArrayList<Event>(); //list for events
		
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
			
			//create statement:
			CallableStatement eventsStatement = connection.prepareCall("{CALL select_events()}");
			CallableStatement airforcesStatement = connection.prepareCall("{CALL select_event_airforces(?)}");
		
			//create resultSet executing query:
			ResultSet eventsRS = eventsStatement.executeQuery();
			 ) { ////////FIX THIS TRY WITH RESOURCES!! :P +++++++++++++++++++++++
			ResultSet airforcesRS = null;
		
			while(eventsRS.next()) {
				
				airforcesStatement.setString(1, eventsRS.getString("event_name")); //set input with event id
				airforcesRS = airforcesStatement.executeQuery(); //execute query
				
				while(airforcesRS.next()) {
					System.out.println(" event airforce name: " + airforcesRS.getString("airforce_name"));
				}
				
				/*
				//add an event to events with data from current row:
				events.add(new Event.EventBuilder()
						.setName(eventsRS.getString("event_name"))
						.build());
				*/
				//System.out.println("event name: " + eventsRS.getString("event_name"));
				
				
			}
			
			System.out.println("events: " + events);
		
		}catch(Exception e) { e.printStackTrace(); }
		
		return events; //return events
	}
}
