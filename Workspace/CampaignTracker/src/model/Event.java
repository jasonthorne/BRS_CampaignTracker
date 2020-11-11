package model;

import java.util.ArrayList;
import java.util.List;

public class Event {
	
	private String eventName; //name of event
	private Period startPeriod; //start period
	private Period endPeriod; //end period
	private EventAirForce eventAirForce; //an air force involved
	private List<EventAirForce>eventAirForces; //air forces involved
	
	private Event() {} //blank constructor
	
	//builder class:
	public static class EventBuilder {
		
		private Event event = new Event(); //create event
		
		//set name:
		public EventBuilder setEventName(String name) {
			event.eventName = name;
			return this;
		}
		
		//set start period:
		public EventBuilder setStartPeriod(Period period) {
			event.startPeriod = new Period(period.getBlock(), period.getYear());
			return this;
		}
		
		//set end period:
		public EventBuilder setEndPeriod(Period period) {
			event.endPeriod = new Period(period.getBlock(), period.getYear());
			return this;
		}
		
		//set air force involved:
		/*public EventBuilder setEventAirForce(EventAirForce eventAirForce) {
			event.eventAirForce = new EventAirForce.EventAirForceBuilder()
					.setAirForceName(eventAirForce.getAirForceName())
					.setHasHomeAdv(eventAirForce.getHasHomeAdv())
					.build();
			event.eventAirForce = eventAirForce;
			
			return this;
		}*/
		
		
		public EventBuilder setEventAirForce(EventAirForce eventAirForce) {
			/*event.eventAirForce = new EventAirForce.EventAirForceBuilder()
					.setAirForceName(eventAirForce.getAirForceName())
					.setHasHomeAdv(eventAirForce.getHasHomeAdv())
					.build();*/
			event.eventAirForce = eventAirForce;
			return this;
		}
		
		//set list of event air forces:
		public EventBuilder setEventAirForces(List<EventAirForce>eventAirForces) {
			event.eventAirForces = new ArrayList<EventAirForce>(eventAirForces);
			return this;
		}
		
		public Event build() { return event; } //return built event
	}
	
	//getters:
	public String getEventName() { return eventName; }
	
	//get start period:
	public Period getStartPeriod() {
		return new Period(startPeriod.getBlock(), startPeriod.getYear());
	}
	
	//get end period:
	public Period getEndPeriod() {
		return new Period(endPeriod.getBlock(), endPeriod.getYear());
	}
	
	
	//get event air force:
	public EventAirForce getEventAirForce() {
		
		/*
		return new EventAirForce.EventAirForceBuilder()
				.setAirForceName(eventAirForce.getAirForceName())
				.setHasHomeAdv(eventAirForce.getHasHomeAdv())
				.build();*/
		
		return eventAirForce;
	}
	
	//get event air forces:
	public List<EventAirForce> getEventAirForces() {
		return new ArrayList<EventAirForce>(eventAirForces);
	}

	@Override
	public String toString() {
		return "Event [name=" + eventName + ", startPeriod=" + startPeriod + ", endPeriod=" + endPeriod + ", eventAirForces="
				+ eventAirForces + "]";
	}
	
	

}