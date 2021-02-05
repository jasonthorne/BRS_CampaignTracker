package model;

import java.util.Map;
import java.util.TreeMap;

public final class Squadron {
	
	private final String airForce; //airForce name
	private final int skillPoints; //skill points available
	private final Map<String, Pilot>nameToPilot = new TreeMap<String, Pilot>(); //pilots involved
	
	//constructor:
	public Squadron(String airForce, int skillPoints) {
		this.airForce = airForce;
		this.skillPoints = skillPoints;
	}


	@Override
	public String toString() {
		return "Squadron [airForce=" + airForce + ", skillPoints=" + skillPoints + ", nameToPilot=" + nameToPilot + "]";
	}
	
	
	
}