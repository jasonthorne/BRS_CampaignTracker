package com.brs.events;

//'event air force' home advantage values:
public enum HomeAdvantage {
	
	TRUE(true),
	FALSE(false);
	
	private final boolean hasHomeAdvantage; //hasHomeAdvantage boolean
	private HomeAdvantage(boolean hasHomeAdvantage) {this.hasHomeAdvantage = hasHomeAdvantage;} //constructor sets boolean
	@Override 
	public String toString() {return Boolean.toString(hasHomeAdvantage);} //return boolean value
}
