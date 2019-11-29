package com.android;

import java.util.ArrayList;
import java.util.List;

import com.android.HistoricEvent.HistoricEventBuilder;
import com.android.HistoricEvent.Name;
import com.android.Player.PlayerBuilder;

public class Campaign {
	
	//Historic event:
	private HistoricEvent historicEvent;
	private HistoricEventBuilder historicEventBuilder;
	
	private List<Player>players = new ArrayList<Player>(); //list of players in campaign
	private Player player; 
	private PlayerBuilder playerBuilder;
	
	
	//a Campaign has a start date (maybe make it the start of the historic date instead of current date)
	//private Date; 
	
	//constructor: 
	private Campaign(){
		System.out.println("Campaign constructed");
	}
	
	

	//-----------GETTERS-----------
	
	public Name getHistoricEventName() {
		return this.historicEvent.getName();
	}
	
	 
	//----------------------------
	
	//builder class:
	static class CampaignBuilder { 
		
		private Campaign campaign = new Campaign();
		//private HistoricEventBuilder historicEventBuilder;
		///private PlayerBuilder playerBuilder;
		//private Player player;
		
		public CampaignBuilder setHistoricEvent(Name name) {
			campaign.historicEventBuilder = new HistoricEventBuilder(); //create builder
			campaign.historicEvent = campaign.historicEventBuilder.setName(name).build();
			return this;
		}
		
		
		public CampaignBuilder addPlayer() {
			campaign.playerBuilder = new PlayerBuilder(); //create builder
			campaign.player = campaign.playerBuilder.build();
			return this;
		}
		
		public Campaign build() {
			return campaign;
		}
		
	}
	
	
	
	
	
	
	/*
	 addPlayer(){
	  
	 }
	 */
	
	/*
	removePlayer(){
		
	}
	*/
	
	
	
	

}
