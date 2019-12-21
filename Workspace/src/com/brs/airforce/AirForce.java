package com.brs.airforce;

import java.util.HashMap;
import java.util.List;

import com.brs.period.Period;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;

//public abstract class AirForce {
public interface AirForce {
	
	//AirForce names:
	public enum AirForceName{
		
		RAF("RAF"), 
		LUFTWAFFE("Luftwaffe"), 
		USAAF("USA"), 
		VVS("Soviet"), 
		IJAAF("Japan"); 
		
		private String airForce; //name of chosen air force
		//constructor:
		private AirForceName(String airForce) { this.airForce = airForce; }  //assign name of air force
		@Override 
		public String toString() { return airForce; }  //return name of air force
	}
	

	//protected String description; 
	//protected List<Model>models;
	
	//SET AVAILABILITIES - used switch to create a hasahmap of periods and statuses to return to plane object.
	

	String getDescription(); //get description of air force
	List<Model> getModels(); //get models available to air force

	
	//protected HashMap<Period, Status>getAvailabilities() {return null;} //get availabilities of a model
	
	
	
	
	
	
	
	
	
	
	
}
