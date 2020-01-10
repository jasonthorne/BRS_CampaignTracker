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
	public Block getBlock() { return block; }
	public Year getYear() { return year; }

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
}
