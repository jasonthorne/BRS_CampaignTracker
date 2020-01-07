package com.brs.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.brs.airforce.AirForceName;
import com.brs.airforce.Luftwaffe;
import com.brs.airforce.Soviet;
import com.brs.period.Block;
import com.brs.period.Period;
import com.brs.period.Periods;
import com.brs.period.Year;

public class Stalingrad extends EventTEST{

	//Stalingrad name:
	private static final EventName NAME = EventName.STALINGRAD;
	
	//Stalingrad air forces:
	private static final List<AirForceName>AIR_FORCE_NAMES = Arrays.asList(
			AirForceName.VVS, AirForceName.LUFTWAFFE);
			
	//Stalingrad periods:
	private static final List<Period>PERIODS = Periods.getPeriods(
			new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE));

	//creates and adds an instance of requested AirForce to map if absent:
	@Override 
	protected void putAirForceIfAbsent(AirForceName airForceName){
		switch(airForceName){ 
		  case VVS:
			  airForceNameToAirForce.putIfAbsent(airForceName, new Soviet());
			  break;
		  case LUFTWAFFE:
			  airForceNameToAirForce.putIfAbsent(airForceName, new Luftwaffe());
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
