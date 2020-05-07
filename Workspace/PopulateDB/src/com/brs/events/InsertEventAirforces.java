package com.brs.events;

import com.brs.Insert;

public interface InsertEventAirforces {
	
	static final Insert INSERT_EVENT_AIRFORCES =()-> { //this should be a consumer, as it needs passed somevalues. OR A BIConsumer!!
			
			System.out.println("INSERT_EVENT_AIRFORCES be here!");
			
	};

}
