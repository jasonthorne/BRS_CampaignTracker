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

public class Events { 
	
	private final List<EventData>events = Arrays.asList(
			new BattleOfBritain());
	
	
	
	/*
	public Events(){
		//System.out.println(new BattleOfBritain().getName());
	}*/
	
	
	protected List<EventData>getEvents(){
		return new ArrayList<EventData>(events);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
