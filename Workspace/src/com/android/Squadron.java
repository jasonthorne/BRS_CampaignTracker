package com.android;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Squadron {
	
	private List<Pilot>pilots = new ArrayList<Pilot>();
	private static final int MAX_PILOT_NUM = 12;
	private static final int MIN_PILOT_NUM = 6;
	private static int pilotNum = 0;
	
	private final int STARTING_SKILL_POINTS = 24;
	private int skillPoints = 0;
	
	//constructor:
	Squadron(){ //+++++++++++++++++++change to private

	}

	@Override
	public String toString() {
		return "Squadron [pilots=" + pilots + ", pilotNum=" + pilotNum + ", skillPoints=" + skillPoints + "]";
	}
	
	
	
	
	/*
	void addPilot(){
		pilots.add(new Pilot(PilotSkill.ROOKIE));
		pilots.add(new Pilot(PilotSkill.AVERAGE));
		pilots.add(new Pilot(PilotSkill.VETERAN));
		pilots.add(new Pilot(PilotSkill.ACE));
		
		//System.out.println(pilots);//test print of pilots list
	}
	*/
	
	
	
	
}

