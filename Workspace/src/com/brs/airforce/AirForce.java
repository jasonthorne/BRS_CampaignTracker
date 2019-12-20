package com.brs.airforce;

import java.util.List;

import com.brs.plane.Plane.Availability;
import com.brs.plane.PlaneData.Model;

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
	
	
	//getters:
	String getDescription(); //get description of air force
	
	List<Model>getModels(); //get models available to air force
	
	List<Availability>getAvailabilities(Model model); //get availabilities of a model

}
