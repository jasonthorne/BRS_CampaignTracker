package com.brs.airforces.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.airforces.AirForce;
import com.brs.events.Event;

public abstract class EventAirForces { 
	
	protected static final Map<Event, List<AirForce>>event_to_airForces = new HashMap<Event, List<AirForce>>();
	
	{
		event_to_airForces.put(Event.BATTLE_OF_BRITAIN, Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE));
		event_to_airForces.put(Event.OPERATION_BARBAROSSA, Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE));
	}
	
	

}


//samne for Event periods