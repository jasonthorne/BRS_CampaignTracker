package com.brs.airforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Period;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;

public abstract class AirForce {
	
	//air force names:
	public enum AirForceName{
		
		RAF("RAF"), 
		LUFTWAFFE("Luftwaffe"), 
		USAAF("USA"), 
		VVS("Soviet"), 
		IJAAF("Japan"); 
		private String airForce; //name of chosen air force
		//constructor:
		private AirForceName(String airForce) { this.airForce = airForce; }  //assign name of air force
		@Override public String toString() { return airForce; }  //return name of air force
	}
	
	protected String name; //name of AirForce
	protected abstract void setName(); //concrete class sets name
	public String getName() { return name; } //return name
	
	protected String description; //description of AirForce
	protected abstract void setDescription(); //concrete class sets description
	public String getDescription() { return description; } //return description
	
	
	//Map of air forces and their models:
	protected static Map<AirForceName, List<Model>> airForceToModels = new HashMap<AirForceName, List<Model>>();
	protected abstract void putAirForceToModels(); //concrete class puts name and models to airForceToModels
	//get models available to an air force:
	public static List<Model>getAirForceModels(AirForceName airForceName){ return airForceToModels.get(airForceName); }

	
	protected static List<Period>periods; //periods
	protected static List<Status>statuses; //statuses of periods
	protected static Map<Period, Status>periodToStatus; //Map for holding a models periods and their statuses
	protected abstract void setPeriodToStatus(Model model) throws Exception; //concrete class creates Map of periods and their statuses
	
	//gets a Map of a model's periods and their statuses:
	public Map<Period, Status>getPeriodToStatus(Model model) throws Exception{ 
		setPeriodToStatus(model); //create Map based on Model
		return periodToStatus; //return Map.
	}
	
	
	/*
	public List<Model>getAllModels(){ //get all models available to all air forces
		return null; 
		
	};*/ 
	
	
	
}
