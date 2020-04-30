package com.brs;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		//get connection to DB:
		
		
		
		
		PeriodStatus ps1 = new PeriodStatus(new Period(Block.EARLY, Year.FORTY), Status.AUTO);
		
		System.out.println(ps1);
		
		Plane plane1 = new Plane(Model.SPITFIRE_II, AirForce.RAF, Arrays.asList(
				new PeriodStatus(new Period(Block.EARLY, Year.FORTY), Status.NONE),
				new PeriodStatus(new Period(Block.MID, Year.FORTY), Status.LIMIT)));
		
		System.out.println(plane1);

	}

}
