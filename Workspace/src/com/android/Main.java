package com.android;
import java.util.Arrays;
import java.util.List;

import com.android.Plane.PlaneBuilder;

/*
import Campaign;
import HistoricDate;
import PilotSkill;
*/


//import Plane.PlaneBuilder;



public class Main {

	public static void main(String[] args) {
	
		//have a list of campaigns
		
		//create a campaign
		
		//open a campaign
		
		//delete a campaign
		
		//close program
		
		/*
		Campaign campaign = new Campaign();
		
		
		//-----------https://stackoverflow.com/questions/2269803/how-to-get-all-enum-values-in-java
		List<PilotSkill> test = Arrays.asList(PilotSkill.values());
		test.forEach(PilotSkill -> {
		    System.out.println(PilotSkill);
		    });
		
		HistoricDate a = new HistoricDate();
		System.out.println(a.getNames());
		//---------------
		
		*/

		//-------------PLANE TEST---------
		PlaneBuilder planeBuilder = new PlaneBuilder();
		Plane testPlane = planeBuilder.setModel("test").setStatus(333).build();
		System.out.println(testPlane);
		
		//-------------------------------
	}

}