package com.android;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.android.Pilot.PilotBuilder;

public class Squadron {
	
	private List<Pilot>pilots = new ArrayList<Pilot>();
	private static final int MAX_PILOT_NUM = 12;
	private static final int MIN_PILOT_NUM = 6;
	private static int pilotNum = 0;
	
	private final int STARTING_SKILL_POINTS = 24;
	private int skillPoints = 0;
	
	//constructor:
	private Squadron(){ //+++++++++++++++++++change to private
		System.out.println("Squadron constructed");
	}

	@Override
	public String toString() {
		return "Squadron [pilots=" + pilots + ", pilotNum=" + pilotNum + ", skillPoints=" + skillPoints + "]";
	}
	
	
	//------------------getters-
	/*
	public Squadron getSquadron() {
		return this;
	}
	*/
	
	public int getTest(){
		return MIN_PILOT_NUM;
	}
	//------------------
	
	
	//builder class:
	static class SquadronBuilder{
		
		private Squadron squadron = new Squadron();
		
		public SquadronBuilder setPilot(PilotBuilder pilotBuilder) {
			squadron.pilots.add(pilotBuilder.build()); //add a built pilot to list of pilots
			return this;
		}
		
		public Squadron build() {
			return squadron;
		}
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

