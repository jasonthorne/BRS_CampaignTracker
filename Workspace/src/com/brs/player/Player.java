package com.brs.player;

import com.brs.Squadron;
import com.brs.airforce.AirForce;
import com.brs.airforce.AirForceName;

public class Player {
	
	private final String name;
	private final AirForce airForce; //=================PLAYER HAS THEIR OWN AIRFORCE OBJECT!!! 
	private Squadron squadron;
	private int joinDate;
	private int score;
	
	public Player(String name, AirForce airForce) { //++++++++++++change privacy!
		this.name = name; //assign name
		this.airForce = airForce; //assign ref to chosen air force
		//make join date
		
	}
	
	
	//++++TEST PRINTING ++++++++++++++++++++++
	public String getPlayerStuff() { 
		return"name is: " + name + ". airforce is: " + airForce.getName();
		//return"name is: " + name;
	}

	
	
	

}
