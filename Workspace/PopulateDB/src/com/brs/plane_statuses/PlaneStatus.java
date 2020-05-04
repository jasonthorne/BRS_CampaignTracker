package com.brs.plane_statuses;

//availability status of a plane:
public enum PlaneStatus {
	
	NONE("Unavailable"),
	LIMIT("Limit"), 
	AUTO("Auto");
	
	private final String name; //name of status
	private PlaneStatus(String name) {this.name = name;} //constructor sets status
	@Override 
	public String toString() {return name;} //return status
		
}
