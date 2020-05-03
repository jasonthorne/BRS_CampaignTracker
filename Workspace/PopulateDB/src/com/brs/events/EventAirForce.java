package com.brs.events;

import com.brs.AirForce;

public class EventAirForce {
	
	private final AirForce airForce; //air force
	private final boolean hasHomeAdvantage; //home advantage status
	
	protected EventAirForce(AirForce airForce, boolean hasHomeAdvantage) {
		this.airForce = airForce;
		this.hasHomeAdvantage = hasHomeAdvantage;
	}

	
	//getters needed here! - to extract each value from plane, for insertion to DB
	
	
	@Override
	public String toString() {
		return "EventAirForce [airForce=" + airForce + ", hasHomeAdvantage=" + hasHomeAdvantage + "]";
	}
	
	

}
