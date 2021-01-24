package model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import controller.Loadable;
import model.AirForce.AirForceBuilder;
import model.Plane.Status;

public class Campaign implements Loadable{
	
	//amount of turns played per period:
	public static final int TURNS_PER_PERIOD = 4; //+++++++++++++++++++++++should this be in db????:
	
	private int id; //id of campaign
	private Event event; //historical event covered 
	private Period period; //current period of history
	private int turn; //current turn
	private Timestamp created; //time stamp when created
	private String host; //name of host
	private Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //players involved
	//+++++++++++++++++++++++turnToMission +++++++++++++++++++++where missions are held!! ADD THIS :P +++++++++++++++++
	
	private boolean isAllDownloaded = false; //if fully downloaded from db //+++++++++++++++++'isFullDownload'?? 
	////////////////private boolean isAllUploaded = false; //if fully uploaded from db
	
	private Campaign() {} //blank constructor
	
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
			campaign.event = event; //+++++++++++++++++++++MAKE THIS STRONGER!!! :P 
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
			campaign.nameToPlayer = new TreeMap<String, Player>(playersMap);
			return this;
		}
		
		//set player:
		public CampaignBuilder setPlayer(String name) {
			campaign.nameToPlayer.putIfAbsent(
					name, new Player.PlayerBuilder().setName(name).build());
			return this;
		}
		
		//set if fully uploaded:
		public CampaignBuilder setIsAllDownloaded(boolean bool) {
			campaign.isAllDownloaded = bool;
			return this;
		}
		
		//return built campaign:
		public Campaign build() { return campaign; } 
	}
	
	public int getId() { return id; } //get id
	public String getEventName() { return event.getName(); } //get event name
	public List<AirForce> getEventAirForces() { return event.getAirForces(); } //get event air forces
	public Timestamp getCreated() { return created; } //get created //?????????? should this return timestamp??? +MAKE STRONGER IF SOI! +++++++++?
	public String getHostName() { return host; } //get host name
	
	public boolean getIsAllDownloaded() { return isAllDownloaded; } //get if fully downloaded
	///////////////public boolean getIsAllUploaded() { return isAllUploaded; } //get if fully uploaded
		
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
		return "Campaign [id=" + id + ", event=" + event + ", period=" + period + ", turnNum=" + turn + ", created="
				+ created + ", host=" + host + ", nameToPlayer=" + nameToPlayer + "]";
	}
	
	
	
	
}
