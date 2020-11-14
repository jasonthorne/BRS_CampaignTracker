package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Event;
import model.Event.EventBuilder;
import model.AirForce;
import model.AirForce.AirForceBuilder;
import model.Period;
import model.Period.Block;
import model.Plane;
import model.Plane.PlaneBuilder;
import model.Plane.Status;

public interface SelectEvents {

	public static List<Event> select() {
		
		List<Event>events = new ArrayList<Event>(); //list for events
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
			//statements for selecting events and their nested data:
			CallableStatement eventsStatement = connection.prepareCall("{CALL select_events()}");
			CallableStatement airForcesStatement = connection.prepareCall("{CALL select_event_airforces(?)}");
			CallableStatement planesStatement = connection.prepareCall("{CALL select_airforce_planes(?)}");
			CallableStatement availabilitiesStatement = connection.prepareCall("{CALL select_plane_availabilities(?)}");
			ResultSet eventsRS = eventsStatement.executeQuery();) { //execute events statement
			
			//result sets for nested data:
			ResultSet airForcesRS = null; //air forces result set
			ResultSet planesRS = null; //air force planes result set
			ResultSet availabilitiesRS = null; //plane availabilities result set
			
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
				List<AirForce>eventAirForces = new ArrayList<>();
				
				//set statement input with event id:
				airForcesStatement.setInt(1, eventsRS.getInt("event_ID")); 
				airForcesRS = airForcesStatement.executeQuery(); //execute air forces query
				
				while(airForcesRS.next()) {
					
					//create new air force builder:
					AirForceBuilder airForceBuilder = new AirForce.AirForceBuilder();
					airForceBuilder.setAirForceName(airForcesRS.getString("airforce_name")); //add air force name
					airForceBuilder.setHasHomeAdv(airForcesRS.getBoolean("home_advantage_value")); //add home adv value
					
					/*
					//add event air force to eventAirforces:
					eventAirForces.add(
							new AirForce.AirForceBuilder()
								.setAirForceName(airForcesRS.getString("airforce_name"))
								.setHasHomeAdv(airForcesRS.getBoolean("home_advantage_value"))
								//++++++++++++++++++STORE AIRFORCEID HERE TOO! ++++++++++++++++++++
								.build());*/
					
					//create list for air force planes:
					List<Plane>airForcePlanes = new ArrayList<>(); 
			
					//set statement input with air force id:
					planesStatement.setInt(1, airForcesRS.getInt("airforce_ID")); 
					planesRS = planesStatement.executeQuery(); //execute planes query
					
					while(planesRS.next()) {
						
						//create new plane builder:
						PlaneBuilder planeBuilder = new Plane.PlaneBuilder();
						planeBuilder.setPlaneName(planesRS.getString("plane_name")); //add plane name
						
						////////////System.out.println("plane_name: " + planesRS.getString("plane_name"));
						//////////////////System.out.println("airforce_plane_ID: " + planesRS.getString("airforce_plane_ID"));
						
						//create map for plane availabilities:
						Map<Period, Status>planeAvailabilities = new HashMap<>();
						
						//set statement input with air force plane id:
						availabilitiesStatement.setInt(1, planesRS.getInt("airforce_plane_ID")); 
						availabilitiesRS = availabilitiesStatement.executeQuery(); //execute availabilities query
						
						System.out.println(""); //++++++++++
						
						while(availabilitiesRS.next()) {
							
							System.out.print("| block_option:" + availabilitiesRS.getString("block_option"));
							System.out.print(". year_value:" + availabilitiesRS.getInt("year_value"));
							System.out.print(". status_option:" + availabilitiesRS.getString("status_option"));
						}
						
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
