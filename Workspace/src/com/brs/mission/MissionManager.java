package com.brs.mission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MissionManager {
	
	private final List<String>unpairedPlayers = new ArrayList<String>(); //list to draw pairings from
	private static final String BYE = "bye"; //bye entry for pairing odd number of players
	
	//add players to list of unpaired players:
	public void setUnpairedPlayers(List<String>players){
		unpairedPlayers.addAll(players); //add unpaired players to list
		if(players.size()%2==1) { unpairedPlayers.add(BYE); } //if odd number of players, add a bye
		Collections.shuffle(unpairedPlayers); //shuffle list
	}
	
	//===============ADD IMPLEMENTATION FOR IF A NEW PLAYER IS ADDED (see recommendations in pdf), or if a player leaves
	
	
	/*
	{
		test.add("bye");
	}
	
	public void test() {
		System.out.println(test);
	}
	
	*/
	/*
	//public static Map<Mission, List<Player>>getMissions(List<Player> players){
	public static Map<Mission, List<Player>>getMissions(List<String> players){
		
		String byePairing = "bye"; //extra pairing for odd number of players
		
		System.out.println(players);
		
		List<String>pairingsPool = new ArrayList<String>(players);
		
		//if odd number of players, add a bye ++++++++++++
		if(players.size()%2==1) { pairingsPool.add(byePairing); }
			
		
		
		System.out.println(pairingsPool);
		
		*/
		
		
		/*
		 	add everyone to array (add a buy player if player number is odd), randomly shuffle array,
			randomly pull someone from array
			randomly pull them a partner. repeat until everyone is matched - record who got the buy.

			next seeding, make sure that the person who got the buy last time doesnt get it this time
			(make new array for them with only non buy players and pull them a partner, then nuke that array. )
			
			
			https://www.geeksforgeeks.org/randomly-select-items-from-a-list-in-java/
			
		 */
		
		//return null;
	//}

}
