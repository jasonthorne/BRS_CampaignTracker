package com.brs.pilot;

import com.brs.plane.Plane;
import com.brs.player.Player;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Supplier;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.brs.FileReadingTEST;
import com.brs.date.Date;
import com.brs.die.Die;
import com.brs.pilot.PilotSkill;


public class Pilot implements Die, FileReadingTEST{
	
	private int id;
	private String name;
	private final String date = Date.getDate(); //date of creation
	private PilotSkill pilotSkill;
	public int exp;
	private int kills;
	private String status;
	private Plane plane;
	
	public Pilot(PilotSkill pilotSkill) {
		this.pilotSkill = pilotSkill;
		setExp(); //+++++CALL THIS LATER (to assign exp AFTER usef has selected ALL their pilots. ++++++++++++++++++
	}
	
	private void setExp() { 
		
		switch(pilotSkill) {
		  case ROOKIE:
			  exp = D6.get()-1; //D6-1 xp (0-5)
			  break;
		  case AVERAGE:
			  exp = D6.get()+10; //D6+10 xp (11-16)
			  break;
		  case VETERAN:
			  exp = (D6.get()+D6.get()+D6.get())+20; //3D6+20 xp (23-38)
			  break;
		  case ACE:
			  exp = D6.get()+50; //D6+50 xp (51-56)
			  break;
		}
	}
	
	
	public void test() { //https://stackoverflow.com/questions/25346512/read-multiple-objects-json-with-java
		
		//getText(this.getClass());
	
		System.out.println(this.getClass().getName());
		System.out.println(this.getClass().getPackage().getName().replace(".", "/") + "/whateverIWant.json");
		System.out.println(this.getClass().getName().replace(".", "/").toLowerCase()+".json");
		
		String filePath = this.getClass().getName().replace(".", "/").toLowerCase()+".json";
		
		try (FileReader reader = new FileReader(ClassLoader.getSystemResource(filePath).getFile())) {
            // read the json file
 
 
	        JSONParser jsonParser = new JSONParser();
	        JSONObject jo = (JSONObject) jsonParser.parse(reader);
	      
	        // getting firstName and lastName 
	        String firstName = (String) jo.get("firstName"); 
	        String lastName = (String) jo.get("lastName"); 
	          
	        System.out.println(firstName); 
	        System.out.println(lastName); 
	          
	        // getting age 
	        long age = (long) jo.get("age"); 
	        System.out.println(age); 
	          
	        // getting address 
	        Map address = ((Map)jo.get("address")); 
	          
	        // iterating address Map 
	        Iterator<Map.Entry> itr1 = address.entrySet().iterator(); 
	        while (itr1.hasNext()) { 
	            Map.Entry pair = itr1.next(); 
	            System.out.println(pair.getKey() + " : " + pair.getValue()); 
	        } 
	          
	        // getting phoneNumbers 
	        JSONArray ja = (JSONArray) jo.get("phoneNumbers"); 
	          
	        // iterating phoneNumbers 
	        Iterator itr2 = ja.iterator(); 
	          
	        while (itr2.hasNext())  
	        { 
	            itr1 = ((Map) itr2.next()).entrySet().iterator(); 
	            while (itr1.hasNext()) { 
	                Map.Entry pair = itr1.next(); 
	                System.out.println(pair.getKey() + " : " + pair.getValue()); 
	            } 
	            
	        
	        } 


	} catch (Exception ex) {
          ex.printStackTrace();
    }
		
		
		
		
		
		
		
		
	}
	
	//post mission stuff: 
	public void getShotDownResult() {
		
		switch(D6.get()) {
		 case 6: case 5: 
			 System.out.println("Forced landing"); 
			 break;
		 case 4: case 3: 
			 System.out.println("Bail out");
			 getBailOutResult(); //get bailout result
			 break;
		 case 2: case 1: 
			 System.out.println("KIA"); 
			 break;
		}
	}
	
	
	public void getBailOutResult() {
		
		switch(D6.get()) {
		 case 6: case 5: case 4:
			 System.out.println("OK"); 
			 break;
		 case 3: case 2: 
			 System.out.println("Bad landing");
			 getInjuryResult();
			  break;
		 case 1: 
			 System.out.println("Chute failure"); 
			 break;
		}
	}
	
	
	public void getInjuryResult() {
		
		switch(D6.get()) {
		 case 6: case 5: 
			 System.out.println("Just a scratch"); 
			 break;
		 case 4: case 3: 
			 System.out.println("Down but not out");
			 break;
		 case 2: 
			 System.out.println("Major Injury");
			 break;
		 case 1: 
			 System.out.println("Crippling injury"); 
			 break;
		}
	}
	
	

}
