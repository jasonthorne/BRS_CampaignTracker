package com.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//public enum HistoricEvent {
public class HistoricEvent {
	
	private Name name; 
	private Period period;
	
	private List<AirForce>airForces; //holds air forces available
	private AirForce airForce; //NEEDED????????????? ++++++++++++++++++++++++++++
	
	enum Name{
		BATTLE_OF_BRITAIN, //Britain v Germany
		GUADALCANAL, //USA v Japan
		STALINGRAD, //Russia v Germany
		ASSAULT_ON_THE_REICH, //Britain & USA v Germany
		KURSK, //Russia v Germany
		THE_ITALIAN_CAMPAIGN, //Britain & USA v Germany
		DEFENCE_OF_THE_HOME_ISLANDS; //USA v Japan
	}
	
	/*
	enum AirForce{
		RAF, //Britain
		LUFTWAFFE, //Germany
		USAAF, //America
		VVS, //Russia
		IJAAF; //Japan
	}
	*/
	
	//-----------------------------------------
	//getters:
	
	/*
	public getName{
		
	}
	*/
	//-----------------------------------------
	
	
	//constructor:
	private HistoricEvent() {
		System.out.println("HistoricEvent constructed");
	}

	@Override
	public String toString() {
		return "HistoricEvent [name=" + name + ", period=" + period + ", airForces=" + airForces + "]";
	}
	
	//builder class:
	static class HistoricEventBuilder {
		
		private HistoricEvent historicEvent = new HistoricEvent();
		
		public HistoricEventBuilder setName(Name name) {
			historicEvent.name = name;
			setAirForces(); //set available air forces 
			return this;
		}
		
		//create a list of available air forces:
		private void setAirForces() { 
			switch(historicEvent.name) {
			  case BATTLE_OF_BRITAIN:
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE); //Britain v Germany
				  break;
			  case GUADALCANAL:
				  historicEvent.airForces = Arrays.asList(AirForce.USAAF, AirForce.IJAAF); //USA v Japan
				  break;
			  case STALINGRAD:
				  historicEvent.airForces = Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE); //Russia v Germany
				  break;
			  case ASSAULT_ON_THE_REICH:
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE); //Britain & USA v Germany
				  break;
			  case KURSK:
				  historicEvent.airForces = Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE); //Russia v Germany 
				  break;
			  case THE_ITALIAN_CAMPAIGN:
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE); //Britain & USA v Germany
				  break;
			  case DEFENCE_OF_THE_HOME_ISLANDS:
				  historicEvent.airForces = Arrays.asList(AirForce.USAAF, AirForce.IJAAF); //USA v Japan
				  break;
			}
		}
		
		
		public HistoricEvent build() {
			return historicEvent;
		}
		
	}
	
	
	
	
	
	
}

