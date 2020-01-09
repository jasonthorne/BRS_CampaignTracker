package com.brs.player;

import com.brs.squadron.Squadron;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.airforce.AirForce;
import com.brs.date.Date;
import com.brs.mission.Mission;
import com.brs.mission.MissionLog;
import com.brs.period.Period;

public class Player {
	
	private final String name; //player name
	private final String date; //date of creation
	private final AirForce airForce; //reference to chosen air force
	private final Squadron squadron; //player's squadron 
	private int score; //current score 
	private final MissionLog missionLog;
	
	
	//mission log instance instead +++++
	//++++have an update method here for mission log
	///private Map<Period, Mission>periodToMission = new HashMap<Mission, List<Player>>(); //map of current missions
	
	public Player(String name, AirForce airForce, Period period) { //++++++++++++change privacy!
		this.name = name; //assign name
		date = Date.getDate(); //set date
		this.airForce = airForce; //assign reference to chosen air force
		//create a squadron, giving it a map of the models available to it:
		squadron = new Squadron(airForce.getAvailableModels(period)); 
		missionLog = new MissionLog();
	}
	
	
	public void updateMissons() {
		
	}
	
	
	
	
	//++++TEST PRINTING ++++++++++++++++++++++
	public String getPlayerStuff() { 
		return"name is: " + name + ". airforce is: " + airForce.getName();
		//return"name is: " + name;
	}

	
	
	

}
