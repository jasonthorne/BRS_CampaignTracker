package com.brs.campaign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
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
import com.brs.period.Year;

public class Campaign { 
	
	private final String name; //name of campaign
	private final String date = Date.getDate(); //date of creation
	private final Event event; //historical event chosen
	private Period period; //current period represented
	private final ListIterator<Period>periodsIterator; //iterator for advancing period
	private static final int TURNS_PER_PERIOD = 4; //amount of turns played per period
	private Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //map of players involved 
	
	private int turnNum; //number of turns in campaign
	
	////////////private int turnNum = 1; //number of turns in campaign
	//int maxTurns = 
	///////////private List<Turn>turns; 
	private static final String BYE = "bye"; //bye entry for pairing odd number of players
	
	private List<List<String>>pairingsTEST = new ArrayList<List<String>>(); //combinations of player pairings 

	private Map<Integer,List<List<String>>>turnToPairings = new TreeMap<Integer,List<List<String>>>(); //combinations of player pairings for each turn
	
	/////////++++++++++++++++missions: ++++++++++++++++++++++++
	private Map<Period, List<Mission>>periodToMission = new HashMap<Period, List<Mission>>(); 
	
	//+++MAYBE JUST MISSIONS LIST (OR STACK THAT GETS REMOVED AS MISSIONS ARE COMPLETED)
	private List<Mission>currentMissions = new ArrayList<Mission>(); //+++++++++++++++++++++++++++++++++++++
	
	//=======================================================================================
	private Map<Integer,List<Mission>>turnToMissions = new TreeMap<Integer,List<Mission>>(); 
	
	////////////////////private Map<Mission, List<Player>>missionToPlayers = new HashMap<Mission, List<Player>>(); //map of current missions
	
	public Campaign(EventName eventName) {
		event = new EventFactory().getEvent(eventName); //create event from EventFactory
		name = "Campaign: " + event.getName(); //create campaign name
		periodsIterator = event.getPeriods().listIterator(); //create iterator from periods
	}
	
	//add a new player to map of players:
	public void setPlayer(String name, AirForceName airForceName) { 
		//attempt to add player to map using their name as key. Warn user if unsuccessful (returned value other than null): 
		if(!(nameToPlayer.putIfAbsent(name, new Player(name, event.getAirForce(airForceName), period))==null)) {
			System.out.println("Error: Player name already exists!"); 
		} else { System.out.println("Player added."); }
	}
	
	
	//Make a turn
	Turn currTurn;
	
	
	public void beginTurns() {
		turnNum = 1; //set turnNum
		System.out.println("TURN NUM: " + turnNum);
		movePeriod(); //set period iterator to starting period
		setPairings();
		setCurrMissions();
		//setOpponents(); //set each player's opponents
		//pairPlayers2(); //pair players 
		
	}
	
	
	public void setPairings() {
		/**
		 * Round-robin 'circle method' scheduling algorithm: https://en.m.wikipedia.org/wiki/Round-robin_tournament
		 * Code adapted from: https://stackoverflow.com/questions/26471421/round-robin-algorithm-implementation-java
		 */
		List<String>players = new ArrayList<String>(nameToPlayer.keySet()); //list of all players
		if(players.size()%2==1) {players.add(BYE);} //if odd number of players, add a bye
		Collections.shuffle(players); //shuffle positions of players 
		String fixedPlayer = players.remove(0); //1st player is removed from list (in order to be given a fixed position for pairing)
		
		System.out.println("players: "+ players);
		
		//===============================================================================
		
		//loop through the number of turns (with unique pairings) available: 
	    for (int turn=0, turns=players.size(); turn<turns; turn++) {
	       
	        System.out.println("\nTurn:" + (turn + 1));  //++++++++++++++++++++++++
	        List<List<String>>pairings = new ArrayList<List<String>>(); //create a new list of pairings
	        
	        System.out.println(players.get(turn) + " vs " + fixedPlayer); 
	        
	        //-------------------------------------------------
	        //each turn, pair the fixed player against a player in players (at the index pos of that turn):
	        pairings.add(Arrays.asList(players.get(turn), fixedPlayer));
	        
	        //=============================
	        List<Mission>missionsTEST = new ArrayList<Mission>(); //++++++++++++++LIST OF MISSIONS
	        //public Mission(List<String>players, Period period, String date) {
	       //currentMissions.add(new Mission(Arrays.asList(players.get(turn), fixedPlayer), period));
	       ////////////////// missionsTEST.add(new Mission(Arrays.asList(players.get(turn), fixedPlayer), period)); //+++++++++++++add to list of missions
	        //=============================
	        
	        //-------------------------------------------------
	        
	        //endPos is at players.size()+1 to replace the removed fixedPlayer's index. 
	        for (int pairPos=1, endPos=(players.size()+1)/2; pairPos<endPos; pairPos++) {  //pairPos starts at 1 to ignore first 
	            int player1Pos = (turn + pairPos) % turns; //turn number + pairingPos (set at 1 to ignore )
	            int player2Pos = (turn  + turns - pairPos) % turns;
	            
	            System.out.println(players.get(player1Pos) + " vs " + players.get(player2Pos));
	            
	            pairings.add(Arrays.asList(players.get(player1Pos), players.get(player2Pos))); 
	            missionsTEST.add(new Mission(Arrays.asList(players.get(player1Pos), players.get(player2Pos)), period)); //+++++++++++++add to list of missions
	        }
	        
	        turnToPairings.put((turn+1), pairings); ///////CHANGE turn+1 to turnNum
	       /////////// turnToMissions.put((turn+1), missionsTEST); //+++++++++++++++++++++++add to map of turns to missions???????????????????
	       pairingsTEST.addAll(pairings);
	    }
	    
	    System.out.println("turnToPairings is: " + turnToPairings);
	    //System.out.println("currentMissions: " + currentMissions);
	    //System.out.println("turnToMissions: " + turnToMissions);
	}
		
	
	public void setCurrMissions() {
		
		currentMissions.clear();
		
		/*
		turnToPairings.get(turnNum).forEach(pairing -> {
			currentMissions.add(new Mission(pairing, period));
		});
		
		turnToPairings.remove(turnNum);
		
		System.out.println("currentMissions: " + currentMissions);
		System.out.println("turnToPairings: " + turnToPairings);
		*/
		
		System.out.println("PAIRINGS_TEST: " + pairingsTEST);
		
		
		/*
		pairingsTEST.get(0).forEach(pairing -> {
			currentMissions.add(new Mission(pairing, period));
		});
		pairingsTEST.remove(0);
		*/
		System.out.println("currentMissions: " + currentMissions);
		System.out.println("pairingsTEST: " + pairingsTEST);
	}
	

	//++++++++++++++++CAN ALSO BE USED FOR STARTING GAME! 
	public void advanceTurn() {
		
	
		
		//increment turnNum and check if period should be moved:
		if(++turnNum>TURNS_PER_PERIOD) { 
			System.out.println("YOOHOO!!");
			turnNum = 1; //reset turnNum 
			movePeriod(); //advance to next period
			System.out.println("period is: " + period);
			
			
		}
		
		System.out.println("TURN NUM: " + turnNum);
		
		if(turnToPairings.isEmpty()) {
			setPairings();
		}
		 ////////////////////////////+this should be done only at 
		setCurrMissions();
	}
	
	
	
	
	
	

	
	
		
		

	
	
	
	
	
	
	//- once everyones finished their missions: check if everyones now played everyone else:
	// If so, reseed. 
	//Check if everyones now played 4 missions: 
	//if so move period, update planes, force players to change planes if their now unavailable.

	 
	// - reseed once each player has played everyone else (Including new players added before last reseed)
	//-Once everyone's played 4 missions: move periods, update planes, force players to change planes if their now unavailable.
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
