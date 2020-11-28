package model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.AirForce.AirForceBuilder;
import model.Plane.Status;

public class Campaign {
	//+++++++++++++++++++++++should this be in db????:
	public static final int TURNS_PER_PERIOD = 4; //amount of turns played per period
	
	private int id; //id of campaign
	private String name; //name of campaign /////////////////////////REMOVE THIS! :P
	private Event event; //historical event covered 
	private Period period; //current period of history
	private int turnNum; //current turn number
	private Timestamp created; //time stamp when created
	private String hostName; //name of host
	private Map<String, Player>playerNameToPlayer = new TreeMap<String, Player>(); //players involved
	
	
	////////private boolean isPlaying; //++++++++++++++determined by whether you are a player
	
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
		
		/*
		//set name:
		public CampaignBuilder setName(String name) {
			campaign.name = name;
			return this;
		}*/
		
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
		public CampaignBuilder setHostName(String name) { //++++++++PROB USE ID AND FETCH FROM MAP
			campaign.hostName = name;
			return this;
		}
		
		//set players:
		public CampaignBuilder setPlayers(Map<String, Player>playersMap) {
			campaign.playerNameToPlayer = new TreeMap<String, Player>(playersMap);
			return this;
		}
		
		//set player:
		public CampaignBuilder setPlayer(Player player) {
			Player tempPlayer = new Player.PlayerBuilder().setName(player.getName()).build();
			campaign.playerNameToPlayer.putIfAbsent(tempPlayer.getName(), tempPlayer);
			return this;
		}
		
		//return built campaign:
		public Campaign build() { return campaign; } 
	}
	
	public Campaign(String name){ //++++++++++++++REMOVE!! :P
		this.name = name;
	}
	
	public int getId() { return id; } //get id
	public String getEventName() { return /*event.getName();*/ name; } //get name
	
	
		
}
