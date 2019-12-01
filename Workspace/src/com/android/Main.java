package com.android;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.android.Campaign.CampaignBuilder;
import com.android.Campaign;
import com.android.HistoricEvent.HistoricEventBuilder;
import com.android.HistoricEvent.Name;
import com.android.Pilot.PilotBuilder;
import com.android.Pilot.PilotSkill;
import com.android.Plane.PlaneBuilder;
import com.android.Plane.Status;
import com.android.Player.PlayerBuilder;

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
		
		
		
		
		
		
		
		
		
		
		
		/*
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
		
		//=========
		
		PilotSkill p1 = pilot1.getPilotSkill();
		System.out.println("p1 is: " + p1);
		
		p1 = p1.ROOKIE;
		System.out.println("p1 is now: " + p1);
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
	
		
		// print list of historic events
		List<Name>names = Arrays.asList(Name.values());
		names.forEach(name -> {
			System.out.println(name);
		});
		
		*/
		
		
		//------------------LANDING PAGE ---------------
		
		List<Campaign>campaigns= new ArrayList<Campaign>(); //list of campaigns created
		
		
		//CREATE CAMPAIGN:
		//create a campaign, selecting a historic event:
		Campaign campaign; //reference for campaign
		CampaignBuilder campaignBuilder = new CampaignBuilder(); //create builder
		campaign = campaignBuilder.setHistoricEvent(Name.BATTLE_OF_BRITAIN).build(); //create campaign with historic event
		campaigns.add(campaign);  //and add to list of campaigns
		
		//+++++++++++++JUMP PLAYER TO CAMPAIGN PAGE HERE (passing campaign reference)++++++++++++++++++++
		
		
		//show the list of campaigns created, for selection of campaign:
		for(Campaign i : campaigns){ //++++++++++++++++++this should prob be in a method from a btn click event, (passing in BoB string)
			////System.out.println("i is: " + i);
			if (i.getHistoricEventName() == "Battle of Britain") campaign = i; //assign target campaign to reference
			
		}
		
		System.out.println(campaign); //test print target campaign
		
		//-----------------CAMPAIGN PAGE -----------------
		
		//campaign ref is sent to this page ++++++++++++++++++
		Campaign campaignInCampaignPage = campaign;
		
		
		//CREATE PLAYER:
		//add a player to the campaign with a selected airforce:
		PlayerBuilder playerBuilder = new PlayerBuilder(); //make player builder 
		
		//player1:
		playerBuilder.setName("player1"); //assign player name
		playerBuilder.setAirForce(AirForce.RAF); //assign air force
		campaignInCampaignPage = campaignBuilder.setPlayer(playerBuilder).build(); //add player to campaign
		//+++++++++++++JUMP PLAYER TO PLAYER PAGE HERE (passing campaign reference as before)++++++++++++++++++++
		
		//second player:
		//player2:
		playerBuilder = new PlayerBuilder(); //re-instantiate player builder (as precaution)
		
		playerBuilder.setName("player2"); //assign player name
		playerBuilder.setAirForce(AirForce.LUFTWAFFE); //assign air force
		campaignInCampaignPage = campaignBuilder.setPlayer(playerBuilder).build(); //add player to campaign
	
		
		
		///playerBuilder = new PlayerBuilder(); //re-instantiate player builder (as precaution)
		
		Player selectedPlayer;
		
		//show list of players for user selection:
		for(Player i : campaignInCampaignPage.getPlayers()){
			//if (i.getName() == "Player1") selectedPlayer = i.getPlayer(); //assign target campaign to reference
			if (i.getName() == "Player1") selectedPlayer = i.getPlayer(); //assign target campaign to reference
		}
		
		System.out.println("Players: " + campaignInCampaignPage.getPlayers());
		/////System.out.println("selectedPlayer is: " + selectedPlayer.getName());
		//-----------------PLAYER PAGE -----------------
		
		Campaign campaignInPlayerPage = campaignInCampaignPage;
		
		//Player player 
		
		//selectedPlayer = campaignInPlayerPage.getPlayer();
		
		
		/////Player player = selectedPlayer; //++++++++++++++DOESNT WORK :P FIX THIS :P
		
		//System.out.println(selectedPlayer.getName());
		
		
		//https://stackoverflow.com/questions/33211585/builder-pattern-nested-objects-created-through-other-builders
		
		
		
		
		
		
		//----------------------------------------------
		
		
		
		
		
		
		
		
		
	}

	
	

}
