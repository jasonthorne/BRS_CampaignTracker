package com.android;

//AirForces:
public enum AirForce{
	RAF("RAF"), 
	LUFTWAFFE("Luftwaffe"), 
	USAAF("USA"), 
	VVS("Soviet"), 
	IJAAF("Japan"); 
	private String airForce; //name of chosen air force
	private AirForce(String airForce) { //constructor
		this.airForce = airForce; //assign name of air force
	}
	@Override //override toString:
	public String toString() {
		return airForce; //return name of air force
	}
}


///this should be a class and dictate the polane avbaliablilty once the airforce is xelected. ++++++++++++++++


//name

//desciption

//planes