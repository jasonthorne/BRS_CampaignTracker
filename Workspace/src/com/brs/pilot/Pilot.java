package com.brs.pilot;

import com.brs.plane.Plane;
import com.brs.player.Player;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Supplier;

import com.brs.pilot.PilotSkill;


public class Pilot {
	
	private int id;
	private String name;
	private int joinDate;
	private PilotSkill pilotSkill;
	public int exp;
	private int kills;
	private int status;
	private Plane plane;
	
	private final Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //map of players involved +++++++++
	
	public Pilot(PilotSkill pilotSkill) {
		this.pilotSkill = pilotSkill;
		setExp(); //+++++CALL THIS LATER (to assign exp AFTER usef has selected ALL their pilots. ++++++++++++++++++
	}
	
	//https://mkyong.com/java/java-generate-random-integers-in-a-range/
	
	private void setExp() { //LOOK UP Chapter 4. 4.2 Ex3 for idea about setting skill automatically (I "think this works!!")
		
		//supply random number from 1-6:
		Supplier<Integer>supplyD6=()->new Random().nextInt(6)+1; 
		
		switch(pilotSkill) {
		  case ROOKIE:
			  exp = supplyD6.get()-1; //D6-1 xp (0-5)
			  break;
		  case AVERAGE:
			  exp = supplyD6.get()+10; //D6+10 xp (11-16)
			  break;
		  case VETERAN:
			  exp = (supplyD6.get()+supplyD6.get()+supplyD6.get())+20; //3D6+20 xp (23-38)
			  break;
		  case ACE:
			  exp = supplyD6.get()+50; //D6+50 xp (51-56)
			  break;
		}
		
	}
	
	
	

}
