package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import controller.CampaignController;
import controller.LoginController;
import javafx.beans.property.SimpleBooleanProperty;
import model.Plane.Status;

public final class Campaign {
	
	//amount of turns played per period:
	public static final int TURNS_PER_PERIOD = 4;
	
	private int id; //id of campaign
	private Event event; //historical event covered 
	private Period period; //current period of history //++++++++++++++++++should prob be iterator, like in prev project #######
	private int turn; //current turn
	private Timestamp created; //time stamp when created
	private String host; //name of host //+++++++++++++++++++++++++++++Have this be a property thingy, for listener checking!?????????
	private Map<String, Player>nameToPlayer; //players involved
	
	private boolean wasCreated; //if campaign was created this session //++++++++++++++++++isNew???
	private boolean hasPlayersData; //if campaign has all players data
	private boolean wasUploaded; //if campaign was uploaded to db
	
	private static final String BYE = "bye"; //bye entry for pairing odd number of players
	//combinations of player pairings, for use during each turn of each period in the campaign:
	private final Queue<List<List<String>>>pairings = new LinkedList<List<List<String>>>();
	
	//queue holding a list of maps, each holding a list of strings
	private final Queue<List<Map<String, List<String>>>>pairingsTEST = new LinkedList<List<Map<String,List<String>>>>();
	
	//++++++++++++ID LIKE ABOVE TO BE:
	//period TO turn TO pairingID to pairings - TRY CONSTRUCT IT LIKE THAT> Have 
	
	/*
	 * 
	 * https://stackoverflow.com/questions/822322/how-to-implement-a-map-with-multiple-keys
	 * 
	 *###########################periodToTurnsToPlayersToMission
	 *[early 1940, turn 2, bob, bob's mission]
	 *early 1940 turn 1...4
	 *
	 *Event.getMaxturns
	 
	 [ [[a,b],[c,d]], [[a,c],[b,d]] ] - current setup
	 
	 
	 queue > list of maps with hash as key & list of names as values
	 
	 WHAT ABOUT THE SECOND PLAYER'S NAME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!????????
	 
	 [ [ [NO_BYE + name,[a,b]] , [BYE,[c,d]] ], [ [BYE,[a,c]] , [NO_BYE + name,[b,d]] ] ] - set up with bye flag
	 
	 private final Queue<List<Map<String, List<String>>>>pairings = new LinkedList<List<Map<String,List<String>>>>();
	 
	 Soooooo.... we want the key consisting of a combination (hash) of both player names. 
	 make each key equal to hash of name + hash of NO_BYE ++++++++++ YES,, YES, YES!!! 
	 
	 to get value: map.get(name # by NO_BYE)
	 
	 https://stackoverflow.com/questions/14677993/how-to-create-a-hashmap-with-two-keys-key-pair-value
	*/
	/////private Map<Integer, List<Mission>>turnToMissions; //missions assigned to players
	
	//missions assigned to pairings, for each turn of each period: //++++++++++PERIOD should maybe be used instead of turn!! :P
	private final Map<Integer, Map<PairingOLD, Mission>>turnToPairingsToMission = new HashMap<Integer, Map<PairingOLD,Mission>>(); 
	
	
	//=========================
	
	//queue > set(or list) of maps with hash as key & list of names as values
	private final List<List<Pairing>>PAIRINGS_TEST = new LinkedList<List<Pairing>>(); //+++++++++++++turnPairings
	
	//===============================
	
	private Campaign(int id, Timestamp created, Event event, String host) {
		this.id = id;
		this.event = event;
		this.created = created;
		this.host = host;
	}
	
	//new campaign:
	public Campaign(int id, Event event, Timestamp created, String user) {
		this(id, created, event, user);
		this.period = event.getPeriods().get(0);
		//add user as player:
		this.nameToPlayer = new HashMap<String, Player>(Collections.singletonMap(user, new Player(user)));
		/** https://stackoverflow.com/questions/6802483/how-to-directly-initialize-a-hashmap-in-a-literal-way/6802523 */
		wasCreated = true; //flag as created
	}
		
	//campaign cell campaign:
	public Campaign(int id, Event event, Period period, int turn, Timestamp created, String host, 
			Map<String, Player>nameToPlayer) {
		this(id, created, event, host);
		this.period = period; 
		this.turn = turn; 
		this.nameToPlayer = new HashMap<String, Player>(nameToPlayer);
	}
	
	//===================================================================
	public void setPairings() {
		/**
		 * Round-robin 'circle method' scheduling algorithm: https://en.m.wikipedia.org/wiki/Round-robin_tournament
		 * Code adapted from: https://stackoverflow.com/questions/26471421/round-robin-algorithm-implementation-java
		 */ pairings.clear(); //+++++++++++++++++++++++++++++REMOVE :P
		if (pairings.isEmpty()) { //if pairings is empty
			
			List<String>players = new ArrayList<String>(nameToPlayer.keySet()); //list of all players
			if (players.size()%2==1) {players.add(BYE);} //if odd number of players, add a bye
			Collections.shuffle(players); //shuffle positions of players 
			System.out.println("PLAYERS: " + players); //+++++++++++++++++++++
			String fixedPlayer = players.remove(0); //1st player is removed from list (to be given a fixed position for pairing)
			
			//loop through the number of turns (with unique pairings) available: 
		    for (int turn=0, turns=players.size(); turn < turns; turn++) {
		        System.out.println("\nTurn:" + (turn + 1));  //++++++++++++++++++++++++
		        List<List<String>>pairing = new ArrayList<List<String>>(); //list to hold new pairing
		        System.out.println(players.get(turn) + " vs " + fixedPlayer); //++++++++++++++++++
		        
		        //each turn, pair the fixed player against a player in players (at the index pos of that turn):
		        pairing.add(Arrays.asList(players.get(turn), fixedPlayer));
		        
		        System.out.println("PAIRING: " + pairing);
		        
		        //endPos is at players.size()+1 to replace the removed fixedPlayer's index. 
		        for (int pairPos=1, endPos=(players.size()+1)/2; pairPos < endPos; pairPos++) {  //pairPos starts at 1 to ignore first 
		            int player1Pos = (turn + pairPos) % turns; //turn number + pairingPos (set at 1 to ignore ..........++++++++++)
		            int player2Pos = (turn  + turns - pairPos) % turns;
		            
		            System.out.println(players.get(player1Pos) + " vs " + players.get(player2Pos)); //+++++++++++++++++
		            pairing.add(Arrays.asList(players.get(player1Pos), players.get(player2Pos)));  //add players to pairing
		        }
		        pairings.add(pairing); //add pairing to pairings
		    }
		}
	    
	    System.out.println("pairings: " + pairings); //++++++++++
	}
	
	public void setPairingsTEST() {
		/**
		 * Round-robin 'circle method' scheduling algorithm: https://en.m.wikipedia.org/wiki/Round-robin_tournament
		 * Code adapted from: https://stackoverflow.com/questions/26471421/round-robin-algorithm-implementation-java
		 */ PAIRINGS_TEST.clear(); //+++++++++++++++++++++++++++++REMOVE :P
		if (PAIRINGS_TEST.isEmpty()) { //if pairings is empty
			
			List<String>players = new ArrayList<String>(nameToPlayer.keySet()); //list of all players
			if (players.size()%2==1) {players.add(BYE);} //if odd number of players, add a bye
			Collections.shuffle(players); //shuffle positions of players 
			System.out.println("PLAYERS: " + players); //+++++++++++++++++++++
			String fixedPlayer = players.remove(0); //1st player is removed from list (to be given a fixed position for pairing)
			
			//loop through the number of turns (with unique pairings) available: 
		    for (int turn=0, turns=players.size(); turn < turns; turn++) {
		        System.out.println("\nTurn:" + (turn + 1));  //++++++++++++++++++++++++
		        ////////////////////////////////##############List<List<String>>pairing = new ArrayList<List<String>>(); //list to hold new pairing ##############WHY a list of lists?????
		        List<Pairing>pairings = new ArrayList<Pairing>();
		        
		        System.out.println(players.get(turn) + " vs " + fixedPlayer); //++++++++++++++++++
		        
		        //each turn, pair the fixed player against a player in players (at the index pos of that turn):
		        ///////////////////////////##############/pairing.add(Arrays.asList(players.get(turn), fixedPlayer));
		        pairings.add(new Pairing(players.get(turn), fixedPlayer));
		        
		        //////////System.out.println("PAIRING 1: " + pairings);
		        
		        //endPos is at players.size()+1 to replace the removed fixedPlayer's index. 
		        for (int pairPos=1, endPos=(players.size()+1)/2; pairPos < endPos; pairPos++) {  //pairPos starts at 1 to ignore first 
		            int player1Pos = (turn + pairPos) % turns; //turn number + pairingPos (set at 1 to ignore ..........++++++++++)
		            int player2Pos = (turn  + turns - pairPos) % turns;
		            
		            System.out.println(players.get(player1Pos) + " vs " + players.get(player2Pos)); //+++++++++++++++++
		            /////////////##################pairing.add(Arrays.asList(players.get(player1Pos), players.get(player2Pos)));  //add players to pairing
		            pairings.add(new Pairing(players.get(player1Pos), players.get(player2Pos)));  //add players to pairing
		            
		            //////////System.out.println("PAIRING 2: " + pairings);
		        }
		        ////////pairings.add(pairing); //add pairing to pairings
		        
		        //for each player in pairing:
		        /*pairing.forEach(player -> {
		        	System.out.println("PLAYER: " + player);
		        });*/
		        
		        PAIRINGS_TEST.add(pairings);
		       
		    }
		    System.out.println("PAIRINGS_TEST: " + PAIRINGS_TEST);
		}
	    
	    System.out.println("pairings: " + pairings); //++++++++++
	    
	    PAIRINGS_TEST.forEach(list ->{
	    	list.forEach(pair ->{
	    		System.out.println(pair.getPairing("jo"));
				System.out.println(pair.getPairing("jay"));
	    	});
			
		});
	}
	//==================================================================

	//update nameToPlayer:
	public void updatePlayers() {
		//if campaign wasn't just created, and players haven't yet been updated:
		if(!wasCreated && !hasPlayersData) {
			nameToPlayer = database.SelectPlayers.select(id); //get players data from db
			hasPlayersData = true; //mark as having players data
		}
		//++++++++++++else throw?????????
	}
	
	//add new player:
	public void addPlayer(Player player) {
		nameToPlayer.putIfAbsent(player.getName(), new Player(player.getName(), player.getCreated()));
		
		if(!pairings.isEmpty()) { //add to pairings //CHECK THIS WORKS :P ????????????????????????????????????????
			
			if (nameToPlayer.size()%2==1) {  //if odd number of players
				
				pairings.forEach(pairing -> {
					//++++++++++++++find bye player and add new player instead
				});
			}
		}
		//+++++++++++++create pairings for player, and remove/add BYE from/to pool as necessary
	}
	
	
	public int getId() { return id; } //get id
	public String getEventName() { return event.getName(); } //get event name
	////////++++public Event getEvent() { return event; }
	public List<AirForce> getEventAirForces() { return event.getAirForces(); } //get event air forces ++++++++++++++Just have a getEvent() instead????
	public Timestamp getCreated() { return created; } //get created //?????????? should this return timestamp??? +MAKE STRONGER IF SOI! +++++++++?
	public String getHostName() { return host; } //get host name
	
	public List<Player> getPlayers() { return new ArrayList<Player>(nameToPlayer.values()); } //get players
	
	/*
	//get missions based on turn (or empty list if not found):
	public List<Mission> getMissions(int turn) {
		return new ArrayList<Mission>(turnToMissions.getOrDefault(turn, Collections.emptyList())); 
	} */
	
	//get current progress:
	public double getProgress() {
		return (((double) turn) / event.getMaxTurns()); //current turn / max turns
	}
	
	//return whether user is present in campaign:
	public boolean getUserIsPlaying(String userName) {
		return nameToPlayer.containsKey(userName);
	}
	
	//=======================TYEST get booleran +++++++++
	/*SimpleBooleanProperty userIsPlaying = new SimpleBooleanProperty(false);
	
	//return whether user is present in campaign:
	public SimpleBooleanProperty getUserIsPlayingTEST() {
		return userIsPlaying;
		//return new SimpleBooleanProperty(nameToPlayer.containsKey(userName));
	}
	
	//------------
	
	
	pu
	*/
	//=================================================
	

	public boolean hasMissionsTEST() {
		return !turnToPairingsToMission.isEmpty();
	}
	
	public boolean getWasCreated() { return wasCreated; }
	public boolean getHasPlayersData() { return hasPlayersData; }

	@Override
	public String toString() {
		return "Campaign [nameToPlayer=" + nameToPlayer + "]";
	}
	
	
	
	
}
