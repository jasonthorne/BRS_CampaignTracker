package com.brs;

//names of air forces involved:
public enum AirForceName {
	
	RAF("RAF"),
	LUFTWAFFE("Luftwaffe"), 
	USAAF("USA"), 
	VVS("Soviet"), 
	IJAAF("Japan"); 
	
	private final String name; //name of air force
	private AirForceName(String name) { this.name = name; } //constructor sets name of air force
	@Override 
	public String toString() { return name; } //return air force
		
}
