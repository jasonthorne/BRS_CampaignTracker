package com.brs.events;

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
	
	private static Period earliestPeriod; //?????????
	private static Period latestPeriod; //?????????
	
	private static final List<Event>EVENTS = Arrays.asList(
			new BattleOfBritain(),
			new Stalingrad());
	
	static{
		
		EVENTS.forEach((event)->{
			
			//find earliest and latest period, then invoke insert periods (using that range)
			earliestPeriod = event.getTest();
		});
	}
	
	public List<Period> test2() {
		
		EVENTS.forEach((event)->{
			
			//find earliest and latest period, then invoke insert periods (using that range)
			earliestPeriod = event.getTest();
		});
		return null;
	}
	

	public void test() {
		System.out.println(earliestPeriod);
	
		//===========================================
		
				
		List<Period>periods = new ArrayList<>();
		Period p1 = new Period(Block.LATE, Year.FORTY_FIVE);
		Period p2 = new Period(Block.MID, Year.FORTY);
		Period p3 = new Period(Block.EARLY, Year.FORTY);
		Period p4 = new Period(Block.LATE, Year.FORTY_FIVE);
		Period p5 = new Period(Block.EARLY, Year.FORTY);
		
		System.out.println("p1.getYear().ordinal(): " + p1.getYear().ordinal());
		System.out.println("p2.getYear().ordinal(): " + p2.getYear().ordinal());
		
		System.out.println("p2.getBlock().ordinal(): " + p2.getBlock().ordinal());
		System.out.println("p3.getBlock().ordinal(): " + p3.getBlock().ordinal());
		
		periods.addAll(Arrays.asList(p1,p2, p3, p4, p5));
		System.out.println("periods: " + periods);
		
		Comparator<Period>comparePeriods = new Comparator<Period>() {

			@Override
			public int compare(Period period1, Period period2) {
			
				Integer year1 = period1.getYear().ordinal(); 
				Integer year2 = period2.getYear().ordinal(); 
				
				Integer block1 = period1.getBlock().ordinal(); 
				Integer block2 = period2.getBlock().ordinal(); 
				
				if(period1.equals(period2)) { //if same year and block (as per overridden equals method)
					System.out.println("p1 is same as p2: " + period1 + " " + period2);
					return 0;
				}
				
				 //if years are the same:
				if(year1.equals(year2)) {
					return block1.compareTo(block2); //order blocks using compare method
				}
					
				
				if(!(year1.equals(year2))) //if years arent the same:
					return year1.compareTo(year2); //order using compare method
				
				/*
				if(!(block1.equals(block2))) //if blocks arent the same:
					return block1.compareTo(block1); //order using compare method
				*/
				/*
				 * Integer weight1 = d1.getWeight();
				Integer weight2 = d2.getWeight();
				
				Integer height1 = d1.getHeight();
				Integer height2 = d2.getHeight();
				
				Integer id1 = d1.getId();
				Integer id2 = d2.getId();
				
				if(d1.equals(d2)) //equals method has been overriden in the duck class
					return 0;
				
				if(!(d1.getName().equals(d2.getName()))) //if names arent the same
					return d1.getName().compareTo(d2.getName()); //use compare method to sort them out! 
				
				if(!(weight1.equals(weight2))) //if weights arent the same
					return weight1.compareTo(weight2); //use compare method to sort them out! 
				
				if(!(height1.equals(height2))) //if heights arent the same
					return height1.compareTo(height2); //use compare method to sort them out! 
				
				//above checks mean they have to not have the same ids!
				return id1.compareTo(id2);
				 */
				
				return 0;
			}

			
			
		};
		
		//Collections.sort(periods, comparePeriods);
		Set<Period> sortedPeriods = new TreeSet<Period>(); 
		sortedPeriods.addAll(periods);
		
		System.out.println("NEW periods: " + periods);
		System.out.println("NEW sortedPeriods: " + sortedPeriods);
		
	
	/*
	 * getPeriods: for each start and end period in list of events, find the LOWEST period, and the HIGHEST period,
	 * 
	 * add them to Periods class, to be used for insertion to DB
	 */
	//////static getPeriods
	
	}
}
