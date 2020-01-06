package com.brs.airforce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.period.Periods;
import com.brs.period.Period;
import com.brs.plane.Model;
import com.brs.plane.Status;


public abstract class AirForce {
	
	public abstract String getName(); //return name of air force
	public abstract List<Model>getAllModels(); //return all air force models
	
	//variables for creating a map of a singular model's periods and their corresponding status values:
	protected static List<Period>periods; //periods
	protected static List<Status>statuses; //statuses of periods
	protected static Map<Period, Status>periodToStatus; //map for holding periods and their statuses
	protected abstract void setPeriodToStatus(Model model); //extended class sets periodToStatus
	
	//map for holding a collection of models and their status in relation to a given period:
	private static Map<Model, Status>modelToStatus; 
	private static Status status; //holds a status value for insertion into modelToStatus
	
	//returns a map of models available to a given period:
	public Map<Model, Status>getAvailableModels(Period period) {  //NEED TO DEAL WITH IF INVALID DATES ARTE ENTERED (Late 1945)+++++++++++++++++
		modelToStatus = new HashMap<Model, Status>(); //(re)set HashMap 
		
		getAllModels().forEach((model) -> { //forEach model in list of models:
			setPeriodToStatus(model); //(re)set periodToStatus with the current model's periods and their statuses
			status = periodToStatus.getOrDefault(period, Status.NONE); //assign status returned with period key (or NONE if period not found) 
			
			if ((!status.equals(Status.NONE))){ //if period key returned a value:
				//System.out.println("model: " + model.toString() + ". TEST MAP: " + periodToStatus); //++++++++++++++++++
				modelToStatus.put(model, status); //add current model and it's status
			}
		});
		return modelToStatus; //return available models
	}
	
	
	
	
	/* MIGHT NOT BE NEEDED FOR PUT IF ABSENT!! WE SHALL SEE....
	@Override //for comparison against other AirForces. See putAirForceIfAbsent() in Event.java:
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override //for comparison against other AirForces. See putAirForceIfAbsent() in Event.java:
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirForce other = (AirForce) obj;
		if (name != other.name)
			return false;
		return true;
	}
	
	*/
	
	
	
	/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++https://stackoverflow.com/questions/18410035/ways-to-iterate-over-a-list-in-java
	 * //for(Model model: airForceToModels.get(airForceName)){ //each model in list returned from airForceName key:
	 * 
	 * 
	 * //forEach model in the list returned from airForceName key:
		/////////////////airForceToModels.get(airForceName).forEach((model) -> {
	 */
	
	/* MIGHT KEEP. Good for showing a planes total statuses.
	//gets a Map of a model's periods and their statuses:
	public Map<Period, Status>getPeriodToStatus(Model model){ ///++++++ throws Exception{ 
		setPeriodToStatus(model); //create Map based on Model
		return periodToStatus; //return Map.
	}*/
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//MAP FOR HOLDING ALL AIRFORCES AND THEIR MODELS: - might use later! :P
	/*
	//Map of all air forces used and their respective models:
	protected static Map<AirForceName, List<Model>>airForceToModels = new HashMap<AirForceName, List<Model>>(); //PROBABLY DONT NEED!! woohoo!
	protected abstract void addAirForceModels(); //concrete class puts name and models to airForceToModels
	
	//needed????????????????????????? +++++++++++++++++++++
	public static List<Model>getAirForceModels(AirForceName airForceName){ //get models available to an air force
		return airForceToModels.get(airForceName); 
	}
	*/

	
}
