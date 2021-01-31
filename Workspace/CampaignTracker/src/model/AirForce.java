package model;

import java.util.ArrayList;
import java.util.List;

public final class AirForce {
	
	private final String name; //name of air force
	private final boolean hasHomeAdv; //if air force has home adv status
	private final List<Plane>planes; //planes available to air force
	
	//constructor:
	public AirForce(String name, boolean hasHomeAdv, List<Plane>planes){
		this.name = name;
		this.hasHomeAdv = hasHomeAdv;
		this.planes = new ArrayList<Plane>(planes);
	}
	
	public String getAirForceName() { return name; } //get air force name
	public boolean getHasHomeAdv() { return hasHomeAdv; } //get home adv status
	
	public List<Plane> getAirForcePlanes() { //+++++++++++++++++MAKE STRONGER :P
		return new ArrayList<Plane>(planes);
	} 
	
	@Override
	public String toString() {
		return "AirForce [name=" + name + ", hasHomeAdv=" + hasHomeAdv + ", planes=" + planes + "]";
	}
	
	

	
	// https://stackoverflow.com/questions/1488472/best-practices-throwing-exceptions-from-properties
	
	
	
	
}
