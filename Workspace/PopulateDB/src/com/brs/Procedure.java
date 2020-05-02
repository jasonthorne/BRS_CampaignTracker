package com.brs;

enum Procedure {
	
	INSERT_YEAR("insert_year(?)");
	
	private final String procedure; //name of procedure
	private Procedure(String procedure) { this.procedure = procedure; } //constructor sets name of procedure
	@Override 
	public String toString() { return procedure; } //return chosen procedure

}
