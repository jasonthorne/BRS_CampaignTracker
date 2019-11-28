package com.android;

import java.util.ArrayList;
import java.util.List;

import com.android.HistoricEvent.HistoricEventBuilder;
import com.android.HistoricEvent.Name;

public class Campaign {
	
	//A Campaign HAS a historic event
	private HistoricEvent historicEvent;
	//private HistoricEventBuilder historicEventBuilder;
	
	private List<Player>players = new ArrayList<Player>(); //list of players in campaign
	private Player player; //needed???????????
	
	//a Campaign has a start date (maybe make it the start of the historic date instead of current date)
	//private Date; 
	
	//constructor:
	Campaign(){
		System.out.println("Campaign constructed");
	}
	
	//builder class:
	static class CampaignBuilder { 
		
		private Campaign campaign = new Campaign();
		private HistoricEventBuilder historicEventBuilder;
		
		public CampaignBuilder setHistoricEvent() {
			historicEventBuilder = new HistoricEventBuilder(); //create builder
			campaign.historicEvent = historicEventBuilder.setName(Name.BATTLE_OF_BRITAIN).build();
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
