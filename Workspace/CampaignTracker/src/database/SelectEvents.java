package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Event.EventBuilder;
import model.EventAirForce;
import model.EventAirForce.EventAirForceBuilder;
import model.Period;
import model.Period.Block;

public interface SelectEvents {

	public static List<Event> select() {
		
		List<Event>events = new ArrayList<Event>(); //list for events
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
			//statements for selecting events and their nested data:
			CallableStatement eventsStatement = connection.prepareCall("{CALL select_events()}");
			CallableStatement airForcesStatement = connection.prepareCall("{CALL select_event_airforces(?)}");
			CallableStatement planesStatement = connection.prepareCall("{CALL select_airforce_planes(?)}");
			ResultSet eventsRS = eventsStatement.executeQuery();) { //execute events statement
			
			//result sets for nested data:
			ResultSet airForcesRS = null; //event air forces result set
			ResultSet planesRS = null; //event air force planes result set
			
			while(eventsRS.next()) {
				
				EventBuilder eventBuilder = new Event.EventBuilder(); //create new event builder
				eventBuilder.setEventName(eventsRS.getString("event_name")); //add event name
				
				//add start period:
				eventBuilder.setStartPeriod(new Period(
						Block.valueOf(eventsRS.getString("event_start_block").toUpperCase()),
						eventsRS.getInt("event_start_year")));
				
				//add end period:
				eventBuilder.setEndPeriod(new Period(
						Block.valueOf(eventsRS.getString("event_end_block").toUpperCase()),
						eventsRS.getInt("event_end_year")));
				
				//create list for event air forces:
				List<EventAirForce>eventAirForces = new ArrayList<>(); 
				
				airForcesStatement.setInt(1, eventsRS.getInt("event_ID")); //set input with event id
				airForcesRS = airForcesStatement.executeQuery(); //execute event air forces query
				
				while(airForcesRS.next()) {
					
					//create event air force builder:
					EventAirForceBuilder eventAirForceBuilder = new EventAirForce.EventAirForceBuilder()
							.setAirForceName(airForcesRS.getString("airforce_name")) //add air force name
							.setHasHomeAdv(airForcesRS.getBoolean("home_advantage_value")); //add home adv value
					
					/*
					//add event air force to eventAirforces:
					eventAirForces.add(
							new EventAirForce.EventAirForceBuilder()
								.setAirForceName(airForcesRS.getString("airforce_name"))
								.setHasHomeAdv(airForcesRS.getBoolean("home_advantage_value"))
								//++++++++++++++++++STORE AIRFORCEID HERE TOO! ++++++++++++++++++++
								.build());*/
					
					System.out.println("airForcesRS.getInt(airforce_ID) " + airForcesRS.getInt("airforce_ID"));
			
					planesStatement.setInt(1, airForcesRS.getInt("airforce_ID")); //set input with air force id
					planesRS = planesStatement.executeQuery(); //execute planes query
					
					while(planesRS.next()) {
						System.out.println("plane_name: " + planesRS.getString("plane_name"));
						System.out.println("airforce_plane_ID: " + planesRS.getString("airforce_plane_ID"));
					}
					
				}
				
				//add event air forces to event builder:
				eventBuilder.setEventAirForces(eventAirForces);
				
				//add built event to events:
				events.add(eventBuilder.build());
			}
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return events; //return events
	}
}
