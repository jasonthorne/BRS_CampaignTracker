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
		BATTLE_OF_BRITAIN("Battle of Britain"), //Britain v Germany
		GUADALCANAL("Guadalcanal"), //USA v Japan
		STALINGRAD("Stalingrad"), //Russia v Germany
		ASSAULT_ON_THE_REICH("Assault on the Reich"), //Britain & USA v Germany
		KURSK("Kursk"), //Russia v Germany
		THE_ITALIAN_CAMPAIGN("The Italian Campaign"), //Britain & USA v Germany
		DEFENCE_OF_THE_HOME_ISLANDS("Defence of the home islands"); //USA v Japan
		
		private String name; //name of chosen historic event
		private Name(String name) { //constructor
			this.name = name;
		}
		
		/*
		@Override //override toString:
		public String toString() {
			return name;
		}
		*/
		
		public String getName() {
			return name;
		}
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
	
	
	public Name getName(){
		return this.name;
	}
	
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
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE); //Britain & Germany
				  break;
			  case GUADALCANAL:
				  historicEvent.airForces = Arrays.asList(AirForce.USAAF, AirForce.IJAAF); //USA & Japan
				  break;
			  case STALINGRAD:
				  historicEvent.airForces = Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE); //Russia & Germany
				  break;
			  case ASSAULT_ON_THE_REICH:
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE); //Britain, USA & Germany
				  break;
			  case KURSK:
				  historicEvent.airForces = Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE); //Russia & Germany 
				  break;
			  case THE_ITALIAN_CAMPAIGN:
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE); //Britain, USA & Germany
				  break;
			  case DEFENCE_OF_THE_HOME_ISLANDS:
				  historicEvent.airForces = Arrays.asList(AirForce.USAAF, AirForce.IJAAF); //USA & Japan
				  break;
			}
		}
		
		
		public HistoricEvent build() {
			return historicEvent;
		}
		
	}
	
	
	
	
	
	
}

