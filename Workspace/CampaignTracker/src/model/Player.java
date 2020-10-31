package model;

public class Player { /** +++++++++++++++++++++ is this class even needed???  :P */ 
	
	private int id; //player id
	private String name; //player name
	private String pswrd; //player password //????????????????????????????

	private Player() {} //blank constructor
	
	//builder class:
	public static class PlayerBuilder {
		
		private Player player = new Player(); //create player
		
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
		
		public Player build() { return player; } //return built player
	}
	
	//getters:
	public int getId() {return this.id; } /** +++++++++++++ not sure if needed??? ++++++++*/
	public String getName() {return this.name; }
	public String getPswrd() {return this.pswrd; }
	
}