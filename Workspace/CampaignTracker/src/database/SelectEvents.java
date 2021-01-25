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
/*import model.Event.EventBuilder;*/
import model.AirForce;
/*import model.AirForce.AirForceBuilder;*/
import model.Period;
import model.Period.Block;
import model.Plane;
/*import model.Plane.PlaneBuilder;*/
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
				
				////////////////EventBuilder eventBuilder = new Event.EventBuilder(); //create new event builder
				//////////////eventBuilder.setName(eventsRS.getString("event_name")); //set event name
				String eventName = eventsRS.getString("event_name"); //get event name
				
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
				
				//get start & end periods from list:
				////////////eventBuilder.setStartPeriod(eventPeriods.get(0));
				////////////eventBuilder.setEndPeriod(eventPeriods.get(eventPeriods.size()-1));
				
				//get start & end periods from list:
				Period startPeriod = eventPeriods.get(0);
				Period endPeriod = eventPeriods.get(eventPeriods.size()-1);
				
				
				//set turn size using number of periods:
				/////////eventBuilder.setMaxTurns(eventPeriods.size());
				
				//get period total:
				int periodTotal = eventPeriods.size(); 
				
				//create list for event air forces:
				List<AirForce>eventAirForces = new ArrayList<>();
				
				//set statement input with event id:
				airForcesStatement.setInt(1, eventsRS.getInt("event_ID")); 
				airForcesRS = airForcesStatement.executeQuery(); //execute air forces query
				
				while(airForcesRS.next()) {
					
					//create new air force builder:
					//////////////////////////AirForceBuilder airForceBuilder = new AirForce.AirForceBuilder();
					////////////////airForceBuilder.setAirForceName(airForcesRS.getString("airforce_name")); //set air force name
					/////////////////airForceBuilder.setHasHomeAdv(airForcesRS.getBoolean("home_advantage_value")); //set home adv value
					String airForceName = airForcesRS.getString("airforce_name"); //get air force name
					boolean hasHomeAdv = airForcesRS.getBoolean("home_advantage_value"); //get home adv value
					
					
					//create list for air force planes:
					List<Plane>airForcePlanes = new ArrayList<>();
			
					//set statement input with air force id:
					planesStatement.setInt(1, airForcesRS.getInt("airforce_ID")); 
					planesRS = planesStatement.executeQuery(); //execute planes query
					
					while(planesRS.next()) {
						
						//create new plane builder:
						///////////////////PlaneBuilder planeBuilder = new Plane.PlaneBuilder();
						////////////////////planeBuilder.setPlaneName(planesRS.getString("plane_name")); //set plane name
						String planeNane = planesRS.getString("plane_name"); //get plane name
						
						//set statement input parameters with air force plane id & event id:
						availabilitiesStatement.setInt(1, planesRS.getInt("airforce_plane_ID"));
						availabilitiesStatement.setInt(2, eventsRS.getInt("event_ID"));
						availabilitiesRS = availabilitiesStatement.executeQuery(); //execute availabilities query
						
						if (availabilitiesRS.isBeforeFirst()){ //if availabilities where found
							
							//create map for availabilities, using event periods:
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
							/////////////planeBuilder.setPlaneAvailabilities(planeAvailabilities);
							
							//add built plane to air force planes:
							///////////airForcePlanes.add(planeBuilder.build());
							//ad plane to air force planes:
							airForcePlanes.add(new Plane(planeNane, planeAvailabilities));
						}
						
					}//planesRS
					
					//add air force planes to air force builder:
					///////////airForceBuilder.setPlanes(airForcePlanes);
					
					//add built air force to event air forces:
					//////eventAirForces.add(airForceBuilder.build());
					
					//add air force to event air forces:
					eventAirForces.add(new AirForce(airForceName, hasHomeAdv, airForcePlanes));
					
					
				}//airForcesRS
				
				//add event air forces to event builder:
				/////////////eventBuilder.setAirForces(eventAirForces);
				
				//add built event to events:
				///////////events.add(eventBuilder.build());
				
				//add event to events:
				events.add(new Event(eventName, startPeriod, endPeriod, periodTotal, eventAirForces));
				
			}//eventsRS
			
		} catch(Exception e) { e.printStackTrace(); }
	
		return events; //return events
	}
}
