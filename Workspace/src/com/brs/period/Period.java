package com.brs.period;

import com.brs.period.Block;
import com.brs.period.Year;

public class Period {
	
	private final Block block; //early, mid, late
	private final Year year; //1940 - 1945
	
	//constructor:
	public Period(Block block, Year year){ 
		this.year = year;
		this.block = block;
	}
	
	//getters:
	public Block getBlock() {return block;}
	public Year getYear() {return year;}

	@Override 
	public String toString() { return "Period: [" + block + " " + year + "]"; }
	
	@Override //for comparison against other Periods. See getAvailableModels() in AirForce.java:
	public int hashCode() { 
		final int prime = 31;
		int result = 1;
		result = prime * result + ((block == null) ? 0 : block.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override //for comparison against other Periods. See getAvailableModels() in AirForce.java:
	public boolean equals(Object obj) { 
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Period other = (Period) obj;
		if (block != other.block)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	/*
	//getPeriods vars:
	private final static List<Block>BLOCKS = Arrays.asList(Block.values()); //all Block values (early, mid, late)
	private final static List<Year>YEARS = Arrays.asList(Year.values()); //all Year values (1940 - 1945)
	private static List<Period>periods; //holds a range of Periods
	private static ListIterator<Block>blocksIterator; //blocks iterator
	private static ListIterator<Year>yearsIterator; //years iterator
	private static Year currYear; //holds year values
	private static Block currBlock; //holds block values
	private static boolean canAdd; //flag for adding values
	
	//creates and returns a list of a range of Periods, based upon the Periods provided to it:
	public static List<Period>getPeriods(Period first, Period last){
		
		periods = new ArrayList<Period>(); //(re)set periods list
		yearsIterator = YEARS.listIterator(); //(re)set years iterator
		canAdd = false; //(re)set canAdd
		
		outerWhile: 
		while(yearsIterator.hasNext()){ //loop through years
			
			blocksIterator = BLOCKS.listIterator(); //(re)set blocks iterator
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
	*/
}
