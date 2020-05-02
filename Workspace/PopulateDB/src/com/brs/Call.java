package com.brs;

//DB procedure calls:
enum Call {
	
	INSERT_YEAR("{call insert_year(?)}"); 
	
	private final String call; //name of call
	private Call(String call) { this.call = call; } //constructor sets name of call
	@Override 
	public String toString() { return call; } //return chosen call

}
