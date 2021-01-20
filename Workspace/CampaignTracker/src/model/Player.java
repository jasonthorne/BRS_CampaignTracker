package model;

import java.sql.Timestamp;

public class Player {
	
	//////////////private int id; //player id ????????????NEEDED????????? ++++++++++PROB NOT :P
	private String name; //user name
	private int score; //score
	private boolean isActive; //if active participant
	private Timestamp created; //creation time stamp ?????????????NEEDED????????
	private Squadron squadron; //squadron managed
	
	private Player() {} //blank constructor
	
	//builder class:
	public static class PlayerBuilder {
		
		private Player player = new Player(); //create player
		
		//set id:
		/*public PlayerBuilder setId(int id) {
			player.id = id; 
			return this; 
		}*/
		
		//set name:
		public PlayerBuilder setName(String name) {
			player.name = name; 
			return this; 
		}
		
		//set score:
		public PlayerBuilder setScore(int score) {
			player.score = score; 
			return this; 
		}
		
		//set isActive:
		public PlayerBuilder setIsActive(boolean bool) {
			player.isActive = bool; 
			return this; 
		}
		
		//set created:
		public PlayerBuilder setCreated(Timestamp timestamp) {
			player.created = timestamp;
			return this;
		}
		
		//set squadron:
		public PlayerBuilder setSquadron(Squadron squadron) { //++++++++++++++++++++++MAKE STRONGER!! :P
			player.squadron = squadron; 
			return this; 
		}
		
		public Player build() { return player; } //return built player
	}
	
	//getters:
	/////////////public int getId() { return id; } /** +++++++++++++ not sure if needed??? ++++++++*/
	public String getName() { return name; }
	public int getScore() { return score; }
	public boolean getIsActive() { return isActive; } 
	public Timestamp getCreated() { return created; } //** +++++++++++++ not sure if needed??? ++++++++*//?????????? should this return timestamp??? +MAKE STRONGER IF SOI! +++++++++?
	
	public Squadron getSquadron() { return squadron; } //++++++++++++++++++++++MAKE STRONGER!! :P
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + ", isActive=" + isActive + ", created=" + created
				+ ", squadron=" + squadron + "]";
	}
	
	
	
}