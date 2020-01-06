package com.brs.period;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public interface PeriodsMaker {
	
	final static List<Block>BLOCKS = Arrays.asList(Block.values()); //all Block values (early, mid, late)
	final static List<Year>YEARS = Arrays.asList(Year.values()); //all Year values (1940 - 1945)
	
	//creates and returns a list of a range of Periods, based upon the Periods provided to it:
	public static List<Period>getPeriods(Period first, Period last){
		
		List<Period>periods = new ArrayList<Period>(); //holds a range of Periods
		ListIterator<Year>yearsIterator = YEARS.listIterator(); //years iterator
		ListIterator<Block>blocksIterator; //blocks iterator
		Year currYear; //holds year values
		Block currBlock; //holds block values
		boolean canAdd = false; //flag for adding values
		
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

}
