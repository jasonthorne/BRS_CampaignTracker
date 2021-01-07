package model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.AirForce.AirForceBuilder;
import model.Plane.Status;

public class Campaign {
	
	//amount of turns played per period:
	public static final int TURNS_PER_PERIOD = 4; //+++++++++++++++++++++++should this be in db????:
	
	private int id; //id of campaign
	private Event event; //historical event covered 
	private Period period; //current period of history
	private int turnNum; //current turn number
	private Timestamp created; //time stamp when created
	private String host; //name of host
	
	private Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //players involved
	
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
		
		//return built campaign:
		public Campaign build() { return campaign; } 
	}
	
	public int getId() { return id; } //get id
	public String getEventName() { return event.getName(); } //get event name
	public Timestamp getCreated() { return created; } //get created //?????????? should this return timestamp??? +MAKE STRONGER IF SOI! +++++++++?
	public String getHostName() { return host; } //get host name
	
	//return current progress:
	public double getProgress() {
		return (((double) turnNum) / event.getMaxTurns());
	}
	
	//return whether user is present in campaign:
	public boolean userIsPlaying(String userName) {
		return nameToPlayer.containsKey(userName);
	}
	
	@Override
	public String toString() {
		return "Campaign [id=" + id + ", nameToPlayer=" + nameToPlayer + "]";
	}
	
}
