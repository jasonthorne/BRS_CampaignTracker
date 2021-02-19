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
	
	
	
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((player1 == null) ? 0 : player1.hashCode());
		result = prime * result + ((player2 == null) ? 0 : player2.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pairing other = (Pairing) obj;
		if (player1 == null) {
			if (other.player1 != null)
				return false;
		} else if (!player1.equals(other.player1))
			return false;
		if (player2 == null) {
			if (other.player2 != null)
				return false;
		} else if (!player2.equals(other.player2))
			return false;
		return true;
	}
	 */



	@Override
	public String toString() {
		return "Pairing [player1=" + player1 + ", player2=" + player2 + "]";
	}
}
