package com.brs.mission;

import java.util.Map;
import java.util.TreeMap;

import com.brs.period.Period;

public class MissionLog { //extends Mission {
	
	//date played 
	
	//period and turn (key!)
	Period period;
	int turn;
	String opponent;
	String date; //+++++++++++++++++this should be created when log is marked as complete!! 
	//squadron (pilots):
	
	
	/*
	exp points gained
	casualties suffered
	planes lost
	*/
	
	public MissionLog() {
		
		System.out.println("MissionLog");
		
		turn = 3;
	}

	
	public MissionLog(Period period, int turn, String opponent) {
		System.out.println("MissionLog");
	}



	@Override
	public String toString() {
		return "MissionLog [turn=" + turn + "]";
	}
	
	
	public Period getPeriod() { return period; } //THis MIGHT not return a copy! (along with others) +++++++++++++
	public int getTurn() { return turn; }	
	
	
	//////////periodTurn<Missions[Opponents<mission>]>
	
	
	
	/*
	private Mission mission; //player's current mission
	private final Map<Period, Mission>periodToMission = new TreeMap<Period, Mission>(); //map of player's previous missions
	
	//set current mission:
	public void setCurrentMission(Mission mission){ this.mission = mission; }
	
	
	
	
		
	public Map<Period, Mission> getMissions() {
		
		//return string and period instead (maybe!!)
		 return periodToMission; //THIS SHOULD BE A DEEP COPY INSTEAD+++++++++++++++++++++++
	}
	
	//THIS SHOULD BE A DEEP COPY INSTEAD+++++++++++++++++++++++
	public Mission getMission(Period period) { return periodToMission.get(period); }
		
	
	public void saveMission(Period period){ periodToMission.put(period, mission); }
		
	*/
	
	

}
