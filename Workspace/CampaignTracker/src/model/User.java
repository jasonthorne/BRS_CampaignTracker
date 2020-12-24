package model;

/** user logged into application */

public class User {
	
	private int id = 0; //id of user
	private String name = " "; //name of user
	
	//setter:
	public boolean setValues(int id, String name){
		//set user values if not yet set:
		if (this.id == 0 && this.name == " "){
			this.id = id; this.name = name;
			return true;
		}else { return false; }
	}
	
	//getters:
	public int getId() { return id; } //get id
	public String getName() { return name; }//get name
}