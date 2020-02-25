package com.brs.squadron;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.brs.plane.Model;
import com.brs.plane.Plane;
import com.brs.plane.Status;
import com.brs.player.Player;

import OldFiles.Pilot;


public class Squadron {
	
	private static final int MAX_PILOTS = 12; //maximum amount of pilots allowed
	private static final int MIN_PILOTS = 6; //minimum amount of pilots required
	private static final int STARTING_SKILL_POINTS = 24; //starting skill points available
	
	private int maxlimitStatus; //maximum amount of limit status planes allowed
	private int skillPoints; //skill points available
	
	private Map<Model, Status>modelToStatus; //map of available models and their current status
	private Map<Pilot, Plane>pilotToPlane = new TreeMap<Pilot, Plane>(); //map of pilots and their assigned planes ??????????
	
	
	//private List<Pilot>pilots = new ArrayList<Pilot>();
	
	//private static int pilotNum = 0; //?????????????needed. just count size of list every time you want to add to it, and after removal :P 
	 

	/*
	private List<Pilot>pilots = new ArrayList<Pilot>();
	private int skillPoints = 0;
	*/
	
	//private Map<Model, >pilots = new ArrayList<Pilot>();
	
	
	public Squadron(Map<Model, Status>modelToStatus) {
		this.modelToStatus = new TreeMap<Model, Status>(modelToStatus); //set models available
		skillPoints = STARTING_SKILL_POINTS; //set skill points
	}
	
	
	

	private void updateModelToStatus(Map<Model, Status>modelToStatus){
		this.modelToStatus = new TreeMap<Model, Status>(modelToStatus); //update models available
	}
	
	
	
	public void getModelToStatus(){
		for (Entry<Model, Status> entry : modelToStatus.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
	}

}
