package model;

import java.sql.Timestamp;

public final class Player {
	
	//////////////private int id; //player id ????????????NEEDED????????? ++++++++++PROB NOT :P
	private String name; //user name
	private int score; //score
	private boolean isActive = true; //if active participant
	private Timestamp created; //creation time stamp ?????????????NEEDED????????
	private Squadron squadron; //squadron managed
	
	//player with name:
	public Player(String name) { this.name = name; }
		
	//player without squadron:
	public Player(String name, int score, boolean isActive, Timestamp created) {
		this(name);
		this.score = score;
		this.isActive = isActive;
		this.created = created; //++++++++++++++++++might need strengthening! +++++++
	}
	
	//player with squadron:
	public Player(String name, int score, boolean isActive, Timestamp created, Squadron squadron) {
		this(name, score, isActive, created);
		this.squadron = squadron; //++++++++++++++++++might need strengthening! +++++++
	}
	
	//getters:
	/////////////public int getId() { return id; } /** +++++++++++++ not sure if needed??? ++++++++*/
	public String getName() { return name; }
	public int getScore() { return score; }
	public boolean getIsActive() { return isActive; } 
	public Timestamp getCreated() { return created; } //** +++++++++++++ not sure if needed??? ++++++++*//?????????? should this return timestamp??? +MAKE STRONGER IF SOI! +++++++++?
	
	public Squadron getSquadron() { return squadron; } //++++++++++++++++++++++MAKE STRONGER!! :P
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + ", isActive=" + isActive + ", created=" + created
				+ ", squadron=" + squadron + "]";
	}
	
	
	
	/** ++++++++++++++++++++++++++++++++++++++++
	 * https://www.genuinecoder.com/javafx-observables-and-bindings/
	 */
	
	
	
	
	
	
}