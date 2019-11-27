package com.android;

public enum AirForce{
	
	RAF("RAF"), //Britain
	LUFTWAFFE("Luftwaffe"), //Germany
	USAAF("USA"), //America
	VVS("Soviet"), //Russia
	IJAAF("Japan"); //Japan
	
	private String airForce; //name of air force
	
	//constructor:
	private AirForce(String airForce) {
		this.airForce = airForce; //assign name to air force
	}
	
	@Override
	public String toString() {
		return airForce; //return name of air force
	}
	
}
