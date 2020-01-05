package com.brs.campaign;

import java.util.List;

import com.brs.player.PlayerTEST;
import com.brs.airforce.AirForceName;
import com.brs.event.EventFactory;
import com.brs.event.EventName;
import com.brs.event.EventTEST;
import com.brs.mission.MissionTEST;
import com.brs.period.Period;

public class CampaignTEST extends EventFactory { //+++++++++++change to Campaign
	
	//name of campaign:
	private final String name;
	
	//date of creation:
	//CURRENT DATE STUFF HERE :P
	
	//historical event being played:
	private final EventTEST event; //++++++++++++++change to Event event 
	
	//current period of history:
	private Period currentPeriod;
	
	//players involved in campaign:
	private List<PlayerTEST>players; //++++++++++++++change to List<Player>
	
	//missions currently assigned to players:
	private List<MissionTEST>currentMissions; //++++++++++++++change to List<Mission>
	
	//constructor sets event and name:
	public CampaignTEST(EventName eventName) { //+++++++++++change to Campaign
		event = setEvent(eventName);  //from EventFactory
		name = event.getName() + " Campaign";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//+++++++++++++++++++++++++++++++++++++++++++++TEST method below:
	public String getName() { return name; }
	public List<AirForceName> getAirForceNames() { 
		//return airForceNames; 
		return event.getAirForceNames();
	}	
	///////////public List<Period> getPeriods() { return periods; }	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	

}
