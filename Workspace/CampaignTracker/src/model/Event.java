package model;

import java.util.List;

public class Event {
	
	private String name; //name of event
	private EventPeriod eventPeriod; //periods covered
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
		
		//others....
		
		public Event build() { return event; } //return built event
	}
	
	//getters:
	public String getName() {return name; }

	
	@Override
	public String toString() {
		return "Event [eventName=" + name + "]";
	}
	
	

}