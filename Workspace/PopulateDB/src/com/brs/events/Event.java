package com.brs.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.brs.airforces.AirForce;
import com.brs.periods.Period;

//events covered (listed by date):
public enum Event {
	
	BATTLE_OF_BRITAIN(
			"Battle of Britain", 
			Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE),
			Arrays.asList(AirForce.RAF));/*	
	OPERATION_BARBAROSSA(
			"Operation Barbarossa",
			);
	GUADALCANAL("Guadalcanal"),
	STALINGRAD("Stalingrad"),
	ASSAULT_ON_THE_REICH("Assault on the Reich"),
	THE_ITALIAN_CAMPAIGN("The Italian Campaign"),
	DEFENCE_OF_THE_HOME_ISLANDS("Defence of the home islands");*/
	
	private final String event; //name of event
	private Event(String event, List<AirForce>airForces, List<AirForce>homeAdvantages) { //constructor sets event
		this.event = event;
		this.airForces = new ArrayList<AirForce>(airForces);
		this.homeAdvantages = new ArrayList<AirForce>(homeAdvantages);
	} 
	@Override 
	public String toString() {return event;} //return event
	
	/*
	 * airforces
	 * home adv
	 */
	private List<AirForce>airForces;
	private List<AirForce>homeAdvantages;
	
	
	
	
}
