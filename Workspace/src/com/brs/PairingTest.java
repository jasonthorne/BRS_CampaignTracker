package com.brs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class PairingTest {
	
	
	List<String>players = Arrays.asList("A", "B", "C", "D", "E", "F"); 
	
	//1. create a map of players and their potential opponents:
	Map<String, List<String>>playerToOpponents = new HashMap<String, List<String>>();
	Map<String, List<String>>playerToNextOpps; 
	
	//2. create a map of players and their previously paired opponents (with initially empty lists):
	Map<String, List<String>>playerToPrevOpps = new HashMap<String, List<String>>(); 
	
	public void setOpponents() {
		
		players.forEach(player ->{
			
			List<String>opponents = new ArrayList<String>(players);
			opponents.remove(player);
			playerToOpponents.put(player, opponents);
			playerToPrevOpps.put(player, new ArrayList<String>());
		});
		
		playerToNextOpps = new HashMap<String, List<String>>(playerToOpponents); //set next opps with all opps
		//System.out.println("playerToOpponents: " + playerToOpponents);
		//System.out.println("playerToPrevOpps: " + playerToPrevOpps);
	}
	
	public void pairPlayers() {
		
		//3. create a list of playerOnes for selecting opponents from:
		List<String>playerOnes = new ArrayList<String>(players);
		
		System.out.println("playerOnes:" + playerOnes);
		
		int pairNum = players.size()/2;
		
		for(int i=0; i<pairNum;i++) {
			
			//4 - randomly select a playerOne from list of playerOnes:
			String playerOne = playerOnes.get(new Random().nextInt(playerOnes.size())); 
			
			//5 - randomly select a playerTwo from playerOne's opponents list in playerToOpponents:
			List<String>playerTwos = playerToNextOpps.get(playerOne);
			
			System.out.println("playerOne+++: " + playerOne);
			
			List<Integer>playerTwoVals = new ArrayList<Integer>();
			
			Map<String, Integer>playerToNumOfNextOpps = new HashMap<String, Integer>(); 
			//playerToNumOfNextOpps.putAll(playerTwos, );
			
			
			playerTwos.forEach(vals ->{
				System.out.println("vals:" +vals);
				playerTwoVals.add(playerToNextOpps.get(vals).size());
				playerToNumOfNextOpps.put(vals, playerToNextOpps.get(vals).size());
			});
			
			//++++++++++++++++++now find the largest value in the hashmap and remove that from list.
			//+++++++++++++++++++then you can still pick a random one from list (woohoo! )
			
			//////////System.out.println("playerTwoVals: " + playerTwoVals);
			/////////////System.out.println("playerToNumOfNextOpps: " + playerToNumOfNextOpps);
			
			String playerTwo = playerTwos.get(new Random().nextInt(playerTwos.size()));
			
			System.out.println("playerOne: " + playerOne + ". playerTwo: " + playerTwo);
			
			//6 - store playerOne & playerTwo as each other's opponents in playerToPrevOpps:
			playerToPrevOpps.get(playerOne).add(playerTwo);
			playerToPrevOpps.get(playerTwo).add(playerOne);
			
			//7 - remove playerOne & playerTwo from playerOnes (to prevent re-selection):
			playerOnes.removeAll(Arrays.asList(playerOne, playerTwo));
			
			//8 - remove playerOne & playerTwo from all playerToOpponents values:
			playerToNextOpps.values().forEach(oppsList ->{
				oppsList.removeAll(Arrays.asList(playerOne, playerTwo));
			});
			
			System.out.println("playerToNextOpps: " + playerToNextOpps);

		}
		
		System.out.println("playerToPrevOpps: " + playerToPrevOpps);
		//System.out.println("playerToNextOpps: " + playerToNextOpps);
		//System.out.println("playerOnes:" + playerOnes);
	}
	
	
	public void updateOpponents() {
		
		players.forEach(player ->{
			
			List<String>opponents = new ArrayList<String>(players);
			opponents.remove(player); //remove player 
			opponents.removeAll(playerToPrevOpps.get(player)); //remove pervious opps
			playerToNextOpps.put(player, opponents);
			
		});
		
		System.out.println("playerToNextOpps: " + playerToNextOpps);
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
