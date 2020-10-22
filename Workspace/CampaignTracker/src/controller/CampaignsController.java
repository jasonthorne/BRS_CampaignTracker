package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class CampaignsController implements Traversable{
	
	
	private final Parent root;
	
	//controllers:
	private final FrameController frameCtrlr;
	
	CampaignsController(FrameController frameCtrlr) {
		this.frameCtrlr = frameCtrlr; //assign frame controller
		
		//create loader:
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/campaigns.fxml"));
		loader.setController(this); //set this class as the controller
		try {
			loader.load(); //load fxml tree
		} catch (IOException e) { e.printStackTrace(); }
		this.root = loader.getRoot(); //set root element
	}

	@Override
	public Parent getRoot() { return root; }
	
		
	

}
