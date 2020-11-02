package model;

import java.util.List;

public class Event {
	
	private String name; //name of event
	
	/////private Period startPeriod; //start period
	///////private Period endPeriod; //end period
	
	private String startPeriod; //start period
	private String endPeriod; //end period
	
	private List<EventAirForce>eventAirforces; //air forces involved
	
	private Event() {} //blank constructor
	
	//builder class:
	public static class EventBuilder{
		
		private Event event = new Event(); //create event
		
		//set name:
		public EventBuilder setName(String name) {
			event.name = name;
			return this;
		}
		
		//set start period:
		public EventBuilder setStartPeriod(String period) {
			event.startPeriod = period;
			return this;
		}
		
		//set end period:
		public EventBuilder setEndPeriod(String period) {
			event.endPeriod = period;
			return this;
		}
		
		public Event build() { return event; } //return built event
	}
	
	//getters:
	public String getName() { return name; }
	public String getStartPeriod() { return startPeriod; }
	public String getEndPeriod() { return endPeriod; }

	
	@Override
	public String toString() {
		return "Event [eventName=" + name + "]";
	}
	
	

}