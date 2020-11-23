package model;

public class Campaign {
	
	public static final int TURNS_PER_PERIOD = 4; //amount of turns played per period
	private String name;
	
	
	public Campaign(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	//================delete
	@Override
	public String toString() {
		
		return name;
	}
	//=================
	
}
