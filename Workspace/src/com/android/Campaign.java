package com.android;

import java.util.ArrayList;
import java.util.List;

import com.android.Player.PlayerBuilder;
import com.android.event.Event;
import com.android.event.Event.EventBuilder;
import com.android.event.EventData.EventName;

public class Campaign {
	
	//Event:
	private Event event;
	//private HistoricEventBuilder historicEventBuilder;
	
	private List<Player>players = new ArrayList<Player>(); //list of players in campaign
	/////private Player player; //--------------------------------------------------------------------????????
	/////////private PlayerBuilder playerBuilder;
	
	
	//a Campaign has a start date (maybe make it the start of the historic date instead of current date)
	private int startDate; //++++++++++++++++make a proper date
	
	//constructor: 
	private Campaign(){
		System.out.println("Campaign constructed");
	}
	
	//toString:
	@Override
	public String toString() {
		return "Campaign: " + event.getName() + ". Created: " + startDate;
	}


	

	//-----------GETTERS-----------
	
	/*
	public Name getHistoricEventName() {
		return this.historicEvent.getName();
	}
	*/
	
	public String getEventName() {
		return event.getName();
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	
	public int getStartDate() { //+++change to proper date
		return startDate;
	}
	
	/*
	public Player getPlayer(Player player) { //CHECK IF THIS IS UNSAFE!!! hould probably return a copy!!+++++++++++++++++++++
		
		
		return null;
	}
	*/
	
	//THIS IS WAAAAY TOO CLUNKY :P ++++++++++++++++++
	public Player getPlayer(String player) {
		for(Player i : players){
			if (i.getName() == player) return i;
		}
		return null; //better way than this???
	}
	
	public Campaign getCampaign() { //+++++++++++++Should probably return a copy!!
		return this;
	}
	
	
	//----------------------------
	
	//builder class:
	static class CampaignBuilder { //+++++++++++++MAYBE SHOULD BE STATIC AND FINAL - same for all other builder classes :P
		
		private Campaign campaign = new Campaign();
		//private HistoricEventBuilder historicEventBuilder;
		///private PlayerBuilder playerBuilder;
		//private Player player;
		
		public CampaignBuilder setEvent(EventName name) {
		//public CampaignBuilder setHistoricEvent(String name) {
	
			//campaign.historicEventBuilder = new HistoricEventBuilder(); //create builder
			//campaign.historicEvent = campaign.historicEventBuilder.setName(name).build();
			//--------------------
			campaign.event = new EventBuilder().setEventName(name).build(); //+++CHANGE TO setValues
	
			//------------------
			
			setStartDate(); //set startDate (as campaign is being created)
			return this;
		}
		
		public CampaignBuilder setPlayer(Player player) { 
		//public CampaignBuilder setPlayer(PlayerBuilder playerBuilder) { ///////needs ot take in a player! @@@@@@@@@@@@@
			//campaign.playerBuilder = new PlayerBuilder(); //create builder
			//campaign.player = campaign.playerBuilder.build();
			
			//campaign.player = playerBuilder.build(); //create player
			//campaign.players.add(campaign.player); //add a  player to list of players
			campaign.players.add(player);
			
			///campaign.players.add(playerBuilder.build()); //add a built player to list of players
			return this;
		}
		
		private void setStartDate() {
			campaign.startDate = 333; //+++++++++++++++++edit to set proper date
		}
		
		
		public Campaign build() {
			//setStartDate();
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
