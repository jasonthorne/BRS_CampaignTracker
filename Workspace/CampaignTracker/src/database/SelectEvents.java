package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Event.EventBuilder;
import model.EventAirForce;
import model.Period;
import model.Period.Block;

public interface SelectEvents {

	public static List<Event> select() {
		
		List<Event>events = new ArrayList<Event>(); //list for events
		
		try (Connection connection = ConnectDB.getConnection();) { //connect to DB
			
			//prepare select_events():
			CallableStatement callableStatement = connection.prepareCall("{CALL select_events()}");
			
			//execute select_events(), storing returned result set:
			ResultSet eventsRS = callableStatement.executeQuery();
			
			//prepare select_event_airforces(?): 
			callableStatement = connection.prepareCall("{CALL select_event_airforces(?)}");
			
			ResultSet airForcesRS = null; //event air forces result set
			
			while(eventsRS.next()) {
				
				EventBuilder eventBuilder = new Event.EventBuilder(); //create new event builder
				eventBuilder.setName(eventsRS.getString("event_name")); //add event name
				
				//add start period:
				eventBuilder.setStartPeriod(new Period(
						Block.valueOf(eventsRS.getString("event_start_block").toUpperCase()),
						eventsRS.getInt("event_start_year")));
				
				//add end period:
				eventBuilder.setEndPeriod(new Period(
						Block.valueOf(eventsRS.getString("event_end_block").toUpperCase()),
						eventsRS.getInt("event_end_year")));
				
				System.out.println("event_name: " + eventsRS.getString("event_name"));
				System.out.println("event_ID: " + eventsRS.getInt("event_ID"));
				System.out.println("event_start_block: " + eventsRS.getString("event_start_block"));
				System.out.println("event_start_year: " + eventsRS.getInt("event_start_year"));
				System.out.println("event_end_block: " + eventsRS.getString("event_end_block"));
				System.out.println("event_end_year: " + eventsRS.getInt("event_end_year"));
				
				List<EventAirForce>eventAirForces = new ArrayList<>(); //create list for event air forces
				
				callableStatement.setInt(1, eventsRS.getInt("event_ID")); //set input with event id
				airForcesRS = callableStatement.executeQuery(); //execute event air forces query
				
				while(airForcesRS.next()) {
					
					//add event air force to eventAirforces:
					eventAirForces.add(new EventAirForce.EventAirForceBuilder()
										.setAirForceName(airForcesRS.getString("airforce_name"))
										.setHasHomeAdv(airForcesRS.getBoolean("home_advantage_value"))
										.build());
				}
				
				//add event air forces to event builder:
				eventBuilder.setEventAirForces(eventAirForces);
				
				//add built event to events:
				events.add(eventBuilder.build());
			}
			
			System.out.println("events: " + events); //=============+++++++++++++
		
		} catch(Exception e) { e.printStackTrace(); }
		
		return events; //return events
	}
}
