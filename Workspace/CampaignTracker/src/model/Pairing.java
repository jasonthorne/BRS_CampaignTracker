package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//////////ADAPTED FROM:
//https://stackoverflow.com/questions/822322/how-to-implement-a-map-with-multiple-keys



public class Pairing {
	
	//maps with their own unique key (player name), holding the same pairing:
	private final Map<String, List<String>> playerOneToPairing = new HashMap<String, List<String>>();
    private final Map<String, List<String>> playerTwoToPairing = new HashMap<String, List<String>>();
    
    //constructor:
    public Pairing(String player1, String player2) {
    	
    	//create pairing holding player names:
    	List<String> pairing = Arrays.asList(player1, player2);
    	
    	//add keys with pairing to maps:
    	playerOneToPairing.put(player1, pairing);
    	playerTwoToPairing.put(player2, pairing);
    }
    
    //try both maps with key, or return empty list:
    public List<String> getPairing(String key) {
    	return playerOneToPairing.getOrDefault(
    			key, playerTwoToPairing.getOrDefault(
    					key, Collections.emptyList())); //+++++++++++++++REMEMBER TO CHECK FOR EMPTY LIST WHEN CALLING :P 
    }
    
    
    
   /*
    public String<K1, V> getMap1() {
        return Collections.unmodifiableMap(map1);
    }

   
    public Map<K2, V> getMap2() {
        return Collections.unmodifiableMap(map2);
    }

  
    public void put(K1 key1, K2 key2, V value) {
        map1.put(key1, value);
        map2.put(key2, value);
    }*/
    
   /*
    //holds the names of 2 paired players:
	private class Pairing {
		
		//paired player names:
		private String player1;
		private String player2;
		
		//constructor:
		private Pairing(String player1, String player2) {
			this.player1 = player1;
			this.player2 = player2;
		}
	}*/
}

