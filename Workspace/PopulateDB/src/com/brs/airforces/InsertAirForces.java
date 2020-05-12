package com.brs.airforces;

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

public abstract class InsertAirForces {
	
	public static void insert() {
		 Connection connection = null;
		 try {
			connection = ConnectDB.getConnection();	//connect to DB
			CallableStatement callableStatement = connection.prepareCall("{CALL insert_airforce(?)}"); //create statement
			///callableStatement.registerOutParameter(1, Types.VARCHAR); //register out param
			
	        try {
	        	String filePath = "com/brs/json/airforces.json"; //path to airforces.json
	        	//parse read file to object, using class loader and json parser: 
	            Object object = new JSONParser().parse(new FileReader(ClassLoader.getSystemResource(filePath).getFile()));
	            
	            //cast object to json array of airForces:
	            JSONArray airForces = (JSONArray) object;
	           ///////////////// System.out.println(airForces); ++++++++++++DELTE
	            
	            //iterate through airForces:
	            Iterator<JSONObject> iterator = airForces.iterator();
				while (iterator.hasNext()) { 
					
					//get airForce from iterator.next():
					JSONObject airForce = (JSONObject) iterator.next().get("airForce");
					
					//get name from airForce:
			        String name = (String) airForce.get("name");    
			        System.out.println(name);
			         
			        //get img from airForce:
			        String imagePath = (String) airForce.get("imagePath");  
			        System.out.println(imagePath);
					
				}
	            
	            
	            
	            
	            
	            /*
	             * //add path to reader with ClassLoader:
	        	FileReader fileReader = new FileReader(ClassLoader.getSystemResource(filePath).getFile()); 
	        	
	        	//parse json to Object with reader:
	            //Object object = jsonParser.parse(fileReader);
	        	
	        	//parse json to Object with fileReader passed to JSONParser:
	           // Object object = new JSONParser().parse(fileReader);
	             */
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
	            
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