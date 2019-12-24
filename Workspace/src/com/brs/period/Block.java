package com.brs.period;

public enum Block {
	
	//Period block values:

	EARLY("Early"), 
	MID("Mid"), 
	LATE("Late");
	
	private String block; //name of chosen block
	private Block(String block) { this.block = block; } //constructor sets name of chosen block
	@Override 
	public String toString() { return block; } //return chosen block
	

}
