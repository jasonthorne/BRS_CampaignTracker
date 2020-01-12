package com.brs.campaign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.brs.player.Player;
import com.brs.squadron.Squadron;
import com.brs.turn.Turn;
import com.brs.airforce.AirForceName;
import com.brs.date.Date;
import com.brs.event.EventFactory;
import com.brs.event.EventName;
import com.brs.event.Event;
import com.brs.mission.Mission;
import com.brs.mission.MissionBuilder;
import com.brs.period.Period;

public class Campaign { //+++++++++++change to Campaign
	
	private final String name; //name of campaign
	private final String date = Date.getDate(); //date of creation
	private final Event event; //historical event chosen
	
	private Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //map of players involved 
	//////////////////private final List<Period>periods; //periods of history covered
	private Period period; //current period represented
	private final ListIterator<Period>periodsIterator; //iterator for advancing period
	///////////private static final int TURNS_PER_PERIOD = 4; //amount of turns played per period
	//=======================================months 1 - 4. after month 4, then move one period
	//private Month month; //current month represented
	private int turnNum; //number of turns in campaign
	private List<Turn>turns; 
	
	
	//=======================================
	
	
/////////++++++++++++++++CURRENT mission: ++++++++++++++++++++++++
	private Map<Mission, List<Player>>missionToPlayers = new HashMap<Mission, List<Player>>(); //map of current missions
	
	public Campaign(EventName eventName) {
		event = new EventFactory().getEvent(eventName); //create event from EventFactory
		name = "Campaign: " + event.getName(); //create campaign name
		/////////////periods = event.getPeriods(); //set periods of history //???????????????
		periodsIterator = event.getPeriods().listIterator(); //create iterator from periods //???????????????????
		
		//======================================
		////////turnNum = periods.size()*4; //???????????????????????
		
		//=======================================
	}
	
	//add a new player to map of players:
	public void setPlayer(String name, AirForceName airForceName) { 
		//attempt to add player to map using their name as key. Warn user if unsuccessful (returned value other than null): 
		if(!(nameToPlayer.putIfAbsent(name, new Player(name, event.getAirForce(airForceName), period))==null)){
			System.out.println("Error: Player name already exists! "); 
		}else { System.out.println("Player added."); }
	}
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	//Make a turn
	Turn currTurn;
	
	
	private static final String BYE = "bye"; //bye entry for pairing odd number of players

	public void makeTurn() {
		turnNum++; //add to turn num
		currTurn = new Turn();	
	}
	
	
	
	
	//Make x pairings (give each pairing a mission)
	public void pairPlayers() { 
		
		//list of player's names to draw pairings from:
		List<String>unpairedPlayers = new ArrayList<String>(nameToPlayer.keySet()); 
		
		//if odd number of players, add a bye:
		if(nameToPlayer.size()%2==1) { unpairedPlayers.add(BYE); }
		
		//while there are unpaired players:
		while(!unpairedPlayers.isEmpty()) { 
			
			List<String>pairedCouple = new ArrayList<String>(); //make list for holding 2 paired players
			
			while(pairedCouple.size()!=2) { //while a couple haven't been picked
	
				//pick a random pos to select an unpaired player from: 
				int randomIndex = new Random().nextInt(unpairedPlayers.size());
				
				//add player at random pos to pairedCouple:
				pairedCouple.add(unpairedPlayers.get(randomIndex));
				
				//remove paired player from list of unpaired players:
				unpairedPlayers.remove(randomIndex);
			}
			
			//mission = new Mission(pair);
			////missionToPlayers.put(new Mission(players), players);
			//MAKE NEW MISSION, ADDING THESE PLAYERS,
			//SEND TO TURN???++++++++++++++++++++++++++++++++++++++++++++++++++
			//LIST OF NAME<played>
			
		}
		
		
	}
	
	
	
	
	/*
	
	-
	- once everyones finished their missions: check if everyones now played everyone else:
	 If so, reseed. 
	Check if everyones now played 4 missions: 
	if so move period, update planes, force players to change planes if their now unavailable.

	 
	 - reseed once each player has played everyone else (Including new players added before last reseed)
	-Once everyone's played 4 missions: move periods, update planes, force players to change planes if their now unavailable.
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	
	public String getName() { return name; } //get name of campaign
	public String getDate() { return date; } //get date of creation
	public List<AirForceName> getAirForceNames() { return event.getAirForceNames(); } //get air forces involved
	public List<Period> getPeriods() { return event.getPeriods(); } //get periods of history covered
	//////////////////////public static Period getPeriod() { return period; }
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++TEST methods below:
	 public void getPeriodTEST() { 
		 movePeriod();
		// currPeriod = periods.get(0);
		//System.out.println("period is: " + period);
	}	
	 
	public void updateTEST() {
		//updatePlayers();
		movePeriod();
		getMissions();
		
	}
	
	public void getMissions(){
		//missionToPlayers = new MissionBuilder
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++
	
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
		
	
	public void test() {
		for (Entry<String, Player> entry : nameToPlayer.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		
		//+++++++++++++++++++
		//MissionBuilder.getMissions(new ArrayList<Player>(nameToPlayer.values()));
		//MissionBuilder.getMissions(new ArrayList<String>(nameToPlayer.keySet()));
		MissionBuilder test = new MissionBuilder(new ArrayList<String>(nameToPlayer.keySet()));
		test.setUnpairedPlayers();
		test.pairPlayers();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void setMissions(){
		
		//randomly assign pairings
		//create missions for each pairing, add to list of missions
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

}
