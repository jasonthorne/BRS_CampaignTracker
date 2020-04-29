package com.brs.mission;

import java.util.ArrayList;
import java.util.List;

import com.brs.player.Player;

public class Pairing {
	
	private final String player1;
	private final String player2;
	
	public Pairing(List<String>pairing) {
		//CHECK THAT LIST IS RIGHT SIZE!! +++++++++++++++++++++++++++++++++++++++++++
		List<String>pairingCopy = new ArrayList<String>(pairing); 
		player1 = pairingCopy.get(0); //+++++++FIND CLEANER WAY OF GRABBING THESE ELEMENTS +++++++++++++++++
		player2 = pairingCopy.get(1);
		
	}

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
		if (!(obj instanceof Pairing))
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

	
	@Override
	public String toString() {
		return "Players [player1=" + player1 + ", player2=" + player2 + "]";
	}
	
	
}
