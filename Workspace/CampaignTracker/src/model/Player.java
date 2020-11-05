package model;

public class Player { /** +++++++++++++++++++++ is this class even needed???  :P */ 
	
	private int id; //player id
	private String name; //player name
	private String pswrd; //player password //????????????????????????????

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
			player.name = name; 
			return this; 
		}
		
		//set password:
		public PlayerBuilder setPswrd(String pswrd) {
			player.pswrd = pswrd; 
			return this; 
		}
		
		public Player build() { return player; } //return built player
	}
	
	//getters:
	public int getId() {return this.id; } /** +++++++++++++ not sure if needed??? ++++++++*/
	public String getName() {return name; }
	public String getPswrd() {return  pswrd; } //?????????????????????
	
}