package com.brs.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Events { //+++++++++++++maybe have this be abstract, with a public static method for firing off insertEventData - maybe name this insertEventData??
	
	//list of every event, and it's pertaining data: 
	private final List<EventData>events = Arrays.asList(
			new BattleOfBritain(),
			new OperationBarbarossa(),
			new Guadalcanal(),
			new Stalingrad(),
			new AssaultOnTheReich(),
			new TheItalianCampaign(),
			new DefenceOfTheHomeIslands());
	
	protected List<EventData>getEvents() { return new ArrayList<EventData>(events); }
	
	/*
	 * static insert method here, which loops through all events in list and fires off necessary inserts +++++++++++ :) 
	 */
	
	/* ????????????????????????????
	protected static void insertEventData() {
		
		new Events().events.forEach((event)-> { //forEach event:
			
			InsertEventAirForces.insert(event.getName(), event.getAirForces()); //populate 'event_airforces'
			//populate 'event_periods':
			
		});
	}*/
	
	/*
	public Events(){
		/dd/System.out.println(new BattleOfBritain().getName());
	}*/
	
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	//available eventDatas:
	private static final List<EventData>EVENTS = Arrays.asList( //can be called in constructor????
			new BattleOfBritain());
	
	
	public static Deque<Period> getPeriods() {	//+++++++++++++++prob should be list!! 
		Set<Period>sortedPeriods = new TreeSet<Period>(); 
	
		EVENTS.forEach((event)->{
			sortedPeriods.addAll(event.getPeriods());
		});
	
		Deque<Period>sortedPeriodsDeque = new ArrayDeque<Period>(sortedPeriods);  
		return new ArrayDeque<Period>(Arrays.asList(sortedPeriodsDeque.pollFirst(), sortedPeriodsDeque.pollLast()));
		
		//could call insert periods here instead of in main!! ie: InvokePeriods(new Period(sortedPeriodsDeque.pollFirst().getPeriod()))
	}
	

	//======================
	private final List<EventData>eventDatas;
	
	public Events() {
		eventDatas = Arrays.asList( 
				new BattleOfBritain()
				);
	}
	
	//======================
		
	
	*/
	
}
