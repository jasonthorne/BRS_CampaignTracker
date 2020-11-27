package model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.AirForce.AirForceBuilder;
import model.Plane.Status;

public class Campaign {
	
	public static final int TURNS_PER_PERIOD = 4; //amount of turns played per period
	
	private String name; //name of campaign
	private Period period; //current period
	private Timestamp created; //time stamp created
	private String hostName; //name of host
	private Map<Integer, Player>players; //players involved
	
	////////private boolean isPlaying; //++++++++++++++determined by whether you are a player
	
	private Campaign() {} //blank constructor
	
	//builder class:
	public static class CampaignBuilder {
		
		//create campaign:
		private Campaign campaign = new Campaign();
		
		//set name:
		public CampaignBuilder setName(String name) {
			campaign.name = name;
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
		
		//set host:
		public CampaignBuilder setPlayers(Map<Integer, Player>players) {
			campaign.players = new HashMap<Integer, Player>(players);
			return this;
		}
		
		//return built campaign:
		public Campaign build() { return campaign; } 
	}
	
	public Campaign(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Campaign [name=" + name + ", period=" + period + ", created=" + created + ", hostName=" + hostName
				+ ", players=" + players + "]";
	}
	
}
