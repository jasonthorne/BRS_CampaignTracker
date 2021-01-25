package model;

import java.util.ArrayList;
import java.util.List;

public class Event {
	
	private String name; //name of event
	private Period startPeriod; //start period
	private Period endPeriod; //end period
	private int maxTurns; //number of turns
	private List<AirForce>airForces; //list of air forces involved
	
	private Event() {} //blank constructor
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++MAKE ALL PROPERTIES FINAL TOO!! 
	public Event(String name, Period startPeriod, Period endPeriod, int periodTotal, List<AirForce>airForces) {
		this.name = name;
		this.startPeriod = new Period(startPeriod.getBlock(), startPeriod.getYear());
		this.endPeriod = new Period(endPeriod.getBlock(), endPeriod.getYear());
		this.maxTurns = setMaxTurns(periodTotal);
		this.airForces = new ArrayList<AirForce>(airForces); //++++++++++++MAKE STRONGER :P
	}
	
	
	
	private int setMaxTurns(int periodTotal) {
		return periodTotal * Campaign.TURNS_PER_PERIOD;
	}
	
	private int getPeriodTotal() {
		return maxTurns / Campaign.TURNS_PER_PERIOD;
	}
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++MAKE ALL PROPERTIES FINAL TOO!! 
	/*
	//builder class:
	public static class EventBuilder {
		
		private Event event = new Event(); //create event
		
		//set name:
		public EventBuilder setName(String name) {
			event.name = name;
			return this;
		}
		
		//set start period:
		public EventBuilder setStartPeriod(Period period) {
			event.startPeriod = new Period(period.getBlock(), period.getYear());
			return this;
		}
		
		//set end period:
		public EventBuilder setEndPeriod(Period period) {
			event.endPeriod = new Period(period.getBlock(), period.getYear());
			return this;
		}
		
		//set number of turns:
		public EventBuilder setMaxTurns(int periodTotal) {
			event.maxTurns = (periodTotal * Campaign.TURNS_PER_PERIOD);
			return this;
		}
		
		//set list of event air forces:
		//+++++++++++++++++++++++++++++++loop through passed list and make a new one with each val
		public EventBuilder setAirForces(List<AirForce>airForces) { //++++++++++++++++++++MAKE STRONGER
			event.airForces = new ArrayList<AirForce>(airForces);
			return this;
		}
		
		//return built event:
		public Event build() { return event; } 
	}*/
	
	//get event name:
	public String getName() { return name; }
	
	//get start period:
	public Period getStartPeriod() {
		return new Period(startPeriod.getBlock(), startPeriod.getYear());
	}
	
	//get end period:
	public Period getEndPeriod() {
		return new Period(endPeriod.getBlock(), endPeriod.getYear());
	}
	
	//get max turns:
	public int getMaxTurns() { return maxTurns; }
		
	//get event air forces:
	public List<AirForce> getAirForces() {
		//+++++++++++++++++++++++++++++++loop through passed list and make a new one with each val & pass that! 
		return new ArrayList<AirForce>(airForces);
	}
	
	public Event getEvent() {
		return new Event(name, startPeriod, endPeriod, getPeriodTotal(), airForces);
	}

	
	
	@Override
	public String toString() {
		return "Event [name=" + name + ", startPeriod=" + startPeriod + ", endPeriod=" + endPeriod
				 + ", airForces=" + airForces + "]";
	}

}