package com.brs.events;

import com.brs.Insert;

public interface InsertEventData extends InsertEventAirforces{
	
	static final Insert INSERT_EVENT_DATA =()-> { 
		
		new Events().getEvents().forEach((event)->{
			//event.test();
			System.out.println(event.getName());
			System.out.println(event.name);
			INSERT_EVENT_AIRFORCES.insert();//??????? HAVE INSERT BE NON FUNCTIONAL, andwith overloaded insert methods!! so you can pass eventname and airforce list into this.
		});
	};
	
	

}
