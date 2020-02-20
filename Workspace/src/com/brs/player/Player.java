package com.brs.player;

import com.brs.squadron.Squadron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.airforce.AirForce;
import com.brs.date.Date;
import com.brs.mission.Mission;
import com.brs.mission.MissionLog;
import com.brs.period.Period;

public class Player implements Date{
	
	private final String name; //player name
	private final String date = DATE.get(); //date of creation
	private final AirForce airForce; //chosen air force
	private final Squadron squadron; //player's squadron 
	////////private int score; //current score 
	//////////////private final MissionLog missionLog = new MissionLog(); //player's mission log
	
	
	
	//private Map<Period, MissionLog>periodTurnToMissionLog = new HashMap<Mission, List<Player>>(); //map of current missions
	
	public Player(String name, AirForce airForce, Period period) { //++++++++++++change privacy!
		this.name = name; //assign name
		this.airForce = airForce; //assign reference to chosen air force
		//create a squadron, giving it a map of the models available to it:
		squadron = new Squadron(airForce.getAvailableModels(period)); //passing period in here is messy!! TRY CHANGE THIS!  ++++++++++++++++++++++++++
	}
	
	
	public void updateMissons() {
		
	}
	
	//++++TEST PRINTING ++++++++++++++++++++++
	public String getPlayerStuff() { 
		return"name is: " + name + ". airforce is: " + airForce.getName();
		//return"name is: " + name;
	}

	
	
	

}
