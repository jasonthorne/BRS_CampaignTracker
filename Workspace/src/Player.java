
public class Player{
	
	
	private AirForce airforce;
	
	Squadron squadron = new Squadron();
	
	//Name name;
	
	Player(AirForce airForce){ //test airforce param
		System.out.println("Creating player:");
		setAirForce(airForce);
		
	}
	
	
	private void setAirForce(AirForce airforce){
		
		/*
		switch(airforce) {
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
			  */
	}
	
	
	

}
