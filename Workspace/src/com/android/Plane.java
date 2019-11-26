package com.android;


/*
import java.util.ArrayList;
import java.util.List;
*/

//public final class Plane {
public class Plane {
	
	//private final String model;
	private String model;
	private Status status;
	
	enum Status{ //try make private somehow!!  ++++++++++++++++
		NONE,
		LIMIT,
		AUTO;
	}
	
	private Plane(String model, Status status) {
		System.out.println("Plane constructed");
		this.model = model;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Plane [model=" + model + ", status=" + status + "]";
	}
	
	
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
	

	/*
	
	private List<Avaliablility>avaliabilites = new ArrayList<Avaliablility>(); //list of availabilities
	Avaliablility avalability;
	
	class Avaliablility{
		Period period;
		Status status;
	}
	*/
	
	

}
