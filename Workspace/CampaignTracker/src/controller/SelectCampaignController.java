package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class SelectCampaignController implements Frameable, Rootable {
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    //root fxml element & children:
    @FXML  private AnchorPane rootAP;
   
    @FXML
    void initialize() {
        
    }
    
    //fxml root node:
  	private Parent root; 

    //constructor:
    SelectCampaignController(FrameController frameCtrlr) {
    	setRoot(); //set root node
    }
    
    SelectCampaignController() {
    	setRoot(); //set root node
    }
    
    
    @Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/selectCampaign.fxml"); } //set root
	@Override 
	public Parent getRoot() { return root; } //get root

	@Override 
	public String getViewTitle() { return "Select Campaign"; } //get title
}
