package com.brs.events;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import com.brs.blocks.Block;
import com.brs.periods.Period;
import com.brs.years.Year;

public class Events { //change to insertEvents?? (prob!!!)
	
	//available events:
	private static final List<Event>EVENTS = Arrays.asList( //can be called in constructor????
			new BattleOfBritain(),
			new Stalingrad());
	
	
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
	private final List<Event>events;
	
	public Events() {
		
		events = Arrays.asList( 
				new BattleOfBritain(),
				new Stalingrad());
	}
	
	
	
	//======================
		
	
	/*
	 * getPeriods: for each start and end period in list of events, find the LOWEST period, and the HIGHEST period,
	 * 
	 * add them to Periods class, to be used for insertion to DB
	 */
	//////static getPeriods
	
	
}
