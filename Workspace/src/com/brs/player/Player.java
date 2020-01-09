package com.brs.player;

import com.brs.squadron.Squadron;
import com.brs.airforce.AirForce;
import com.brs.date.Date;
import com.brs.period.Period;

public class Player {
	
	private final String name; //player name
	private final String date; //date of creation
	private final AirForce airForce; //reference to chosen air force
	private Squadron squadron; //player's squadron 
	private int score; //current score 
	
	public Player(String name, AirForce airForce, Period period) { //++++++++++++change privacy!
		this.name = name; //assign name
		date = Date.getDate(); //set date
		this.airForce = airForce; //assign reference to chosen air force
		//create a squadron, giving it a map of the models available to it:
		this.squadron = new Squadron(airForce.getAvailableModels(period)); 
	}
	
	
	
	
	
	
	//++++TEST PRINTING ++++++++++++++++++++++
	public String getPlayerStuff() { 
		return"name is: " + name + ". airforce is: " + airForce.getName();
		//return"name is: " + name;
	}

	
	
	

}
