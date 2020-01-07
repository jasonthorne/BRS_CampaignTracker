package com.brs.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.brs.airforce.AirForceName;
import com.brs.airforce.Luftwaffe;
import com.brs.airforce.RoyalAirForce;
import com.brs.airforce.UnitedStates;
import com.brs.period.Block;
import com.brs.period.Period;
import com.brs.period.Periods;
import com.brs.period.Year;

public class TheItalianCampaign extends EventTEST{

	//TheItalianCampaign name:
	private static final EventName NAME = EventName.THE_ITALIAN_CAMPAIGN;
	
	//TheItalianCampaign air forces:
	private static final List<AirForceName>AIR_FORCE_NAMES = Arrays.asList(
			AirForceName.RAF, AirForceName.USAAF, AirForceName.LUFTWAFFE);
			
	//TheItalianCampaign periods:
	private static final List<Period>PERIODS = Periods.getPeriods(
			new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR));

	//creates and adds an instance of requested AirForce to map if absent:
	@Override 
	protected void putAirForceIfAbsent(AirForceName airForceName){
		switch(airForceName){ 
		  case RAF:
			  airForceNameToAirForce.putIfAbsent(airForceName, new RoyalAirForce());
			  break;
		  case USAAF:
			  airForceNameToAirForce.putIfAbsent(airForceName, new UnitedStates());
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
