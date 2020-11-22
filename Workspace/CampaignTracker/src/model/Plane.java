package model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Plane {
	
	public enum Status {
		UNAVAILABLE("Unavailable"), LIMIT("Limit"), AUTO("Auto");
		
		private final String status; //name of status
		
		//constructor sets name of status:
		private Status(String status) { this.status = status; } 
		@Override 
		public String toString() { return status; } //return chosen status
	}
	
	private SimpleStringProperty name; //name of plane
	private SimpleStringProperty status2 = new SimpleStringProperty("bum"); //+++++++++++++++++++++++++
	private Map<Period, Status>availabilities; //availability per period of history
	
	private Plane() {} //blank constructor
	
	//builder class:
	public static class PlaneBuilder {
	
		private Plane plane = new Plane(); //create plane
		
		//set name:
		public PlaneBuilder setPlaneName(String name) {
			plane.name = new SimpleStringProperty(name);
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
	public String getName() { return name.get(); }
	
	
	//-----------------------
	
	
	//set plane status:
	public void setStatus(Period period) {
		/*SimpleStringProperty availabilty = new SimpleStringProperty(
				availabilities.get(period).toString());
		return availabilty.get();*/
		
		status2 = new SimpleStringProperty(availabilities.get(period).toString());
	}
	
	//get plane status:
	public String getStatus() { return status2.get(); }
	
	//-----------------------
	//get plane availabilities:
	public HashMap<Period, Status> getPlaneAvailabilities() { 
		return new HashMap<Period, Status>(availabilities); 
	}
	
	//get availability:
	public ObservableValue<String> getAvailability(Period period) {
	//public String getAvailability(Period period) { 
	/*	SimpleStringProperty availabilty = new SimpleStringProperty(
				availabilities.get(period).toString());
		return availabilty.get();*/
		
		ObservableValue<String>availabilty = new SimpleStringProperty(
				availabilities.get(period).toString());
		return availabilty;
	}


	@Override
	public String toString() {
		return "Plane [name=" + name + ", status2=" + status2.get() + ", availabilities=" + availabilities + "]";
	}

	
	
}
