package model;

import java.util.ArrayList;
import java.util.List;

import model.Period.Block;

public final class Event {
	
	private final String name; //name of event
	private final Period startPeriod; //start period
	private final Period endPeriod; //end period
	private final int maxTurns; //number of turns
	private final List<AirForce>airForces; //list of air forces involved
	
	//constructor:
	public Event(String name, Period startPeriod, Period endPeriod, int periodTotal, List<AirForce>airForces) {
		this.name = name;
		this.startPeriod = startPeriod; 
		this.endPeriod = endPeriod; 
		this.maxTurns = setMaxTurns(periodTotal);
		this.airForces = new ArrayList<AirForce>(airForces);
	}
	
	private int setMaxTurns(int periodTotal) {
		return periodTotal * Campaign.TURNS_PER_PERIOD;
	}
	
	//get event name:
	public String getName() { return name; }
	
	//get start period:
	public Period getStartPeriod() {
		return new Period(startPeriod.getBlock(), startPeriod.getYear()); //+++++++++++++++too strong??????
	}
	
	//get end period:
	public Period getEndPeriod() {
		return new Period(endPeriod.getBlock(), endPeriod.getYear()); //+++++++++++++++too strong??????
	}
	
	//get max turns:
	public int getMaxTurns() { return maxTurns; }
		
	//get event air forces:
	public List<AirForce> getAirForces() {
		//+++++++++++++++++++++++++++++++loop through passed list and make a new one with each val & pass that! 
		return new ArrayList<AirForce>(airForces);
	}
	
	/*public Event getEvent() {
		return new Event(name, startPeriod, endPeriod, getPeriodTotal(), airForces);
	}*/

	
	
	@Override
	public String toString() {
		return "Event [name=" + name + ", startPeriod=" + startPeriod + ", endPeriod=" + endPeriod
				 + ", airForces=" + airForces + "]";
	}

}