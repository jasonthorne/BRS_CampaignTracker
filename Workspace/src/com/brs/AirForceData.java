package com.brs;

public final class AirForceData{
	
	//AirForce values:
	public enum AirForce{
		RAF("RAF"), 
		LUFTWAFFE("Luftwaffe"), 
		USAAF("USA"), 
		VVS("Soviet"), 
		IJAAF("Japan"); 
		private String airForce; //name of chosen air force
		private AirForce(String airForce) { //constructor
			this.airForce = airForce; //assign name of air force
		}
		@Override //override toString:
		public String toString() {
			return airForce; //return name of air force
		}
	}


	///this should be a class and dictate the polane avbaliablilty once the airforce is xelected. ++++++++++++++++


	//name

	//desciption

	//planes
	
	/*
	 * MAKE A PLANE DATA CLASS - include all models of planes avaliable
	 * //and plane statuses.
	 * for default val: 
	 * https://stackoverflow.com/questions/4664026/default-or-initial-value-for-a-java-enum-array
	 * Day[] days = new Day[3];
	Arrays.fill(days, Day.MONDAY);
	 * and name and description
	 * 
	 *  In THIS class add the data of which planes are related to what airforce.
	 *  and what dates they're avalaible(somehow!!!)
	 *  and what planes 
	 *  
	 *  eg:
	 *  
	 *  plane models hashmap - airForces_models
	 *  plane avaliability hashmap - airForces_avaliability
	 */
	
	
}


	
	

