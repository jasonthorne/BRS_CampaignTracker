package com.brs;

import java.util.Arrays;
import java.util.List;

import com.brs.airforces.AirForce;
import com.brs.airforces.InsertAirForces;
import com.brs.airforces.SelectAirForces_TEST;
import com.brs.blocks.Block;
import com.brs.blocks.InsertBlocks;
import com.brs.events.Events;
import com.brs.events.InsertEvents;
import com.brs.periods.InsertPeriods;
import com.brs.periods.Period;
import com.brs.planes.InsertPlanes;
import com.brs.planes.Plane;
import com.brs.statuses.InsertStatuses;
import com.brs.statuses.Status;
import com.brs.years.InsertYears;
import com.brs.years.Year;

public class Main implements InsertBlocks, InsertYears, InsertPeriods, InsertEvents, /*InsertAirForces,*/ InsertPlanes, InsertStatuses, SelectAirForces_TEST {
	
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
		System.out.println("------------------");
		System.out.println("Blocks:\n"); 
		//////InsertBlocks.insert();
		
		//add years to DB:
		System.out.println("------------------");
		System.out.println("Years:\n");
		///////InsertYears.insert();
	
		
		//return boolean of successfull insertion as out param, and then do select * query to prove and print to screen.
		//This means these wstatic methods can be predicates instead!! wooHoo!!
		//https://docs.microsoft.com/en-us/sql/connect/jdbc/using-a-stored-procedure-with-a-return-status?view=sql-server-ver15
		
		//add periods to DB:
		System.out.println("------------------");
		System.out.println("Periods:\n"); 
		////////InsertPeriods.insert();
		
		//add events to DB:
		System.out.println("------------------");
		System.out.println("Events:\n"); 
		///////InsertEvents.insert();
		
		//add air forces to DB:
		System.out.println("------------------");
		//System.out.println("Air Forces:\n");
		//////////////////////InsertAirForces.insert();
		//????????????????????????????????????
		SelectAirForces_TEST.select();
		
		//add planes to DB:
		//INSERT_PLANES.insert();
		
		//add statuses to DB:
		//INSERT_STATUSES.insert(); 
		
		
		System.out.println("------------------");
		System.out.println("Event data:\n");
		/////Events.insertEventData();
		
		
		
		//System.out.println(Events.getPeriods());
		
		/*
		 * json:
		 * 
		 * https://code.google.com/archive/p/json-simple/
		 * 
		 * https://howtodoinjava.com/library/json-simple-read-write-json-examples/
		 * 
		 * https://www.geeksforgeeks.org/parse-json-java/
		 * 
		 * https://www.google.com/search?q=reading+json+file+in+java&rlz=1C1CHBF_enIE863IE863&oq=reading+json+&aqs=chrome.5.0j69i57j0l6.6442j0j7&sourceid=chrome&ie=UTF-8
		 */
	}

	
	

}
