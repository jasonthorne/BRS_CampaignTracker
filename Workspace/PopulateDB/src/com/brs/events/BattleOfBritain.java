package com.brs.events;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.AirForce;
import com.brs.blocks.Block;
import com.brs.periods.Period;
import com.brs.years.Year;


public class BattleOfBritain implements Event {
	
	/*
	//name of event:
	private static final EventName NAME = EventName.BATTLE_OF_BRITAIN;
	
	//air forces involved, and their home advantage status:
	private static final List<EventAirForce>AIR_FORCES = Arrays.asList(
			new EventAirForce(AirForce.RAF, true),
			new EventAirForce(AirForce.LUFTWAFFE, false));
	
	//periods of history covered:
	private static final Period PERIOD_START = new Period(Block.MID, Year.FORTY);
	private static final Period PERIOD_END = new Period(Block.EARLY, Year.FORTY_ONE);
	*/
	
	
	/*
	 * private static final List<Period>PERIODS = Arrays.asList(
			new StartPeriod(AirForce.RAF, true),
			new endPeriod(AirForce.LUFTWAFFE, false));
	 */
	
	public Period getTest() {
		return new Period(Block.EARLY, Year.FORTY);
	}
	
}

