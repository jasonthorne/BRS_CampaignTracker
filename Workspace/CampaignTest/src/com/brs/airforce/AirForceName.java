package com.brs.airforce;

//AirForce name values:
public enum AirForceName {
	
	RAF("RAF"),
	LUFTWAFFE("Luftwaffe"), 
	USAAF("USA"), 
	VVS("Soviet"), 
	IJAAF("Japan"); 
	
	private final String airForce; //name of chosen air force
	private AirForceName(String airForce) { this.airForce = airForce; } //constructor sets name of air force
	@Override 
	public String toString() { return airForce; } //return chosen air force
		
}
