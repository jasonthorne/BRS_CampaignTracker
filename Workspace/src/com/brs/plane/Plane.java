package com.brs.plane;

import java.util.ArrayList;
import java.util.List;

import com.brs.period.Period;
import com.brs.plane.PlaneData.Status;

//public final class Plane {
public class Plane {
	
	//private final String model;
	private String model; //this might be better as the enum object!!! +++++++++++ (as might other versions tgurned to string. Check those too!)
	///private List<Availability>availabilities = new ArrayList<Availability>();
	private Status status;
	
	
	private Plane(String model, Status status) {
		System.out.println("Plane constructed");
		this.model = model;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Plane [model=" + model + ", status=" + status + "]";
	}
	
	/*
	//=========================
	//create a list of availability objects - containing the periods avaliable and the plasnes status within them
	public static class Availability{
		
		private final Period period;
		private final Status status;
		
		public Availability(Period period, Status status){
			this.period = period;
			this.status = status;
		}

		@Override
		public String toString() {
			return "\nAvailability: [" + period + ", " + status + "]";
		}
		
		
	}
	
	//NEED A METHOD HERE THAT RETURNS AN AVALIBILTY THATS STATIC. THIS class itslef SHOULDNT BE STATIC (dumb asss) :P
	//========================
	 * 
	 */
	
	
	//Builder class:
	static class PlaneBuilder{ 

		private String model;
		private Status status;
		
		//setters:
		public PlaneBuilder setModel(String model) { 
			this.model = model;
			return this;
		}
		
		public PlaneBuilder setStatus(Status status) {
			this.status = status;
			return this;
		}
		
		public Plane build() {
			return new Plane(model, status);
		}
		
	}
	
}
