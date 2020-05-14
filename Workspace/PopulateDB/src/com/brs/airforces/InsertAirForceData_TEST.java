package com.brs.airforces;

import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.brs.ConnectDB;

public interface InsertAirForceData_TEST {
	
	static void insert() { 
		
		Connection connection = null;
		CallableStatement callableStatement = null;
		try {
			connection = ConnectDB.getConnection();	//connect to DB
		    
			//read json file to object reference, using json parser: 
	        Object object = new JSONParser().parse(new FileReader("json/airforce_data/airforce_data.json"));
	        JSONArray airForces = (JSONArray) object; //cast object to json array of air forces
	        Iterator<JSONObject> airForceIterator = airForces.iterator(); //iterate through air forces
	        
			while (airForceIterator.hasNext()) {
				JSONObject airForce = (JSONObject) airForceIterator.next().get("airforce"); //get air force from iterator.next()
			    //-------------------------------------------------
			    //add air force to 'airforces':
			    
				String airForceName = (String) airForce.get("name");  //get name of event
				callableStatement = connection.prepareCall("{CALL insert_airforce(?)}"); //create statement
		        callableStatement.setString(1, airForceName); //set input with name
			    try {
			    	callableStatement.execute(); //execute statement
				}catch(Exception e) { e.printStackTrace(); }
			    ////////System.out.println(airForceName);
			    //-------------------------------------------------
				//add images to 'airforce_images':
			    //+++++++++++figure this out :P +++++++++++++++++++++
				//-------------------------------------------------
				//add planes to 'airforce_planes':
			    
			    
			
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
