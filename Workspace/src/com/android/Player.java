package com.android;

import com.android.Squadron.SquadronBuilder;

public class Player{
	
	private String name;
	private int score;
	private AirForce airForce; 
	private Squadron squadron;
	private int joinDate;
	
	//constructor:
	private Player(){
		System.out.println("Player constructed");
	}

	
	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + ", airForce=" + airForce + ", squadron=" + squadron
				+ ", joinDate=" + joinDate + "]";
	}
	
	//---------------getters------
	
	public String getName(){
		return name;
	}
	
	//--------------------------




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
		
		private void setSquadron() {
			player.squadron = new SquadronBuilder().build();
		}
		
		private void setJoinDate() {
			player.joinDate = 333; //++++++++++++++change to proper date
		}
		
		private void setScore() {
			player.score = 0;
		}
		
		public Player build() {
			
			//PUT CHECKS ON THESE: +++++++++++++
			setSquadron();
			setJoinDate();
			setScore();
			
			return player;
		}
		
		
	}


	
	
	
	
	

}
