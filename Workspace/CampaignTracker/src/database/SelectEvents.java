package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Event;
import model.Event.EventBuilder;
import model.EventAirForce;
import model.EventAirForce.EventAirForceBuilder;

public interface SelectEvents {

	public static List<Event> select() {
	
	//++++++++++++++++++++
	//https://www.roseindia.net/jdbc/Jdbc-nested-result-set.shtml#:~:text=The%20JDBC%20Nested%20Result%20Set,is%20the%20simplest%20join%20algorithm.
		
		List<Event>events = new ArrayList<Event>(); //list for events
		
		try (Connection connection = ConnectDB.getConnection(); //get a connection to the db
			
			//statement for events:
			CallableStatement eventsStatement = connection.prepareCall("{CALL select_events(?,?,?)}");
				
			//statement for event air forces:
			CallableStatement airForcesStatement = connection.prepareCall("{CALL select_event_airforces(?)}");
		
			//result set events query:
			/*ResultSet eventsRS = eventsStatement.executeQuery();*/) { 
			
			eventsStatement.registerOutParameter(1, Types.VARCHAR); //register the out param
			eventsStatement.registerOutParameter(2, Types.INTEGER); //register the out param
			eventsStatement.registerOutParameter(3, Types.INTEGER); //register the out param
			ResultSet eventsRS = eventsStatement.executeQuery();
			
			
			//result set for air forces query:
			ResultSet airForcesRS = null; 
		
			while(eventsRS.next()) {
				
				EventBuilder eventBuilder = new Event.EventBuilder(); //create new event builder
				////////////eventBuilder.setName(eventsRS.getString("event_name")); //add event name
				eventBuilder.setName(eventsRS.getString(1)); //add event name
				
				//////////System.out.println("periodID_start: " + eventsRS.getInt("periodID_start"));
				
				List<EventAirForce>eventAirForces = new ArrayList<>(); //create list for event air forces
				///////////airForcesStatement.setInt(1, eventsRS.getInt("event_ID")); //set input with event id
				airForcesStatement.setInt(1, eventsRS.getInt(2)); //set input with event id
				airForcesRS = airForcesStatement.executeQuery(); //execute event air forces query
				
				//System.out.println("eventsRS.getInt(1): " + eventsRS.getInt(1));
				
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
			
			System.out.println("events: " + events);
		
		}catch(Exception e) { e.printStackTrace(); }
		
		return events; //return events
	}
}
