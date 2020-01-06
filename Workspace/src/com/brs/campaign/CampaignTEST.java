package com.brs.campaign;

import java.util.List;
import java.util.ListIterator;

import com.brs.player.Player;
import com.brs.airforce.AirForceName;
import com.brs.event.EventMaker;
import com.brs.event.EventName;
import com.brs.event.EventTEST;
import com.brs.mission.Mission;
import com.brs.period.Period;

public class CampaignTEST extends EventMaker { //+++++++++++change to Campaign
	
	private final String name; //name of campaign
	//CURRENT DATE STUFF HERE :P //date of creation
	private final EventTEST event; //historical event chosen
	
	private final List<Period>periods; //periods of history covered
	private final ListIterator<Period>periodsIterator; //periods iterator
	private Period period; //current period
	
	private List<Player>players; //players involved
	private List<Mission>missions; //missions assigned to players
	
	public CampaignTEST(EventName eventName) { //+++++++++++++++++change access level from public
		//creation date implementation here +++++++++++++
		event = getEvent(eventName); //from EventMaker
		name = event.getName() + " Campaign";
		periods = event.getPeriods();
		periodsIterator = periods.listIterator();
	}
	
	
	//A player (one in list of players) pushes begin campaign: 
	//-------------
	/*
	advance campaign(){
		recordResults();
		movePeriod();
		assignMissions();
	}
	
	*/
	
	
	
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
	
	private void setMissions(){
		
		//randomly assign pairings
		//create missions for each pairing, add to list of missions
	}
	
	
	
	
	
	
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++TEST method below:
	public String getName() { return name; }
	public List<AirForceName> getAirForceNames() { 
		//return airForceNames; 
		return event.getAirForceNames();
	}	
	
	
	public List<Period> getPeriods() { return periods; }	
	
	
	 public void getPeriodTEST() { 
		 movePeriod();
		// currPeriod = periods.get(0);
		//System.out.println("period is: " + period);
	}	
	
	//+++++++++++++++++++++++++++++++++++++++++++++++
	
	
	
	
	
	
	
	
	

	
	

}
