package model;

public class Period {
	
	//blocks of a year:
	public enum Block {
		EARLY("Early"), MID("Mid"), LATE("Late");
		
		private final String block; //name of block
		
		//constructor sets name of block:
		private Block(String block) { this.block = block; } 
		@Override 
		public String toString() { return block; } //return chosen block
	}
	
	private Block block; //block
	private int year; //year
	
	//constructor:
	public Period (Block block, int year) {
		this.block = block;
		this.year = year;
	}
	
	//getters:
	public Block getBlock() { return block; }
	public int getYear() { return year; }

	@Override
	public String toString() { return block + " " + year; }
}