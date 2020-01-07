package com.brs.player;

import com.brs.Squadron;
import com.brs.airforce.AirForce;
import com.brs.airforce.AirForceName;

public class Player { //look at this access level +++++++++++++
	
	//PLAYER SHOULD CONTAIN THE AIRFORCE. THIS CLASS MERELY DICTATES WHAT AIRFORCES ARE ALLOWED AND PROVIDES REFERENCES TO THEM!! +++++++++++
	
	private final String name;
	private final AirForce airForce;
	private Squadron squadron;
	private int joinDate;
	private int score;
	
	public Player(String name, AirForce airForce) {
		this.name = name;
		this.airForce = airForce;
	}
	
	
	//++++TEST PRINTING ++++++++++++++++++++++
	public void getPlayerStuff() { 
		System.out.println("name is: " + name);
		System.out.println("airforce is: " + airForce.getName());
	}

}
