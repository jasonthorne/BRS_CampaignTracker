package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.Event;
import model.Event.EventBuilder;
import model.AirForce;
import model.AirForce.AirForceBuilder;
import model.Period;
import model.Period.Block;
import model.Plane;
import model.Plane.PlaneBuilder;
import model.Plane.Status;

/** selects events & their dependencies from db. 
 * returning a list of event objects, for displaying to user when choosing an event during campaign creation. */

public interface SelectEvents {

	public static List<Event> select() {
		
		List<Event>events = new ArrayList<Event>(); //list for events
		
		try (Connection connection = ConnectDB.getConnection();  //connect to DB
			//statements for selecting events and their children:
			CallableStatement eventsStatement = connection.prepareCall("{CALL select_events()}");
			CallableStatement periodsStatement = connection.prepareCall("{CALL select_event_periods(?)}");	
			CallableStatement airForcesStatement = connection.prepareCall("{CALL select_event_airforces(?)}");
			CallableStatement planesStatement = connection.prepareCall("{CALL select_airforce_planes(?)}");
			CallableStatement availabilitiesStatement = connection.prepareCall("{CALL select_plane_availabilities(?,?)}");
			ResultSet eventsRS = eventsStatement.executeQuery();) { //execute events statement
			
			//result sets for nested data:
			ResultSet periodsRS = null; //periods result set
			ResultSet airForcesRS = null; //air forces result set
			ResultSet planesRS = null; //air force planes result set
			ResultSet availabilitiesRS = null; //plane availabilities result set
			
			while(eventsRS.next()) {
				
				EventBuilder eventBuilder = new Event.EventBuilder(); //create new event builder
				eventBuilder.setEventName(eventsRS.getString("event_name")); //add event name
				
				/*
				//add start period:
				eventBuilder.setStartPeriod(new Period(
						Block.valueOf(eventsRS.getString("event_start_block").toUpperCase()),
						eventsRS.getInt("event_start_year")));
				
				//add end period:
				eventBuilder.setEndPeriod(new Period(
						Block.valueOf(eventsRS.getString("event_end_block").toUpperCase()),
						eventsRS.getInt("event_end_year")));
				*/
				
				
				//============================================================
				//create list for event periods:
				List<Period>eventPeriods = new ArrayList<>();
				
				//set statement input with event id:
				periodsStatement.setInt(1, eventsRS.getInt("event_ID")); 
				periodsRS = periodsStatement.executeQuery(); //execute event periods query
				
				while(periodsRS.next()) {
					
					//add period to list:
					eventPeriods.add(new Period(
							Block.valueOf(periodsRS.getString("period_block").toUpperCase()),
							periodsRS.getInt("period_year"))); 
				}
				
				//get event's start period from front of list:
				eventBuilder.setStartPeriod(eventPeriods.get(0));
				
				//get event's end period from end of list:
				eventBuilder.setEndPeriod(eventPeriods.get(eventPeriods.size()-1));
				
				
				//System.out.println("eventPeriods: " + eventPeriods);
				
				
				/*
				//create map with event period keys for storing plane availabilities:
				Map<Period, Status>planeAvailabilities = eventPeriods.stream()
						.collect(Collectors.toMap(period -> period, status -> Status.UNAVAILABLE));
				       
				System.out.println("planeAvailabilities: " + planeAvailabilities);
				*/
				//============================================================
				
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
					
					//create list for air force planes:
					List<Plane>airForcePlanes = new ArrayList<>();
			
					//set statement input with air force id:
					planesStatement.setInt(1, airForcesRS.getInt("airforce_ID")); 
					planesRS = planesStatement.executeQuery(); //execute planes query
					
					while(planesRS.next()) {
						
						//create new plane builder:
						PlaneBuilder planeBuilder = new Plane.PlaneBuilder();
						planeBuilder.setPlaneName(planesRS.getString("plane_name")); //add plane name
						
						//+++++++++++++++++++++++++++++++++++++++++++++
						//make map here, but have it be a COPY of one made above.
						
						//+++++++++++++++++++++++++++++++++++++++++++++++
						//create map for plane availabilities:
						//////Map<Period, Status>status = new HashMap<>(); //+++++++++++++++++make this the full size of event length.
						
						
						
						//++++++++++++++++++++with all unavilables in it.
						
						//set statement input parameters with air force plane id & event id:
						availabilitiesStatement.setInt(1, planesRS.getInt("airforce_plane_ID"));
						availabilitiesStatement.setInt(2, eventsRS.getInt("event_ID"));
						availabilitiesRS = availabilitiesStatement.executeQuery(); //execute availabilities query
						
						if (availabilitiesRS.isBeforeFirst()){ //if availabilities where found
							
							//create map for storing availabilities:
							Map<Period, Status>planeAvailabilities = eventPeriods.stream()
									.collect(Collectors.toMap(period -> period, status -> Status.UNAVAILABLE));
							
							while(availabilitiesRS.next()) {
								
								//add plane's availabilities to map:
								planeAvailabilities.put(new Period(
										Block.valueOf(availabilitiesRS.getString("block_option").toUpperCase()),
										availabilitiesRS.getInt("year_value")),
										Status.valueOf(availabilitiesRS.getString("status_option").toUpperCase()));
							}
							
							//add availabilities to plane builder:
							planeBuilder.setPlaneAvailabilities(planeAvailabilities);
							
							//add built plane to air force planes:
							airForcePlanes.add(planeBuilder.build());
						}
						
					}//planesRS
					System.out.println("airForcePlanes: " + airForcePlanes);
					//add air force planes to air force builder:
					airForceBuilder.setPlanes(airForcePlanes);
					
					//add built air force to event air forces:
					eventAirForces.add(airForceBuilder.build());
					
				}//airForcesRS
				
				//add event air forces to event builder:
				eventBuilder.setEventAirForces(eventAirForces);
				
				//add built event to events:
				events.add(eventBuilder.build());
				
			}//eventsRS
			
		} catch(Exception e) { e.printStackTrace(); }
	
		return events; //return events
	}
}
