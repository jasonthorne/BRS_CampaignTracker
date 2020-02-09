package com.brs.pilot;

import com.brs.plane.Plane;
import com.brs.pilot.PilotSkill;


public class Pilot {
	
	private int id;
	private String name;
	private int joinDate;
	private PilotSkill pilotSkill;
	private int exp;
	private int kills;
	private int status;
	private Plane plane;
	
	public Pilot(PilotSkill pilotSkill) {
		this.pilotSkill = pilotSkill;
		setExp();
	}
	
	
	private void setExp() { //LOOK UP Chapter 4. 4.2 Ex3 for idea about setting skill automatically (I "think this works!!")
		switch(pilotSkill) {
		  case ROOKIE:
			  exp=(((int)(Math.random()*6)+1)-1); //D6-1 xp
			  break;
		  case AVERAGE:
			  exp=(((int)(Math.random()*6)+1)+10); //D6+10 xp
			  break;
		  case VETERAN:
			  exp=((((int)(Math.random()*6)+1) + ((int)(Math.random()*6)+1) + ((int)(Math.random()*6)+1)) + 20); //3D6+20 xp
			  break;
		  case ACE:
			  exp=(((int)(Math.random()*6)+1)+50); //D6+50 xp
			  break;
		}
	}
	

}
