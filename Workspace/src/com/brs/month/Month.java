package com.brs.month;

//month values
public enum Month {
	
	FIRST("First"), 
	SECOND("Second"), 
	THIRD("Third"),
	FORTH("Forth");
	
	private final String month; //name of chosen month
	private Month(String month) { this.month = month; } //constructor sets name of chosen month
	@Override 
	public String toString() { return month; } //return chosen month

}
