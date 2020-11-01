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
		
		List<Event>events = new ArrayList<Event>(); //list for events
		
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
			//create statement:
			CallableStatement callableStatement = connection.prepareCall("{CALL select_events()}");
			//create resultSet executing query:
			ResultSet resultSet = callableStatement.executeQuery();) {
			
			while(resultSet.next()) {
				
				//add an event to events with data from current row:
				events.add(new Event.EventBuilder()
						.setName(resultSet.getString("name"))
						.build());
				
				System.out.println("event name: " + resultSet.getString("name"));
				
			}
		
		}catch(Exception e) { e.printStackTrace(); }
		
		return events; //return events
	}
}
