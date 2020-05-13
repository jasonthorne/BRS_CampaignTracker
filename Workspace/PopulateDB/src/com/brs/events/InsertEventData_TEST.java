package com.brs.events;

import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.brs.ConnectDB;

public interface InsertEventData_TEST {
	
	static void insert() {
		
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			connection = ConnectDB.getConnection();	//connect to DB
	
			//read json file to object, using json parser: 
	        Object object = new JSONParser().parse(new FileReader("json/event_data/event_data.json"));
	        JSONArray events = (JSONArray) object; //cast object to json array of events
	        Iterator<JSONObject> eventsIterator = events.iterator(); //iterate through events
	       
			while (eventsIterator.hasNext()) {
				JSONObject event = (JSONObject) eventsIterator.next().get("event"); //get event from iterator.next()
				//-------------------------------------------------
				//add event name to 'events':
				
				String eventName = (String) event.get("name");  //get name of event
		        callableStatement = connection.prepareCall("{CALL insert_event(?)}"); //create statement
		        callableStatement.setString(1, eventName); //set input with name
			    try {
			    	callableStatement.execute(); //execute statement
				}catch(Exception e) { e.printStackTrace(); }
			    //-------------------------------------------------
			    //add air force name to 'airforces':
			    
			    JSONArray airForces = (JSONArray) event.get("airforces"); //get array of air forces
				Iterator<JSONObject> airForcesIterator = airForces.iterator(); //iterate through airForces
				while (airForcesIterator.hasNext()) {
					JSONObject airForce = (JSONObject) airForcesIterator.next().get("airforce"); //get airForce from iterator.next()
					
					String airForceName = (String) airForce.get("name");  //get name of event
					callableStatement = connection.prepareCall("{CALL insert_airforce(?)}"); //create statement
			        callableStatement.setString(1, airForceName); //set input with name
				    try {
				    	callableStatement.execute(); //execute statement
					}catch(Exception e) { e.printStackTrace(); }
					//-------------------------------------------------
					//add event name, air force name & home advantage status to 'event_airforces':
				    
				    boolean homeAdvantage = Boolean.parseBoolean((String) airForce.get("has_home_advantage")); //get home adv status
				    System.out.println(airForceName + ": " + homeAdvantage); //++++++++++++++
				}
		      
			}
			
		}catch(Exception e) { e.printStackTrace(); }
		finally {
			if (connection != null) {	//finally, try close connection:
				try {
					connection.close(); 
				} catch (SQLException e) { e.printStackTrace(); } 
			}
		}
	}
}