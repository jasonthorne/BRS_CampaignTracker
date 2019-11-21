
public class Pilot {
	
	private static int counter = 0;
	private int id;
	private String name;
	private int joinDate;
	private int exp = 0;
	private int kills;
	private int status;
	
	private Plane plane;
	private PilotSkill pilotSkill;
	
	/*
	//set pilotSkills
	private enum PilotSkill {
		Rookie, Average, Veteran, Ace;
	}
	*/
	
	Pilot(PilotSkill pilotSkill){
		this.id = ++counter;
		setSkill(pilotSkill);
		System.out.println(this.toString());
	}

	
	private void setSkill(PilotSkill pilotSkill){
		switch(pilotSkill) {
		  case ROOKIE:
			  this.pilotSkill = PilotSkill.ROOKIE;
			  setExp((((int)(Math.random()*6)+1)-1)); //D6-1 xp
			  break;
		  case AVERAGE:
			  this.pilotSkill = PilotSkill.AVERAGE;
			  setExp((((int)(Math.random()*6)+1)+10)); //D6+10 xp
			  break;
		  case VETERAN:
			  this.pilotSkill = PilotSkill.VETERAN;
			  setExp(((((int)(Math.random()*6)+1) + ((int)(Math.random()*6)+1) + ((int)(Math.random()*6)+1)) + 20)); //3D6+20 xp
			  break;
		  case ACE:
			  this.pilotSkill = PilotSkill.ACE;
			  setExp((((int)(Math.random()*6)+1)+50)); //D6+50 xp
			  break;
		}
	}
	
	
	private void setExp(int exp) {
		this.exp+=exp;
	}


	@Override
	public String toString() {
		return "Pilot [id=" + id + ", name=" + name + ", joinDate=" + joinDate + ", expPoints=" + exp + ", kills="
				+ kills + ", status=" + status + ", plane=" + plane + ", pilotSkill=" + pilotSkill + "]";
	}
	
	//HAVE INTERFACES FOR ACCESSING ONLY THESE VARS ++++++++++++++++++++++++++++++

}




	