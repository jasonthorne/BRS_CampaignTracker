package com.brs.airforce;

public enum AirForceName {
	
	//AirForce name values:
	
	RAF("RAF"), 
	LUFTWAFFE("Luftwaffe"), 
	USAAF("USA"), 
	VVS("Soviet"), 
	IJAAF("Japan"); 
	
	private String airForce; //name of chosen air force
	private AirForceName(String airForce) { this.airForce = airForce; } //constructor sets name of air force
	@Override 
	public String toString() { return airForce; } //return chosen air force
		
	
}
