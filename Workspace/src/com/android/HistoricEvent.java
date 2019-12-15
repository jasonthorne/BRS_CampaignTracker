package com.android;

import java.util.Arrays;
import java.util.List;

import com.android.Period.Block;
import com.android.Period.Year;


public final class HistoricEvent {
	
	/////////private Name name; 
	
	private final String name;
	//////////private Period period;

	private final List<Period>periods; //holds periods of history pertaining to historic event
	
	private final List<AirForce>airForces; //holds air forces available
	
	public enum EventName{
		
		BATTLE_OF_BRITAIN(
				"Battle of Britain", 
				Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE), 
				Period.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE))), 
		OPERATION_BARBAROSSA(
				"Operation Barbarossa",
				Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE),
				Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_ONE))),
		GUADALCANAL(
				"Guadalcanal",
				Arrays.asList(AirForce.USAAF, AirForce.IJAAF),
				Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE))), 
		STALINGRAD(
				"Stalingrad",
				Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE),
				Period.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE))), 
		ASSAULT_ON_THE_REICH(
				"Assault on the Reich",
				Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE),
				Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.EARLY, Year.FORTY_FIVE))),
		THE_ITALIAN_CAMPAIGN(
				"The Italian Campaign",
				Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE),
				Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR))),
		DEFENCE_OF_THE_HOME_ISLANDS(
				"Defence of the home islands",
				Arrays.asList(AirForce.USAAF, AirForce.IJAAF),
				Period.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE)));
		
		/*
		BATTLE_OF_BRITAIN("Battle of Britain"), 
		OPERATION_BARBAROSSA("Operation Barbarossa"), 
		GUADALCANAL("Guadalcanal"), 
		STALINGRAD("Stalingrad"), 
		ASSAULT_ON_THE_REICH("Assault on the Reich"),  
		THE_ITALIAN_CAMPAIGN("The Italian Campaign"), 
		DEFENCE_OF_THE_HOME_ISLANDS("Defence of the home islands");
		*/
		
		private String name; //name of chosen historic event
		private List<AirForce>airForces; //air forces involved
		private List<Period>periods; //periods of history covered
		//private String description; //=====================================
		
		/*
		private Name(String name) { //constructor
			this.name = name;
		}
		*/
		//constructor
		private EventName(String name, List<AirForce>airForces, List<Period>periods) { 
			this.name = name;
			this.airForces = airForces;
			this.periods = periods;
			//this.description = getDescription(name); //===================================
		}
		
		@Override //override toString:
		public String toString() {
			return name;
		}
		
		//---------------------
		/*
		public List<AirForce> getAirForces(){
			return airForces;
		}*/
	}
	
	
	//-----------------------------------------
	//getters:
	
	public String getName(){ //???????????????????????NEEDED NOW WE DONT HAVE AN INSTANCE OF THE ENUM???
		return name;
	}
	
	//-----------------------------------------

	/*
	//constructor:
	private HistoricEvent() {
		System.out.println("HistoricEvent constructed");
	}
	*/
	
	//constructor:
	private HistoricEvent(EventName eventName) {
		System.out.println("NEW HistoricEvent constructed");
		this.name = eventName.name; //??????????????
		this.airForces = eventName.airForces;
		this.periods = eventName.periods;
		System.out.println("Periods are: " + this.periods);
		System.out.println(this.name);
	}

	@Override
	public String toString() {
		//return "HistoricEvent [name=" + name + ", period=" + period + ", airForces=" + airForces + "]";
		return "HistoricEvent [name=" + name + ", airForces=" + airForces + "]";
	}
	
	//builder class:
	static class HistoricEventBuilder {
		
		//private HistoricEvent historicEvent = new HistoricEvent();
		private EventName eventName;
		
		public HistoricEventBuilder setEventName(EventName eventName) { //+++++++++++++++++change to setValues
			//historicEvent.name = name; //change to setName
			this.eventName = eventName; //change to setName
			//SWICH WOULD HAVE TO BE HERE!! - for chosing  which event name to pick.
			//take in name as string, use switch in setName above to set enum.
			//---------------
			//set airforces
			//setAirForces();
			//historicEvent.airForces = name.airForces;
			//set periods
			//set descriptions
			////////////////setPeriodsAndAirForces(); //set periods of history and available air forces
			return this;
		}
		
		/*
		private void setAirForces() {
			historicEvent.airForces = name.airForces;
		}
		*/
		
		/*
		private void setPeriodsAndAirForces() {
			switch(historicEvent.name) {
			  case BATTLE_OF_BRITAIN: //Britain & Germany. Mid 1940 - Mid 1941.
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.LUFTWAFFE); 
				  historicEvent.periods = Period.getPeriods(new Period(Block.MID, Year.FORTY), new Period(Block.MID, Year.FORTY_ONE));
				  System.out.println("BATTLE_OF_BRITAIN periods: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case OPERATION_BARBAROSSA: //Russia & Germany. Mid 1941 - Late 1941.
				  historicEvent.airForces = Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE); 
				  historicEvent.periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_ONE), new Period(Block.LATE, Year.FORTY_ONE));
				  System.out.println("OPERATION_BARBAROSSA periods: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case GUADALCANAL: //USA & Japan. Mid 1942 - Early 1943.
				  historicEvent.airForces = Arrays.asList(AirForce.USAAF, AirForce.IJAAF); 
				  historicEvent.periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE));
				  System.out.println("GUADALCANAL periods: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case STALINGRAD: //Russia & Germany. Late 1942 - Early 1943.
				  historicEvent.airForces = Arrays.asList(AirForce.VVS, AirForce.LUFTWAFFE);
				  historicEvent.periods = Period.getPeriods(new Period(Block.LATE, Year.FORTY_TWO), new Period(Block.EARLY, Year.FORTY_THREE));
				  System.out.println("STALINGRAD periods: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case ASSAULT_ON_THE_REICH: //Britain, USA & Germany. Mid 1943 - Early 1945.
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE); 
				  historicEvent.periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.EARLY, Year.FORTY_FIVE));
				  System.out.println("ASSAULT_ON_THE_REICH periods: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case THE_ITALIAN_CAMPAIGN: //Britain, USA & Germany. Mid 1943 - Mid 1944.
				  historicEvent.airForces = Arrays.asList(AirForce.RAF, AirForce.USAAF, AirForce.LUFTWAFFE); 
				  historicEvent.periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_THREE), new Period(Block.MID, Year.FORTY_FOUR));
				  System.out.println("THE_ITALIAN_CAMPAIGN periods: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			  case DEFENCE_OF_THE_HOME_ISLANDS: //USA & Japan. Mid 1944 - Mid 1945.
				  historicEvent.airForces = Arrays.asList(AirForce.USAAF, AirForce.IJAAF); 
				  historicEvent.periods = Period.getPeriods(new Period(Block.MID, Year.FORTY_FOUR), new Period(Block.MID, Year.FORTY_FIVE));
				  System.out.println("DEFENCE_OF_THE_HOME_ISLANDS periods: " + historicEvent.periods); //++++++++++++++++++++++++++++++++++++++TEST PRINT
				  break;
			}
		}*/
		

		public HistoricEvent build() {
			//return historicEvent;
			return new HistoricEvent(eventName);
		}
		
	}
	

}

/*
class Period{
	
}

*/