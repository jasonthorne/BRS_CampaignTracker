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
			    
				String airForceName = (String) airForce.get("name");  //get name of air force
				callableStatement = connection.prepareCall("{CALL insert_airforce(?)}"); //create statement
		        callableStatement.setString(1, airForceName); //set input with name
			    try {
			    	callableStatement.execute(); //execute statement //++++++++++++++++++++++++++++++++++
				}catch(Exception e) { e.printStackTrace(); }
			    //-------------------------------------------------
				//add images to 'airforce_images':
			   
			    JSONArray images = (JSONArray) airForce.get("images"); //get array of images
				Iterator<JSONObject> imageIterator = images.iterator(); //iterate through images
				while (imageIterator.hasNext()) {
					JSONObject image = (JSONObject) imageIterator.next().get("image"); //get image from imageIterator.next()
					
					String imageName = (String) image.get("name");  //get name of image
					String imagePath = (String) image.get("path");  //get path of image
					callableStatement = connection.prepareCall("{CALL insert_airforce_image(?,?,?)}"); //create statement
			        callableStatement.setString(1, airForceName); //set input with name
			        callableStatement.setString(2, imageName); //set input with image
			        callableStatement.setString(3, imagePath); //set input with path
			        try {
				    	callableStatement.execute(); //execute statement //++++++++++++++++++++++++++++++++++
					}catch(Exception e) { e.printStackTrace(); }
				}
				//-------------------------------------------------
				//add planes to 'airforce_planes':
			    
				JSONArray planes = (JSONArray) airForce.get("planes"); //get array of planes
				Iterator<JSONObject> planeIterator = planes.iterator(); //iterate through planes
				while (planeIterator.hasNext()) {
					JSONObject plane = (JSONObject) planeIterator.next().get("plane"); //get plane from planeIterator.next()
					
					String planeName = (String) plane.get("name");  //get name of plane
					callableStatement = connection.prepareCall("{CALL insert_airforce_plane(?,?)}"); //create statement
			        callableStatement.setString(1, airForceName); //set input with air force
			        callableStatement.setString(2, planeName); //set input with plane
			        try {
				    	callableStatement.execute(); //execute statement //++++++++++++++++++++++++++++++++++
					}catch(Exception e) { e.printStackTrace(); }
					//-------------------------------------------------
					//add periods and statuses to 'airforce_plane_period_statuses':
					
					JSONArray periodStatuses = (JSONArray) plane.get("period_statuses"); //get array of period statuses
					Iterator<JSONObject> periodStatusesIterator = periodStatuses.iterator(); //iterate through periodStatuses
					while (periodStatusesIterator.hasNext()) {
						//get period_status from planeIterator.next()
						JSONObject periodStatus = (JSONObject) periodStatusesIterator.next().get("period_status");
						
						JSONObject period = (JSONObject) periodStatus.get("period"); //get period object from periodStatus
						String block = (String) period.get("block"); //get block from period
						String year = (String) period.get("year"); //get year from period
						String status = (String) periodStatus.get("status"); //get status from periodStatus
						callableStatement = connection.prepareCall("{CALL insert_airforce_plane_period_status(?,?,?,?,?)}"); //create statement
						callableStatement.setString(1, airForceName); //set input with air force
				        callableStatement.setString(2, planeName); //set input with plane
				        callableStatement.setString(3, block); //set input with block
				        callableStatement.setString(4, year); //set input with year
				        callableStatement.setString(5, status); //set input with status
				        try {
					    	callableStatement.execute(); //execute statement //++++++++++++++++++++++++++++++++++
				        }catch(Exception e) { e.printStackTrace(); }
					}
				} //planeIterator
			} //airForceIterator
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
