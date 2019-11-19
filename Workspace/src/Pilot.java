
public class Pilot {
	
	private static int counter = 0;
	private int id;
	private String name;
	private int joinDate;
	private int expPoints;
	private int kills;
	private int status;
	
	private Plane plane;
	PilotSkill pilotSkill;
	
	//set pilotSkills
	private enum PilotSkill {
		Rookie, Average, Veteran, Ace;
	}
	
	private void setPilotSkill(PilotSkill pilotSkill){
		
	}
	
	Pilot(){
		this.id = ++counter;
		//this.pilotSkill = PilotSkill.Ace; ///////test
		setPilotSkill(PilotSkill.Ace);
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "Pilot [id=" + id + ", name=" + name + ", joinDate=" + joinDate + ", expPoints=" + expPoints + ", kills="
				+ kills + ", status=" + status + ", plane=" + plane + ", pilotSkill=" + pilotSkill + "]";
	}

	

	//HAVE INTERFACES FOR ACCESSING ONLY THESE VARS ++++++++++++++++++++++++++++++
	
	

}




	