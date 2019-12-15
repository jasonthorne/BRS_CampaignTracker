package com.android;

import java.util.Arrays;
import java.util.List;

import com.android.Period.Block;
import com.android.Period.Year;


public final class Event {
	
	/////////private Name name; 
	//////////private Period period;
	private final String name; //name of event
	private final List<Period>periods; //periods of history pertaining to event
	private final List<AirForce>airForces; //air forces available
	
	public enum EventName{
		
		BATTLE_OF_BRITAIN(
				"Battle of Britain", 
				Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE), 
				Period.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE))), 
		OPERATION_BARBAROSSA(
				"Operation Barbarossa",
				Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE),
				Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_ONE))),
		GUADALCANAL(
				"Guadalcanal",
				Arrays.asList(AirForce.USAAF, AirForce.IJAAF),
				Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE))), 
		STALINGRAD(
				"Stalingrad",
				Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE),
				Period.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE))), 
		ASSAULT_ON_THE_REICH(
				"Assault on the Reich",
				Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE),
				Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.EARLY, Year.FORTY_FIVE))),
		THE_ITALIAN_CAMPAIGN(
				"The Italian Campaign",
				Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE),
				Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR))),
		DEFENCE_OF_THE_HOME_ISLANDS(
				"Defence of the home islands",
				Arrays.asList(AirForce.USAAF, AirForce.IJAAF),
				Period.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE)));
		
		private String name; //name of chosen historic event
		private List<AirForce>airForces; //air forces involved
		private List<Period>periods; //periods of history covered
		//private String description; //=====================================
		
		//constructor
		private EventName(String name, List<AirForce>airForces, List<Period>periods) { 
			this.name = name;
			this.airForces = airForces;
			this.periods = periods;
			//this.description = getDescription(name); //===================================
		}
		
		@Override //override toString:
		public String toString() {
			return name;
		}
		
		//---------------------
		/*
		public List<AirForce> getAirForces(){
			return airForces;
		}*/
	}
	

	public String getName(){ return name; } //get name of event
		
	//constructor creates an Event, with values pertaining to chosen event name:
	private Event(EventName eventName) { 
		System.out.println("NEW Event constructed"); //+++++++++++++++TEST PRINT
		this.name = eventName.name; 
		this.airForces = eventName.airForces;
		this.periods = eventName.periods;
		System.out.println("Periods are: " + this.periods);  //+++++++++++++++TEST PRINT
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", airForces=" + airForces + "]";
	}
	
	//builder class:
	static class EventBuilder {
		
		private EventName eventName; //chosen even name
		
		//set chosen event name:
		public EventBuilder setEventName(EventName eventName) { 
			this.eventName = eventName; 
			return this;
		}
		//build and return an Event:
		public Event build() { 
			return new Event(eventName); 
		}
	}
	
}

