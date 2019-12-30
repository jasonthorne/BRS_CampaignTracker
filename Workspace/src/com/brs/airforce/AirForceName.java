package com.brs.airforce;

import java.util.Arrays;
import java.util.List;

import com.brs.airforce.RoyalAirForce;

//AirForce name values:
public enum AirForceName {
	
	RAF("RAF"){
		//{ RoyalAirForce.putAirForceToModels(); }
	},
	LUFTWAFFE("Luftwaffe"), 
	USAAF("USA"), 
	VVS("Soviet"), 
	IJAAF("Japan"); 
	
	
	private final String airForce; //name of chosen air force
	private AirForceName(String airForce) { //constructor sets name of air force
		this.airForce = airForce; 
		
	} 
	@Override 
	public String toString() { return airForce; } //return chosen air force
		
}
