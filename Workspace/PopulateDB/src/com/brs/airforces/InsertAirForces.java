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

public interface InsertAirForces {	 ///////////////// System.out.println(airForces); ++++++++++++DELTE https://crunchify.com/how-to-read-json-object-from-file-in-java/
    
	static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_airforce(?,?)}"); //create statement
			
        	//read json file to object, using json parser: 
            Object object = new JSONParser().parse(new FileReader("json/airforces/airforces.json"));
            JSONArray airForces = (JSONArray) object; //cast object to json array of airForces
            Iterator<JSONObject> iterator = airForces.iterator(); //iterate through airForces
            
			while (iterator.hasNext()) {
				JSONObject airForce = (JSONObject) iterator.next().get("airforce"); //get airForce from iterator
				
				//name:
		        String name = (String) airForce.get("name");  //get name of airForce
		        callableStatement.setString(1, name); //set input with name
		        
		        //image_path:
		        String imagePath = (String) airForce.get("image_path");  //get path of airForce image
		        callableStatement.setString(2, imagePath); //set input with path
		        
		        /*
		        String imagePath = (String) airForce.get("image_path");  //get path of airForce image
		        callableStatement.setBinaryStream(2, new FileInputStream(new File(imagePath))); //set input with image
		        */
				callableStatement.execute(); //execute statement
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