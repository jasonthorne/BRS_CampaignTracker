package com.brs.events;

import com.brs.airforces.AirForce;

public class EventAirForce {
	
	private final AirForce airForce; //air force
	private final HomeAdvantage homeAdvantage; //home advantage status
	
	protected EventAirForce(AirForce airForce, HomeAdvantage homeAdvantage) {
		this.airForce = airForce;
		this.homeAdvantage = homeAdvantage;
	}

	
	//getters needed here! - to extract each value from plane, for insertion to DB
	
	
	@Override
	public String toString() {
		return "EventAirForce [airForce=" + airForce + ", homeAdvantage=" + homeAdvantage + "]";
	}
	
}
