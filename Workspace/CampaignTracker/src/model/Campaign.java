package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

import controller.CampaignController;
import controller.LoginController;
import javafx.beans.property.SimpleBooleanProperty;
import model.Plane.Status;

public final class Campaign {
	
	//amount of turns played per period:
	public static final int TURNS_PER_PERIOD = 4;
	
	private int id; //id of campaign
	private Event event; //historical event covered 
	private Period period; //current period of history
	private int turn; //current turn
	private Timestamp created; //time stamp when created
	private String host; //name of host 
	private Map<String, Player>nameToPlayer; //players involved
	
	private boolean wasCreated; //if campaign was created this session //++++++++++++++++++isNew???
	private boolean hasPlayersData; //if campaign has all players data
	private boolean wasUploaded; //if campaign was uploaded to db
	
	private static final String BYE = "bye"; //bye entry for pairing odd number of players
	//combinations of player pairings, for each turn of each period in the campaign:
	private final Queue<List<List<String>>>pairings = new LinkedList<List<List<String>>>();
	
	/////private Map<Integer, List<Mission>>turnToMissions; //missions assigned to players
	
	//missions assigned to pairings, for each turn of each period: //++++++++++PERIOD should maybe be used instead of turn!! :P
	private final Map<Integer, Map<Pairing, Mission>>turnToPairingsToMission = new HashMap<Integer, Map<Pairing,Mission>>(); 
	
	
	private Campaign(int id, Timestamp created, Event event, String host) {
		this.id = id;
		this.event = event;
		this.created = created;
		this.host = host;
	}
	
	//new campaign:
	public Campaign(int id, Event event, Timestamp created, String user) {
		this(id, created, event, user);
		this.period = event.getStartPeriod();
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
		 */
		if (pairings.isEmpty()) { //if pairings is empty:
			
			List<String>players = new ArrayList<String>(nameToPlayer.keySet()); //list of all players
			if (players.size()%2==1) {players.add(BYE);} //if odd number of players, add a bye
			Collections.shuffle(players); //shuffle positions of players 
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
	
	
	//==================================================================
	
	
	/*
	//update nameToPlayer:
	public void updatePlayers(Campaign campaign) {
		//if campaign wasn't just created, and players haven't yet been updated:
		if(!campaign.wasCreated && !campaign.hasPlayersData) {
			campaign.nameToPlayer = database.SelectPlayers.select(campaign.getId());
			campaign.hasPlayersData = true;
		}
		//++++++++++++else throw?????????
	}*/
	
	
	//update nameToPlayer:
	public void updatePlayers() {
		//if campaign wasn't just created, and players haven't yet been updated:
		if(!wasCreated && !hasPlayersData) {
			nameToPlayer = database.SelectPlayers.select(id); //get players data from db
			hasPlayersData = true; //mark as having players data
		}
		//++++++++++++else throw?????????
	}
	
	/*
	//add new player:
	public void addPlayer(Campaign campaign, String playerName, Timestamp created) {
		campaign.nameToPlayer.putIfAbsent(playerName, new Player(playerName, created));
	}*/
	
	/*
	//add new player:
	public void addPlayer(String playerName, Timestamp created) {
		nameToPlayer.putIfAbsent(playerName, new Player(playerName, created));
		//+++++++++++++create pairings for player, and remove/add BYE from/to pool as necessary
	}*/
	
	//add new player:
	public void addPlayer(Player player) {
		nameToPlayer.putIfAbsent(player.getName(), new Player(player.getName(), player.getCreated()));
		//+++++++++++++create pairings for player, and remove/add BYE from/to pool as necessary
	}
	
	
	
	/*
	//update nameToPlayer:
	public void updatePlayers2(CampaignController campaignCtrlr) {
		if(!wasCreated && !hasPlayersData) {
			campaignCtrlr.updatePlayers();
			campaign.nameToPlayer = database.SelectPlayers.select(campaign.getId());
			campaign.hasPlayersData = true;
		}
		//++++++++++++else throw?????????
	}*/
	
	
	
	
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
	
	
	boolean testMe = false;
	
	///////////boolean hasUser??? 
	*/
	//=================================================
	
	
	public boolean getWasCreated() { return wasCreated; }
	public boolean getHasPlayersData() { return hasPlayersData; }

	@Override
	public String toString() {
		return "Campaign [nameToPlayer=" + nameToPlayer + "]";
	}
	
	
	
	
}
