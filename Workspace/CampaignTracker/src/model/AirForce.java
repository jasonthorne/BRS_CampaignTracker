package model;

import java.util.ArrayList;
import java.util.List;

public class AirForce {
	
	private String name; //name of air force
	private boolean hasHomeAdv; //if air force has home adv status
	private List<Plane>planes; //planes available to air force
	
	private AirForce() {} //blank constructor
	
	//builder class:
	public static class AirForceBuilder {
		
		//create air force:
		private AirForce airForce = new AirForce();
		
		//set air force name:
		public AirForceBuilder setAirForceName(String name) {
			airForce.name = name;
			return this;
		}
		
		//set home advantage status:
		public AirForceBuilder setHasHomeAdv(boolean bool) {
			airForce.hasHomeAdv = bool;
			return this;
		}
		
		//set planes:
		public AirForceBuilder setPlanes(List<Plane>planes) { //+++++++++++++++++MAKE STRONGER :P
			airForce.planes = new ArrayList<Plane>(planes);
			return this;
		}
		
		//return built event air force:
		public AirForce build() { return airForce; } 
	}
	
	
	public String getAirForceName() { return name; } //get air force name
	public boolean getHasHomeAdv() { return hasHomeAdv; } //get home adv status
	
	@Override
	public String toString() {
		return "AirForce [name=" + name + ", hasHomeAdv=" + hasHomeAdv + ", planes=" + planes + "]";
	}
	
	
	
}
