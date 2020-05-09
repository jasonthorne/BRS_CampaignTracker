package com.brs.events;

import java.util.Arrays;
import java.util.List;

import com.brs.airforces.AirForce;
import com.brs.blocks.Block;
import com.brs.periods.Period;
import com.brs.years.Year;

public class BattleOfBritain extends EventData {
	
	//name of event:
	private static final Event NAME = Event.BATTLE_OF_BRITAIN;
	
	//air forces involved, and their home advantage status:
	private static final List<EventAirForce>AIR_FORCES = Arrays.asList(
			new EventAirForce(AirForce.RAF, true),
			new EventAirForce(AirForce.LUFTWAFFE, false));
	
	//periods of history covered:
	private static final Period START_PERIOD = new Period(Block.EARLY, Year.FORTY); ////////not real!!
	private static final Period END_PERIOD = new Period(Block.EARLY, Year.FORTY_ONE); //////////
	
	protected BattleOfBritain() {
		//pass values to parent for encapsulation during 'InsertEventData.insert()'
		super(NAME, AIR_FORCES, START_PERIOD, END_PERIOD); 
	}
	
}

