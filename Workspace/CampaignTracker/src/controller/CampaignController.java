package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class CampaignController implements Rootable{
	
	
	
	
	
	private final Stage stage = new Stage(); //stage
    private final Scene scene = new Scene(Rootable.getRoot(this, "/view/campaign.fxml")); //rooted scene
	
	//constructor:
	CampaignController(){
		stage.setScene(scene); //add scene to stage	
	}
	
	//show stage:
    void showStage() { System.out.println("showing campainCtrlr stage"); }

}
