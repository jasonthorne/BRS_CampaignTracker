package com.brs.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.airforce.AirForce;
import com.brs.airforce.AirForceName;
import com.brs.airforce.RoyalAirForce;
import com.brs.airforce.UnitedStates;

public class BattleOfBritain {
	
	//BattleOfBritain name:
	private static final EventName NAME = EventName.BATTLE_OF_BRITAIN;
	
	//BattleOfBritain description:
	private static final String DESCRIPTION = "***BATTLE_OF_BRITAIN description here***"; 
	
	//BattleOfBritain airForces:
	private static final List<AirForce>AIRFORCES = Arrays.asList(new RoyalAirForce(), new UnitedStates());
	
	
	@SuppressWarnings("serial")
	private static final Map<AirForceName, AirForce>AIRFORCES2 = new HashMap<AirForceName, AirForce>(){{
		put(AirForceName.RAF, new RoyalAirForce());
		put(AirForceName.USAAF, new UnitedStates());
	}}; 
	
	
	
	public List<AirForce> getAirForcesTEST(){
		return AIRFORCES;
	}
	
	
	public Map<AirForceName, AirForce> getAIRFORCES2(){
		return AIRFORCES2;
	}

}
