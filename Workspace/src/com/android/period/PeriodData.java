package com.android.period;

public class PeriodData {
	
	//Period block values:
	public enum Block{
		EARLY("Early"), MID("Mid"), LATE("Late");
		
		private String block; //name of chosen block
		private Block(String block) { this.block = block; }  //constructor sets name of chosen block
		@Override 
		public String toString() { return block; } //return chosen block
	}
	
	//Period year values:
	public enum Year{
		FORTY("1940"), FORTY_ONE("1941"), FORTY_TWO("1942"), FORTY_THREE("1943"), FORTY_FOUR("1944"), FORTY_FIVE("1945");
		
		private String year; //name of chosen year
		private Year(String year) { this.year = year; } //constructor sets name of chosen year
		@Override 
		public String toString() { return year; }  //return chosen year
	}
}
