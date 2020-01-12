package com.brs.turn;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.brs.mission.Mission;
import com.brs.mission.MissionBuilder;
import com.brs.period.Period;

public class TurnManager {
	
	//A TURN MANAGER MAKES AND MANAGES TURNS
	private Map<Period, List<Turn>>periodToTurns = new HashMap<Period, List<Turn>>(); //map of each period's turns
	private Turn turn;
	
	
	////////private List<Period>periods;
	private Period period; //current period of history represented
	private final ListIterator<Period>periodsIterator; //iterator for moving period
	
	private static final int TURNS_PER_PERIOD = 4; //amount of turns played per period
	///MIGHT NOT BE NEEDED//////////private final int maxTurns; //maximum amount of turns available to campaign
	private int turnNum;
	private MissionBuilder missionBuilder;
	
	
	
	public TurnManager(List<Period>periods, int maxTurns, List<String>players) {
		periodsIterator = periods.listIterator();
		/////////MIGHT NOT BE NEEDED/////this.maxTurns = periods.size()*MISSIONS_PER_PERIOD; //calc max amount of turns 
		//this.missionBuilder = new MissionBuilder(players); //create a mission builder, giving it players 
	}
	
	
	public void makeTurn() {
		turnNum++; //add to turn num
		turn = new Turn();
		
		//////////////missionBuilder.makeMissions();
		
	}
	
	
	
	
	//advance period to following period of history
	private void movePeriod() {
		
		if(periodsIterator.hasNext()) {
			period = periodsIterator.next();
			System.out.println(period); //++++++++++++++
		}
		else {  
			System.out.println("end of periods - game is over"); //++++++++++++++
		}
	}
	
	
	

}
