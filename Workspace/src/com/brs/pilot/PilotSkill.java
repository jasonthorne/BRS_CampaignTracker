package com.brs.pilot;

//pilot skills:
public enum PilotSkill{
		
		ROOKIE("Rookie"), 
		AVERAGE("Average"), 
		VETERAN("Veteran"), 
		ACE("Ace"); 
		
		private String pilotSkill; //name of chosen skill
		private PilotSkill(String pilotSkill) { this.pilotSkill = pilotSkill; } //constructor sets name of skill
		@Override 
		public String toString() { return pilotSkill; } //return chosen skill
}
