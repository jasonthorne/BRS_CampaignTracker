package com.android;
import java.util.Arrays;
import java.util.List;

import com.android.PilotTest.PilotTestBuilder;
import com.android.Plane.PlaneBuilder;
import com.android.Plane.Status;

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
		Plane testPlane1 = planeBuilder.setStatus(Status.AUTO).setModel("testModel").build();
		System.out.println(testPlane1);
		//-------------------------------
		
		//-------------PILOT TEST---------
		PilotTestBuilder pilotTestBuilder = new PilotTestBuilder();
		PilotTest testPilot1 = pilotTestBuilder.setName("Bob").setAge(33).build();
		System.out.println(testPilot1);
		
		
		
		//-------------------------------
	}

}
