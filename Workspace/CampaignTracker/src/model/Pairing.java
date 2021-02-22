package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import controller.Frameable;
import controller.LoginController;

//////////ADAPTED FROM:
//https://stackoverflow.com/questions/822322/how-to-implement-a-map-with-multiple-keys

public class Pairing {
	
	//maps with their own player name key, holding the same pairing:
    private final Map<String, Stack<String>> playerOneToPairing = new HashMap<String, Stack<String>>();
    private final Map<String, Stack<String>> playerTwoToPairing = new HashMap<String, Stack<String>>();
    
    //constructor:
    public Pairing(String player1, String player2) {
    	
    	//create pairing holding player names:
    	Stack<String> pairing = new Stack<String>();
    	pairing.addAll(Arrays.asList(player1, player2));
    	
    	//add keys with pairing to maps:
    	playerOneToPairing.put(player1, pairing);
    	playerTwoToPairing.put(player2, pairing);
    }
    
    //try both maps with key, or return empty stack:
    public Stack<String> getPairing(String key) {
    	return playerOneToPairing.getOrDefault(
    			key, playerTwoToPairing.getOrDefault(
    					key, new Stack<String>()));
    }

    //return if a map contains given key:
    public boolean getHasKey(String key) {
    	return playerOneToPairing.containsKey(key)? true: 
    		playerTwoToPairing.containsKey(key)? true: false;
    }
    
    public void swapPlayer(String oldKey, String newKey) {
    	
    	Stack<String> pairing; //stack for holding pairing
    	
    	//if pairing was found using oldKey:
    	if(!(pairing = this.getPairing(oldKey)).isEmpty()) {
    		
    		pairing.remove(oldKey); //remove old key
    		pairing.push(newKey); //add new key
    		
    		//if old key is in playerOne map:
    		if(playerOneToPairing.containsKey(oldKey)) {
    			playerOneToPairing.remove(oldKey); //remove old entry
    			playerOneToPairing.put(newKey, pairing); //add new entry
    		} else { //is in playerTwo map:
    			playerTwoToPairing.remove(oldKey); //remove old entry
    			playerTwoToPairing.put(newKey, pairing); //add new entry
			}
    	}
    	System.out.println("pairing: ================= " + this);
    }
    
	@Override
	public String toString() {
		return  playerOneToPairing.toString() + ", " + playerTwoToPairing.toString(); 
	}
    
}
