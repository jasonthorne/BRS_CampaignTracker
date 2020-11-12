package model;

import java.util.Map;

public class Plane {
	
	public enum Status {
		UNAVAILABLE("Unavailable"), LIMIT("Limit"), AUTO("Auto");
		
		private final String status; //name of status
		
		//constructor sets name of status:
		private Status(String status) { this.status = status; } 
		@Override 
		public String toString() { return status; } //return chosen status
	}
	
	private String planeName; //name of plane
	private Map<Period, Status>periodToStatus; //status per period of history

	private Plane() {} //blank constructor
	
	//builder class:
	public static class PlaneBuilder {
	
		private Plane plane = new Plane(); //create plane
		
		//set name:
		public PlaneBuilder setPlaneName(String name) {
			plane.planeName = name;
			return this;
		}
		
		public Plane build() { return plane; } //return built plane
	}
	
	//get plane name:
	public String getPlaneName() { return planeName; }

	@Override
	public String toString() {
		return "Plane [planeName=" + planeName + ", periodToStatus=" + periodToStatus + "]";
	}
	
		
}
