package model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import model.Plane.Status;

public class Campaign {
	
	public static final int TURNS_PER_PERIOD = 4; //amount of turns played per period
	
	private String name; //name of campaign
	private Period period; //current period
	private Timestamp created; //timestamp created
	private int hostName; //name of host

	////////private boolean isPlaying; //++++++++++++++determined by whether you are a player
	
	private Map<Integer, Player>players; //players involved
	
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
