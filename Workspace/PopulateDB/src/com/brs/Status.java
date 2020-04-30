package com.brs;

//availability status of a plane:
public enum Status {
	
	LIMIT("Limit"), 
	AUTO("Auto");
	
	private final String status; //name of status
	private Status(String status) {this.status = status;} //constructor sets status
	@Override 
	public String toString() {return status;} //return status
		
}
