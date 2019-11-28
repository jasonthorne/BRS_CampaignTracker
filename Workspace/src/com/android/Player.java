package com.android;
public class Player{
	
	private String name;
	private int score;
	private AirForce airForce; //airForce chosen by player
	
	private Squadron squadron = new Squadron();
	private int joinDate;
	
	
	//constructor:
	private Player(){
		System.out.println("Player constructed");
	}
	
	
	//=====================remove
	private void setAirForce(AirForce airForce){
		this.airForce = airForce;
	}
	
	
	public AirForce getAirForce() {
		return airForce;
	}
	//==========================
	
	//builder class:
	static class PlayerBuilder{
		private Player player = new Player();
		
		public PlayerBuilder setName(String name) {
			player.name = name;
			return this;
		}
		
		public PlayerBuilder setAirForce(AirForce airForce) {
			player.airForce = airForce;
			return this;
		}
		
		
		
		
	}


	
	
	
	
	

}
