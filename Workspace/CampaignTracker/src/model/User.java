package model;

/** user logged into application */

public class User {
	
	private final int id; //id of user
	private final String name; //name of user
	
	//constructor:
	public User(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	//getters:
	int getId() { return id; }
	String getName() { return name; }	
}