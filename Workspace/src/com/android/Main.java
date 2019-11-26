package com.android;
import java.util.Arrays;
import java.util.List;

import com.android.Pilot.PilotBuilder;
import com.android.Pilot.PilotSkill;
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
		Plane plane1 = planeBuilder.setStatus(Status.AUTO).setModel("testModel").build();
		System.out.println(plane1);
		//-------------------------------
		
		//-------------PILOT TEST---------
		PilotBuilder pilotBuilder = new PilotBuilder();
		Pilot pilot1 = pilotBuilder.setPilotSkill(PilotSkill.ACE).setExp().build();
		System.out.println(pilot1);
		
		Pilot pilot2 = new PilotBuilder().setPilotSkill(PilotSkill.ROOKIE).setExp().build();
		System.out.println(pilot2);
		
		
		Pilot pilot3 = new PilotBuilder().setName("Bob").build();
		System.out.println(pilot3);
		
		
		Pilot pilot4 = new PilotBuilder().setExp().build();
		System.out.println(pilot4);
		//-------------------------------
	}

}
