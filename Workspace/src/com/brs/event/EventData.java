package com.brs.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.airforce.AirForceName;
import com.brs.period.Period;
import com.brs.period.Periods;
import com.brs.period.Block;
import com.brs.period.Year;


public abstract class EventData {
	
	//events and the corresponding air forces involved:
	private static final Map<String, List<AirForceName>> eventNameToAirForces = new HashMap<String, List<AirForceName>>() {{
	    put(EventName.BATTLE_OF_BRITAIN.toString(), Arrays.asList(AirForceName.RAF, AirForceName.LUFTWAFFE));
	    put(EventName.OPERATION_BARBAROSSA.toString(), Arrays.asList(AirForceName.VVS, AirForceName.LUFTWAFFE));
	    put(EventName.GUADALCANAL.toString(), Arrays.asList(AirForceName.USAAF, AirForceName.IJAAF));
	    put(EventName.STALINGRAD.toString(), Arrays.asList(AirForceName.VVS, AirForceName.LUFTWAFFE));
	    put(EventName.ASSAULT_ON_THE_REICH.toString(), Arrays.asList(AirForceName.RAF, AirForceName.USAAF, AirForceName.LUFTWAFFE));
	    put(EventName.THE_ITALIAN_CAMPAIGN.toString(), Arrays.asList(AirForceName.RAF, AirForceName.USAAF, AirForceName.LUFTWAFFE));
	    put(EventName.DEFENCE_OF_THE_HOME_ISLANDS.toString(), Arrays.asList(AirForceName.USAAF, AirForceName.IJAAF));
	}};
	
	
	//events and their corresponding periods of history: //+++++++++++++THINK ABOUT STORING IN JUST BLOCKS AND YEARS, AND CREATING PERIODS IN METHOD INSTEAD
	private static final Map<String, List<Period>> eventNameToPeriods = new HashMap<String, List<Period>>() {{
	    put(EventName.BATTLE_OF_BRITAIN.toString(), Periods.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE)));
	    put(EventName.OPERATION_BARBAROSSA.toString(), Periods.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_ONE)));
	    put(EventName.GUADALCANAL.toString(), Periods.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE)));
	    put(EventName.STALINGRAD.toString(), Periods.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE)));
	    put(EventName.ASSAULT_ON_THE_REICH.toString(), Periods.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.EARLY, Year.FORTY_FIVE)));
	    put(EventName.THE_ITALIAN_CAMPAIGN.toString(), Periods.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR)));
	    put(EventName.DEFENCE_OF_THE_HOME_ISLANDS.toString(), Periods.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE)));
	}};
	
	
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
	
	
	//getters:
	
	//get AirForces associated with target event:
	protected static List<AirForceName>getAirForces(String name){ return eventNameToAirForces.get(name); }
		
	//get Periods associated with target event:
	protected static List<Period>getPeriods(String name){ return eventNameToPeriods.get(name); }
	
	//get description associated with target event:
	protected static String getDescription(String name){ return eventNameToDescription.get(name); }
		
		
	
}
