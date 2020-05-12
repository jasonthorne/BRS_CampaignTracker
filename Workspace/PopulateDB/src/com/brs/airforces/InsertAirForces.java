package com.brs.airforces;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.brs.ConnectDB;
import com.brs.Insert;

public interface InsertAirForces {
	
	static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_airforce(?,?)}"); //create statement
			///callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
	        try {
	        	//read json file to object, using json parser: 
	            Object object = new JSONParser().parse(new FileReader("json/airforces/airforces.json"));
	            JSONArray airForces = (JSONArray) object; //cast object to json array of airForces
	            Iterator<JSONObject> iterator = airForces.iterator(); //iterate through airForces
	            
				while (iterator.hasNext()) {
					JSONObject airForce = (JSONObject) iterator.next().get("airforce"); //get airForce from iterator
					
			        String name = (String) airForce.get("name");  //get name of airForce
			        callableStatement.setString(1, name); //set input with name
			        
			        String imagePath = (String) airForce.get("image_path");  //get path of airForce image
			        callableStatement.setBinaryStream(2, new FileInputStream(new File(imagePath))); //set input with binary stream of image
			        
					
			        
			       // callableStatement.setString(2, (String) airForce.get("name")); //set image input
			        /////System.out.println(name);
					////System.out.println(imagePath);
			       
					//FileInputStream fileInputStream = new FileInputStream(new File(imagePath)); //add file with image path to input stream
					
			        
					callableStatement.execute(); //execute statement
					
				}
	            
	            
	            
	            
	            
	            /*
	             * //add path to reader with ClassLoader:
	        	FileReader fileReader = new FileReader(ClassLoader.getSystemResource(filePath).getFile()); 
	        	
	        	//parse json to Object with reader:
	            //Object object = jsonParser.parse(fileReader);
	        	
	        	//parse json to Object with fileReader passed to JSONParser:
	           // Object object = new JSONParser().parse(fileReader);
	             */
	            
	            
	            
	            
	            
	            
	            
				 
		           ///////////////// System.out.println(airForces); ++++++++++++DELTE https://crunchify.com/how-to-read-json-object-from-file-in-java/
		            
	            
	            
	            
	            /*
	            JSONObject airForce = null;
	            //Iterate over employee array
	           // airForces.forEach((airForce) -> parseAirForceObject((JSONObject)airForce ));
	            
	            Iterator iterator = airForces.iterator();
	            while (iterator.hasNext()) {
	            	
	            	JSONObject airForceObject = (JSONObject) iterator.next();
	            	
	            	//JSONObject airForceObject = (JSONObject) airForce.get("airForce");
	            	
	            	String name = (String) airForceObject.get("name");    
	    	        System.out.println(name);
	    	        
	    	        
	               
	            }*/
	            
	            //->{
	            	
	            	/*
	            	//Get employee object within list
	                JSONObject airForceObject = (JSONObject) ((JSONObject) airForce).get("airForce");
	                
	            	 
	            	try {
	            		 String test = (String) ((JSONObject) airForce).get("airForce");    
		                 System.out.println(test);
	            	 }catch(Exception e) { e.printStackTrace(); }
	            	
	            	
	            
	            }); */
	            
	            
	            /*
	             * //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
	             */
	        	
	        }catch(Exception e) { e.printStackTrace(); }
			
			
			
			//==========================================
			/*
			Arrays.asList(AirForce.values()).forEach((airForce)->{ //forEach air force in list of air forces:
				try {
					callableStatement.setString(1, airForce.toString()); //set input param
					callableStatement.execute(); //execute statement
					System.out.println(callableStatement.getString(1)); //print response
				}catch(Exception e) { e.printStackTrace(); }
			});
			*/	
		 }catch(Exception e) { e.printStackTrace(); }
		 finally {
			 if (connection != null) {	//finally, try close connection:
				 try {
					 connection.close(); 
				 } catch (SQLException e) { e.printStackTrace(); } 
			 }
		 }
	}
	
	
	
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	 static void parseAirForceObject(JSONObject airForce) 
	    {
	        //Get employee object within list
	        JSONObject airForceObject = (JSONObject) airForce.get("airForce");
	         
	        //Get employee first name
	        String name = (String) airForceObject.get("name");    
	        System.out.println(name);
	         
	        //Get employee last name
	        String img = (String) airForceObject.get("img");  
	        System.out.println(img);
	         
	        /*
	        //Get employee website name
	        String website = (String) employeeObject.get("website");    
	        System.out.println(website);
	        */
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//+++++++++++++++++++++++++++++++++++++++Overloaded method of this!! woohoo!! 
	static void insert(String yo) {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_airforce(?)}"); //create statement
			callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
			Arrays.asList(AirForce.values()).forEach((airForce)->{ //forEach air force in list of air forces:
				try {
					callableStatement.setString(1, airForce.toString()); //set input param
					callableStatement.execute(); //execute statement
					System.out.println(callableStatement.getString(1)); //print response
				}catch(Exception e) { e.printStackTrace(); }
			});
				
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