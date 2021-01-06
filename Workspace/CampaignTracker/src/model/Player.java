package model;

public class Player {
	
	private int id; //player id
	private String userName; //user name

	private Player() {} //blank constructor
	
	//builder class:
	public static class PlayerBuilder {
		
		private Player player = new Player(); //create player
		
		//set id:
		public PlayerBuilder setId(int id) {
			player.id = id; 
			return this; 
		}
		
		//set name:
		public PlayerBuilder setName(String name) {
			player.userName = name; 
			return this; 
		}
		
		public Player build() { return player; } //return built player
	}
	
	//getters:
	public int getId() {return id; } /** +++++++++++++ not sure if needed??? ++++++++*/
	public String getName() {return userName; }
	
}