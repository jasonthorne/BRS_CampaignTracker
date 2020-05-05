package com.brs.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.brs.AirForce;
import com.brs.blocks.Block;
import com.brs.periods.Period;
import com.brs.years.Year;

public class BattleOfBritain implements Event__OLD {
	
	//DONT HAVE THIS!!
	/*
	 * 
	 * mimick the tabnlesexactly. 
	 * 
	 * event airforces class
	 * 
	 * map with event > List airforces
	 */
	
	//name of event:
	private final Event name = Event.BATTLE_OF_BRITAIN;
	
	//air forces involved, and their home advantage status:
	private final List<EventAirForce>airForces = Arrays.asList(
			new EventAirForce(AirForce.RAF, true),
			new EventAirForce(AirForce.LUFTWAFFE, false));
	
	//periods of history covered:
	private final Period startPeriod = new Period(Block.EARLY, Year.FORTY); ////////not real!!
	private final Period endPeriod = new Period(Block.EARLY, Year.FORTY_ONE); //////////
	
	
	@Override
	public List<Period> getPeriods() {	//get copy of periods covered:
		return new ArrayList<Period>(Arrays.asList(startPeriod, endPeriod)); 
	}
	
	
}

