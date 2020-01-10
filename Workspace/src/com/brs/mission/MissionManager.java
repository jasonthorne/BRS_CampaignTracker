package com.brs.mission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class MissionManager {
	
	private final List<String>unpairedPlayers = new ArrayList<String>(); //list to draw pairings from
	private static final String BYE = "bye"; //bye entry for pairing odd number of players
	
	//map of players and their previous opponents:
	private final Map<String, List<String>>playerToPrevOpps = new HashMap<String, List<String>>(); 
	
	//add players to list of unpaired players:
	public void setUnpairedPlayers(List<String>players){
		unpairedPlayers.addAll(players); //add unpaired players to list
		if(players.size()%2==1) { unpairedPlayers.add(BYE); } //if odd number of players, add a bye
		Collections.shuffle(unpairedPlayers); //shuffle list
	}
	
	//===============ADD IMPLEMENTATION FOR IF A NEW PLAYER IS ADDED (see recommendations in pdf), or if a player leaves
	
	public void pairPlayers() {
		
		Random random = new Random();
		int randomIndex;
		
		for(int i=0;i<(unpairedPlayers.size()/2);i++) {
			System.out.println("outer: " + i);
			
			for(int j=0;j<2;j++) {
				System.out.println("	inner: " + j);
				/*
				randomIndex = random.nextInt(unpairedPlayers.size());
				String randomPlayer = unpairedPlayers.get(randomIndex);
				System.out.println(randomPlayer);
				unpairedPlayers.remove(randomIndex);
				*/
			}
			
		}
		
		
		
		
		
		System.out.println(unpairedPlayers);
		
		
		
		
		/*
		unpairedPlayers.forEach((player) ->{
			
		});*/
	}
	
	/*
	 * String, list<String>playerToPrevPlayers
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
