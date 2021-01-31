package model;

import java.util.Map;
import java.util.TreeMap;

public final class Squadron {
	
	private final String airForce; //airForce name
	private final int skillPoints; //skill points available
	private final Map<String, Pilot>nameToPilot = new TreeMap<String, Pilot>(); //pilots involved
	
	///////////private Squadron() {} //blank constructor
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public Squadron(String airForce, int skillPoints) {
		this.airForce = airForce;
		this.skillPoints = skillPoints;
	}
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	/*
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
	}*/

	@Override
	public String toString() {
		return "Squadron [skillPoints=" + skillPoints + "]";
	}
	
}