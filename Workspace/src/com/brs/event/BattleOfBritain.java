package com.brs.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.airforce.AirForce;
import com.brs.airforce.AirForceName;
import com.brs.airforce.RoyalAirForce;
import com.brs.airforce.UnitedStates;
import com.brs.period.Block;
import com.brs.period.Period;
import com.brs.period.Year;

public class BattleOfBritain {
	
	//BattleOfBritain name:
	private static final EventName NAME = EventName.BATTLE_OF_BRITAIN;
	
	//BattleOfBritain description:
	private static final String DESCRIPTION = "***BATTLE_OF_BRITAIN description here***"; 
	
	//BattleOfBritain AirForces:
	@SuppressWarnings("serial")
	private static final Map<AirForceName, AirForce>AIRFORCES = new HashMap<AirForceName, AirForce>(){{
		put(AirForceName.RAF, new RoyalAirForce());
		put(AirForceName.USAAF, new UnitedStates());
	}}; 
	
	//BattleOfBritain Periods:
	private static final List<Period>PERIODS = Period.getPeriods(
		new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE));
	
	
	
	
	//+++++++++++++++++++++++++++++++++++NOT NEEDED:++++++++++++++++++++
	
	
	public Map<AirForceName, AirForce> getAIRFORCES2(){
		return AIRFORCES;
	}
	
	private static final List<AirForce>AIRFORCES2 = Arrays.asList(new RoyalAirForce(), new UnitedStates());
	public List<AirForce> getAirForcesTEST(){
		return AIRFORCES2;
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	
	
	
	

}
