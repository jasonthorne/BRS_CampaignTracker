package com.brs;

//status values of a plane:
public enum Status {
	
	NONE("None"), 
	LIMIT("Limit"), 
	AUTO("Auto");
	
	private final String status; //name of status
	private Status(String status) {this.status = status;} //constructor sets status
	@Override 
	public String toString() {return status;} //return status
		
}
