package com.brs.turn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.brs.mission.Mission;

public class Turn {
	
	//A TURN HAS MISSIONS
	private Map<String, Mission>playerToMission = new HashMap<String, Mission>(); //map of current missions
	
	public Turn() {
		System.out.println("turn made");
	}
	
	
	public void addMission() {
		
	}

}
