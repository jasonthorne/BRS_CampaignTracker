package com.brs.datetime;

//formats for DateTime:
public enum DateTimeFormat {
	
	DATE("dd/MM/yyyy"),
	DATE_TIME("dd/MM/yyyy HH:mm");
	
	private final String format; //string of chosen format
	private DateTimeFormat(String format) {this.format = format;} //constructor sets format
	@Override 
	public String toString() {return format;} //return chosen format
	
}
