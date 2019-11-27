package com.android;

public enum AirForce{
	RAF("RAF"), LUFTWAFFE("Luftwaffe"), USAAF("USA"), VVS("Soviet"), IJAAF("Japan"); //air forces
	private String airForce; //name of chosen air force
	private AirForce(String airForce) { //constructor
		this.airForce = airForce; //assign name of air force
	}
	@Override //override toString:
	public String toString() {
		return airForce; //return name of air force
	}
	
}
