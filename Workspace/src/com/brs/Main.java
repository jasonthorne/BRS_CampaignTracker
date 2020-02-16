package com.brs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.brs.airforce.AirForceName;
import com.brs.airforce.Japan;
import com.brs.airforce.RoyalAirForce;
import com.brs.airforce.AirForce;
import com.brs.airforce.UnitedStates;
import com.brs.campaign.Campaign;
import com.brs.event.BattleOfBritain;
import com.brs.event.EventName;
//import com.brs.event.EventData.EventName;
import com.brs.period.Period;
import com.brs.period.Block;
import com.brs.period.Year;
import com.brs.pilot.PilotSkill;
import com.brs.plane.Plane;
//import com.brs.plane.Plane.Availability;
import com.brs.plane.Model;
import com.brs.plane.Status;
import com.brs.Pairing;
import com.brs.pilot.PilotSkill;
import com.brs.pilot.Pilot;

import OldFiles.Player;
import OldFiles.Squadron;
//import OldFiles.Campaign.CampaignBuilder;
//import OldFiles.Pilot.PilotBuilder;
//import OldFiles.Pilot.PilotSkill;
//import OldFiles.Player.PlayerBuilder;



//--------
import java.io.FileReader; 
import java.util.Iterator; 
import java.util.Map; 
  
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 



//------------


import com.brs.event.Event;

/*
import Campaign;
import HistoricDate;
import PilotSkill;
*/


//import Plane.PlaneBuilder;



public class Main  implements FileReadingTEST{ //remove fileReadingTest ++++++

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
	
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
		
		
		/*
		//------------------LANDING PAGE ---------------
		System.out.println("\nLANDING PAGE:");
		
		//////////////AirForceData.getTest();
		
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
			if (i.getEventName() == "Battle of AirForceRAF") campaign = i; //assign target campaign to reference
		}
		
		System.out.println("campaign is: "+ campaign);
		
	*/
		/*
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
		playerBuilder.setAirForce(AirForceName.RAF); //assign air force
		player = playerBuilder.build(); //build player
		campaign = campaignBuilder.setPlayer(player).build(); //add player to campaign
		//+++++++++++++JUMP USER TO PLAYER PAGE HERE (passing campaign (OR PLAYER) reference as before)++++++++++++++++++++
		
		//second player:
		//player2:
		playerBuilder = new PlayerBuilder(); //re-instantiate player builder
		playerBuilder.setName("player2"); //assign player name
		playerBuilder.setAirForce(AirForceName.LUFTWAFFE); //assign air force
		player = playerBuilder.build(); //build player
		campaign = campaignBuilder.setPlayer(player).build(); //add player to campaign
		
		
		//SELECT A PLAYER:
		player = campaign.getPlayer("player1"); //change this setup +++++++++++++++(if null warn user)
		System.out.println("selected Player is: " + player.getName());
		System.out.println("selected Player's airForce is: " + player.getAirForce());
		*/
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
		
		/*
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
		
		*/

		/*
		//----------------------------------------------
		//POPULATE THE INNER MAP BY ITERATING OVER THE ABOVE MAP 
		//inner map: models_avaliabilities
		  HashMap<Model, Availability> models_Availabilities = new HashMap<Model, Availability>(); //{{
			//put(Model.SPITFIRE_II, new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO)); //++++++TEST KEYS
			
		//}};
		
		//Map<String, Map<String, Value>> outerMap = new HashMap<String, HashMap<String, Value>>();
		//Map<Map<String, Value>,String> outerMap = new HashMap<HashMap<String, Value>, String>();
		// outer map: airForcesModels_Availabilities
		 HashMap<AirForce, HashMap<Model,Availability>> airForces_ModelsAvailabilities = new HashMap<AirForce, HashMap<Model,Availability>>(); //{{
			//put(AirForce.RAF, models_Availabilities); 
			
		//}};
		
		
		//models_Availabilities.put(Model.SPITFIRE_II, new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO));
		airForces_ModelsAvailabilities.put(AirForce.RAF, (models_Availabilities.put(Model.SPITFIRE_II, new Plane.Availability(new Period(Block.EARLY, Year.FORTY_FOUR), Status.AUTO))));
		*/
		
		

		/*
		 
		]
		<K: <k: airforce, v: model>, V: availability>
		<K: <k: airforce, v: model>, V: availability>
		<K: <k: airforce, v: model>, V: availability>
		[
		
		*/
		//HASHMAP .CLEAR
		//https://www.geeksforgeeks.org/hashmap-clear-method-in-java/
		/*
		HashMap<AirForce, Model> K_airForce_V_model1 = new HashMap<AirForce, Model>(){{ 
			put(AirForce.RAF, Model.SPITFIRE_II);
		}};
			
			
		
		
		HashMap<AirForce, Model> K_airForce_V_model2 = new HashMap<AirForce, Model>(){{
			put(AirForce.LUFTWAFFE, Model.BF109_E); 
			
		}};
		
		
		 HashMap<HashMap<AirForce,Model>,Availability>K_k_airForce_v_Model_V_availabilities1 = new HashMap<HashMap<AirForce,Model>,Availability>(){{
			put(K_airForce_V_model1, new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.LIMIT));
			
		}};
		
		HashMap<HashMap<AirForce,Model>,Availability>K_k_airForce_v_Model_V_availabilities2 = new HashMap<HashMap<AirForce,Model>,Availability>(){{
			put(K_airForce_V_model2, new Plane.Availability(new Period(Block.LATE, Year.FORTY_FOUR), Status.AUTO));
			
		}};
		
		
		//K_k_airForce_v_Model_V_availabilities1.put(K_airForce_V_model1, new Plane.Availability(new Period(Block.EARLY, Year.FORTY_ONE), Status.LIMIT));
		
		//K_airForce_V_model1.put(AirForce.LUFTWAFFE, Model.BF109_E);
		
		List<HashMap>TEST = Arrays.asList(
				K_k_airForce_v_Model_V_availabilities1,
				K_k_airForce_v_Model_V_availabilities2
		);
		*/		
		/////////////////System.out.println("TEST:" + TEST);
		//System.out.println(AirForceData.airForceModelsToAvailabilities);
		//AirForceData.testLoops(); //TEST PRINT
			
		//==========================SWITCH HASMAP TESTING ==========================
		
		AirForce testUSAirForce = new UnitedStates();
		
		Map<Period, Status>TestMap_periodToStatus = null;

		/*
		try {
			TestMap_periodToStatus = testUSAirForce.getPeriodToStatus(Model.P_51D_MUSTANG);  //a map for the plane being passed
			System.out.println(TestMap_periodToStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//GETORDEFAULT ++++++++++++++++++++++++++++++++++++++USE THIS!! :P
		System.out.println("STATUS IS: " + TestMap_periodToStatus.getOrDefault(new Period(Block.EARLY, Year.FORTY_ONE), Status.NONE));
		
		for ( Period key : TestMap_periodToStatus.keySet() ) {
		    System.out.println( key );
		}
		
		for ( Status status : TestMap_periodToStatus.values() ) {
		    System.out.println( status );
		}
		*/
		
		//===============================
		
		/*
		try {
			TestMap_periodToStatus = testUSAirForce.getPeriodToStatus(Model.P_38E_LIGHTNING); //a map for the plane being passed
			System.out.println(TestMap_periodToStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//GETORDEFAULT ++++++++++++++++++++++++++++++++++++++USE THIS!! :P
		System.out.println("STATUS IS: " + TestMap_periodToStatus.getOrDefault(new Period(Block.EARLY, Year.FORTY_TWO), Status.NONE));
		
		for ( Period key : TestMap_periodToStatus.keySet() ) {
		    System.out.println( key );
		}
		
		for ( Status status : TestMap_periodToStatus.values() ) {
		    System.out.println( status );
		}
		*/
		
		/*
		
		//Pulling from now obsolete hashmap of ALL airforce and their planes
		System.out.println("PRINT BE HERE: " + AirForce.getAirForceModels(AirForceName.RAF));
		
		

		//System.out.println("PRINT BE HERE2: " + RoyalAirForce.putAirForceToModels());
		
		//RoyalAirForce.putAirForceToModels();

		System.out.println("PRINT BE HERE2: " + AirForce.getAirForceModels(AirForceName.RAF));
		

		System.out.println(testUSAirForce.getAirForceModels(AirForceName.RAF));
		
		//UnitedStates.putAirForceToModels();
		
		System.out.println(AirForce.getAirForceModels(AirForceName.USAAF));
		
		System.out.println(UnitedStates.getAirForceModels(AirForceName.USAAF));
		
		
		

		System.out.println(AirForce.getAirForceModels(AirForceName.RAF));
		System.out.println(UnitedStates.getAirForceModels(AirForceName.RAF));
		*/
		
		
		//===================
		
		AirForce testRAFAirForce = new RoyalAirForce();
		
		Map<Model, Status>TESTmodelToStatus;
		
		//THIS SHOULD EXIST IN CLASS THAT CONTAIS AIRFORCE ('player' for now)!! 
		TESTmodelToStatus  = testUSAirForce.getAvailableModels(new Period(Block.MID, Year.FORTY_ONE)); //should pass in current (or newly current) date
		
		
		System.out.println("yo dawg 1: " + TESTmodelToStatus);
		
		
		
		//System.out.println("getName: " + testUSAirForce.getName() + ". getDescription: " + testUSAirForce.getDescription());
		
		//UnitedStates a = new UnitedStates(); //++++++++++++FIX THIS BEIMNG ALLOWED!! :P
		
		
		TESTmodelToStatus  = testUSAirForce.getAvailableModels(new Period(Block.LATE, Year.FORTY_ONE));
		System.out.println("yo dawg 2: " + TESTmodelToStatus);
		
		
		TESTmodelToStatus  = testRAFAirForce.getAvailableModels(new Period(Block.MID, Year.FORTY_ONE));
		System.out.println("RAF TEST. yo dawg 3: " + TESTmodelToStatus);
		
		TESTmodelToStatus  = testUSAirForce.getAvailableModels(new Period(Block.LATE, Year.FORTY_ONE));
		System.out.println("yo dawg 4: " + TESTmodelToStatus);
		
		System.out.println("name is: " + testUSAirForce.getName());
		System.out.println("all models is: " + testUSAirForce.getAllModels());
		System.out.println("available models is: " + testUSAirForce.getAvailableModels(new Period(Block.MID, Year.FORTY_TWO)));
		
		//=====================================
		
		//----------
		System.out.println();
		BattleOfBritain TEST_BOB = new BattleOfBritain();
		
		System.out.println("TEST_BOB: " + TEST_BOB.getName());
		System.out.println("TEST_BOB: " + TEST_BOB.getAirForceNames());
		System.out.println("TEST_BOB: " + TEST_BOB.getPeriods());
		System.out.println();
		//---------------------
		//BELOW ALL WORKS (but shouldnt be grabbed as a hashmap)
		
		/*
		Map<AirForceName, AirForce>testAirForcesMAP = TEST_BOB.getAIRFORCES2();
		
		//System.out.println(testAirForcesMAP);
		
		AirForce testBOB_USA = testAirForcesMAP.get(AirForceName.LUFTWAFFE);
		System.out.println("testBOB_USA_getName: " + testBOB_USA.getName());
		System.out.println("testBOB_USA_getAllModels: " + testBOB_USA.getAllModels());
		System.out.println("testBOB_USA_getDescription: " + testBOB_USA.getDescription());
		System.out.println("testBOB_USA_getAvailableModels: " + testBOB_USA.getAvailableModels(new Period(Block.LATE, Year.FORTY_ONE)));
		*/
		
		
		//Retrieving AirForce from Event:
		AirForce eventTestAirForce1 = TEST_BOB.getAirForce(AirForceName.RAF);
		System.out.println("eventTestAirForce1: " + eventTestAirForce1.getName());
		System.out.println("eventTestAirForce1: " + eventTestAirForce1.getAllModels());
		System.out.println("eventTestAirForce1: " + eventTestAirForce1.getAvailableModels(new Period(Block.MID, Year.FORTY)));
		
		
		//----------
		System.out.println();
		List<Model> testCopy1 = eventTestAirForce1.getAllModels();
		System.out.println("TEST COPY1: " + testCopy1);
		testCopy1.set(0, Model.BF109_E);
		System.out.println("TEST COPY1 changed: " + testCopy1);
		System.out.println("original list: " + eventTestAirForce1.getAllModels());
		System.out.println();
		//------------
		
		
		//Retrieving AirForce from Event:
		AirForce eventTestAirForce2 = TEST_BOB.getAirForce(AirForceName.LUFTWAFFE);
		System.out.println("eventTestAirForce2: " + eventTestAirForce2.getName());
		//System.out.println("eventTestAirForce2: " + eventTestAirForce2.getAllModels());
		System.out.println("eventTestAirForce2: " + eventTestAirForce2.getAvailableModels(new Period(Block.MID, Year.FORTY)));
	
		//----------
		System.out.println();
		List<Model> testCopy2 = eventTestAirForce2.getAllModels();
		System.out.println("TEST COPY2: " + testCopy2);
		testCopy2.set(0, Model.SPITFIRE_II);
		System.out.println("TEST COPY2 changed: " + testCopy2);
		System.out.println("original list: " + eventTestAirForce2.getAllModels());
		System.out.println();
		//------------
		
		
		
		
		
		//Retrieving AirForce from Event:
		//AirForce eventTestAirForce2 = TEST_BOB.getAirForce(AirForceName.USAAF); ///OOPS!! :P
		//System.out.println("eventTestAirForce1: " + eventTestAirForce2.getName());
		//System.out.println("eventTestAirForce1: " + eventTestAirForce2.getAllModels());
		//System.out.println("eventTestAirForce1: " + eventTestAirForce2.getAvailableModels(new Period(Block.MID, Year.FORTY)));
		
		///======================================
		Campaign campaign = new Campaign(EventName.BATTLE_OF_BRITAIN);
		
		System.out.println(campaign.getName());
		System.out.println(campaign.getAirForceNames());
		System.out.println(campaign.getPeriods());
		System.out.println("Created: " + campaign.getDate());
		
		//System.out.println("current period: " + campaignTEST.getPeriodTEST());
		//campaignTEST.changePeriod();
		//campaignTEST.changePeriod();
		//campaignTEST.changePeriod();
		//campaignTEST.changePeriod();
		
		///////////campaign.getPeriodTEST();
		/////////////campaign.getPeriodTEST();
		////////////////campaign.getPeriodTEST();
		//campaign.getPeriodTEST();

		
		campaign.setPlayer("A", AirForceName.RAF);
		campaign.setPlayer("B", AirForceName.RAF);
		campaign.setPlayer("C", AirForceName.RAF);
		campaign.setPlayer("D", AirForceName.RAF);
		/////////////campaign.setPlayer("A", AirForceName.RAF);
		campaign.setPlayer("E", AirForceName.RAF);
		campaign.setPlayer("F", AirForceName.RAF);
		
		
		//campaign.setPlayer("G", AirForceName.RAF);
		//campaign.setPlayer("H", AirForceName.RAF);
		//campaign.setPlayer("I", AirForceName.RAF);
		//campaign.setPlayer("J", AirForceName.RAF);
		//System.out.println(campaign.nameToPlayer.get("Jammy").getPlayerStuff());
		
		///////////////campaign.test();
		
		
		//campaign.setOpponents();
		
		///campaign.beginTurns();
		
		//campaign.pairPlayers(); //pair players 
		//System.out.println("-----------------------");
		///////////campaign.pairPlayers2(); //pair players 
		
		//System.out.println("======================");
		///////campaign.pairPlayers2(); //pair players 
		
		//campaign.pairPlayers3(); //pair players 
		
		//campaign.pairPlayers4(); //pair players 
		//System.out.println("-------------------------");
		//campaign.pairPlayers4(); //pair players 
		
		//campaign.ughhhh();
		//System.out.println("-------------------------");
		//campaign.ughhhh();
		
		//System.out.println("======================");
		//campaign.pairPlayers4(); //pair players
		
		
		//campaign.swapTEST();
		//System.out.println("==============SECOND PAIRING========");
		//campaign.pairPlayers4(); //pair players
		
		//campaign.pairPlayers4(); //pair players
		
		
		/*
		campaign.swapTEST();
		System.out.println("========THIRD PAIRING==============");
		//campaign.pairPlayers4(); //pair players
		
		campaign.pairPlayers4(); //pair players
		*/
		
		
		
		
		
		
		
		System.out.println();
		
		/*
		//=========================================================
		List<String>list = Arrays.asList("A", "B", "C", "D", "E", "F");
		
		List<Pairing>pairings = Stream.generate(()->
				new Pairing(list.get(new Random().nextInt(list.size())), list.get(new Random().nextInt(list.size()))))
				.distinct().limit(3).collect(Collectors.toCollection(ArrayList::new));
				
		System.out.println(pairings);
	*/
		
		
		/////campaign.makePairings();
		
		//////campaign.makeMissions();
		
		//campaign.makeMissions();
		//campaign.makeMissions();
		//campaign.makeMissions();
		//campaign.makeMissions();
	
		/*
		campaign.testMakeUsedPairings();
		
	
		campaign.setOpponents();
		
		
		campaign.setOpponents();
		//campaign.setOpponents();
		
		*/
		
		//################################################################
		
		
		/*
		 * +++++out off all possible options: if that guy has only one option left, then you pick him!! 
		 * 
		 * If one of youre options only has you as its available option, then pick that as your option. +++++++++++
		 */
		
		/*
		PairingTest pairingTest = new PairingTest();
		System.out.println("--------------------------1st pairing--------------------------");
		pairingTest.setOpponents();
		pairingTest.pairPlayers();
		
		System.out.println();
		System.out.println("--------------------------2nd pairing--------------------------");
		pairingTest.updateOpponents();
		pairingTest.pairPlayers();
		
		System.out.println();
		System.out.println("--------------------------3rd pairing--------------------------");
		pairingTest.updateOpponents();
		pairingTest.pairPlayers();
		
		System.out.println();
		System.out.println("--------------------------4th pairing--------------------------");
		pairingTest.updateOpponents();
		pairingTest.pairPlayers();
		
		System.out.println();
		System.out.println("--------------------------5th pairing--------------------------");
		pairingTest.updateOpponents();
		pairingTest.pairPlayers();
		*/
		
		//PairingTest pairingTest = new PairingTest();
		//pairingTest.testRR();
		
		
		
		//campaign.setPairings();
		
		System.out.println("+++++++++++");
		//campaign.beginTurns();
		
		//campaign.setCurrMissions();
		
		
		campaign.advanceTurn();
		campaign.advanceTurn();
		campaign.advanceTurn();
		campaign.advanceTurn();
		campaign.advanceTurn();
		campaign.advanceTurn();
		campaign.advanceTurn();

		campaign.advanceTurn();
		campaign.advanceTurn();
		campaign.advanceTurn();
		campaign.advanceTurn();
		
		campaign.advanceTurn();
		campaign.advanceTurn();
		/*
		campaign.advanceTurn();
		
		campaign.advanceTurn();
		campaign.advanceTurn();
		*/
		
		//--
		Pilot rookie = new Pilot(PilotSkill.ROOKIE);
		System.out.println(rookie.exp);
		
		Pilot avg = new Pilot(PilotSkill.AVERAGE);
		System.out.println(avg.exp);
		
		Pilot vet = new Pilot(PilotSkill.VETERAN);
		System.out.println(vet.exp);
		
		Pilot ace = new Pilot(PilotSkill.ACE);
		System.out.println(ace.exp);
		
	
		
		
		
		
		
		
		 
		//---------------------------
		 
		 System.out.println("=================================");
		 Pilot test = new Pilot(PilotSkill.ROOKIE);
		
		 
		
			
		//---------------------------
		
		 
		 
		 
		 
	
	}
	
	
	
		
}
	
	
