package model;

public class Player {
	
	//private int id;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Player(String name){
		this.name = name;
	}
}
