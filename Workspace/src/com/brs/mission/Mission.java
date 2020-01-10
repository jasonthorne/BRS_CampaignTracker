package com.brs.mission;

import java.util.List;

import com.brs.period.Period;
import com.brs.player.Player;

public class Mission {
	
	private final List<Player>players; //players involved
	private final Period period; //period represented
	private final String date; //date played
	//private Results resutls; ??????????????????
	
	public Mission(List<Player>players, Period period, String date) {
		this.players = players; //set list of players
		this.period = period; //set period
		this.date = date; //set date played
	}
	
	

}
	