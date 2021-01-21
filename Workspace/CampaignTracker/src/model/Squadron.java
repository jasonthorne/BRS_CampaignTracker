package model;

import java.util.Map;
import java.util.TreeMap;

public class Squadron {
	
	private String airForce = ""; //airForce name
	private int skillPoints; //skill points available
	
	private Map<String, Pilot>nameToPilot = new TreeMap<String, Pilot>(); //pilots involved
	
	private Squadron() {} //blank constructor
	
	//builder class:
	public static class SquadronBuilder {
		
		//create squadron:
		private Squadron squadron = new Squadron();
		
		//set air force:
		public SquadronBuilder setAirForce(String airForce) {
			squadron.airForce = airForce;
			return this;
		}
		
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