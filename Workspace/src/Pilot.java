
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
	
	//set pilotSkills
	private enum PilotSkill {
		Rookie, Average, Veteran, Ace;
	}
	
	
	Pilot(){
		this.id = ++counter;
		setSkill(PilotSkill.Rookie);
		System.out.println(this.toString());
	}

	

	private void setSkill(PilotSkill pilotSkill){
		switch(pilotSkill) {
		  case Rookie:
			  this.pilotSkill = PilotSkill.Rookie;
			  int exp = (((int)(Math.random()*6)+1)-1); //D6 -1 xp
			  setExp(exp); 
			  break;
		  case Average:
			  this.pilotSkill = PilotSkill.Average;
			  break;
		  case Veteran:
			  this.pilotSkill = PilotSkill.Veteran;
			  break;
		  case Ace:
			  this.pilotSkill = PilotSkill.Ace;
			  break;
		}
	}
	
	private void setExp(int exp) {
		this.exp+=exp;
	}

	//HAVE INTERFACES FOR ACCESSING ONLY THESE VARS ++++++++++++++++++++++++++++++
	
	@Override
	public String toString() {
		return "Pilot [id=" + id + ", name=" + name + ", joinDate=" + joinDate + ", expPoints=" + exp + ", kills="
				+ kills + ", status=" + status + ", plane=" + plane + ", pilotSkill=" + pilotSkill + "]";
	}

}




	