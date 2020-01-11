package com.brs.campaign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.brs.player.Player;
import com.brs.turn.Turn;
import com.brs.airforce.AirForceName;
import com.brs.date.Date;
import com.brs.event.EventFactory;
import com.brs.event.EventName;
import com.brs.event.Event;
import com.brs.mission.Mission;
import com.brs.mission.MissionManager;
import com.brs.period.Period;

public class Campaign { //+++++++++++change to Campaign
	
	private final String name; //name of campaign
	private final String date = Date.getDate(); //date of creation
	private final Event event; //historical event chosen
	private final List<Period>periods; //periods of history covered
	private final ListIterator<Period>periodsIterator; //periods iterator
	private Period period; //current period represented
	//=======================================months 1 - 4. after month 4, then move one period
	//private Month month; //current month represented
	private int turnNum;
	private List<Turn>turns; 
	
	
	//=======================================
	
	private Map<String, Player>nameToPlayer = new TreeMap<String, Player>(); //map of players involved 
/////////++++++++++++++++CURRENT mission: ++++++++++++++++++++++++
	private Map<Mission, List<Player>>missionToPlayers = new HashMap<Mission, List<Player>>(); //map of current missions
	
	public Campaign(EventName eventName) {
		event = new EventFactory().getEvent(eventName); //create event from EventFactory
		name = "Campaign: " + event.getName(); //create campaign name
		periods = event.getPeriods(); //set periods of history
		periodsIterator = periods.listIterator(); //create iterator from periods
		
		//======================================
		
		
		//=======================================
	}
	
	//add a new player to map of players:
	public void setPlayer(String name, AirForceName airForceName) {  ////////////??????????look at access level of this!!
		//attempt to add player to map using their name as key. Warn user if unsuccessful (returned value other than null): 
		if(!(nameToPlayer.putIfAbsent(name, new Player(name, event.getAirForce(airForceName), period))==null)){
			System.out.println("Error: Player name already exists! "); //+++++++++++++++make this better :P +++++++++++++++++
		}else {
			System.out.println("Player added."); //+++++++++++++++make this better :P +++++++++++++++++
		}
	}
	
	public String getName() { return name; } //get name of campaign
	public String getDate() { return date; } //get date of creation
	public List<AirForceName> getAirForceNames() { return event.getAirForceNames(); } //get air forces involved
	public List<Period> getPeriods() { return event.getPeriods(); } //get periods of history covered
	//////////////////////public static Period getPeriod() { return period; }
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++TEST methods below:
	 public void getPeriodTEST() { 
		 movePeriod();
		// currPeriod = periods.get(0);
		//System.out.println("period is: " + period);
	}	
	 
	public void updateTEST() {
		//updatePlayers();
		movePeriod();
		getMissions();
		
	}
	
	public void getMissions(){
		//missionToPlayers = new MissionBuilder
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++
	
	//advance period to following period of history
	private void movePeriod() {
		
		if(periodsIterator.hasNext()) {
			period = periodsIterator.next();
			System.out.println(period); //++++++++++++++
		}
		else {  
			System.out.println("end of periods - game is over"); //++++++++++++++
		}
	}
		
	
	public void test() {
		for (Entry<String, Player> entry : nameToPlayer.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue());
		}
		
		//+++++++++++++++++++
		//MissionManager.getMissions(new ArrayList<Player>(nameToPlayer.values()));
		//MissionManager.getMissions(new ArrayList<String>(nameToPlayer.keySet()));
		MissionManager test = new MissionManager();
		test.setUnpairedPlayers(new ArrayList<String>(nameToPlayer.keySet()));
		test.pairPlayers();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void setMissions(){
		
		//randomly assign pairings
		//create missions for each pairing, add to list of missions
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

}
