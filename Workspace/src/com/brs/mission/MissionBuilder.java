package com.brs.mission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.brs.player.Player;


public class MissionBuilder { //++++++++++++++CHECK PRIVACY LEVEL OF EVERYTHING
	
	
	//---------
	private Map<Mission, List<String>>missionToPlayers = new HashMap<Mission, List<String>>(); //map of current missions ????????
	//---------
	
	
	private final List<String>allPlayers = new ArrayList<String>(); //list of all players
	private final List<String>unpairedPlayers = new ArrayList<String>(); //list to draw pairings from
	private static final String BYE = "bye"; //bye entry for pairing odd number of players
	
	//map of players and their previous opponents: USE THIS TO CHECK THAT ALL OPPS HAVE BEEN PLAYED +++++++++
	private final Map<String, List<String>>playerToPrevOpps = new HashMap<String, List<String>>(); 
	
	//================
	/*
	check size of above map.
	If not equal to list of players 
	
	*/
	
	public MissionBuilder(List<String>players) {
		allPlayers.addAll(players); //add players
	}
	
	
	public void createPairings() {
		
		//add all players to list of unpairedPlayers (ready for pairing):
		setUnpairedPlayers();
		pairPlayers();
	}
	
	
	//add players to list of unpaired players:
	public void setUnpairedPlayers(){
		unpairedPlayers.addAll(allPlayers); //add players to unpaired list
		if(allPlayers.size()%2==1) { unpairedPlayers.add(BYE); } //if odd number of players, add a bye
		Collections.shuffle(unpairedPlayers); //shuffle list
	}
	
	
	
	
	
	public void addNewPlayer(String player) { ///////////FIGURE OUT WHAT NEEDS OTO HAPPEN HERE (what needs to happen to throw a new player into mix(maybe remove bye if existing or add bye if everyone is already paired ))
		allPlayers.add(player);
		
		//check current pairings for bye, and add new player as pairing instead.
		//if no buyt exists, then create a bye (as number is now odd) and pair bye with this new player
	}
	
	
	
	
	//===============ADD IMPLEMENTATION FOR IF A NEW PLAYER IS ADDED (see recommendations in pdf), or if a player leaves
	
	public void pairPlayers() {
		
		Random random; 
		int randomIndex;
		Mission mission; 
		List<String>players;
		
		//while there are unpaired players:
		while(!unpairedPlayers.isEmpty()) { 
			
			players = new ArrayList<String>();
			
			while(players.size()!=2) {
				//System.out.println("inner: " + j);
				
				random = new Random();
				randomIndex = random.nextInt(unpairedPlayers.size());
				//String randomPlayer = unpairedPlayers.get(randomIndex);
				//-----
				//players.add(randomPlayer);
				players.add(unpairedPlayers.get(randomIndex));
				//--------
				///////System.out.println(randomPlayer);
				unpairedPlayers.remove(randomIndex);
				
			}
			
			//mission = new Mission(pair);
			missionToPlayers.put(new Mission(players), players);
			
			
		}
		
		
		
		
		
		System.out.println(unpairedPlayers);
		System.out.println(missionToPlayers);
		
		
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
