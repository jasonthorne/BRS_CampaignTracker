package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Campaign;
import view.FxmlPath;

public class CampaignCellController extends JFXListCell<Campaign> implements Rootable {

	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private Label nameLbl;
    @FXML private JFXButton showCampaignBtn;
    
    @FXML
    void initialize() {
      
    }
    
    //root element for this controller:
  	private final Parent root = Rootable.getRoot(this, FxmlPath.CAMPAIGN_CELL);
	
	private CampaignsController campaignsCtrlr;
	
	//constructor:
	CampaignCellController(CampaignsController campaignsCtrlr){
		this.campaignsCtrlr = campaignsCtrlr; //assign campaigns controller
	}
	
	//update cell with campaign data:
	@Override 
	protected void updateItem(Campaign campaign, boolean isEmpty) {
        super.updateItem(campaign, isEmpty);
        
  		if (isEmpty || campaign == null) {
  	        setText(null);
  	        setGraphic(null);
  	    } else {
			//populate nameLbl with data from campaign:
			nameLbl.setText(campaign.getName()); 
			 
			setText(null); 
	        setGraphic(rootAP); //set this root element as the graphic	
        }
    }

}