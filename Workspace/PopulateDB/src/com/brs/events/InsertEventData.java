package com.brs.events;

import com.brs.Insert;

public interface InsertEventData extends InsertEventAirforces{
	
	static void insert() { 
		new Events().getEvents().forEach((event)->{
			//event.test();
			
			System.out.println(event.getName());
			//System.out.println(event.);
			//.insert(this.getName) //this should be in the abstract class constructor 
			InsertEventAirforces.insert(event.getName(), event.getAirForces());//??????? HAVE INSERT BE NON FUNCTIONAL, and with overloaded insert methods!! so you can pass eventname and airforce list into this.
		});
	};
	
}
