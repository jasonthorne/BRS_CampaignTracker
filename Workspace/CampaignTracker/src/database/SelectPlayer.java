package database;

import model.Player;

public interface SelectPlayer {
	
	public static void select(Player player) {
		
		System.out.println(player.getName() + " " + player.getPswrd());
	}
	

}
