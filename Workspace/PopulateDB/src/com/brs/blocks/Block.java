package com.brs.blocks;

//blocks of a year:
public enum Block {
	
	EARLY("Early"), 
	MID("Mid"), 
	LATE("Late");
	
	private final String block; //name of block
	private Block(String block) { this.block = block; } //constructor sets name of block
	@Override 
	public String toString() { return block; } //return chosen block
	
}