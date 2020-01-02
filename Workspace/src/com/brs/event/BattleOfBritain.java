package com.brs.event;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.airforce.AirForce;
import com.brs.airforce.AirForceName;
import com.brs.airforce.RoyalAirForce;
import com.brs.airforce.Luftwaffe;
import com.brs.period.Block;
import com.brs.period.Period;
import com.brs.period.Year;

public class BattleOfBritain extends EventTEST{
	
	//BattleOfBritain name:
	private static final EventName NAME = EventName.BATTLE_OF_BRITAIN;
	
	//BattleOfBritain description:
	private static final String DESCRIPTION = "***BattleOfBritain description here***";
	
	//BattleOfBritain air forces:
	private static final List<AirForceName>AIRFORCENAMES = Arrays.asList(AirForceName.RAF, AirForceName.LUFTWAFFE);
	
	//BattleOfBritain periods:
	private static final List<Period>PERIODS = Period.getPeriods(
			new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE));

	@Override
	protected void setName() {name = NAME;}
	@Override
	protected void setDescription() {description = DESCRIPTION;}
	@Override
	protected void setAirForceNames() {airForceNames = AIRFORCENAMES;}
	@Override
	protected void setPeriods() {periods = PERIODS;}

	@Override
	protected void setAirForceNameToAirForce(AirForceName airForceName) {
		
		
	}
	
	
	
	
	//map of Airforces
	//use put if absent here to add to this map with a switch. 
	
	
	
	
	/*
	 * PLAYER SHOULD CREATE THE AIRFORCE. THIS CLASS MERELY DICTATES WHAT AIRFORCES ARE ALLOWED!! 
	//BattleOfBritain air forces:
	@SuppressWarnings("serial")
	private static final Map<AirForceName, AirForce>AIRFORCENAME_TO_AIRFORCE = new HashMap<AirForceName, AirForce>(){{
		put(AirForceName.RAF, new RoyalAirForce()); 
		put(AirForceName.LUFTWAFFE, new Luftwaffe()); 
	}}; 
	*/
	
	
	
	
	
	//++++++++++++
	
	
	
	
	
	//+++++++++++
	
	//+++++++++++++++++++++++++++++++++++NOT NEEDED:++++++++++++++++++++
	
	/*
	public Map<AirForceName, AirForce> getAIRFORCES2(){
		return AIRFORCENAME_TO_AIRFORCE;
	}
	*/
	/*
	private static final List<AirForce>AIRFORCES2 = Arrays.asList(new RoyalAirForce(), new Luftwaffe());
	public List<AirForce> getAirForcesTEST(){
		return AIRFORCES2;
	}
	*/
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	
	
	
	

}
