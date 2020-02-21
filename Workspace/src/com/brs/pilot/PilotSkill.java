package com.brs.pilot;

//pilot skills:
public enum PilotSkill{
	
	ROOKIE("Rookie", 2), 
	AVERAGE("Average", 3), 
	VETERAN("Veteran", 4), 
	ACE("Ace", 5); 
	
	private final String pilotSkill; //name of chosen skill
	private final int value; //value of chosen skill
	
	//constructor sets name and value:
	private PilotSkill(String pilotSkill, int value) { 
		this.pilotSkill = pilotSkill; 
		this.value = value; 
	} 
	
	@Override 
	public String toString() { return pilotSkill; } //return skill name
	public int getValue() { return value; } //return skill value
	
}
