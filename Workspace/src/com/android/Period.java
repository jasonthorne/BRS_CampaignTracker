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
		NINETEEN_FORTY("1940"),
		NINETEEN_FORTY_ONE("1941"),
		NINETEEN_FORTY_TWO("1942"),
		NINETEEN_FORTY_THREE("1943"),
		NINETEEN_FORTY_FOUR("1944"),
		NINETEEN_FORTY_FIVE("1945");
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
	
	
	//----------------------------------------------
	private final static List<Block>blocks = Arrays.asList(Block.values());
	private final static List<Year>years = Arrays.asList(Year.values());
	private static List<Period>periods; // = new ArrayList<Period>();
	private static ListIterator<Block>blocksIterator; 
	private static ListIterator<Year>yearsIterator;
	private static Year currYear;
	private static Block currBlock;
	private static boolean canAdd = false;
	
	public static List<Period>getPeriods(Period first, Period last){
		
		periods = new ArrayList<Period>();
		yearsIterator = years.listIterator(); //set years iterator
		
		//loop through block and year lists and grab relevant periods ++++++++++++++++++
		while (yearsIterator.hasNext()) { 
			
			blocksIterator = blocks.listIterator(); //(re)set blocks iterator
			currYear = yearsIterator.next(); //move year
			
			while(blocksIterator.hasNext()) {
				currBlock = blocksIterator.next(); //move block
				System.out.println("current block is: " + currBlock);
				System.out.println("current year is: " + currYear);
				
				if(currBlock.equals(first.block) && currYear.equals(first.year)) { //if found start date
					canAdd = true;
					System.out.println("first date is: " + currBlock + " " + currYear);
				}
				
				//add values to list of Periods
				if(canAdd){ periods.add(new Period(currBlock, currYear));}
				
				//if hit last date:
				if((currBlock.equals(last.block)) && (currYear.equals(last.year))) {
					System.out.println("last date is: " + currBlock + " " + currYear);
					canAdd = false;
					return periods;
				}
			}
		}
		
		return null;
	}
	
	/*
	if(blocksIterator.hasNext()) {
		currBlock = blocksIterator.next(); //move to next block
		System.out.println("current block is: " + currBlock);
		System.out.println("current year is: " + currYear);
		
		
	}else {
		blocksIterator = blocks.listIterator(); //reset blocks
		currYear = yearsIterator.next(); //move to next year
		
	}
	*/
	
	//----------------------------------------------
		
	
	
}
