package com.brs.event;

import java.util.ArrayList;
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
import com.brs.plane.Model;
import com.brs.plane.Status;


public class BattleOfBritain extends EventTEST{
	//PLAYER SHOULD CONTAIN THE AIRFORCE. THIS CLASS MERELY DICTATES WHAT AIRFORCES ARE ALLOWED AND PROVIDES REFERENCES TO THEM!! +++++++++++
	//BattleOfBritain name:
	private static final EventName NAME = EventName.BATTLE_OF_BRITAIN;
	
	//BattleOfBritain air forces:
	private static final List<AirForceName>AIR_FORCE_NAMES = Arrays.asList(
			AirForceName.RAF, AirForceName.LUFTWAFFE);
	
	//BattleOfBritain periods:
	private static final List<Period>PERIODS = Period.getPeriods(
			new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE));

	/*
	//constructor calls setters:
	public BattleOfBritain(){ 
		setName(); 
		setAirForceNames(); 
		setPeriods(); 
	}*/

	@Override //add requested AirForce object to map if absent:
	protected void putAirForceIfAbsent(AirForceName airForceName){
		switch(airForceName){ 
		  case RAF:
			  airForceNameToAirForce.putIfAbsent(airForceName, new RoyalAirForce());
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
