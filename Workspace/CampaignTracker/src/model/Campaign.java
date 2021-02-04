package model;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	private Map<String, Player>nameToPlayer; // = new HashMap<String, Player>(); //players involved 
	//+++++++++++++++++++++++turnToMission +++++++++++++++++++++where missions are held!! ADD THIS :P +++++++++++++++++
	
	///////private boolean isAllDownloaded = false; //if fully downloaded from db //+++++++++++++++++'isFullDownload'?? 
	////////////////private boolean isAllUploaded = false; //if fully uploaded from db
	private boolean wasCreated; //++++++++++++++++++NEEDED???????
	private boolean isUploaded; //+++++++++'was' instead of 'is'????
	private boolean isDownloaded;
	
	////////////private Campaign() {} //blank constructor
	
	
	//====================================================================================
	//https://softwareengineering.stackexchange.com/questions/284215/how-do-you-avoid-getters-and-setters
	
	
	//++++++++++++++TAKE WHATS IN COMMON FROM THESE AND MAKE ANOTHER CONSTRUCTOR FOR THEM TO CALL (a PRIVATE one!! :P)
	private Campaign(int id, Timestamp created, Event event, String host) {
		this.id = id;
		this.event = event;
		this.created = created;
		this.host = host;
	}
	
	
	//new campaign:
	public Campaign(int id, Event event, Timestamp created, String user) {
		/*this.id = id;
		this.event = event; 
		this.period = event.getStartPeriod();
		this.created = created;
		this.host = user;*/
		this(id, created, event, user);
		this.period = event.getStartPeriod();
		//add user as player:
		this.nameToPlayer = new HashMap<String, Player>(Collections.singletonMap(user, new Player(user)));
		/** https://stackoverflow.com/questions/6802483/how-to-directly-initialize-a-hashmap-in-a-literal-way/6802523 */
		wasCreated = true; //flag as created
	}
		
	//campaign cell campaign:
	public Campaign(int id, Event event, Period period, int turn, Timestamp created, 
			String host, Map<String, Player>nameToPlayer) {
		this(id, created, event, host);
		////////////////this.id = id;
		/////////////this.event = event; //+++++++++++make stronger?????? needed????
		this.period = period; //?????????
		this.turn = turn; //???????????
		///////////this.created = created; //++++++++++++make stronger?????
		//////////////this.host = host;
		this.nameToPlayer = new HashMap<String, Player>(nameToPlayer); //+++++++make stronger?????
		///////////////////wasDownloaded = true; //flag as down-loaded +++++++++WRONG!!
	}
	
	
	//full campaign:
	public Campaign(int i) {
		
		isDownloaded = true;
		
	}
	
	
	
	/*Campaign increasePeriod(Period period){
		this.period = period;
		return this;
	
	}*/
	
	//====================================================================================
	/*
	//builder class:
	public static class CampaignBuilder {
		
		//create campaign:
		private Campaign campaign = new Campaign();
		
		//set id:
		public CampaignBuilder setId(int id) {
			campaign.id = id;
			return this;
		}
		
		//set event:
		public CampaignBuilder setEvent(Event event) {
			campaign.event = event;
			return this;
		}
		
		//set period:
		public CampaignBuilder setPeriod(Period period) {
			campaign.period = new Period(period.getBlock(), period.getYear());
			return this;
		}
		
		//set turn:
		public CampaignBuilder setTurn(int turn) {
			campaign.turn = turn;
			return this;
		}
		
		//set created:
		public CampaignBuilder setCreated(Timestamp timestamp) {
			campaign.created = timestamp;
			return this;
		}
		
		//set host:
		public CampaignBuilder setHost(String name) { //++++++++PROB USE ID AND FETCH FROM MAP
			campaign.host = name; //set name of host
			return this;
		}
		
		//set players:
		public CampaignBuilder setPlayers(Map<String, Player>playersMap) {
			campaign.nameToPlayer = new HashMap<String, Player>(playersMap);
			return this;
		}
		
		//set player:
		/*public CampaignBuilder setPlayer(String name) {
			campaign.nameToPlayer.putIfAbsent(
					name, new Player.PlayerBuilder().setName(name).build());
			return this;
		}
		
		//set player:
		public CampaignBuilder setPlayer(String name) {
			campaign.nameToPlayer.putIfAbsent(name, new Player(name));
			return this;
		}
		
		//set if fully uploaded:
		/*public CampaignBuilder setIsAllDownloaded(boolean bool) {
			campaign.isAllDownloaded = bool;
			return this;
		}
		
		//return built campaign:
		public Campaign build() { return campaign; } 
	}*/
	
	public int getId() { return id; } //get id
	public String getEventName() { return event.getName(); } //get event name
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
