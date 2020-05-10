package com.brs.events;

import com.brs.airforces.AirForce;

//air force information pertaining to an event: 
public class EventAirForce {
	
	private final AirForce airForce; //air force
	private final HomeAdvantage homeAdvantage; //home advantage status
	
	//constructor:
	protected EventAirForce(AirForce airForce, HomeAdvantage homeAdvantage) {
		this.airForce = airForce;
		this.homeAdvantage = homeAdvantage;
	}

	//getters:
	protected AirForce getAirForce() { return this.airForce; }
	protected HomeAdvantage getHomeAdvantage() { return this.homeAdvantage; }
	
	@Override
	public String toString() {
		return "EventAirForce [airForce=" + airForce + ", homeAdvantage=" + homeAdvantage + "]";
	}
	
}