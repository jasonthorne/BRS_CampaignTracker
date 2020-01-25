package com.brs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
	
	
	/**
	 * If the value that I pick from my selected key causes any other value in the list to lose all of its values (when chosen as a key itself. ie is still in playerOnes list),
	 *  then that value cant be picked.    
	 */
	
	public void pairPlayers() {
		
		//sorts a player's opponents, in relation to the amount of their own opponents: 
		Comparator<String>playerToNextOppsComparator=(opp1, opp2)-> playerToNextOpps.get(opp1).size() - playerToNextOpps.get(opp2).size();
		
		//3. create a list of playerOnes for selecting opponents from:
		List<String>playerOnes = new ArrayList<String>(players);
		
		System.out.println("playerOnes:" + playerOnes);
		
		for(int i=0, j=players.size()/2; i<j; i++) {
	
			//4 - randomly select a playerOne from list of playerOnes:
			
			//String playerOne = playerOnes.get(new Random().nextInt(playerOnes.size())); 
			//String playerOne = null;
			Random rand = new Random();
			
			//++++++++++++++++NOT FIXING PROBLEMS :P
			//if(playerOnes.get(new Random().nextInt(playerOnes.size())) == null){
			while(playerToNextOpps.get(playerOnes.get(rand.nextInt(playerOnes.size()))).isEmpty()){
				System.out.println("REMOVED RAND from: " + playerOnes.get(rand.nextInt(playerOnes.size())));
				playerOnes.remove(rand.nextInt(playerOnes.size()));
				rand = new Random();
			}
			
			String playerOne = playerOnes.get(rand.nextInt(playerOnes.size()));
			
			//5 - randomly select a playerTwo from playerOne's opponents list in playerToOpponents:
			////////List<String>playerTwos = playerToNextOpps.get(playerOne);
			List<String>playerTwos = playerToNextOpps.get(playerOne).stream()
					.sorted(playerToNextOppsComparator.reversed())
					.collect(Collectors.toList());
			
			System.out.println("playerOne: " + playerOne);
			
			//------------------------------------------------------------------------------
			
			
			//test print of player 2 vals:
			playerTwos.forEach(val ->{
				System.out.println("val:" +val);
			});
			
			//===========================
			
			
		
			System.out.println("player2s before removal: " + playerTwos);
			
			//list with player twos sorted by the amount of their own next opps 
			List<String>sortedPlayerTwos = playerTwos.stream().sorted(playerToNextOppsComparator.reversed()).collect(Collectors.toList());
			
			//////////if (sortedPlayerTwos.size() > 1) {
			if (playerTwos.size() > 1) {
				///////////if (playerToNextOpps.get(sortedPlayerTwos.get(0)).size() > playerToNextOpps.get(sortedPlayerTwos.get(1)).size()) {
				if (playerToNextOpps.get(playerTwos.get(0)).size() > playerToNextOpps.get(playerTwos.get(1)).size()) {
					System.out.println("**********hi there!********");
					//////////playerTwos.remove(sortedPlayerTwos.get(0));
					playerTwos.remove(0);
				}
			}
			
			//System.out.println("sortedP after removal: " + sortedPlayerTwos);
			System.out.println("player2s after removal: " + playerTwos);
			//==========================
			
			
			
			
			
			//----------------------------------------------------------------------------------
			String playerTwo = playerTwos.get(new Random().nextInt(playerTwos.size()));
			
			System.out.println("playerOne: " + playerOne + ". playerTwo: " + playerTwo);
			
			//6 - store playerOne & playerTwo as each other's opponents in playerToPrevOpps:
			playerToPrevOpps.get(playerOne).add(playerTwo);
			playerToPrevOpps.get(playerTwo).add(playerOne);
			
			//7 - remove playerOne & playerTwo from playerOnes (to prevent re-selection):
			playerOnes.removeAll(Arrays.asList(playerOne, playerTwo));
			System.out.println("playerOnes:" + playerOnes);
			
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
	
	
	
	
	
	/*
	Optional<String>playerTwoWithMostOpps = playerTwos.stream().collect(Collectors.maxBy(playerToNextOppsComparator));
	String test =  "no max opps";
	
	System.out.println("player2 with the most opponents: " + playerTwoWithMostOpps.get()); //playerTwoWithMostOpps.get());
	*/
	
	
	

}
