package com.brs.airforce;

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
	
	//nereded?????????????????????????
	public static List<Model>getAirForceModels(AirForceName airForceName){ //get models available to an air force
		return airForceToModels.getOrDefault(airForceName, null); 
	}
	
	//vars for creating maps of a model's periods and their statuses:
	protected static List<Period>periods; //periods
	protected static List<Status>statuses; //statuses of periods
	protected static Map<Period, Status>periodToStatus; //Map for holding a model's periods and their statuses
	protected abstract void setPeriodToStatus(Model model); ////++++++++ throws Exception; //concrete class creates Map of periods and their statuses
	
	//gets a Map of a model's periods and their statuses:
	public Map<Period, Status>getPeriodToStatus(Model model){ ///++++++ throws Exception{ 
		setPeriodToStatus(model); //create Map based on Model
		return periodToStatus; //return Map.
	}
	
	/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */
	//createa listof available models. 
	private static List<Model>avaiableModels;
	
	//based on the atatus in relation to the CURRENT period.
	public List<Model> getAvaliableModels(Period period, AirForceName airForceName) {
		
		for(Model i: getAirForceModels(airForceName)){
			
			getPeriodToStatus(i)
		}
		return null;
	}
	
	/*
	for(Campaign i : campaigns){ 
		System.out.println(i); 
		if (i.getEventName() == "Battle of AirForceRAF") campaign = i; //assign target campaign to reference
	}*/
	
	/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */
	
	
	/*
	public List<Model>getAllModels(){ //get all models available to all air forces
		return null; 
		
	};*/ 
	
	
	
}
