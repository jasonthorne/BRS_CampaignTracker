package com.brs;

import java.util.Arrays;
import java.util.List;

import com.brs.airforces.AirForce;
import com.brs.airforces.InsertAirForces;
import com.brs.blocks.Block;
import com.brs.blocks.InsertBlocks;
import com.brs.events.Events;
import com.brs.events.InsertEventData;
import com.brs.events.InsertEvents;
import com.brs.periods.InsertPeriods;
import com.brs.periods.Period;
import com.brs.planes.InsertPlanes;
import com.brs.planes.Plane;
import com.brs.periods.InsertPeriods;
import com.brs.statuses.InsertStatuses;
import com.brs.statuses.Status;
import com.brs.years.InsertYears;
import com.brs.years.Year;

public class Main implements InsertBlocks, InsertYears, InsertPeriods, InsertEvents, InsertAirForces, InsertPlanes, InsertStatuses, InsertEventData {
	
	public static void main(String[] args) {
		
		
	
		// each county has its own class,. with - airforce name, planes, events (if home adv or not)
		
		/*
		Plane_OLD plane1 = new Plane_OLD(Plane.SPITFIRE_II, AirForce.RAF, Arrays.asList(
				new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT),
				new PeriodStatus(new Period(Block.LATE, Year.FORTY), Status.AUTO)));*/
		
		List<Plane_OLD>plane_OLDs = Arrays.asList(
				new Plane_OLD(Plane.SPITFIRE_II, AirForce.RAF, Arrays.asList(
						new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT),
						new PeriodStatus(new Period(Block.LATE, Year.FORTY), Status.AUTO))),
				new Plane_OLD(Plane.HURRICANE_I, AirForce.RAF, Arrays.asList(
						new PeriodStatus(new Period(Block.EARLY, Year.FORTY), Status.AUTO),
						new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT)))
		);
		
		
		/*
		 * https://www.baeldung.com/java-connection-pooling
		*/
		
		//https://www.geeksforgeeks.org/functional-interfaces-java/ ++++++++++REMOVE :P
		
		
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
	
		
		InsertYears.insert();
		
		//return boolean of successfull insertion as out param, and then do select * query to prove and print to screen.
		//This means these wstatic methods can be predicates instead!! wooHoo!!
		//https://docs.microsoft.com/en-us/sql/connect/jdbc/using-a-stored-procedure-with-a-return-status?view=sql-server-ver15
		
		//add periods to DB:
		//INSERT_PERIODS.insert();
		
		//add events to DB:
		//INSERT_EVENTS.insert(); 
		
		//add air forces to DB:
		//INSERT_AIRFORCES.insert();
		
		//add planes to DB:
		//INSERT_PLANES.insert();
		
		//add statuses to DB:
		//INSERT_STATUSES.insert(); 
		
		//new Events();
		
		//INSERT_EVENT_DATA.insert();
		
		
		//System.out.println(Events.getPeriods());
	}
	

}
