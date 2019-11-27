package com.android;
import java.util.Arrays;
import java.util.List;

import com.android.HistoricEvent.HistoricEventBuilder;
import com.android.HistoricEvent.Name;
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
		System.out.println("\nPlanes:");
		
		PlaneBuilder planeBuilder = new PlaneBuilder();
		Plane plane1 = planeBuilder.setStatus(Status.AUTO).setModel("testModel").build();
		System.out.println(plane1);
		//-------------------------------
		
		//-------------PILOT TEST---------
		System.out.println("\nPilots:");
		
		PilotBuilder pilotBuilder = new PilotBuilder();
		Pilot pilot1 = pilotBuilder.setPilotSkill(PilotSkill.ACE).build();
		System.out.println(pilot1);
		
		Pilot pilot2 = new PilotBuilder().setPilotSkill(PilotSkill.VETERAN).build();
		System.out.println(pilot2);
		
		Pilot pilot3 = new PilotBuilder().setPilotSkill(PilotSkill.AVERAGE).build();
		System.out.println(pilot3);
		
		Pilot pilot4 = new PilotBuilder().setPilotSkill(PilotSkill.ROOKIE).build();
		System.out.println(pilot4);
		
		PilotSkill testSkill = PilotSkill.ACE;
		System.out.println(testSkill);
		
		
		System.out.println("getPilotSkill: " + pilot1.getPilotSkill());
		//-------------------------------
		
		
		//-------------HISTORIC EVENT TEST---------
		System.out.println("\nHistoric events:");
		
		HistoricEventBuilder historicEventBuilder = new HistoricEventBuilder();
		HistoricEvent historicEvent1 = historicEventBuilder.setName(Name.BATTLE_OF_BRITAIN).build();
		System.out.println(historicEvent1);
		
		HistoricEvent historicEvent2 = new HistoricEventBuilder().setName(Name.DEFENCE_OF_THE_HOME_ISLANDS).build();
		System.out.println(historicEvent2);
		//-------------------------------
		
		
		//-------------AIRFORCE TEST---------
		AirForce testAirForce = AirForce.RAF;
		System.out.println(testAirForce);
		//-------------------------------
		
	}

}
