package com.brs.mission;

import java.util.List;
import java.util.Set;

import com.brs.period.Period;
import com.brs.player.Player;

public class Mission {
	
	private final List<String>players; //players involved
	private final Period period; //period represented
	private final int turnNum;
	//private final String date; //date played
	//private Results resutls; ??????????????????
	
	////A mission contains 2 mission logs (player 1 & p2). when they're both filled out, then the mission logs arer saved to the players 
	
	////////public Mission(List<String>players, Period period, String date) {
	///////////////public Mission(List<String>players, Period period) {
	public Mission(List<String>players, Period period, int turnNum) { //add turn num too for mission log creation
		this.players = players; //set list of players
		this.period = period; //set period
		this.turnNum = turnNum;
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

	
	//trigger the post mission stuff once BOTH players have added all their input.
	

}
	