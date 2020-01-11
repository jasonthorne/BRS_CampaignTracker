package OldFiles;

import java.util.List;

import com.brs.airforce.AirForce;
import com.brs.airforce.AirForceName;
import com.brs.event.EventName;
//import com.brs.event.EventData.EventName;
import com.brs.period.Period;


public final class Event { //++++++ADD FINALS TO ALL OTHER CLASSES THHAT NEED IT (aren't extended.)
	
	private final String name; //name of event
	private final List<AirForceName>airForces; //air forces involved +++++++++++CREATE AIFORCE OBJECTS IN THIS LIST. these are then assigned to player when they pick an airforce from them.
	private final List<Period>periods; //periods of history covered
	////////private final String description; //description of event
	
	//=======================
	
	/*
	 * set airforces
	 * 
	 * set periods
	 * 
	 * 
	 * set description
	 * 
	 * set name
	 * 
	 * 
	 */
	
	
	//========================
	
	//constructor:
	private Event(String eventName) { 
		
		System.out.println("Event constructed");
		this.name = eventName; //name event
		this.airForces = EventData.getAirForces(name); //assign air forces
		this.periods = EventData.getPeriods(name); //assign periods
		///////this.description = EventData.getDescription(name); //assign description
		System.out.println("AIRFORCES: " + this.airForces); //+++++++++++++++TEST PRINT
		System.out.println("PERIODS: " + this.periods); //+++++++++++++++TEST PRINT
		//////////System.out.println("DESCRIPTION: " + this.description); //+++++++++++++++TEST PRINT
	}
	
	public String getName(){ return name; } //get name of event

	@Override
	public String toString() {
		return "Event [name=" + name + ", airForces=" + airForces + "]";
	}
	
	//builder class:
	public static final class EventBuilder {
		
		private String eventName; //chosen event name
		
		//set chosen event name:
		public EventBuilder setEventName(EventName eventName) { 
			this.eventName = eventName.toString(); 
			return this;
		}
		//build and return Event:
		public Event build() { return new Event(eventName); }
	}
	
}

