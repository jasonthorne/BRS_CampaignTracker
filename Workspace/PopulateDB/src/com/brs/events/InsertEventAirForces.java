package com.brs.events;

import java.util.List;

public interface InsertEventAirForces {
	
	static void insert(Event event, List<EventAirForce>eventAirForces) {
		
		eventAirForces.forEach((airForce)->{ //forEach air force in eventAirForces:
			System.out.println(airForce);
		});
		
		System.out.println("INSERT_EVENT_AIRFORCES be here!");
		System.out.println(event);
		System.out.println(eventAirForces);
	};

}
