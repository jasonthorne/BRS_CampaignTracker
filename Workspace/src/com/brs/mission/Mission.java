package com.brs.mission;

import java.util.List;

import com.brs.period.Period;
import com.brs.player.Player;

public class Mission {
	
	private final List<String>players; //players involved
	private final Period period; //period represented
	//private final String date; //date played
	//private Results resutls; ??????????????????
	
	
	////////public Mission(List<String>players, Period period, String date) {
	public Mission(List<String>players, Period period) {
		this.players = players; //set list of players
		this.period = period; //set period
		///////////this.date = date; //set date played
	}
	
	/*
	public Mission(List<String>players) {
		this.players = players; //set list of players
		
	}*/
	
	public void getPlayersTEST() {
		System.out.println(players);
	}

	
	@Override
	public String toString() {
		return "Mission [players=" + players + ", period=" + period  + "]";
	}

	
	
	

}
	