package com.brs.event;

//Event name values:
public enum EventName {
	
	BATTLE_OF_BRITAIN("Battle of Britain"),
	OPERATION_BARBAROSSA("Operation Barbarossa"),	
	GUADALCANAL("Guadalcanal"),
	STALINGRAD("Stalingrad"),
	ASSAULT_ON_THE_REICH("Assault on the Reich"),
	THE_ITALIAN_CAMPAIGN("The Italian Campaign"),
	DEFENCE_OF_THE_HOME_ISLANDS("Defence of the home islands");
	
	private final String name; //name of chosen event
	private EventName(String name) {this.name = name;} //constructor sets name of event
	@Override 
	public String toString() {return name;} //return chosen event
		
}
