package com.brs.pilot;

import com.brs.plane.Plane;
import com.brs.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Supplier;

import com.brs.date.Date;
import com.brs.pilot.PilotSkill;


public class Pilot {
	
	private int id;
	private String name;
	private final String date = Date.getDate(); //date of creation
	private PilotSkill pilotSkill;
	public int exp;
	private int kills;
	private String status;
	private Plane plane;
	
	//imitates D6 roll by providing random number from 1-6:
	private static final Supplier<Integer>D6=()->new Random().nextInt(6)+1; 
	
	public Pilot(PilotSkill pilotSkill) {
		this.pilotSkill = pilotSkill;
		setExp(); //+++++CALL THIS LATER (to assign exp AFTER usef has selected ALL their pilots. ++++++++++++++++++
	}
	
	private void setExp() { 
		
		switch(pilotSkill) {
		  case ROOKIE:
			  exp = D6.get()-1; //D6-1 xp (0-5)
			  break;
		  case AVERAGE:
			  exp = D6.get()+10; //D6+10 xp (11-16)
			  break;
		  case VETERAN:
			  exp = (D6.get()+D6.get()+D6.get())+20; //3D6+20 xp (23-38)
			  break;
		  case ACE:
			  exp = D6.get()+50; //D6+50 xp (51-56)
			  break;
		}
	}
	
	
	private void addExp() {
		
	}
	
	//post mission stuff: 
	public void getShotDownResult() {
		
		switch(D6.get()) {
		 case 6: case 5: 
			 System.out.println("forced landing"); 
			 break;
		 case 4: case 3: 
			 System.out.println("Bail out");
			 getBailOutResult(); //get bailout result
			 break;
		 case 2: case 1: 
			 System.out.println("KIA"); 
			 break;
		}
	}
	
	
	public void getBailOutResult() {
		
		switch(D6.get()) {
		 case 6: case 5: case 4:
			 System.out.println("OK"); 
			 break;
		 case 3: case 2: 
			 System.out.println("Bad landing");
			 getInjuryResult();
			  break;
		 case 1: 
			 System.out.println("Chute failure"); 
			 break;
		}
	}
	
	
	public void getInjuryResult() {
		
		switch(D6.get()) {
		 case 6: case 5: 
			 System.out.println("Just a scratch"); 
			 break;
		 case 4: case 3: 
			 System.out.println("Down but not out");
			 break;
		 case 2: 
			 System.out.println("Major Injury");
			 break;
		 case 1: 
			 System.out.println("Crippling injury"); 
			 break;
		}
	}
	
	

}
