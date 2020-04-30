package com.brs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		//get connection to DB:
		
		
		
		
		PeriodStatus ps1 = new PeriodStatus(new Period(Block.EARLY, Year.FORTY), Status.AUTO);
		
		System.out.println(ps1);
		
		Plane plane1 = new Plane(Model.SPITFIRE_II, AirForce.RAF, Arrays.asList(
				new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT),
				new PeriodStatus(new Period(Block.LATE, Year.FORTY), Status.AUTO)));
		
		System.out.println(plane1);
		
		List<Plane>planes = Arrays.asList(
				new Plane(Model.SPITFIRE_II, AirForce.RAF, Arrays.asList(
						new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT),
						new PeriodStatus(new Period(Block.LATE, Year.FORTY), Status.AUTO))),
				new Plane(Model.HURRICANE_I, AirForce.RAF, Arrays.asList(
						new PeriodStatus(new Period(Block.EARLY, Year.FORTY), Status.AUTO),
						new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT)))
				
				
				
			// each county has its own class,. with - airforce name, planes, events (if home adv or not)
				
		);

	}

}
