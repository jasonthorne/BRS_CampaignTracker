package com.brs.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.airforce.AirForce;
import com.brs.airforce.AirForceName;
import com.brs.period.Period;
import com.brs.plane.Model;

public abstract class EventTEST {
	
	protected EventName name; //name of event
	protected String description; //description of event
	protected List<AirForceName>airForceNames; //air forces involved 
	protected List<Period>periods; //periods of history covered
	
	protected abstract void setName(); //extended class sets name
	protected abstract void setDescription(); //extended class sets description
	protected abstract void setAirForceNames(); //extended class sets airForces
	protected abstract void setPeriods(); //extended class sets periods
	
	//++++CHANGE THESE TO ONLY RETURN DEEP COPIES!!!!!!!!!!++++++++++++++
	public String getName() { return name.toString(); } //return name of event
	public String getDescription() { return description; } //return description of event 
	public List<AirForceName>getAirForceNames() { return airForceNames; } //return air forces involved 
	public List<Period> getPeriods() { return periods; } //return periods covered
	
	//map for holding selected AirForces:
	protected Map<AirForceName, AirForce>airForceNameToAirForce = new HashMap<AirForceName, AirForce>();
	protected abstract void setAirForceNameToAirForce(AirForceName airForceName);  //extended class sets map above
	
	
	
		
	

}
