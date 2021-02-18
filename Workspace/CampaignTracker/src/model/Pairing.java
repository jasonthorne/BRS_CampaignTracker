package model;
import java.util.Queue;

/** object for holding the names of two paired players */

public class Pairing {
	
	//players:
	private final String player1;
	private final String player2;
	
	//constructor:
	public Pairing(Queue<String>pairing) { //+++++++++++++++Should check that this is of size 2, and throw if not!! 
		player1 = pairing.poll();
		player2 = pairing.poll();
	}
	
	@Override
	public String toString() {
		return "Pairing [player1=" + player1 + ", player2=" + player2 + "]";
	}
}
