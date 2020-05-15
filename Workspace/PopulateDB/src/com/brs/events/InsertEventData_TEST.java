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
	
			//read json file to object reference, using json parser: 
	        Object object = new JSONParser().parse(new FileReader("json/event_data/event_data.json"));
	        JSONArray events = (JSONArray) object; //cast object to json array of events
	        Iterator<JSONObject> eventIterator = events.iterator(); //iterate through events
	       
			while (eventIterator.hasNext()) {
				JSONObject event = (JSONObject) eventIterator.next().get("event"); //get event from iterator.next()
				//-------------------------------------------------
				//add event to 'events':
				
				String eventName = (String) event.get("name");  //get name of event
		        callableStatement = connection.prepareCall("{CALL insert_event(?)}"); //create statement
		        callableStatement.setString(1, eventName); //set input with name
			    try {
			    	//////////////callableStatement.execute(); //execute statement
				}catch(Exception e) { e.printStackTrace(); }
			    //-------------------------------------------------
			    //add air force to 'airforces': //+++++++++++++++instead of this, just grab airforce, for adding to event_airforce below
			    
			    JSONArray airForces = (JSONArray) event.get("airforces"); //get array of air forces
				Iterator<JSONObject> airForceIterator = airForces.iterator(); //iterate through airForces
				while (airForceIterator.hasNext()) {
					JSONObject airForce = (JSONObject) airForceIterator.next().get("airforce"); //get airForce from iterator.next()
					
					String airForceName = (String) airForce.get("name");  //get name of event
					callableStatement = connection.prepareCall("{CALL insert_airforce(?)}"); //create statement
			        callableStatement.setString(1, airForceName); //set input with name
				    try {
				    	///////////////callableStatement.execute(); //execute statement
					}catch(Exception e) { e.printStackTrace(); }
					//-------------------------------------------------
					//add event, air force & home advantage status to 'event_airforces':
				    
				    boolean homeAdvantage = Boolean.parseBoolean((String) airForce.get("has_home_advantage")); //get home adv status
				    callableStatement = connection.prepareCall("{CALL insert_event_airforce(?,?,?)}"); //create statement
			        callableStatement.setString(1, eventName); //set input with event name
			        callableStatement.setString(2, airForceName); //set input with air force name
			        callableStatement.setBoolean(3, homeAdvantage); //set input with home advantage status
			        try {
				    	//////////////callableStatement.execute(); //execute statement
					}catch(Exception e) { e.printStackTrace(); }
				}
				//-------------------------------------------------
			    //??????????????
				
				JSONObject startPeriod = (JSONObject) event.get("start_period");  //get event start period
				System.out.println(startPeriod); //+++++++++++++
				
				
				
				
		      
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
