package com.android;

public class Period{
	
	private Block block;
	private Year year;
	
	enum Block{
		EARLY,
		MID,
		LATE;
	}
	
	enum Year{
		NINETEEN_FORTY,
		NINETEEN_FORTY_ONE,
		NINETEEN_FORTY_TWO,
		NINETEEN_FORTY_THREE,
		NINETEEN_FORTY_FOUR,
		NINETEEN_FORTY_FIVE;
	}
	
	Period(Block block, Year year){
		this.year = year;
		this.block = block;
	}

	
	@Override
	public String toString() {
		return "Period [block=" + block + ", year=" + year + "]";
	}
	
	
}
