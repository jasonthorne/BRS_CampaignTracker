package com.android;


/*
import java.util.ArrayList;
import java.util.List;
*/

public class Plane {
	
	private final String model;
	private final int status; //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	private Plane(String model, int status) {
		System.out.println("Plane constructed");
		this.model = model;
		this.status = status;
	}

	
	@Override
	public String toString() {
		return "Plane [model=" + model + ", status=" + status + "]";
	}
	
	
	//Builder class:
	static class PlaneBuilder{ //WHY DOES THIS NEED TO BE PUBLIC???????????
		
		private String model;
		private int status;
		
		//setters:
		public PlaneBuilder setModel(String model) { //test string input +++++++++++++++
			this.model = model;
			return this;
		}
		
		public PlaneBuilder setStatus(int status) { //test status input +++++++++++++++
			this.status = status;
			return this;
		}
		
		public Plane build() {
			return new Plane(model, status);
		}
		
		
		/*
		 * MUTABLE method:
		private Plane plane = new Plane();

		public PlaneBuilder setModel(String model) { //test string input +++++++++++++++
			plane.model = model;
			return this;
		}
		
		public PlaneBuilder setStatus(String status) { //test status input +++++++++++++++
			plane.status = status;
			return this;
		}
		
		public Plane build() {
			return plane;
		}
		*/
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	private enum Status{
		NONE,
		LIMIT,
		AUTO;
	}
	
	private List<Avaliablility>avaliabilites = new ArrayList<Avaliablility>(); //list of availabilities
	Avaliablility avalability;
	
	class Avaliablility{
		Period period;
		Status status;
	}
	*/
	
	

}
