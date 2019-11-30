package com.android;
public class Pilot {
	
	////private static int counter = 0; //figure this out!! (builder makes an extra object each time it's instantiated) :P
	private int id;
	private String name;
	private int joinDate;
	private PilotSkill pilotSkill;
	private int exp;
	private int kills;
	private int status;
	private Plane plane;
	
	enum PilotSkill{
		ROOKIE("Rookie"), AVERAGE("Average"), VETERAN("Veteran"), ACE("Ace"); //pilot skills
		private String pilotSkill; //name of chosen skill
		private PilotSkill(String pilotSkill) { //constructor
			this.pilotSkill = pilotSkill; //assign name of skill
		}
		
		@Override //override toString:
		public String toString() {
			return pilotSkill; //return name of skill
		}
	}
	
	//constructor:
	private Pilot() {
		System.out.println("Pilot constructed");
	}
	
	
	//-------------getters-----------------------
	public PilotSkill getPilotSkill() {
		return pilotSkill;
	}
	//--------------------------------------------
	
	@Override
	public String toString() {
		return "Pilot [name=" + name + ", joinDate=" + joinDate + ", exp=" + exp + ", kills=" + kills
				+ ", status=" + status + ", plane=" + plane + ", pilotSkill=" + pilotSkill + "]";
	}
		
	//builder class:
	static class PilotBuilder{
		
		private Pilot pilot = new Pilot();
		
		public PilotBuilder setName(String name) {
			pilot.name = name;
			return this;
		}
		
		/*
		public PilotBuilder setJoinDate(int joinDate) { //++++++++++++++++Change this to use proper date
			pilot.joinDate = joinDate;
			return this;
		}*/
		
		public PilotBuilder setPilotSkill(PilotSkill pilotSkill) {
			pilot.pilotSkill = pilotSkill;
			setExp();
			return this;
		}
		
		private void setExp() {
			switch(pilot.pilotSkill) {
			  case ROOKIE:
				  pilot.exp=(((int)(Math.random()*6)+1)-1); //D6-1 xp
				  break;
			  case AVERAGE:
				  pilot.exp=(((int)(Math.random()*6)+1)+10); //D6+10 xp
				  break;
			  case VETERAN:
				  pilot.exp=((((int)(Math.random()*6)+1) + ((int)(Math.random()*6)+1) + ((int)(Math.random()*6)+1)) + 20); //3D6+20 xp
				  break;
			  case ACE:
				  pilot.exp=(((int)(Math.random()*6)+1)+50); //D6+50 xp
				  break;
			}
		}
		
		//private void setJoinDate
		
		public Pilot build() {
			return pilot;
		}
		
	}
	

}








	