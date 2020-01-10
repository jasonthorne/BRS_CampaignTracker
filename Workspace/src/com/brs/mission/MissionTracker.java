package com.brs.mission;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.brs.player.Player;

public class MissionTracker {
	
	public static Map<Mission, List<Player>>getMissions(List<Player> players){
		
		System.out.println(players);
		
		List<Player>waitingPlayers = new ArrayList<Player>(players);
		
		if(waitingPlayers.size()%2==1) {
			//waitingPlayers.add(new Player())
		}
		
		
		/*
		 	add everyone to array (add a buy player if player number is odd), randomly shuffle array,
			randomly pull someone from array
			randomly pull them a partner. repeat until everyone is matched - record who got the buy.

			next seeding, make sure that the person who got the buy last time doesnt get it this time
			(make new array for them with only non buy players and pull them a partner, then nuke that array. )
			
			
			https://www.geeksforgeeks.org/randomly-select-items-from-a-list-in-java/
			
		 */
		
		return null;
	}

}
