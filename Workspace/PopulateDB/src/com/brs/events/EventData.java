package com.brs.events;

import java.util.List;

import com.brs.periods.Period;

//parent class for events:
public abstract class EventData {
	
	//getters:
	protected abstract Event getName();
	protected abstract List<EventAirForce> getAirForces();
	protected abstract Period getStartPeriod();
	protected abstract Period getEndPeriod();
	
}
