package com.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Period{
	
	//Block values:
	public enum Block{
		EARLY("Early"), 
		MID("Mid"), 
		LATE("Late");
		private String block; //name of chosen block
		private Block(String block) { 
			this.block = block; //constructor assigns name of chosen block
		}
		@Override 
		public String toString() { 
			return block; //return chosen block
		} 
	}
	
	//Year values:
	public enum Year{
		FORTY("1940"),
		FORTY_ONE("1941"),
		FORTY_TWO("1942"),
		FORTY_THREE("1943"),
		FORTY_FOUR("1944"),
		FORTY_FIVE("1945");
		private String year; //name of chosen year
		private Year(String year) { 
			this.year = year; //constructor assigns name of chosen year
		} 
		@Override 
		public String toString() {
			return year; //return chosen year
		} 
	}
	
	private final Block block;
	private final Year year;
	
	//constructor:
	public Period(Block block, Year year){ 
		this.year = year;
		this.block = block;
	}

	@Override
	public String toString() {
		return "Period: [" + block + " " + year + "]";
	}
	
	//getPeriods() vars:
	private final static List<Block>blocks = Arrays.asList(Block.values()); //list of all Block values
	private final static List<Year>years = Arrays.asList(Year.values()); //list of all Year values
	private static List<Period>periods; //list for holding range of Periods
	private static ListIterator<Block>blocksIterator; //blocks iterator
	private static ListIterator<Year>yearsIterator; //years iterator
	private static Year currYear; //holds year values
	private static Block currBlock; //holds block values
	private static boolean canAdd; //flag for adding values
	
	//creates and returns a list of a range of Periods, based upon the Periods provided to it
	public static List<Period>getPeriods(Period first, Period last){
		
		periods = new ArrayList<Period>(); //(re)set periods list
		yearsIterator = years.listIterator(); //(re)set years iterator
		canAdd = false; //(re)set canAdd
		
		outerWhile: 
		while(yearsIterator.hasNext()){ //loop through years
			
			blocksIterator = blocks.listIterator(); //(re)set blocks iterator
			currYear = yearsIterator.next(); //advance to next year
			
			while(blocksIterator.hasNext()) { //loop through blocks
				
				currBlock = blocksIterator.next(); //advance to next block
			
				//if found start date, allow adding of values:
				if(currBlock.equals(first.block) && currYear.equals(first.year)) { canAdd = true; }
				
				if(canAdd){ 
					//create a Period with current values, and add to list of periods:
					periods.add(new Period(currBlock, currYear)); 
					//stop when final target Period is added:
					if(currBlock.equals(last.block) && currYear.equals(last.year)) { break outerWhile; }
				}
			}
		}
		return periods; //return list
	}
	
}
