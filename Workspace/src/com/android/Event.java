package com.android;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.Period.Block;
import com.android.Period.Year;


public final class Event {
	
	//////////private Period period;
	private final String name; //name of event
	private final List<AirForce>airForces; //air forces involved
	private final List<Period>periods; //periods of history covered
	
	//All events and their corresponding air forces involved:
	private static final HashMap<String, List<AirForce>> eventNames_airForces = new HashMap<String, List<AirForce>>() {{
	    put("Battle of Britain", Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE));
	    put("Operation Barbarossa", Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE));
	    put("Guadalcanal", Arrays.asList(AirForce.USAAF, AirForce.IJAAF));
	    put("Stalingrad", Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE));
	    put("Assault on the Reich", Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE));
	    put("The Italian Campaign", Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE));
	    put("Defence of the home islands", Arrays.asList(AirForce.USAAF, AirForce.IJAAF));
	}};
	
	//All events and their corresponding periods of history:
	private static final HashMap<String, List<Period>> eventNames_periods = new HashMap<String, List<Period>>() {{
	    put("Battle of Britain", Period.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE)));
	    put("Operation Barbarossa", Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_ONE)));
	    put("Guadalcanal", Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE)));
	    put("Stalingrad", Period.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE)));
	    put("Assault on the Reich", Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.EARLY, Year.FORTY_FIVE)));
	    put("The Italian Campaign", Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR)));
	    put("Defence of the home islands", Period.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE)));
	}};
	
	public enum EventName{
		
		BATTLE_OF_BRITAIN("Battle of Britain"), 
				//Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE), 
				//Period.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE))), 
		OPERATION_BARBAROSSA(
				"Operation Barbarossa"),
				//Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE),
				//Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_ONE))),
		GUADALCANAL(
				"Guadalcanal"),
				//Arrays.asList(AirForce.USAAF, AirForce.IJAAF),
				//Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE))), 
		STALINGRAD(
				"Stalingrad"),
				//Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE),
				//Period.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE))), 
		ASSAULT_ON_THE_REICH(
				"Assault on the Reich"),
				//Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE),
				//Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.EARLY, Year.FORTY_FIVE))),
		THE_ITALIAN_CAMPAIGN(
				"The Italian Campaign"),
				//Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE),
				//Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR))),
		DEFENCE_OF_THE_HOME_ISLANDS(
				"Defence of the home islands");
				//Arrays.asList(AirForce.USAAF, AirForce.IJAAF),
				//Period.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE)));
		
		private String name; //name of chosen historic event
		//private List<AirForce>airForces; //air forces involved
		//private List<Period>periods; //periods of history covered
		//private String description; //=====================================
		
		//constructor:
		private EventName(String name) { //, List<AirForce>airForces, List<Period>periods) { 
			this.name = name;
			//this.airForces = airForces;
			//this.periods = periods;
			//this.description = getDescription(name); //===================================
		}
		
		@Override 
		public String toString() { return name; } //override toString
			

		//---------------------
		/*
		public List<AirForce> getAirForces(){
			return airForces;
		}*/
	}
	

	public String getName(){ return name; } //get name of event
	
	
	//constructor creates an Event, with values pertaining to chosen event name:
	private Event(String eventName) { 
			System.out.println("NEW Event constructed"); //+++++++++++++++TEST PRINT
			this.name = eventName; 
			
			this.airForces = eventNames_airForces.get(name);
			this.periods = eventNames_periods.get(name);
			
			System.out.println("AIRFORCES: " + this.airForces);
			System.out.println("PERIODS: " + this.periods);
			/*
			this.airForces = eventName.airForces;
			this.periods = eventName.periods;
			System.out.println("Periods are: " + this.periods);  //+++++++++++++++TEST PRINT
			*/
			
	}

	/*	
	//constructor creates an Event, with values pertaining to chosen event name:
	private Event(EventName eventName) { 
		System.out.println("NEW Event constructed"); //+++++++++++++++TEST PRINT
		this.name = eventName.name; 
		this.airForces = eventName.airForces;
		this.periods = eventName.periods;
		System.out.println("Periods are: " + this.periods);  //+++++++++++++++TEST PRINT
	}
	*/

	@Override
	public String toString() {
		return "Event [name=" + name + ", airForces=" + airForces + "]";
	}
	
	//builder class:
	static final class EventBuilder {
		
		
		private String eventName;
		// private EventName eventName;
		
		//set chosen event name:
		public EventBuilder setEventName(EventName eventName) { 
			this.eventName = eventName.name; 
			return this;
		}
		//build and return an Event:
		public Event build() { 
			return new Event(eventName); 
		}
		
		/*
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
		
		*/
	}
	
}

