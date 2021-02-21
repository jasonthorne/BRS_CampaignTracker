package model;
import java.util.List;
import java.util.Queue;

/** object for holding the names of two paired players */

public class Pairing {
	
	
	//players:
	private final String player1;
	private final String player2;
	
	//constructor:
	public Pairing(String player1, String player2) { //+++++++++++++++Should check that this is of size 2, and throw if not!! 
		/*player1 = list.poll();
		player2 = list.poll();*/
		
		this.player1 = player1;
		this.player2 = player2;
	}
	
	@Override
	public int hashCode() {
		/*
		final int prime = 31;
		int result = 1;
		result = prime * result + ((player1 == null) ? 0 : player1.hashCode());
		result = prime * result + ((player2 == null) ? 0 : player2.hashCode());
		return result;
		*/
		
		 return player1.hashCode() ^ player2.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		 if (!(obj instanceof Pairing))
		        return false;
		 Pairing ref = (Pairing) obj;
		      return this.player1.equals(ref.player1) && 
		          this.player2.equals(ref.player2);
		        
	}





	@Override
	public String toString() {
		return "Pairing [player1=" + player1 + ", player2=" + player2 + "]";
	}
	
	

	
	
}
