package model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.beans.property.SimpleStringProperty;

public class Plane {
	
	public enum Status {
		UNAVAILABLE("Unavailable"), LIMIT("Limit"), AUTO("Auto");
		
		private final String status; //name of status
		
		//constructor sets name of status:
		private Status(String status) { this.status = status; } 
		@Override 
		public String toString() { return status; } //return chosen status
	}
	
	/*private*/ String name; //name of plane
	private Map<Period, Status>availabilities; //availability per period of history
	
	private SimpleStringProperty SSname; // = new SimpleStringProperty(name);

	private Plane() {} //blank constructor
	
	//builder class:
	public static class PlaneBuilder {
	
		private Plane plane = new Plane(); //create plane
		
		//set name:
		public PlaneBuilder setPlaneName(String name) {
			plane.name = name;
			plane.SSname = new SimpleStringProperty(name);//++++++++++++++++++++++++++
			return this;
		}
		
		//set availabilities:
		public PlaneBuilder setPlaneAvailabilities(Map<Period, Status>availabilities) {
			plane.availabilities = new HashMap<Period, Status>(availabilities);
			return this;
		}
		
		public Plane build() { return plane; } //return built plane
	}
	
	//get plane name:
	public String getPlaneName() { return name; }
	
	public String getSSname() { return SSname.get(); }
		
	
	
	//get plane availabilities:
	public HashMap<Period, Status> getPlaneAvailabilities() { 
		return new HashMap<Period, Status>(availabilities); 
	}

	@Override
	public String toString() {
		return "Plane [name=" + name + ", availabilities=" + availabilities + "]";
	}
	
}
