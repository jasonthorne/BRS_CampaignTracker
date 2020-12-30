package model;

/** user logged into application */

public class User {
	
	private int id = 0; //id of user
	private String name = " "; //name of user
	
	//set user values:
	public void setValues(int id, String name){
		if (this.id == 0 && this.name == " "){
			this.id = id; this.name = name;
		}
	}
	
	//getters:
	public int getId() { return id; } //get id
	public String getName() { return name; }//get name
}