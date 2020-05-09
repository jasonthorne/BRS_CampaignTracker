package com.brs.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.brs.airforces.AirForce;
import com.brs.airforces.InsertAirForces;
import com.brs.airforces.event.EventAirForces;
import com.brs.blocks.Block;
import com.brs.periods.Period;
import com.brs.years.Year;

//public class BattleOfBritain implements EventData {
public class BattleOfBritain extends EventData implements InsertEventAirforces{
	
	//name of event:
	private Event name = Event.BATTLE_OF_BRITAIN;
	
	//air forces involved, and their home advantage status:
	private final List<EventAirForce>airForces = Arrays.asList(
			new EventAirForce(AirForce.RAF, true),
			new EventAirForce(AirForce.LUFTWAFFE, false));
	
	//periods of history covered:
	private final Period startPeriod = new Period(Block.EARLY, Year.FORTY); ////////not real!!
	private final Period endPeriod = new Period(Block.EARLY, Year.FORTY_ONE); //////////
	
	
	
	protected BattleOfBritain() { //, List<EventAirForce>airForces, Period start, Period end) {
		
	}

	@Override
	protected void setName() {
		//super.
		
	}

	
	
	/*
	protected BattleOfBritain() {
		new BattleOfBritain(this.getName());
	}
	 */
	/*
	@Override
	public void test() {
		System.out.println(this.name);
	}
	*/
	//@Override 
	//protected Event getName() { return this.name; }
	
	/*
	@Override
	public List<Period> getPeriods() {	//get copy of periods covered:
		return new ArrayList<Period>(Arrays.asList(START_PERIOD, END_PERIOD)); 
	}*/
	
	
	
}

