package model;

public class Player {
	
	private int id; //player id
	private String name; //player name
	private String pswrd; //player password

	private Player() {} //blank constructor
	
	//builder class:
	public static class PlayerBuilder {
		
		//create player:
		private Player player = new Player(); 
		
		public PlayerBuilder setId(int id) {
			player.id = id; //set id
			return this; //return this object
		}
		
		public PlayerBuilder setName(String name) {
			player.name = name; //set name
			return this; //return this object
		}
		
		public PlayerBuilder setPswrd(String pswrd) {
			player.pswrd = pswrd; //set password
			return this; //return this object
		}
		
		//return built player:
		public Player build() { return player; }
	}
	
	//getters:
	public int getId() {return this.id; } /** +++++++++++++ not sure if needed??? ++++++++*/
	public String getName() {return this.name; }
	public String getPswrd() {return this.pswrd; }
	
}