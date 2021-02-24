package model;

import java.sql.Timestamp;
import java.util.Comparator;

public final class Player {
	
	//////////////private int id; //player id ????????????NEEDED????????? ++++++++++PROB NOT :P
	private String name; //user name
	private int score; //score
	private boolean isActive = true; //if active participant
	private Timestamp created; //creation time stamp ?????????????NEEDED????????
	private Squadron squadron; //squadron managed
	
	//existing player with name:
	public Player(String name) { this.name = name; }
	
	//new player without squadron:
	public Player(String name, Timestamp created) {
		this(name);
		this.created = created; //++++++++++++++++++might need strengthening! +++++++
	}
		
	//existing player without squadron:
	public Player(String name, int score, boolean isActive, Timestamp created) {
		this(name, created);
		this.score = score;
		this.isActive = isActive;
	}
	
	//existing player with squadron:
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
	
	public Squadron getSquadron() { return squadron; } //++++++++++++++++++++++MAKE STRONGER!! :P ISSUE if called with no squadron existing :P 
	
	@Override
	public String toString() {
		return "Player [name=" + name + ", score=" + score + ", isActive=" + isActive + ", created=" + created
				+ ", squadron=" + squadron + "]";
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
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
		Player other = (Player) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		return true;
	}

	
	/** ++++++++++++++++++++++++++++++++++++++++
	 * https://www.genuinecoder.com/javafx-observables-and-bindings/
	 */
	
	
	
	
	
	
}