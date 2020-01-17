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

public class Player {
	
	private final String name; //player name
	private final String date = Date.getDate(); //date of creation
	private final AirForce airForce; //reference to chosen air force
	private final Squadron squadron; //player's squadron 
	private int score; //current score 
	//////////////private final MissionLog missionLog = new MissionLog(); //player's mission log
	
	//POTENTIAL OPPONETS THIS ROUND (havent been picked by anyone yet this round) //POTENTIAL_OPPONENTS
	private List<String>couldPlayNow = new ArrayList<String>(); //holds previously played opponents previously paired against
		
	//STILL TO BE PLAYED IN A FUTURE ROUND (already taken by someone else this round) //FUTURE_OPPONENTS
	private List<String>canPlayLater = new ArrayList<String>(); //holds opponents to be paired against
	
	private List<String>opponents = new ArrayList<String>(); //holds opponents to be paired against
	
	
	//===================================================
	public List<String> getCouldPlayNow() {
		return couldPlayNow;
	}
	
	public void setCouldPlayNow(List<String>opponents) {
		this.couldPlayNow.addAll(opponents);
	}
	
	public List<String> getCanPlayLater() {
		return canPlayLater;
	}
	
	public void setCanPlayLater(String player) {
		canPlayLater.add(player);
	}
	
	public void swapListsTEST() {
		couldPlayNow.clear();
		couldPlayNow.addAll(canPlayLater);
		canPlayLater.clear();
	}
	
	//=======================================================
	//mission log instance instead +++++
	//++++have an update method here for mission log
	///private Map<Period, Mission>periodToMission = new HashMap<Mission, List<Player>>(); //map of current missions
	
	public Player(String name, AirForce airForce, Period period) { //++++++++++++change privacy!
		this.name = name; //assign name
		this.airForce = airForce; //assign reference to chosen air force
		//create a squadron, giving it a map of the models available to it:
		squadron = new Squadron(airForce.getAvailableModels(period)); 
	}
	
	
	public void updateMissons() {
		
	}
	
	public void setOpponents(List<String>opponents) { ///////////look at making hard copy of this
		this.opponents.addAll(opponents);
		System.out.println(this.name + "'s opponents are: " + this.opponents);
	}
	
	public List<String> getOpponents() { ///////////look at making hard copy of this
		return opponents;
	}
	
	
	//++++TEST PRINTING ++++++++++++++++++++++
	public String getPlayerStuff() { 
		return"name is: " + name + ". airforce is: " + airForce.getName();
		//return"name is: " + name;
	}

	
	
	

}
