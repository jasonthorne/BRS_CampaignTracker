package com.brs.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import com.brs.periods.Period;

public abstract class EventData {
	
	private Event name;	//name of event
	private List<EventAirForce>airForces; //air forces involved
	private Period startPeriod;	//starting period of history
	private Period endPeriod; //ending period of history
	
	//getters:
	protected Event getName() { return this.name; }
	protected List<EventAirForce> getAirForces() { return new ArrayList<EventAirForce>(this.airForces); }
	protected Period getStartPeriod() { return new Period(this.startPeriod.getBlock(), this.startPeriod.getYear()); }
	protected Period getEndPeriod() { return new Period(this.endPeriod.getBlock(), this.endPeriod.getYear()); }
	
	//constructor:
	EventData(Event name, List<EventAirForce>airForces, Period startPeriod, Period endPeriod){
		this.name = name;
		this.airForces = new ArrayList<EventAirForce>(airForces);
		this.startPeriod = new Period(startPeriod.getBlock(), startPeriod.getYear());
		this.endPeriod = new Period(endPeriod.getBlock(), endPeriod.getYear());
	}

}
