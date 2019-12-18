package com.brs.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.brs.airforce.AirForceData.AirForce;
import com.brs.period.Period;
import com.brs.period.PeriodData;
import com.brs.period.PeriodData.Block;
import com.brs.period.PeriodData.Year;
import com.brs.plane.PlaneData;

public abstract class EventData {
	
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
	
	//events and the corresponding air forces involved:
	private static final HashMap<String, List<AirForce>> eventNames_airForces = new HashMap<String, List<AirForce>>() {{
	    put("Battle of Britain", Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE));
	    put("Operation Barbarossa", Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE));
	    put("Guadalcanal", Arrays.asList(AirForce.USAAF, AirForce.IJAAF));
	    put("Stalingrad", Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE));
	    put("Assault on the Reich", Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE));
	    put("The Italian Campaign", Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE));
	    put("Defence of the home islands", Arrays.asList(AirForce.USAAF, AirForce.IJAAF));
	}};
	
	//events and their corresponding periods of history: ////////++++++++++++++++TRY THIS!!! +++++++++++++++++++++++++++++GULP!!! :P
	private static final HashMap<String, List<List<?>>> eventNames_periods = new HashMap<String, List<List<?>>>() {{
	    put("Battle of Britain", Arrays.asList(Arrays.asList(Block.MID, Year.FORTY), Arrays.asList(Block.MID, Year.FORTY_ONE)));
	    put("Operation Barbarossa", Arrays.asList(Arrays.asList(Block.MID, Year.FORTY), Arrays.asList(Block.MID, Year.FORTY_ONE)));
	    put("Guadalcanal", Arrays.asList(Arrays.asList(Block.MID, Year.FORTY), Arrays.asList(Block.MID, Year.FORTY_ONE)));
	    put("Stalingrad", Arrays.asList(Arrays.asList(Block.MID, Year.FORTY), Arrays.asList(Block.MID, Year.FORTY_ONE)));
	    put("Assault on the Reich", Arrays.asList(Arrays.asList(Block.MID, Year.FORTY), Arrays.asList(Block.MID, Year.FORTY_ONE)));
	    put("The Italian Campaign", Arrays.asList(Arrays.asList(Block.MID, Year.FORTY), Arrays.asList(Block.MID, Year.FORTY_ONE)));
	    put("Defence of the home islands", Arrays.asList(Arrays.asList(Block.MID, Year.FORTY), Arrays.asList(Block.MID, Year.FORTY_ONE)));
	}};
	
	/* https://stackoverflow.com/questions/19602601/create-an-arraylist-with-multiple-object-types
	 * 
	 * https://stackoverflow.com/questions/22806202/java-nested-generic-type
	 * 
	//events and their corresponding periods of history:
	private static final HashMap<String, List<Period>> eventNames_periods = new HashMap<String, List<Period>>() {{
	    put("Battle of Britain", Period.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE)));
	    put("Operation Barbarossa", Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_ONE)));
	    put("Guadalcanal", Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE)));
	    put("Stalingrad", Period.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE)));
	    put("Assault on the Reich", Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.EARLY, Year.FORTY_FIVE)));
	    put("The Italian Campaign", Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR)));
	    put("Defence of the home islands", Period.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE)));
	}};
	*/
	
	//events and their corresponding description:
	private static final HashMap<String, String> eventNames_descriptions = new HashMap<String, String>() {{
	    put("Battle of Britain", "'Battle of Britain' description");
	    put("Operation Barbarossa", "'Operation Barbarossa' description");
	    put("Guadalcanal", "'Guadalcanal' description");
	    put("Stalingrad", "'Stalingrad' description");
	    put("Assault on the Reich", "'Assault on the Reich' description");
	    put("The Italian Campaign", "'The Italian Campaign' description");
	    put("Defence of the home islands", "'Defence of the home islands' description");
	}};
	
	
	//getters (protected so only Event has access):
	
	//get AirForces associated with target event:
	protected static List<AirForce>getAirForces(String name){
		return eventNames_airForces.get(name);
	}
	
	/*
	//get Periods associated with target event:
	protected static List<Period>getPeriods(String name){
		return eventNames_periods.get(name);
	}
	*/
	
	//get description associated with target event:
	protected static String getDescription(String name){
		return eventNames_descriptions.get(name);
	}
	
	
}
