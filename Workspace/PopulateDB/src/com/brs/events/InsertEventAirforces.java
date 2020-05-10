package com.brs.events;

import java.util.List;

public interface InsertEventAirforces {
	
	static void insert(Event event, List<EventAirForce>airForces) { 
			
			System.out.println("INSERT_EVENT_AIRFORCES be here!");
			System.out.println(event);
			System.out.println(airForces);
	};

}
