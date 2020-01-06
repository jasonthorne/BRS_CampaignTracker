package com.brs.event;

import com.brs.event.EventTEST;

//create an event according to chosen event name:
public abstract class EventMaker {
	
	protected EventTEST getEvent(EventName eventName) { //CHANGE TO EVENT +++++++++++++
		
		switch(eventName){
			case ASSAULT_ON_THE_REICH:
				//return new AssaultOnTheReich();
			case BATTLE_OF_BRITAIN:
				return new BattleOfBritain();
			case DEFENCE_OF_THE_HOME_ISLANDS:
				//return new DefenceOfTheHomeIslands();
			case GUADALCANAL:
				//return new Guadalcanal();
			case OPERATION_BARBAROSSA:
				//return new OperationBarbarossa();
			case STALINGRAD:
				//return new Stalingrad();
			case THE_ITALIAN_CAMPAIGN:
				//return new TheItalianCampaign();
		}
		return null; //HAVE ERROR HANDLING FOR THIS +++++++++++++++
	}
}
