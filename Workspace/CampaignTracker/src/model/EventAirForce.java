package model;

import java.util.List;

public class EventAirForce {
	
	///////////////private String eventName; //name of event
	private String airForceName; //name of air force
	private boolean hasHomeAdv; //if air force has home adv status
	private List<Plane>airForcePlanes; //planes available to air force
	
	private EventAirForce() {} //blank constructor
	
	//builder class:
	public static class EventAirForceBuilder {
		
		//create event sir force:
		private EventAirForce eventAirForce = new EventAirForce();
		
		//set event name:
		/*public EventAirForceBuilder setEventName(String name) {
			eventAirForce.eventName = name;
			return this;
		}*/
		
		//set air force name:
		public EventAirForceBuilder setAirForceName(String name) {
			eventAirForce.airForceName = name;
			return this;
		}
		
		//set home advantage status:
		public EventAirForceBuilder setHasHomeAdv(boolean bool) {
			eventAirForce.hasHomeAdv = bool;
			return this;
		}
		
		//return built event air force:
		public EventAirForce build() { return eventAirForce; } 
	}
	
	//getters:
	////////public String getEventName() { return eventName; }
	public String getAirForceName() { return airForceName; }
	public boolean getHasHomeAdv() { return hasHomeAdv; }
	
	@Override
	public String toString() {
		return "EventAirForce ["
					///+ "eventName=" + eventName
					+ /*",*/ "airForceName=" + airForceName
					+ ", hasHomeAdv=" + hasHomeAdv
				+ "]";
	}
	
}
