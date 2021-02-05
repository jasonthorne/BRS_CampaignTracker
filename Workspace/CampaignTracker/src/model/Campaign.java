package model;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controller.CampaignController;
import model.Plane.Status;

public final class Campaign implements Loadable{
	
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
	
	///////private boolean isAllDownloaded = false; //if fully downloaded from db //+++++++++++++++++'isFullDownload'?? 
	////////////////private boolean isAllUploaded = false; //if fully uploaded from db
	private boolean wasCreated; //++++++++++++++++++NEEDED???????
	private boolean isUploaded; //+++++++++'was' instead of 'is'????
	private boolean isDownloaded;
	
	////////////private Campaign() {} //blank constructor
	boolean hasAllData; 
	boolean hasDownloadedAll;
	boolean hasPulledAll;
	boolean hasPushedAll;
	boolean wasUploaded;
	boolean hasUploadedAll;
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
	}
		
	//campaign cell campaign:
	public Campaign(int id, Event event, Period period, int turn, Timestamp created, String host, Map<String,
			Player>nameToPlayer) {
		this(id, created, event, host);
		this.period = period; 
		this.turn = turn; 
		this.nameToPlayer = new HashMap<String, Player>(nameToPlayer);
	}
	
	//update nameToPlayer:
	public void updateNameToPlayer(Campaign campaign) {
		if(!wasCreated && !isDownloaded) {
			campaign.nameToPlayer = database.SelectPlayers.select(campaign.getId());
			campaign.isDownloaded = true;
		}
		//++++++++++++else throw?????????
	}
	
	
	public int getId() { return id; } //get id
	public String getEventName() { return event.getName(); } //get event name
	////////++++public Event getEvent() { return event; }
	public List<AirForce> getEventAirForces() { return event.getAirForces(); } //get event air forces
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
	
	/////////////public int getAirForceName(int airForceId) { return event.get} +++++++++++++
	
	@Override
	public String toString() {
		return "Campaign [id=" + id + ", event=" + event + ", period=" + period + ", turn=" + turn + ", created="
				+ created + ", host=" + host + ", nameToPlayer=" + nameToPlayer + ", wasCreated=" + wasCreated
				+ ", isUploaded=" + isUploaded + ", isDownloaded=" + isDownloaded + "]";
	}

	@Override
	public boolean getWasCreated() { return wasCreated; }
	@Override
	public boolean getIsDownloaded() { return isDownloaded; }
	@Override
	public boolean getIsUploaded() { return isUploaded; }
	
	
	
	
	
	
}
