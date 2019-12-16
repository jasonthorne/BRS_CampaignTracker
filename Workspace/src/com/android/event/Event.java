package com.android.event;

import java.util.List;
import com.android.Period;
import com.android.event.EventData.EventName;
import com.android.AirForceData.AirForce;


public final class Event { 
	
	private final String name; //name of event
	private final List<AirForce>airForces; //air forces involved
	private final List<Period>periods; //periods of history covered
	private final String description; //description of event
	
	//constructor:
	private Event(String eventName) { 
		System.out.println("Event constructed");
		this.name = eventName; //name event
		this.airForces = EventData.getAirForces(name); //assign air forces
		this.periods = EventData.getPeriods(name); //assign periods
		this.description = EventData.getDescription(name); //assign description
		
		System.out.println("AIRFORCES: " + this.airForces); //+++++++++++++++TEST PRINT
		System.out.println("PERIODS: " + this.periods); //+++++++++++++++TEST PRINT
		System.out.println("DESCRIPTION: " + this.description); //+++++++++++++++TEST PRINT
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

