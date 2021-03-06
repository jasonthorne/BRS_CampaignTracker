package com.brs.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.brs.airforce.AirForceName;
import com.brs.airforce.Japan;
import com.brs.airforce.UnitedStates;
import com.brs.period.Block;
import com.brs.period.Period;
import com.brs.period.Periods;
import com.brs.period.Year;

public class DefenceOfTheHomeIslands extends Event{

	//DefenceOfTheHomeIslands name:
	private static final EventName NAME = EventName.DEFENCE_OF_THE_HOME_ISLANDS;
	
	//DefenceOfTheHomeIslands air forces:
	private static final List<AirForceName>AIR_FORCE_NAMES = Arrays.asList(
			AirForceName.USAAF, AirForceName.IJAAF);
			
	//DefenceOfTheHomeIslands periods:
	private static final List<Period>PERIODS = Periods.getPeriods(
			new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));

	//creates and adds an instance of requested AirForce to map if absent:
	@Override 
	protected void putAirForceIfAbsent(AirForceName airForceName){
		switch(airForceName){ 
		  case USAAF:
			  airForceNameToAirForce.putIfAbsent(airForceName, new UnitedStates());
			  break;
		  case IJAAF:
			  airForceNameToAirForce.putIfAbsent(airForceName, new Japan());
			  break;
		}
	}

	@Override
	public String getName() {return NAME.toString();} //get name of event
	@Override
	public List<AirForceName> getAirForceNames() {return new ArrayList<AirForceName>(AIR_FORCE_NAMES);} //get copy of air forces involved
	@Override
	public List<Period> getPeriods() {return new ArrayList<Period>(PERIODS);} //get copy of periods covered
}
