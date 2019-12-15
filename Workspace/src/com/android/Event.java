package com.android;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.android.AirForceData.AirForce;
import com.android.Period.Block;
import com.android.Period.Year;

public final class Event {
	
	//event name values:
	public enum EventName{
		BATTLE_OF_BRITAIN("Battle of Britain"), 
		OPERATION_BARBAROSSA("Operation Barbarossa"),
		GUADALCANAL("Guadalcanal"),
		STALINGRAD("Stalingrad"),
		ASSAULT_ON_THE_REICH("Assault on the Reich"),
		THE_ITALIAN_CAMPAIGN("The Italian Campaign"),
		DEFENCE_OF_THE_HOME_ISLANDS("Defence of the home islands");
		
		private String name; //name of chosen event
		//constructor:
		private EventName(String name) {this.name = name;} //set name of event
		@Override 
		public String toString() {return name;} //return chosen event
	}
	
	//////////private Period period;
	private final String name; //name of event
	private final List<AirForce>airForces; //air forces involved
	private final List<Period>periods; //periods of history covered
	private final String description; //description of event
	
	//All events and the corresponding air forces involved:
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
	
	//All events and their corresponding description:
	private static final HashMap<String, String> eventNames_descriptions = new HashMap<String, String>() {{
	    put("Battle of Britain", "'Battle of Britain' description");
	    put("Operation Barbarossa", "'Operation Barbarossa' description");
	    put("Guadalcanal", "'Guadalcanal' description");
	    put("Stalingrad", "'Stalingrad' description");
	    put("Assault on the Reich", "'Assault on the Reich' description");
	    put("The Italian Campaign", "'The Italian Campaign' description");
	    put("Defence of the home islands", "'Defence of the home islands' description");
	}};
	
	public String getName(){ return name; } //get name of event
	
	//constructor:
	private Event(String eventName) { 
		System.out.println("Event constructed");
		this.name = eventName; //name event
		this.airForces = eventNames_airForces.get(name); //assign air forces
		this.periods = eventNames_periods.get(name); //assign periods
		this.description = eventNames_descriptions.get(name); //assign description
		System.out.println("AIRFORCES: " + this.airForces); //+++++++++++++++TEST PRINT
		System.out.println("PERIODS: " + this.periods); //+++++++++++++++TEST PRINT
		System.out.println("DESCRIPTION: " + this.description); 
	}

	@Override
	public String toString() {
		return "Event [name=" + name + ", airForces=" + airForces + "]";
	}
	
	//builder class:
	static final class EventBuilder {
		
		private String eventName; //chosen event
		
		//set chosen event name:
		public EventBuilder setEventName(EventName eventName) { 
			this.eventName = eventName.name; 
			return this;
		}
		//build and return an Event:
		public Event build() { 
			return new Event(eventName); 
		}
		
	}
	
}

