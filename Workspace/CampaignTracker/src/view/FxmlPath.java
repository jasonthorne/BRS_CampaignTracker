package view;

public enum FxmlPath {
	
	//filenames:
	CAMPAIGN("campaign.fxml"),
	CAMPAIGN_CELL("campaignCell.fxml"),
	CAMPAIGNS("campaigns.fxml"),
	FRAME("frame.fxml"),
	LOGIN("login.fxml"),
	SELECT_CAMPAIGN("selectCampaign.fxml"),
	a1("a1.fxml"),
	a2("a2.fxml"),
	b("b.fxml");
	
	//initial path:
	private String path = "/view/"; 
	
	//constructor appends path:
	private FxmlPath(String fileName) { 
		path = path.concat(fileName); //add file to path
	}
	@Override 
	public String toString() { return path; } //return path
}