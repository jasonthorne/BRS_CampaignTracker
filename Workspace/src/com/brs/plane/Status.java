package com.brs.plane;

//Status values:
public enum Status {
	
	NONE("Unavailable"), 
	LIMIT("Limit"), 
	AUTO("Auto");
	
	private final String status; //name of chosen status
	private Status(String status) {this.status = status;} //constructor sets name of status
	@Override 
	public String toString() {return status;} //return chosen status
		
}
