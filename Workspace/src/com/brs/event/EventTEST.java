package com.brs.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.airforce.AirForce;
import com.brs.airforce.AirForceName;
import com.brs.period.Period;
import com.brs.plane.Model;

import ideas.FileReading;

public abstract class EventTEST{
	
	protected EventName name; //name of event
	protected List<AirForceName>airForceNames; //air forces involved 
	protected List<Period>periods; //periods of history covered
	
	protected abstract void setName(); //extended class sets name
	protected abstract void setAirForceNames(); //extended class sets airForces
	protected abstract void setPeriods(); //extended class sets periods
	
	//++++CHANGE THESE TO ONLY RETURN DEEP COPIES!!!!!!!!!maybe!++++++++++++++
	public String getName() { return name.toString(); } //return name of event
	public List<AirForceName>getAirForceNames() { return airForceNames; } //return air forces involved 
	public List<Period>getPeriods() { return periods; } //return periods covered
	
	//map for holding AirForces relevant to event:
	protected Map<AirForceName, AirForce>airForceNameToAirForce = new HashMap<AirForceName, AirForce>();
	protected abstract void putAirForceIfAbsent(AirForceName airForceName);  //extended class adds to map above

	//returns required AirForce:
	public AirForce getAirForce(AirForceName airForceName) {
		putAirForceIfAbsent(airForceName); //add AirForce object to map if absent
		return airForceNameToAirForce.get(airForceName); //return reference to AirForce object
	}
	
	//-------------
	
	//code for moving period UNTILL you hit the last period in the list. //////////MAYBE THIS SHOULD BE IN CAMPAIGN PAGE????
	
	public Period getCurrPeriod() {
		return null;
		//hmmmmmmmmmmmmmm..........
	}
		
	//--------------

}
