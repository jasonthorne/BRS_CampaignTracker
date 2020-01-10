package com.brs.event;

import com.brs.event.EventTEST;

public class EventFactory {
	
	//creates an event from given event name:
	public EventTEST getEvent(EventName eventName) { 
		
		EventTEST event = null;
		switch(eventName){
			case ASSAULT_ON_THE_REICH:
				event = new AssaultOnTheReich();
				break;
			case BATTLE_OF_BRITAIN:
				event = new BattleOfBritain();
				break;
			case DEFENCE_OF_THE_HOME_ISLANDS:
				event = new DefenceOfTheHomeIslands();
				break;
			case GUADALCANAL:
				event = new Guadalcanal();
				break;
			case OPERATION_BARBAROSSA:
				event = new OperationBarbarossa();
				break;
			case STALINGRAD:
				event = new Stalingrad();
				break;
			case THE_ITALIAN_CAMPAIGN:
				event = new TheItalianCampaign();
		}
		return event; 
	}
}
