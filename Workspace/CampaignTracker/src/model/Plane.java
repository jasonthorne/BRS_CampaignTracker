package model;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import model.Period.Block;

public final class Plane {
	
	public enum Status {
		
		UNAVAILABLE("Unavailable"), LIMIT("Limit"), AUTO("Auto");
		
		private final String status; //status
		//constructor:
		private Status(String status) { this.status = status; } 
		@Override 
		public String toString() { return status; } //return status
	}
	
	private final SimpleStringProperty name; //name of plane
	private final Map<Period, Status>periodToStatus; //periods available and their corresponding status
	
	//constructor:
	public Plane(String name, Map<Period, Status>periodToStatus) {
		this.name = new SimpleStringProperty(name);
		this.periodToStatus = new HashMap<Period, Status>(periodToStatus); //+++++++++++++++++++++make stronger+++++++++++++!
	}
	
	//get plane name:
	public String getName() { return name.get(); }
	
	//get plane availabilities:
	public HashMap<Period, Status> getAvailabilities() { 
		return new HashMap<Period, Status>(periodToStatus); //+++++++++++++++++++++make stronger+++++++++++++!
	}

	@Override
	public String toString() {
		return "Plane [name=" + name + ", periodToStatus=" + periodToStatus + "]";
	}
	
}
