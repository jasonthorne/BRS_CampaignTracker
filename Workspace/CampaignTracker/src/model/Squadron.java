package model;

import java.util.Map;
import java.util.TreeMap;

public class Squadron {
	
	int skillPoints; //skill points available
	AirForce airForce; //airForce flown for
	
	private Map<String, Pilot>nameToPilot = new TreeMap<String, Pilot>(); //pilots involved
	
	private Squadron() {} //blank constructor
	
	//builder class:
	public static class SquadronBuilder {
		
		//create squadron:
		private Squadron squadron = new Squadron();
		
		//set skill points:
		public SquadronBuilder setSkillPoints(int skillPoints) {
			squadron.skillPoints = skillPoints;
			return this;
		}
		
		//return built squadron:
		public Squadron build() { return squadron; } 
	}

	@Override
	public String toString() {
		return "Squadron [skillPoints=" + skillPoints + "]";
	}
	
}