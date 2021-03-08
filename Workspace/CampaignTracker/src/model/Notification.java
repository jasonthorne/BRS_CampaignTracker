package model;

public final class Notification {
	
	public enum Status {
		
		SELECT_AIRFORCE("Select Air Force"), 
		PLAY_MATCH("Play Match")
		/* etc... */;
		
		private final String status; //status
		//constructor:
		private Status(String status) { this.status = status; } 
		@Override 
		public String toString() { return status; } //return status
	}
	
	
	public Notification(Status status, String path) {
		
	}
	
	/*
	 * title
	 * 
	 * date 
	 * 
	 * message
	 * 
	 * link
	 */
	
	
	

}
