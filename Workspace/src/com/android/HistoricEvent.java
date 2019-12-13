package com.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.android.Period.Block;
import com.android.Period.Year;
import com.android.Period.*;

public class HistoricEvent {
	
	private Name name; 
	//////////private Period period;
	//////private List<Period>periods; //holds periods of history pertaining to historic event
	private List<Period>periods; //holds periods of history pertaining to historic event
	
	private List<AirForce>airForces; //holds air forces available

	public enum Name{
		BATTLE_OF_BRITAIN("Battle of Britain"), //Britain & Germany
		GUADALCANAL("Guadalcanal"), //USA & Japan
		STALINGRAD("Stalingrad"), //Russia & Germany
		ASSAULT_ON_THE_REICH("Assault on the Reich"), //Britain, USA & Germany
		KURSK("Kursk"), //Russia & Germany
		THE_ITALIAN_CAMPAIGN("The Italian Campaign"), //Britain, USA & Germany
		DEFENCE_OF_THE_HOME_ISLANDS("Defence of the home islands"); //USA & Japan
		
		private String name; //name of chosen historic event
		private Name(String name) { //constructor
			this.name = name;
		}
		
		@Override //override toString:
		public String toString() {
			return name;
		}
	}
	
	
	//-----------------------------------------
	//getters:
	
	/*
	public Name getName(){
		return this.name;
	}
	*/
	public String getName(){
		return name.toString();
	}
	
	//-----------------------------------------
	
	//constructor:
	private HistoricEvent() {
		System.out.println("HistoricEvent constructed");
	}

	@Override
	public String toString() {
		//return "HistoricEvent [name=" + name + ", period=" + period + ", airForces=" + airForces + "]";
		return "HistoricEvent [name=" + name + ", airForces=" + airForces + "]";
	}
	
	//builder class:
	static class HistoricEventBuilder {
		
		private HistoricEvent historicEvent = new HistoricEvent();
		
		public HistoricEventBuilder setName(Name name) {
			historicEvent.name = name;
			setPeriodsAndAirForces(); //set periods of history and available air forces
			return this;
		}
		
		private void setPeriodsAndAirForces() {
			switch(historicEvent.name) {
			  case BATTLE_OF_BRITAIN:
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE); //Britain & Germany
				  historicEvent.periods = Arrays.asList( //+++++++++++++++++++++++++++++Look into using some sort of queue for these maybe!! Or some other collection anyway :P
						  	new Period(Block.MID, Year.NINETEEN_FORTY), 
						  	new Period(Block.LATE, Year.NINETEEN_FORTY),
						  	new Period(Block.EARLY, Year.NINETEEN_FORTY_ONE)); 
				  System.out.println("Periods test: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  
				  Period.getPeriods(new Period(Block.MID, Year.NINETEEN_FORTY), new Period(Block.EARLY, Year.NINETEEN_FORTY_ONE));
				  
				  break;
			  case GUADALCANAL:
				  historicEvent.airForces = Arrays.asList(AirForce.USAAF, AirForce.IJAAF); //USA & Japan
				  historicEvent.periods = Arrays.asList( 
						  	new Period(Block.MID, Year.NINETEEN_FORTY_TWO),
						  	new Period(Block.LATE, Year.NINETEEN_FORTY_TWO),
						  	new Period(Block.EARLY, Year.NINETEEN_FORTY_THREE)); 
				  System.out.println("Periods test: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case STALINGRAD: //????????????????????
				  historicEvent.airForces = Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE); //Russia & Germany
				  historicEvent.periods = Arrays.asList( 
						  	new Period(Block.MID, Year.NINETEEN_FORTY), 
						  	new Period(Block.LATE, Year.NINETEEN_FORTY),
						  	new Period(Block.EARLY, Year.NINETEEN_FORTY_ONE)); 
				  System.out.println("Periods test: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case ASSAULT_ON_THE_REICH:
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE); //Britain, USA & Germany
				  historicEvent.periods = Arrays.asList( 
						  	new Period(Block.MID, Year.NINETEEN_FORTY_THREE), 
						  	new Period(Block.LATE, Year.NINETEEN_FORTY_THREE),
						  	new Period(Block.EARLY, Year.NINETEEN_FORTY_FOUR),
						  	new Period(Block.MID, Year.NINETEEN_FORTY_FOUR),
						  	new Period(Block.LATE, Year.NINETEEN_FORTY_FOUR),
						  	new Period(Block.EARLY, Year.NINETEEN_FORTY_FIVE)); 
				  System.out.println("Periods test: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case KURSK: //??????????????????????????
				  historicEvent.airForces = Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE); //Russia & Germany
				  historicEvent.periods = Arrays.asList(
						  	new Period(Block.MID, Year.NINETEEN_FORTY), 
						  	new Period(Block.LATE, Year.NINETEEN_FORTY),
						  	new Period(Block.EARLY, Year.NINETEEN_FORTY_ONE)); 
				  System.out.println("Periods test: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case THE_ITALIAN_CAMPAIGN:
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE); //Britain, USA & Germany
				  historicEvent.periods = Arrays.asList( 
						  	new Period(Block.MID, Year.NINETEEN_FORTY_THREE), 
						  	new Period(Block.LATE, Year.NINETEEN_FORTY_THREE),
						  	new Period(Block.EARLY, Year.NINETEEN_FORTY_FOUR),
						  	new Period(Block.MID, Year.NINETEEN_FORTY_FOUR)); 
				  System.out.println("Periods test: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case DEFENCE_OF_THE_HOME_ISLANDS:
				  historicEvent.airForces = Arrays.asList(AirForce.USAAF, AirForce.IJAAF); //USA & Japan
				  historicEvent.periods = Arrays.asList( 
						  	new Period(Block.MID, Year.NINETEEN_FORTY_FOUR), 
						  	new Period(Block.LATE, Year.NINETEEN_FORTY_FOUR),
						  	new Period(Block.EARLY, Year.NINETEEN_FORTY_FIVE),
						  	new Period(Block.MID, Year.NINETEEN_FORTY_FIVE)); 
				  System.out.println("Periods test: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			}
		}
		

		public HistoricEvent build() {
			return historicEvent;
		}
		
	}
	

}

/*
class Period{
	
}

*/