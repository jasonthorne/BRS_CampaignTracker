package com.brs.campaign;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.brs.player.Player;
import com.brs.airforce.AirForceName;
import com.brs.datetime.DateTime;
import com.brs.datetime.DateTimeFormat;
import com.brs.event.EventMaker;
import com.brs.event.EventName;
import com.brs.event.EventTEST;
import com.brs.mission.Mission;
import com.brs.period.Period;
import com.brs.plane.Model;

public class CampaignTEST extends EventMaker { //+++++++++++change to Campaign
	
	private final String name; //name of campaign
	private final String dateTime; //date & time of creation
	private final EventTEST event; //historical event chosen
	private final List<Period>periods; //periods of history covered
	private final ListIterator<Period>periodsIterator; //periods iterator
	private Period period; //current period
	
	private Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //map of players involved 
	private Map<Mission, List<Player>>missionToPlayers = new HashMap<Mission, List<Player>>(); //map of current missions

	public CampaignTEST(EventName eventName) {
		dateTime = DateTime.getDateTime(DateTimeFormat.DATE_TIME); //set dateTime
		event = getEvent(eventName); //create event from EventMaker
		name = event.getName() + " Campaign"; //create campaign name
		periods = event.getPeriods(); //set periods of history
		periodsIterator = periods.listIterator(); //create iterator from periods
	}
	
	//add a player to map of players:
	public void setPlayer(String name, AirForceName airForceName) {  
		//attempt to add player to map using their name as key. Warn user if unsuccessful (returned value other than null): 
		if(!(nameToPlayer.putIfAbsent(name, new Player(name, event.getAirForce(airForceName), period))==null)){
			System.out.println("Error: Player name already exists! "); //+++++++++++++++make this better :P +++++++++++++++++
		}else {
			System.out.println("Player added."); //+++++++++++++++make this better :P +++++++++++++++++
		}
	}
	
	public String getName() { return name; } //get name of campaign
	public String getDateTime() { return dateTime; }
	public List<AirForceName> getAirForceNames() { return event.getAirForceNames(); } //get air forces involved
	public List<Period> getPeriods() { return event.getPeriods(); } //get periods of history
	
	
	
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
