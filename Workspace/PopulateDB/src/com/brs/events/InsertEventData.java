package com.brs.events;

public interface InsertEventData {
	
	static void insert() { //++++++++++++This can probably just be in Events :P +++++++++++++++++++
		new Events().getEvents().forEach((event)-> { //forEach event:
			InsertEventAirForces.insert(event.getName(), event.getAirForces()); //populate 'event_airforces'
			InsertEventPeriods.insert(event.getName(), event.getStartPeriod(), event.getEndPeriod()); //populate 'event_periods':
		});
	}
	
}
