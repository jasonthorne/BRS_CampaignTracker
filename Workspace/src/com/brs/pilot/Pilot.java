package com.brs.pilot;

import com.brs.plane.Plane;
import com.brs.date.Date;
import com.brs.die.Die;
import com.brs.pilot.PilotSkill;


public class Pilot implements Die, Date {
	
	private int id;
	private String name;
	private final String date = DATE.get(); //date of creation
	private PilotSkill pilotSkill;
	public int exp;
	private int kills;
	private String status;
	private Plane plane;
	
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
	
	
	public void testManoeuvre() {
		System.out.println(MANOEUVRE.test(3));
	}
	

}
