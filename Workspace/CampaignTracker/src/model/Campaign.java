package model;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controller.CampaignController;
import controller.LoginController;
import javafx.beans.property.SimpleBooleanProperty;
import model.Plane.Status;

public final class Campaign {
	
	//amount of turns played per period:
	public static final int TURNS_PER_PERIOD = 4; //+++++++++++++++++++++++should this be in db????:
	
	private int id; //id of campaign
	private Event event; //historical event covered 
	private Period period; //current period of history
	private int turn; //current turn
	private Timestamp created; //time stamp when created
	private String host; //name of host
	private Map<String, Player>nameToPlayer; //players involved 
	//+++++++++++++++++++++++turnToMission +++++++++++++++++++++where missions are held!! ADD THIS :P +++++++++++++++++
	

	private boolean wasCreated; //++++++++++++++++++NEEDED???????
	private boolean hasPlayersData;
	private boolean wasUploaded;
	
	//====================================================================================
	//https://softwareengineering.stackexchange.com/questions/284215/how-do-you-avoid-getters-and-setters
	
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
		//isPlaying = true;//+++++++++++++
	}
		
	//campaign cell campaign:
	public Campaign(int id, Event event, Period period, int turn, Timestamp created, String host, Map<String,
			Player>nameToPlayer) {
		this(id, created, event, host);
		this.period = period; 
		this.turn = turn; 
		this.nameToPlayer = new HashMap<String, Player>(nameToPlayer);
	}
	
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
	
	
	//add new player:
	public void addPlayer(String playerName, Timestamp created) {
		nameToPlayer.putIfAbsent(playerName, new Player(playerName, created));
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
