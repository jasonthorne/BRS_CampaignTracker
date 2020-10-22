package view;

public enum ViewPath {
	
	FRAME_FXML("frame.fxml"),
	LOGIN_FXML("login.fxml"),
	CAMPAIGNS_FXML("campaigns.fxml"); 
	
	//initial path:
	private String path = "/view/"; 
	
	//constructor appends path:
	private ViewPath(String file) { 
		path = path.concat(file); //add file to path
	}
	@Override 
	public String toString() { return path; } //return path
}