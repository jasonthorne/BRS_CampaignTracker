package ideas;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReadingJson {
	
	public void readJson() {
		
	
	//=================TEST JSON++++++++++++++++++++++++++++++++++++++++++++++
			//https://code.google.com/archive/p/json-simple/
			
			
			//https://stackoverflow.com/questions/5698900/add-json-package-reference-new-to-java/39867765
			
			
			/*
			// parsing file "JSONExample.json" 
	        Object obj = new JSONParser().parse(new FileReader("TEST.json")); 
	          
	        // typecasting obj to JSONObject 
	        JSONObject jo = (JSONObject) obj; 
	        
	        */
			
			
			//String path = "/" + (this.getClass().getCanonicalName().replace(".", "/")) + "main.json";
			///System.out.println(test);
			
			final String filePath = "com/brs/test.json";
			
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
	
	
	
	
	
	
	

}


