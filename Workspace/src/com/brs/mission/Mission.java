package com.brs.mission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.brs.die.Die;
import com.brs.period.Period;
import com.brs.player.Player;

	
	//---------------------------
	
	/*
	 * 
	 * logbook contains logs
	
	----------------------
	log has: period and turn (2 keys)
	
	player faced
	
	reults to squad
	-----------------------
	
	
	
	
	mission creates the logs:
	make a player log (containing opponent, date and results )
	
	
	mission saves the logs to player's mission books. 
	
	after conflict: 
	
	
	 * 
	 * 
	 */
	//---------------------

public class Mission implements Die{
	
	private static int playerNum; //counter for making 'numToPlayer' key
	private final Map<Integer, Player>numToPlayer = new HashMap<Integer, Player>(); //map of players involved
	
	
	private Period period; //period represented
	private int turnNum;
	//private final String date; //date played
	//private Results resutls; ??????????????????
	List<MissionLog>missionLogs = new ArrayList<MissionLog>();
	
	////A mission contains 2 mission logs (player 1 & p2). when they're both filled out, then the mission logs arer saved to the players 
	
	////////public Mission(List<String>players, Period period, String date) {
	///////////////public Mission(List<String>players, Period period) {
	//public Mission(List<Player>players, Period period, int turnNum) { //add turn num too for mission log creation
		
	//public Mission(Set<Player>players, Period period, int turnNum) { //add turn num too for mission log creation
		
	//public Mission(Map<String, Player>nameToPlayer, Period period, int turnNum) { 	
		
	////////////public Mission(Map<String, Player>nameToPlayer, Period period, int turnNum) { 	//++MAKE A SET INSTEAD!! +++++++++
		
	public Mission(Set<Player>players, Period period, int turnNum) { 	//++MAKE A SET INSTEAD!! +++++++++
		
		//this.players = players; //set list of players
		this.period = period; //set period
		this.turnNum = turnNum;
		///////////this.date = date; //set date played
		
		/*
		//--------mission creates logs:
		players.forEach(player ->{
			
			//nameToPlayer.put(player.getName(), player); //add player to map
			
			missionLogs.add(new MissionLog());
		});
		*/
		
		//missions.add(new Mission(pairing.stream().collect(Collectors.toMap(name->name, name->nameToPlayer.get(name))), period, turnNum)); //NOT PASSING IN PLAYERS ++++++
		
		
		////nameToPlayer = players.stream().collect(Collectors.toMap(name->name, player->name)));
		
		
		//System.out.println("woow!: " + nameToPlayer);
		
		
		//player1 = players
		//player2 = players.poll();
		
		//System.out.println("after poll: " + players);
		
		//missionLogs.add(nameToPlayer.get(player).getLogTest(new PeriodTurn(period, Month.FIRST)));
		
		//missionLogs.add(nameToPlayer.get(player).setLogTest(new PeriodTurn(period, Month.FIRST)));
		
		
		//pass in map. keys are named during map creation. ++++++++++++++++++++''''''''''''''''''''''''''''' and op can be grabbed by referencing i -1 
		setNumToPlayer(players);
	}
	
	
	private void setNumToPlayer(Set<Player>players){
		playerNum=0; //(re)set playerNum
		players.forEach(player ->{
			numToPlayer.put(++playerNum, player); //add player i to map
		});
		
		System.out.println("nameToPlayer: " + numToPlayer);
	}
	
	
	
	public Mission(List<MissionLog>missionLogs) {
		this.missionLogs = missionLogs;
	}
	
	//+++++++++++++++++++https://www.geeksforgeeks.org/parse-json-java/
	
	//imitates D6 roll by providing random number from 1-6:
	//private static final Supplier<Integer>D6=()->new Random().nextInt(6)+1; //MOVE THIS TO IT's OWN INTERFACE!! 
	
	
	
	//post mission stuff: 
	
	public void shotDown() {
		
		switch(D6.get()) {
		 case 6: case 5: 
			 System.out.println("Forced landing"); 
			 break;
		 case 4: case 3: 
			 System.out.println("Bail out");
			 bailOut(); //get bailout result
			 break;
		 case 2: case 1: 
			 System.out.println("KIA"); 
			 break;
		}
	}
	
	
	public void bailOut() {
		
		switch(D6.get()) {
		 case 6: case 5: case 4:
			 System.out.println("OK"); 
			 break;
		 case 3: case 2: 
			 System.out.println("Bad landing");
			 injury();
			  break;
		 case 1: 
			 System.out.println("Chute failure"); 
			 break;
		}
	}
	
	
	public void injury() {
		
		switch(D6.get()) {
		 case 6: case 5: 
			 System.out.println("Just a scratch"); 
			 break;
		 case 4: case 3: 
			 System.out.println("Down but not out");
			 break;
		 case 2: 
			 System.out.println("Major Injury");
			 break;
		 case 1: 
			 System.out.println("Crippling injury"); 
			 break;
		}
	}
	
	
	public void damagedAircraft() {
		
		switch(D6.get()) {
		 case 6: case 5: case 4:
			 System.out.println("RTB"); 
			 break;
		  case 3: case 2:
			 System.out.println("Forced Landing");
			 break;
		  case 1:
			 System.out.println("Bail out");
			 bailOut(); //get bailout result
			 break;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	//trigger the post mission stuff once BOTH players have added all their input.
	

}
	