package com.brs.events;

import com.brs.blocks.Block;
import com.brs.periods.Period;
import com.brs.years.Year;

public class Stalingrad implements Event{

	@Override
	public Period getTest() {
		return new Period(Block.MID, Year.FORTY);
	}
	
}


