package com.brs.period;

//Period block values:
public enum Block {
	
	EARLY("Early"), 
	MID("Mid"), 
	LATE("Late");
	
	private final String block; //name of chosen block
	private Block(String block) { this.block = block; } //constructor sets name of chosen block
	@Override 
	public String toString() { return block; } //return chosen block
	
}
