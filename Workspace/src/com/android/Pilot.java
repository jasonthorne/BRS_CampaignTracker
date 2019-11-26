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
		ROOKIE, AVERAGE, VETERAN, ACE;
	}
	
	//constructor:
	private Pilot() {
		System.out.println("Pilot constructed");
	}

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
		
		public PilotBuilder setJoinDate(int joinDate) { //++++++++++++++++Change this to use proper date
			pilot.joinDate = joinDate;
			return this;
		}
		
		public PilotBuilder setPilotSkill(PilotSkill pilotSkill) {
			pilot.pilotSkill = pilotSkill;
			return this;
		}
		
		public PilotBuilder setExp() {
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
			  default:
				  pilot.exp=0;
			}
			return this;
		}
		
		public Pilot build() {
			return pilot;
		}
		
	}
	

}








	