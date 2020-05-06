package com.brs.events;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.brs.Insert;

public interface InsertEventData {
	
	/*
	static final Insert INSERT_EVENT_DATA =()-> { //SHOULD MAYBE BE A COSUMER INSTEAD
		
		new Events().getEvents().forEach((event)->{
			
		});
	};*/
	
	static final Consumer<List<EventData>> INSERT_EVENT_DATA =(new Events().getEvents())-> { //SHOULD MAYBE BE A COSUMER INSTEAD
		
		
	};

}
