package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import view.FxmlPath;

public class SelectCampaignController extends FrameContent implements Rootable {
	
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
    SelectCampaignController() {
    	setRoot(); //set root node
    }
    
    @Override
	void setRoot() { root = Rootable.getRoot(this, FxmlPath.SELECT_CAMPAIGN); } //set root
	@Override
	Parent getRoot() { return root; } //get root
}
