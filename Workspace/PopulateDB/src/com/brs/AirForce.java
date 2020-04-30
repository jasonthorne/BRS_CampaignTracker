package com.brs;

//air forces involved:
public enum AirForce {
	
	RAF("RAF"),
	LUFTWAFFE("Luftwaffe"), 
	USAAF("USA"), 
	VVS("Soviet"), 
	IJAAF("Japan"); 
	
	private final String airForce; //name of air force
	private AirForce(String airForce) { this.airForce = airForce; } //constructor sets name of air force
	@Override 
	public String toString() { return airForce; } //return air force
		
}
