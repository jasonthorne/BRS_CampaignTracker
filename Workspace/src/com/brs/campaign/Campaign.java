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
	private Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //map of players involved  ////+++++++MAYBE USE SET HERE INSTEAD OF HASHMAP - then use streams for playing about with it.
	
	//=======================================months 1 - 4. after month 4, then move one period
	//private Month month; //current month represented
	private static final int TURNS_PER_PERIOD = 4; //amount of turns played per period
	private int turnNum; //number of turns in campaign
	///////////private List<Turn>turns; 
	private static final String BYE = "bye"; //bye entry for pairing odd number of players
	
	//=======================================
	private List<List<String>>pairings = new ArrayList<List<String>>(); //combinations of player pairings 
	
	
	private Map<Integer,List<List<String>>>turnToPairings = new TreeMap<Integer,List<List<String>>>(); 
	
	/////////++++++++++++++++missions: ++++++++++++++++++++++++
	private Map<Period, List<Mission>>periodToMission = new HashMap<Period, List<Mission>>(); 
	
	////////////////////private Map<Mission, List<Player>>missionToPlayers = new HashMap<Mission, List<Player>>(); //map of current missions
	
	public Campaign(EventName eventName) {
		event = new EventFactory().getEvent(eventName); //create event from EventFactory
		name = "Campaign: " + event.getName(); //create campaign name
		periodsIterator = event.getPeriods().listIterator(); //create iterator from periods
	}
	
	//add a new player to map of players:
	public void setPlayer(String name, AirForceName airForceName) { 
		//attempt to add player to map using their name as key. Warn user if unsuccessful (returned value other than null): 
		if(!(nameToPlayer.putIfAbsent(name, new Player(name, event.getAirForce(airForceName), period))==null)){
			System.out.println("Error: Player name already exists!"); 
		}else { System.out.println("Player added."); }
	}
	
	//////////////List<String>unpairedPlayers;
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	//Make a turn
	Turn currTurn;
	
	
	public void beginTurns() {
		turnNum = 1; //set turnNum
		movePeriod(); //set period iterator to starting period
		//setOpponents(); //set each player's opponents
		//pairPlayers2(); //pair players 
		
	}
	
	
	public void setPairings() {
		
		/**
		 * Round-robin 'circle method' scheduling algorithm: https://en.m.wikipedia.org/wiki/Round-robin_tournament
		 * Code adapted from: https://stackoverflow.com/questions/26471421/round-robin-algorithm-implementation-java
		 */
		
		List<String>players = new ArrayList<String>(nameToPlayer.keySet()); //list of all players
		
		//if odd number of players, add a bye:
		if(players.size()%2==1) { players.add(BYE); }
		
		//Collections.shuffle(players); //randomise positions of players 
		
		String fixedPlayer = players.remove(0); //1st player is removed from list, in order to be given a fixed position
		
		System.out.println("players: "+ players);
		
		List<List<String>>pairings; // = new ArrayList<List<String>>(); //===============================================================================
		
		int turn=0;
		
		//loop through the number of turns (with unique pairings) available: 
	    for (int turns=players.size(); turn<turns; turn++) {
	       
	        System.out.println("\nTurn:" + (turn + 1));  //++++++++++++++++++++++++
	        pairings = new ArrayList<List<String>>(); //create a new list of pairings
	        
	        //each turn, pair a player in players (at the index pos of that turn) against the first player:
	        System.out.println(players.get(turn) + " vs " + fixedPlayer); 
	        
	        //-------------------------------------------------
	        //pairings.add(Arrays.asList(players.get(turn), fixedPlayer)); //======================adding to list
	        pairings.add(Arrays.asList(players.get(turn), fixedPlayer));
	        
	        //-------------------------------------------------
	        
	        //endPos is at players.size()+1 to replace the removed fixedPlayer's index. 
	        for (int pairPos=1, endPos=(players.size()+1)/2; pairPos<endPos; pairPos++) {  //pairPos starts at 1 to ignore first 
	            int player1Pos = (turn + pairPos) % turns; //turn number + pairingPos (set at 1 to ignore )
	            int player2Pos = (turn  + turns - pairPos) % turns;
	            
	            //System.out.println("player1Pos: " + player1Pos + " vs " + "player2Pos: " + player2Pos);
	            System.out.println(players.get(player1Pos) + " vs " + players.get(player2Pos));
	            
	            //pairings.add(Arrays.asList(players.get(player1Pos), players.get(player2Pos))); //======================adding to list
	            pairings.add(Arrays.asList(players.get(player1Pos), players.get(player2Pos))); //======================adding to list
	            
	        }
	        
	        turnToPairings.put((turn+1), pairings); ///////CHANGE turn+1 to turnNum
	    }
	    
	   
	   // System.out.println("testList are: " + testList);
	   // System.out.println("pairings are: " + pairings);
	    System.out.println("turnToPairings is: " + turnToPairings);
		 
	}
		
	
	/*
	public void testRR() {
		
		//int numDays = (numTeams - 1); // Days needed to complete tournament
	    //int halfSize = numTeams / 2;

	    List<String> teams = new ArrayList<String>(nameToPlayer.keySet());

	    //teams.AddRange(ListTeam); // Add teams to List and remove the first team
	    
	    String fixedPlayer = teams.remove(0); 
	    
	    
	    System.out.println("teams: "+ teams);

	    int teamsSize = teams.size();

	    //loop through number of unique pairings available in relation to number of players available:
	    for (int day = 0; day < teamsSize; day++) {
	       
	        System.out.println("\nDay:" + (day + 1));

	        int teamIdx = day % teamsSize; //remainder of team / number of days
	        System.out.println("teamIdx: " + teamIdx);
	        
	        //each round, pair each player in the list (at the index pos of that round) against the first player:
	        System.out.println(teams.get(teamIdx) + " vs " + fixedPlayer); 
	        System.out.println("pos: " + teamIdx + " (" + teams.get(teamIdx) + ") vs " + "fixedPlayer: (" + fixedPlayer + ")");

	        for (int idx =1, j=nameToPlayer.keySet().size()/2; idx<j; idx++) {               
	            int firstTeam = (day + idx) % teamsSize;
	            int secondTeam = (day  + teamsSize - idx) % teamsSize;
	            System.out.println(teams.get(firstTeam) + " vs " + teams.get(secondTeam));
	        }
	    }
		 
		
	}*/
		
		
	//++++++++++++++++CAN ALSO BE USED FOR STARTING GAME! 
	public void advanceTurn() {
		//increment turnNum and check if period should be moved:
		if(++turnNum>TURNS_PER_PERIOD) { 
			turnNum = 1; //reset turnNum 
			movePeriod(); //advance to next period
		}
		//pairPlayers(); //pair players
		
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
