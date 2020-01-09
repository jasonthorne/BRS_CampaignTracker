package com.brs.mission;

import java.util.Map;
import java.util.TreeMap;

import com.brs.period.Period;

public class MissionLog {
	
	private Map<Period, Mission>periodToMission = new TreeMap<Period, Mission>(); //map of player's previous missions
	private Mission mission; //player's current mission
	
	public void setCurrentMission(Mission mission){ this.mission = mission; }
		
	public Map<Period, Mission> getMissions() {
		
		//return string and period instead (maybe!!)
		 return periodToMission; //THIS SHOULD BE A DEEP COPY INSTEAD+++++++++++++++++++++++
	}
	
	//THIS SHOULD BE A DEEP COPY INSTEAD+++++++++++++++++++++++
	public Mission getMission(Period period) { return periodToMission.get(period); }
		
	
	public void saveMission(Period period){ periodToMission.put(period, mission); }
		
	
	
	

}
