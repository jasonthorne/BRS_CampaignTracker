package com.brs;

import java.util.Arrays;
import java.util.List;

import com.brs.airforces.AirForce;
import com.brs.airforces.InsertAirForces;
import com.brs.blocks.Block;
import com.brs.blocks.InsertBlocks;
import com.brs.events.Events__OLD;
import com.brs.events.InsertEvents;
import com.brs.periods.InsertPeriods;
import com.brs.periods.Period;
import com.brs.periods.InsertPeriods;
import com.brs.statuses.InsertStatuses;
import com.brs.statuses.Status;
import com.brs.years.InsertYears;
import com.brs.years.Year;

public class Main implements InsertBlocks, InsertYears, InsertPeriods, InsertEvents, InsertAirForces, InsertStatuses {
	
	public static void main(String[] args) {
		
		
	
		// each county has its own class,. with - airforce name, planes, events (if home adv or not)
		
		/*
		Plane plane1 = new Plane(Model.SPITFIRE_II, AirForce.RAF, Arrays.asList(
				new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT),
				new PeriodStatus(new Period(Block.LATE, Year.FORTY), Status.AUTO)));*/
		
		List<Plane>planes = Arrays.asList(
				new Plane(Model.SPITFIRE_II, AirForce.RAF, Arrays.asList(
						new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT),
						new PeriodStatus(new Period(Block.LATE, Year.FORTY), Status.AUTO))),
				new Plane(Model.HURRICANE_I, AirForce.RAF, Arrays.asList(
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
		//INSERT_BLOCKS.insert();
		
		//add years to DB:
		//INSERT_YEARS.insert();
		
		//add periods to DB:
		//INSERT_PERIODS.insert();
		
		//add events to DB:
		//INSERT_EVENTS.insert(); 
		
		//add air forces to DB:
		INSERT_AIRFORCES.insert();
		
		//add statuses to DB:
		//INSERT_STATUSES.insert(); 
		
		//new Events__OLD().test();
		
		//System.out.println(Events__OLD.getPeriods());
	}
	

}
