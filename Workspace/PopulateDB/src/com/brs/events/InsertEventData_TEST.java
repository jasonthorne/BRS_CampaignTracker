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
			//CallableStatement callableStatement = connection.prepareCall("{CALL insert_airforce(?,?)}"); //create statement
			
			//read json file to object, using json parser: 
	        Object object = new JSONParser().parse(new FileReader("json/event_data/event_data.json"));
	        JSONArray events = (JSONArray) object; //cast object to json array of events
	        Iterator<JSONObject> iterator = events.iterator(); //iterate through events
	       
			while (iterator.hasNext()) {
				JSONObject event = (JSONObject) iterator.next().get("event"); //get event from iterator
				
				//add event name to events table:
				String eventName = (String) event.get("name");  //get name of event
				System.out.println("event: " + eventName);
		        callableStatement = connection.prepareCall("{CALL insert_event(?)}"); //create statement
		        callableStatement.setString(1, eventName); //set input with name
			    try { 
			        callableStatement.execute(); //execute statement
				}catch(Exception e) { e.printStackTrace(); }
					
				
		       
		        /*
		        //image_path:
		        String imagePath = (String) airForce.get("image_path");  //get path of airForce image
		        callableStatement.setString(2, imagePath); //set input with path
		        */
		        /*
		        String imagePath = (String) airForce.get("image_path");  //get path of airForce image
		        callableStatement.setBinaryStream(2, new FileInputStream(new File(imagePath))); //set input with image
		        */
				//callableStatement.execute(); //execute statement
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
