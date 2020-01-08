package com.brs.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.airforce.AirForce;
import com.brs.airforce.AirForceName;
import com.brs.period.Period;


public abstract class EventTEST{
	
	public abstract String getName(); //return name of event
	public abstract List<AirForceName>getAirForceNames(); //return air forces involved 
	public abstract List<Period>getPeriods(); //return periods covered
	
	//map for holding AirForces required:
	protected Map<AirForceName, AirForce>airForceNameToAirForce = new HashMap<AirForceName, AirForce>();
	protected abstract void putAirForceIfAbsent(AirForceName airForceName);  //extended class adds to map above

	//returns required AirForce:
	public AirForce getAirForce(AirForceName airForceName) {
		putAirForceIfAbsent(airForceName); //add AirForce object to map if absent
		return airForceNameToAirForce.get(airForceName); //return reference to AirForce object
	}
	
}
