package com.brs;

import java.util.Arrays;
import java.util.List;

import com.brs.blocks.Block;
import com.brs.blocks.InsertBlocks;
import com.brs.periods.InsertPeriods;
import com.brs.years.InsertYears;
import com.brs.years.Year;
import com.brs.AirForceName;

public class Main {
	
	public static void main(String[] args) {
		
		
	
		// each county has its own class,. with - airforce name, planes, events (if home adv or not)
		
		/*
		Plane plane1 = new Plane(Model.SPITFIRE_II, AirForce.RAF, Arrays.asList(
				new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT),
				new PeriodStatus(new Period(Block.LATE, Year.FORTY), Status.AUTO)));*/
		
		List<Plane>planes = Arrays.asList(
				new Plane(Model.SPITFIRE_II, AirForceName.RAF, Arrays.asList(
						new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT),
						new PeriodStatus(new Period(Block.LATE, Year.FORTY), Status.AUTO))),
				new Plane(Model.HURRICANE_I, AirForceName.RAF, Arrays.asList(
						new PeriodStatus(new Period(Block.EARLY, Year.FORTY), Status.AUTO),
						new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT)))
		);
		
		
		/*
		 * https://www.baeldung.com/java-connection-pooling
		*/
		
		
		/*
		insertYear.accept(connection, Year.FORTY_TWO); //should just be passing in yearS (list of all years) here into PREDICATE instead, and letting insertYear esatblish the connection
		insertYear.accept(connection, Year.FORTY_THREE);
		*/
		System.out.println("==================");
		System.out.println(" INSERTING DATA:");
		
		//add blocks to DB:
		//InsertBlocks.insert();
		
		//add years to DB:
		//InsertYears.insert();
		
		//add periods to DB:
		InsertPeriods.insert();
		//InsertYears.insertYear(connection, Year.FORTY_ONE);
		
		
	}
	

}
