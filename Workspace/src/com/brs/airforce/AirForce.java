package com.brs.airforce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.Campaign;
import com.brs.period.Block;
import com.brs.period.Period;
import com.brs.period.Year;
import com.brs.plane.PlaneData.Model;
import com.brs.plane.PlaneData.Status;

public abstract class AirForce {
	
	//AirForce name:
	protected String name; //name of AirForce
	protected abstract void setName(); //concrete class sets name
	public String getName() { return name; } //return name
	
	//AirForce description:
	protected String description; //description of AirForce
	protected abstract void setDescription(); //concrete class sets description
	public String getDescription() { return description; } //return description
	
	//Map of all air forces used and their respective models:
	protected static Map<AirForceName, List<Model>>airForceToModels = new HashMap<AirForceName, List<Model>>();
	protected abstract void addAirForceModels(); //concrete class puts name and models to airForceToModels
	
	//needed????????????????????????? +++++++++++++++++++++
	public static List<Model>getAirForceModels(AirForceName airForceName){ //get models available to an air force
		return airForceToModels.get(airForceName); 
	}
	
	//vars for creating maps of a model's periods and their statuses:
	protected static List<Period>periods; //periods
	protected static List<Status>statuses; //statuses of periods
	protected static Map<Period, Status>periodToStatus; //Map for holding a model's periods and their statuses
	protected abstract void setPeriodToStatus(Model model); ////++++++++ throws Exception; //concrete class creates Map of periods and their statuses
	
	
	/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++NEED TO DEAL WITH IF INVALID DATES ARTE ENTERED (Late 1945)
	*/
	private static Map<Model, Status>modelToStatus; //Map for holding a model and it's status (in relation to a given period)
	private static Status statusVal; //holds a status value, for insertion into modelToStatus
	
	public Map<Model, Status>getAvaliableModels(Period period, AirForceName airForceName) { 
		modelToStatus = new HashMap<Model, Status>(); //(re)set HashMap 
		
		for(Model currModel: airForceToModels.get(airForceName)){ //every model returned with airForceName key
			
			setPeriodToStatus(currModel); //(re)set periodToStatus with the current model's periods and their statuses
			statusVal = periodToStatus.getOrDefault(period, Status.NONE); //assign status returned with period key (or NONE if period not found)
			
			if ((!statusVal.equals(Status.NONE))){ //if period key returned a value:
				//System.out.println("model: " + currModel.toString() + ". TEST MAP: " + periodToStatus); //++++++++++++++++++
				modelToStatus.put(currModel, statusVal); //add current model and it's status
			}
		}
		return modelToStatus; //return available models
	}
	
	
	/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */
	
	/*
	//gets a Map of a model's periods and their statuses:
	public Map<Period, Status>getPeriodToStatus(Model model){ ///++++++ throws Exception{ 
		setPeriodToStatus(model); //create Map based on Model
		return periodToStatus; //return Map.
	}*/
	

	
}
