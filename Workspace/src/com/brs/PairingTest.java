package com.brs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class PairingTest {
	
	
	List<String>players = Arrays.asList("A", "B", "C", "D", "E", "F"); 
	
	//1. create a map of players and their potential opponents:
	Map<String, List<String>>playerToOpponents = new TreeMap<String, List<String>>();
	
	//2. create a map of players and their previously paired opponents (with initially empty lists):
	Map<String, List<String>>playerToPrevOpps = new TreeMap<String, List<String>>(); 
	
	public void setOpponents() {
		
		players.forEach(player ->{
			
			List<String>opponents = new ArrayList<String>(players);
			opponents.remove(player);
			playerToOpponents.put(player, opponents); 
			playerToPrevOpps.put(player, new ArrayList<String>());
		});
		
		System.out.println("playerToOpponents: " + playerToOpponents);
		System.out.println("playerToPrevOpps: " + playerToPrevOpps);
	}
	
	
	//3. create a list of playerOnes for selecting opponents from:
	List<String>playerOnes = new ArrayList<String>(players);
	
	
	public void pairPlayers() {
		
		System.out.println("playerOnes:" + playerOnes);
		String playerOne;
		int pairingNum = players.size()/2;
		
		for(int i=0; i<pairingNum;i++) {
			
			//4 - randomly select a playerOne from list of playerOnes:
			playerOne = playerOnes.get(new Random().nextInt(playerOnes.size())); 
			
			//5 - randomly select a playerTwo from playerOne's opponents list in playerToOpponents:
			List<String>playerTwos = playerToOpponents.get(playerOne);
			String playerTwo = playerTwos.get(new Random().nextInt(playerTwos.size()));
			
			System.out.println("playerOne: " + playerOne + ". playerTwo: " + playerTwo);
			
			//6 - remove playerOne & playerTwo from playerOnes (to prevent re-selection):
			playerOnes.removeAll(Arrays.asList(playerOne, playerTwo));
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
	}
	

}
