package model;

public class Campaign {
	
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