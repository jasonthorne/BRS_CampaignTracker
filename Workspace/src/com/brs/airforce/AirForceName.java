package com.brs.airforce;

public enum AirForceName {
	
	//names of air forces:
	RAF("RAF"), 
	LUFTWAFFE("Luftwaffe"), 
	USAAF("USA"), 
	VVS("Soviet"), 
	IJAAF("Japan"); 
	
	private String airForce; //name of chosen air force
	//constructor:
	private AirForceName(String airForce) { this.airForce = airForce; }  //set name of air force
		
	@Override 
	public String toString() { return airForce; } //return chosen air force
		
}
