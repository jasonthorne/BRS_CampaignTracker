package com.brs.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static final Map<String, List<AirForce>> eventNameToAirForces = new HashMap<String, List<AirForce>>() {{
	    put(EventName.BATTLE_OF_BRITAIN.toString(), Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE));
	    put(EventName.OPERATION_BARBAROSSA.toString(), Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE));
	    put(EventName.GUADALCANAL.toString(), Arrays.asList(AirForce.USAAF, AirForce.IJAAF));
	    put(EventName.STALINGRAD.toString(), Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE));
	    put(EventName.ASSAULT_ON_THE_REICH.toString(), Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE));
	    put(EventName.THE_ITALIAN_CAMPAIGN.toString(), Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE));
	    put(EventName.DEFENCE_OF_THE_HOME_ISLANDS.toString(), Arrays.asList(AirForce.USAAF, AirForce.IJAAF));
	}};
	
	//get AirForces associated with target event:
	protected static List<AirForce>getAirForces(String name){ return eventNameToAirForces.get(name);}
	
	
	
	//events and their corresponding periods of history: //+++++++++++++THINK ABOUT STORING IN JUST BLOCKS AND YEARS, AND CREATING PERIODS IN METHOD INSTEAD
	private static final Map<String, List<Period>> eventNameToPeriods = new HashMap<String, List<Period>>() {{
	    put(EventName.BATTLE_OF_BRITAIN.toString(), Period.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE)));
	    put(EventName.OPERATION_BARBAROSSA.toString(), Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_ONE)));
	    put(EventName.GUADALCANAL.toString(), Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE)));
	    put(EventName.STALINGRAD.toString(), Period.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE)));
	    put(EventName.ASSAULT_ON_THE_REICH.toString(), Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.EARLY, Year.FORTY_FIVE)));
	    put(EventName.THE_ITALIAN_CAMPAIGN.toString(), Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR)));
	    put(EventName.DEFENCE_OF_THE_HOME_ISLANDS.toString(), Period.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE)));
	}};
	
	//get Periods associated with target event:
	protected static List<Period>getPeriods(String name){ return eventNameToPeriods.get(name); }
	
	
	
	//events and their corresponding description:
	private static final Map<String, String> eventNameToDescription = new HashMap<String, String>() {{
	    put(EventName.BATTLE_OF_BRITAIN.toString(), "'Battle of Britain' description");
	    put(EventName.OPERATION_BARBAROSSA.toString(), "'Operation Barbarossa' description");
	    put(EventName.GUADALCANAL.toString(), "'Guadalcanal' description");
	    put(EventName.STALINGRAD.toString(), "'Stalingrad' description");
	    put(EventName.ASSAULT_ON_THE_REICH.toString(), "'Assault on the Reich' description");
	    put(EventName.THE_ITALIAN_CAMPAIGN.toString(), "'The Italian Campaign' description");
	    put(EventName.DEFENCE_OF_THE_HOME_ISLANDS.toString(), "'Defence of the home islands' description");
	}};
	
	//get description associated with target event:
	protected static String getDescription(String name){ return eventNameToDescription.get(name); }
		
		
	
}
