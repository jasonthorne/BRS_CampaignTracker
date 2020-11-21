package com.brs.periods;

import com.brs.blocks.Block;
import com.brs.years.Year;

public class Period implements Comparable <Period> {	//++++++++++++THIS WHOLE CLASS MIGHT NOT BE NEEDED!! :P
	
	private final Block block; //early, mid, late
	private final Year year; //1940 - 1945
	
	//constructor:
	public Period(Block block, Year year){ 
		this.year = year;
		this.block = block;
	}
	
	//getters:
	public Block getBlock() { return block; }
	public Year getYear() { return year; }

	@Override
	public String toString() {
		return "Period [block=" + block + ", year=" + year + "]";
	}

	@Override	
	public int compareTo(Period period2) {	//compare periods ==================MIGHT NOT BE NEEDED!!
		
		//ordinal values of years: 
		Integer year1 = this.getYear().ordinal(); 
		Integer year2 = period2.getYear().ordinal(); 
		
		//ordinal values of blocks:
		Integer block1 = this.getBlock().ordinal(); 
		Integer block2 = period2.getBlock().ordinal(); 
		
		//if years are the same, order by blocks:
		if(year1.equals(year2)) { return block1.compareTo(block2); }
		
		//if years aren't the same, order by years:
		if(!(year1.equals(year2))) { return year1.compareTo(year2); } 
			
		return 0; //same periods
	}
	
}
