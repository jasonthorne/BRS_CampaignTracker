import com.android.Plane;

public class Pilot {
	
	private static int counter = 0;
	private int id;
	private String name;
	private int joinDate;
	private int exp;
	private int kills;
	private int status;
	
	private Plane plane;
	private PilotSkill pilotSkill;
	
	
	Pilot(PilotSkill pilotSkill){
		setId();
		setPilotSkill(pilotSkill);
		setExp();
		System.out.println(this.toString());
	}
	
	//-----------------------------------------------------
	//setters
	private void setId() {
		this.id = ++counter;
	}

	private void setPilotSkill(PilotSkill pilotSkill){
		this.pilotSkill = pilotSkill;
	}
	
	private void setExp() {
		switch(pilotSkill) {
		  case ROOKIE:
			  this.exp=(((int)(Math.random()*6)+1)-1); //D6-1 xp
			  break;
		  case AVERAGE:
			  this.exp=(((int)(Math.random()*6)+1)+10); //D6+10 xp
			  break;
		  case VETERAN:
			  this.exp=((((int)(Math.random()*6)+1) + ((int)(Math.random()*6)+1) + ((int)(Math.random()*6)+1)) + 20); //3D6+20 xp
			  break;
		  case ACE:
			  this.exp=(((int)(Math.random()*6)+1)+50); //D6+50 xp
			  break;
		}
	}
	


	@Override
	public String toString() {
		return "Pilot [id=" + id + ", name=" + name + ", joinDate=" + joinDate + ", exp=" + exp + ", kills="
				+ kills + ", status=" + status + ", plane=" + plane + ", pilotSkill=" + pilotSkill + "]";
	}
	
	
	//=========================================================================
	
	
	
	
	
	

}








	