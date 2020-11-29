package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import model.Campaign;

public class CampaignCellController extends JFXListCell<Campaign> implements Rootable {

	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private Label eventNameLbl;
    @FXML private Label createdLbl;
    @FXML private Label hostNameLbl;
    @FXML private Label isPlayingLbl;
    @FXML private ProgressIndicator progressPI;
    //@FXML private JFXButton showCampaignBtn;
    
    @FXML
    void initialize() {
    	
    	//show campaign:
    	/*showCampaignBtn.setOnAction(event -> 
		new CampaignController().showStage());*/

    }
    
    //root element for this controller:
  	private final Parent root = Rootable.getRoot(this, "/view/campaignCell.fxml");
	
	private final CampaignsController campaignsCtrlr; //??????????????????????????? needed???
	
	//constructor:
	CampaignCellController(CampaignsController campaignsCtrlr){
		this.campaignsCtrlr = campaignsCtrlr; //assign campaigns controller //??????????????????????????? needed???
	}
	
	//update cell with campaign data:
	@Override 
	protected void updateItem(Campaign campaign, boolean isEmpty) {
        super.updateItem(campaign, isEmpty);
        
  		if (isEmpty || campaign == null) {
  	        setText(null);
  	        setGraphic(null);
  	    } else {
  	    	//populate cell with data from campaign:
  	    	eventNameLbl.setText(campaign.getEventName()); //get event name
  	    	createdLbl.setText(campaign.getCreated().toString()); //get created
			hostNameLbl.setText(campaign.getHostName()); //get host name
			//isPlayingLbl.setText(value);
			//https://docs.oracle.com/javafx/2/ui_controls/progress.htm#CHDDJAJE
			
			setText(null); 
	        setGraphic(rootAP); //set this root element as the graphic	
        }
    }

}