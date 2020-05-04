package com.brs.events;

//events covered:
public enum EventName {
	
	BATTLE_OF_BRITAIN("Battle of Britain"),
	OPERATION_BARBAROSSA("Operation Barbarossa"),	
	GUADALCANAL("Guadalcanal"),
	STALINGRAD("Stalingrad"),
	ASSAULT_ON_THE_REICH("Assault on the Reich"),
	THE_ITALIAN_CAMPAIGN("The Italian Campaign"),
	DEFENCE_OF_THE_HOME_ISLANDS("Defence of the home islands");
	
	private final String event; //name of event
	private EventName(String event) {this.event = event;} //constructor sets event
	@Override 
	public String toString() {return event;} //return event
		
}
