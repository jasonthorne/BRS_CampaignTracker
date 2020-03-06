package com.brs.player;

import com.brs.squadron.Squadron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brs.DoubleKey;
import com.brs.PeriodTurn;
import com.brs.airforce.AirForce;
import com.brs.date.Date;
import com.brs.mission.Mission;
import com.brs.mission.MissionLog;
import com.brs.month.Month;
import com.brs.period.Block;
import com.brs.period.Period;
import com.brs.period.Year;

public class Player implements Date{
	
	private final String name; //player name
	private final String date = DATE.get(); //date of creation
	private final AirForce airForce; //chosen air force
	private final Squadron squadron; //player's squadron 
	////////private int score; //current score 
	//////////////private final MissionLog missionLog = new MissionLog(); //player's mission log
	
	//private Map<DoubleKey, MissionLog>periodTurnToMissionLog = new HashMap<DoubleKey, MissionLog>(); //map of current missions
	
	//--------
	private Map<PeriodTurn, MissionLog>periodTurnToMissionLog = new HashMap<PeriodTurn, MissionLog>(); //player's mission logs
	//---------
	
	public Player(String name, AirForce airForce, Period period) { //++++++++++++change privacy!
		this.name = name; //assign name
		this.airForce = airForce; //assign reference to chosen air force
		//create a squadron, giving it a map of the models available to it:
		squadron = new Squadron(airForce.getAvailableModels(period)); //passing period in here is messy!! TRY CHANGE THIS!  ++++++++++++++++++++++++++
		testYr = Year.FORTY; ///////////////////++++++++++++++TEST
	}
	
	private void updateMissons() {
		System.out.println("woohoo!");
	}
	
	//+++++++++++++++++++++TEST CONSTRUVTOR
	
	private Year testYr;

	public Player() {
		this.name = null;
		this.airForce = null;
		squadron = null;
		testYr = Year.FORTY;
	}
	
	
	public Year getTestYr(){
		return testYr;
	}
	
	
	//+++++++++++++++++++++TEST CONSTRUVTOR
	
	//++++TEST PRINTING ++++++++++++++++++++++
	public String getPlayerStuff() { 
		return"name is: " + name + ". airforce is: " + airForce.getName();
		//return"name is: " + name;
	}

	public void test() {
		///periodTurnToMissionLog.put(new DoubleKey("Yo", "dawg"), new MissionLog());
		
		////System.out.println(periodTurnToMissionLog.get(new DoubleKey("Yo", "dawg")));
	}
	
	/*
	public void test2() {
		periodTurnToMissionLog.put(new PeriodTurn(new Period(Block.EARLY, Year.FORTY_ONE), Month.THIRD), new MissionLog());
		
		System.out.println(periodTurnToMissionLog.get(new PeriodTurn(new Period(Block.EARLY, Year.FORTY_ONE), Month.THIRD)));
	}*/
	
	
	public MissionLog getLogTest(PeriodTurn periodTurn) {
		return periodTurnToMissionLog.get(periodTurn); //should be COPY!! 
	}
	
	
	public MissionLog getMissionLog(PeriodTurn periodTurn){
		return periodTurnToMissionLog.get(periodTurn); //should be COPY!! 
	}
	
	public void setMissionLog(MissionLog missionLog) {
		//++HAVE CHECKS HERE (does period turn record exist already? does player currently HAS a mission?)
		
		//take periodTurn from MissionLog to use as key!! 
		periodTurnToMissionLog.put(new PeriodTurn(missionLog.getPeriod(), missionLog.getTurn()), missionLog);
		
		
	}
	
	
	
	
	//++++++++++++++++++++TEST TO STRING
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", date=" + date + ", airForce=" + airForce.getName() + ", squadron=" + squadron
				+ ", periodTurnToMissionLog=" + periodTurnToMissionLog + "]";
	}
	
	
	
	
	
	public String getName() {
		return this.name;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
