package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CampaignController implements Rootable{
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane testAP;

    @FXML
    private Label testLbl;

    @FXML
    void initialize() {
    
    }
	
	
	private final Stage stage = new Stage(); //stage
    private final Scene scene = new Scene(Rootable.getRoot(this, "/view/campaign.fxml")); //rooted scene
	
	//constructor:
	CampaignController(){
		stage.setScene(scene); //add scene to stage	
	}
	
	//show stage:
    void showStage() { 
    	System.out.println("showing campainCtrlr stage"); 
    	stage.showAndWait(); 
	}

}
