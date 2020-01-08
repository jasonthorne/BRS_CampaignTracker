package com.brs.campaign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.brs.player.Player;
import com.brs.airforce.AirForceName;
import com.brs.event.EventMaker;
import com.brs.event.EventName;
import com.brs.event.EventTEST;
import com.brs.mission.Mission;
import com.brs.period.Period;
import com.brs.plane.Model;

public class CampaignTEST extends EventMaker { //+++++++++++change to Campaign
	
	private final String name; //name of campaign
	//CURRENT DATE STUFF HERE :P //date of creation
	private final EventTEST event; //historical event chosen
	
	private final List<Period>periods; //periods of history covered
	private final ListIterator<Period>periodsIterator; //periods iterator
	private Period period; //current period
	
	private Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //map of players involved 
	private Map<Mission, List<Player>>missionToPlayers = new HashMap<Mission, List<Player>>(); //map of current missions
	
	
	//+++++++++++++++have landing page extend this class to make these in it's collection of Campaigns
	public CampaignTEST(EventName eventName) { //+++++++++++++++++change access level from public to pretected.
		//creation date implementation here +++++++++++++
		event = getEvent(eventName); //from EventMaker
		name = event.getName() + " Campaign";
		periods = event.getPeriods();
		periodsIterator = periods.listIterator();
	}
	
	
	//add a player map of players involved:
	public void setPlayer(String name, AirForceName airForceName) { 
		
		/*
		//+++++++++++++++++I think because trhis is a tree map, that we need a comparator for this to work +++++++++++
		if(nameToPlayer.putIfAbsent(name, new Player(name, event.getAirForce(airForceName))).equals(null)){
			System.out.println("ENTRY ADDED");
		}
		*/
		
		//if player name already exists in nameToPlayer (a value other than null was returned by the put):
		//if(!nameToPlayer.putIfAbsent(name, new Player(name, event.getAirForce(airForceName))).equals(null)) {
		//	System.out.println("Error: Player name already exists. "); //+++++++++++++++make this better :P +++++++++++++++++
		//}
	}
	
	public String getName() { return name; }
	public List<AirForceName> getAirForceNames() { return event.getAirForceNames(); }	
	public List<Period> getPeriods() { return event.getPeriods(); }	
	
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++TEST methods below:
	 public void getPeriodTEST() { 
		 movePeriod();
		// currPeriod = periods.get(0);
		//System.out.println("period is: " + period);
	}	
	 
	public void updateTEST() {
		movePeriod();
		
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++
	
	//advance period to following period of history
		private void movePeriod() {
			
			if(periodsIterator.hasNext()) {
				period = periodsIterator.next();
				System.out.println(period); //++++++++++++++
			}
			else {
				//CAMPAIGN OVER!!  
				//add some sort of campaign ending functionality +++++
				System.out.println("end of periods"); //++++++++++++++
			}
		}
		
	
	public void test() {
		for (Entry<String, Player> entry : nameToPlayer.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void setMissions(){
		
		//randomly assign pairings
		//create missions for each pairing, add to list of missions
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

}
