package com.android;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Period{
	
	//----------------------------------------------
	//private final List<String>blocks = Arrays.asList("Early", "Mid", "Late");
	//private final List<String>years = Arrays.asList("1940", "1941", "1942", "1943", "1943", "1945");
	
	private final List<Block>blocks = Arrays.asList(Block.EARLY, Block.MID, Block.LATE);
	private final List<Year>years = Arrays.asList(
			Year.NINETEEN_FORTY, Year.NINETEEN_FORTY_ONE, Year.NINETEEN_FORTY_TWO, Year.NINETEEN_FORTY_THREE, Year.NINETEEN_FORTY_FOUR, Year.NINETEEN_FORTY_FIVE);
	
	private ListIterator<Block>blocksIterator; //= blocks.listIterator();
	private ListIterator<Year>yearsIterator; // = years.listIterator();
	
	
	//private constructor called using getList ??????????????????????????????????????
	public List<Period> getPeriods(Period startPeriod, Period endPeriod){
		blocksIterator = blocks.listIterator(); //instantiate blocks iterator
		yearsIterator = years.listIterator(); //instantiate years iterator
		
		//loop through block and year lists and grab relevant periods ++++++++++++++++++
		
		return null;
	}
	
	
	//----------------------------------------------
	
	private Block block;
	private Year year;
	
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
	
	//constructor:
	Period(Block block, Year year){ /////////Maybe should be private!!!!!!!
		this.year = year;
		this.block = block;
	}

	
	@Override
	public String toString() {
		return "Period: [" + block + " " + year + "]";
	}
	
	
}
