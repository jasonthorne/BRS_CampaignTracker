package model;

public class Player {
	
	private int id; //player id
	private String name; //player name
	private String pswrd; //player password

	private Player() {} //blank constructor
	
	//builder class:
	public static class Builder {
		
		private Player player = new Player(); //create player
		
		public Builder setId(int id) {
			player.id = id; //set id
			return this; //return this object
		}
		
		public Builder setName(String name) {
			player.name = name; //set name
			return this; //return this object
		}
		
		public Builder setPswrd(String pswrd) {
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