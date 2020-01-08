package com.brs.player;

import com.brs.squadron.Squadron;
import com.brs.airforce.AirForce;
import com.brs.airforce.AirForceName;
import com.brs.period.Period;

public class Player {
	
	private final String name;
	private final AirForce airForce;
	private Squadron squadron;
	private int joinDate;
	private int score;
	
	public Player(String name, AirForce airForce, Period period) { //++++++++++++change privacy!
		this.name = name; //assign name
		this.airForce = airForce; //assign reference to chosen air force
		//make join date
		//create a squadron, giving it a map of the models available to it:
		this.squadron = new Squadron(airForce.getAvailableModels(period)); 
		
	}
	
	
	
	
	
	
	//++++TEST PRINTING ++++++++++++++++++++++
	public String getPlayerStuff() { 
		return"name is: " + name + ". airforce is: " + airForce.getName();
		//return"name is: " + name;
	}

	
	
	

}
