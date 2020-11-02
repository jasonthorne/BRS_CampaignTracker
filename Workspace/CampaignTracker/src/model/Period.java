package model;

public class Period { //+++++++++++++MIGHT NOT BE NEEDED!! 
	
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
	private String year; //year
	
	//constructor:
	public Period (Block block, String year) {
		this.block = block;
		this.year = year;
	}
	
	//getters:
	public Block getBlock() { return block; }
	public String getYear() { return year; }
}
