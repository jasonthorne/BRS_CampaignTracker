package com.brs;
import java.util.ArrayList;
import java.util.List;

import com.brs.Campaign;
import com.brs.AirForceData.AirForce;
import com.brs.Campaign.CampaignBuilder;
import com.brs.Pilot.PilotBuilder;
import com.brs.Pilot.PilotSkill;
import com.brs.Player.PlayerBuilder;
import com.brs.event.EventData.EventName;


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
		System.out.println("\nLANDING PAGE:");
		
		//CAMPAIGN VARS:
		List<Campaign>campaigns= new ArrayList<Campaign>(); //list of campaigns created
		CampaignBuilder campaignBuilder = new CampaignBuilder(); //create builder
		//============
		//ideal setup for both grabbing and creating campaigns:
		Campaign campaign = campaignBuilder.build(); //reference for campaign. PreBuilt to allow for selecting of campaign.
		//=========
		
		//SHOW CAMPAIGNS:
		//show the list of campaigns created, for selection of campaign: //stick this in it's own method +++++++++++++++++++++++
		if (campaigns.isEmpty()){
			System.out.println("No campaigns");
		}else {
			for(Campaign i : campaigns){ 
				System.out.println("Campaign name: " + i); //show campaign
			}
		}
		
		//CREATE CAMPAIGNS:
		//create a campaign, selecting a historic event:
		////////campaignBuilder = new CampaignBuilder(); //++++++++++++++++++NO NEEDED AS BUILT ABOVE (method should pass a new builder)
		campaign = campaignBuilder.setEvent(EventName.BATTLE_OF_BRITAIN).build(); //create campaign with historic event
		campaigns.add(campaign);  //and add to list of campaigns
		//+++++++++++++JUMP USER TO CAMPAIGN PAGE HERE (passing campaign reference)++++++++++++++++++++
	
		//add another:
		campaignBuilder = new CampaignBuilder(); //reset builder. IMPORTANT :P
		campaign = campaignBuilder.setEvent(EventName.DEFENCE_OF_THE_HOME_ISLANDS).build(); //create campaign with historic event
		campaigns.add(campaign);  //and add to list of campaigns
		
		//add another:
		campaignBuilder = new CampaignBuilder(); //reset builder. IMPORTANT :P
		campaign = campaignBuilder.setEvent(EventName.ASSAULT_ON_THE_REICH).build(); //create campaign with historic event
		campaigns.add(campaign);  //and add to list of campaigns
		
		//add another:
		campaignBuilder = new CampaignBuilder(); //reset builder. IMPORTANT :P
		campaign = campaignBuilder.setEvent(EventName.OPERATION_BARBAROSSA).build(); //create campaign with historic event
		campaigns.add(campaign);  //and add to list of campaigns
	
		//+++++++++++++++++++++++.get .contains ++++++++++++++++++++++++++++++++USE THIS NSTEAD OF LOOP ABOVE!! :P
		
		//SELECT A CAMPAIGN:
		//select target campaign: 
		for(Campaign i : campaigns){ 
			System.out.println(i); 
			if (i.getEventName() == "Battle of Britain") campaign = i; //assign target campaign to reference
		}
		
		System.out.println("campaign is: "+ campaign);
		
	
		//-----------------CAMPAIGN PAGE -----------------
		System.out.println("\nCAMPAIGN PAGE:");
		
		//campaign ref is sent to this page ++++++++++++++++++Might need to send campaignBuilder too for player creation. (or change how this is configed)
		////Campaign campaignInCampaignPage = campaign;
		
		//PLAYER VARS:
		PlayerBuilder playerBuilder = new PlayerBuilder(); //make player builder 
		Player player = playerBuilder.build(); //player reference
		
		//SHOW PLAYERS:
		//grab the list of players created, for selection of player: //stick this in it's own method +++++++++++++++++++++++
		if (campaign.getPlayers().isEmpty()){
			System.out.println("No players");
		}else {
			for(Player i : campaign.getPlayers()) { 
				System.out.println("Player name: " + i); //show player
			}
		}
		
		//CREATE PLAYERS:
		//add a player to the campaign with a selected airforce:
		//player1:
		playerBuilder = new PlayerBuilder(); //re-instantiate player builder //++++++++++++++++++NO NEEDED AS BUILT ABOVE (method should pass a new builder)
		playerBuilder.setName("player1"); //assign player name
		playerBuilder.setAirForce(AirForce.RAF); //assign air force
		player = playerBuilder.build(); //build player
		campaign = campaignBuilder.setPlayer(player).build(); //add player to campaign
		//+++++++++++++JUMP USER TO PLAYER PAGE HERE (passing campaign (OR PLAYER) reference as before)++++++++++++++++++++
		
		//second player:
		//player2:
		playerBuilder = new PlayerBuilder(); //re-instantiate player builder
		playerBuilder.setName("player2"); //assign player name
		playerBuilder.setAirForce(AirForce.LUFTWAFFE); //assign air force
		player = playerBuilder.build(); //build player
		campaign = campaignBuilder.setPlayer(player).build(); //add player to campaign
		
		
		//SELECT A PLAYER:
		player = campaign.getPlayer("player1"); //change this setup +++++++++++++++(if null warn user)
		System.out.println("selected Player is: " + player.getName());
		System.out.println("selected Player's airForce is: " + player.getAirForce());
		
		/*
		for(Player i : campaign.getPlayers()){
			System.out.println(i);
			if (i.getName() == "player1") player = i; //assign target player to reference
		}
		*/

		/*
		if (player!=null)
			System.out.println("player test is: "+ player);
		else
			System.out.println("player not found");
		*/
		
		//-----------------PLAYER PAGE -----------------
		System.out.println("\nPLAYER PAGE:");
		
		//Either player or campaign reference is passed here (probably player!) ++++++++++++++
		System.out.println(player);
		
		//I "THINK" we show squadron and give edit options on this page. Though it might need its own +++++++++++++++
		
		//SQUADRON VARS:
		Squadron squadron = player.getSquadron(); //squadron reference
		System.out.println("squadron: " + squadron);
		
		//PILOT VARS:
		List<Pilot>pilots = squadron.getPilots();
		PilotBuilder pilotBuilder = new PilotBuilder();
		Pilot pilotTest;
		System.out.println(pilots);
		
		//-------------
		//CREATE PILOTS:
		//add a pilot:
		
		pilotTest = pilotBuilder.setPilotSkill(PilotSkill.ACE).build();
		squadron.addPilot(pilotTest);
		//--
		pilotBuilder = new PilotBuilder();
		pilotTest = pilotBuilder.setPilotSkill(PilotSkill.ROOKIE).build();
		squadron.addPilot(pilotTest);
		
		System.out.println("pilots: " + pilots);
		//--------------
		
		//SHOW SQUADRON:
		//List<Pilot>pilots= new ArrayList<Pilot>(); //list of campaigns created
		
	
	
		//https://stackoverflow.com/questions/33211585/builder-pattern-nested-objects-created-through-other-builders
		
		

		
		//----------------------------------------------
		
		
		
		
		
	}
	
	

	
	

}