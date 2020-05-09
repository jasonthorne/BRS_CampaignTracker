package com.brs.events;

import com.brs.Insert;

public interface InsertEventData extends InsertEventAirforces{
	
	static final Insert INSERT_EVENT_DATA =()-> { 
		
		new Events().getEvents().forEach((event)->{
			//event.test();
			System.out.println(event.getName());
			//System.out.println(event.name);
			//.insert(this.getName) //this should be in the abstract class constructor 
			INSERT_EVENT_AIRFORCES.insert();//??????? HAVE INSERT BE NON FUNCTIONAL, and with overloaded insert methods!! so you can pass eventname and airforce list into this.
		});
	};
	
	

}
