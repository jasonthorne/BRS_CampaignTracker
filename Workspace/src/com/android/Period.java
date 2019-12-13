package com.android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Period{
	
	public enum Block{
		EARLY("Early"), 
		MID("Mid"), 
		LATE("Late");
		private String block; //name of chosen block
		private Block(String block) { //constructor
			this.block = block; //assign name of block
		}
		@Override //override toString
		public String toString() {
			return block; //return name of chosen block
		}
	}
	
	public enum Year{
		FORTY("1940"),
		FORTY_ONE("1941"),
		FORTY_TWO("1942"),
		FORTY_THREE("1943"),
		FORTY_FOUR("1944"),
		FORTY_FIVE("1945");
		private String year; //name of chosen year
		private Year(String year) { //constructor
			this.year = year; //assign name of year
		}
		@Override //override toString
		public String toString() {
			return year; //return name of chosen year
		}
	}
	
	private Block block;
	private Year year;
	
	//constructor:
	public Period(Block block, Year year){ 
		this.year = year;
		this.block = block;
	}

	@Override
	public String toString() {
		return "Period: [" + block + " " + year + "]";
	}
	
	//creates and returns a list of Periods from the range of Periods given to it
	public static List<Period>getPeriods(Period first, Period last){
		
		final List<Block>blocks = Arrays.asList(Block.values()); //list of all Block values
		final List<Year>years = Arrays.asList(Year.values()); //list of all Year values
		List<Period>periods = new ArrayList<Period>(); //list for holding range of Periods
		ListIterator<Block>blocksIterator; //blocks iterator ref
		ListIterator<Year>yearsIterator = years.listIterator(); //years iterator
		Year currYear; //holds year values
		Block currBlock; //holds block values
		boolean canAdd = false; //flag for adding values
	
		while (yearsIterator.hasNext()){ //loop through years
			
			blocksIterator = blocks.listIterator(); //(re)set blocks iterator
			currYear = yearsIterator.next(); //move year
			
			while(blocksIterator.hasNext()) { //loop through blocks
				
				currBlock = blocksIterator.next(); //move block
			
				//if found start date, allow adding of values
				if(currBlock.equals(first.block) && currYear.equals(first.year)) {canAdd = true;}
				
				//create a Period with current values, and add to list of periods
				if(canAdd){ periods.add(new Period(currBlock, currYear));}
				
				//return list of periods once final target Period has been added
				if((currBlock.equals(last.block)) && (currYear.equals(last.year))) { return periods; }
			}
		}
		
		return null;
	}
	
}
