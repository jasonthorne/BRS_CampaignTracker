package com.brs;

import com.brs.Block;
import com.brs.Year;

public class Period {
	
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

}
