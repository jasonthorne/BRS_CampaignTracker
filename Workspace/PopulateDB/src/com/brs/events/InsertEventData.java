package com.brs.events;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.brs.Insert;

public interface InsertEventData extends InsertEventAirforces{
	
	static final Insert INSERT_EVENT_DATA =()-> { 
		
		new Events().getEvents().forEach((event)->{
			//event.test();
			System.out.println(event.getName());
			System.out.println(event.name);
			INSERT_EVENT_AIRFORCES.insert();//???????
		});
	};
	
	

}
