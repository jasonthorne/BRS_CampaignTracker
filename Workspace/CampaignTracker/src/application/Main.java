package application;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import controller.FrameController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Pairing;
import model.Player;

public class Main extends Application {
	
	//============================================================DELETE ALL OF  THIS LATER :P 
	private final List<List<Pairing>>pairings = new LinkedList<List<Pairing>>(); 
	Map<String, Player>nameToPlayer = new HashMap<String, Player>();  
	private static final String BYE = "bye"; 
	//==================================================================

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	FrameController frameCtrlr = FrameController.getFrameCtrlr();
    	frameCtrlr.showStage();
    	
    	/*
    	//adding EVEN number of players: (so the bye needs evenly spread here)
    	nameToPlayer.put("Adam", new Player("Adam", new Timestamp(Calendar.getInstance().getTimeInMillis())));
    	nameToPlayer.put("Bob", new Player("Bob", new Timestamp(Calendar.getInstance().getTimeInMillis())));
    	nameToPlayer.put("Charlie", new Player("Charlie", new Timestamp(Calendar.getInstance().getTimeInMillis())));
    	nameToPlayer.put("Derek", new Player("Derek", new Timestamp(Calendar.getInstance().getTimeInMillis())));
    	//nameToPlayer.put("Edith", new Player("Edith", new Timestamp(Calendar.getInstance().getTimeInMillis())));
    	
    	//System.out.println(nameToPlayer);
    	
    	setPairings();
    	*/
    	
    	
    	
    	
    }
    
    //===============================================================================
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void setPairings() {
		
		 pairings.clear(); //+++++++++++++++++++++++++++++REMOVE :P
		if (pairings.isEmpty()) { //if pairings is empty
			
			System.out.println("nameToPlayer: " + nameToPlayer);
			
			//////////https://stackoverflow.com/questions/30425836/java-8-stream-map-to-list-of-keys-sorted-by-values
			
			//create list of player's names from map of players, sorted by creation date:
			List<String>players = nameToPlayer.entrySet().stream()
		    	.sorted((e1, e2) -> e1.getValue().getCreated().compareTo(e2.getValue().getCreated()))
		    	.map(e -> e.getKey())
		    	.collect(Collectors.toList());
			
			if (players.size()%2==1) {players.add(0, BYE);} //if odd number of players, add a bye
			System.out.println("players: " + players); //+++++++++++++++++++++
			String fixedPlayer = players.remove(0); //1st player is removed from list (to be given a fixed position for pairing)
			
			//loop through the number of turns (with unique pairings) available: 
		    for (int turn=0, turns=players.size(); turn < turns; turn++) {
		        System.out.println("\nTurn:" + (turn + 1));  //++++++++++++++++++++++++
		        List<Pairing>pairsList = new ArrayList<Pairing>(); //list of pairings
		        
		        System.out.println(players.get(turn) + " vs " + fixedPlayer); //++++++++++++++++++
		        
		        //each turn, pair the fixed player against a player in players (at the index pos of that turn):
		        pairsList.add(new Pairing(players.get(turn), fixedPlayer));
		        
		        //////////System.out.println("PAIRING 1: " + pairings);
		        
		        //endPos is at players.size()+1 to replace the removed fixedPlayer's index. 
		        for (int pairPos=1, endPos=(players.size()+1)/2; pairPos < endPos; pairPos++) {  //pairPos starts at 1 to ignore first 
		            int player1Pos = (turn + pairPos) % turns; //turn number + pairingPos (set at 1 to ignore ..........++++++++++)
		            int player2Pos = (turn  + turns - pairPos) % turns;
		            
		            System.out.println(players.get(player1Pos) + " vs " + players.get(player2Pos)); //+++++++++++++++++
		            pairsList.add(new Pairing(players.get(player1Pos), players.get(player2Pos)));  //add paired players to list
		            
		            //////////System.out.println("PAIRING 2: " + pairings);
		        }
		        pairings.add(pairsList); //add list of pairings to pairings
		    }
		    System.out.println("\npairings: " + pairings);
		}
	    ///////////////##############addPlayer(new Player("yo dawg", new Timestamp(Calendar.getInstance().getTimeInMillis()))); //+++++++++++++
	}
    
    
    
    
    
    
    
}