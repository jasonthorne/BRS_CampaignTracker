package com.brs.events;

import java.util.Arrays;
import java.util.List;

public class Events {
	
	//list of every event, and it's pertaining data: 
	private final List<EventData>events = Arrays.asList(
			new BattleOfBritain(),
			new OperationBarbarossa(),
			new Guadalcanal(),
			new Stalingrad(),
			new AssaultOnTheReich(),
			new TheItalianCampaign(),
			new DefenceOfTheHomeIslands());
	
	//insert data from each event:
	public static void insertEventData() {
		//forEach event:
		new Events().events.forEach((event)-> { 
			System.out.println(event.getName() + ":");
			InsertEventPeriods.insert(event.getName(), event.getStartPeriod(), event.getEndPeriod()); //populate 'event_periods':
			InsertEventAirForces.insert(event.getName(), event.getAirForces()); //populate 'event_airforces'
			System.out.println();
		});
	}
	
}