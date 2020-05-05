package com.brs.statuses;

//availability status of a plane:
public enum Status {
	
	NONE("Unavailable"),
	LIMIT("Limit"), 
	AUTO("Auto");
	
	private final String status; //name of status
	private Status(String status) {this.status = status;} //constructor sets status
	@Override 
	public String toString() {return status;} //return status
		
}
