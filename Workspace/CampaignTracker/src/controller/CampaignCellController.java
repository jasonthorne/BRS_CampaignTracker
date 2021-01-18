package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseButton;
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
    @FXML private JFXButton showCampaignBtn;
    
    @FXML
    void initialize() {

    }
    
    //root element for this controller:
  	private final Parent root = Rootable.getRoot(this, "/view/campaignCell.fxml");
  
  	//campaigns controller:
	private final CampaignsController campaignsCtrlr;

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
  	    	//populate cell with data from campaign:
  	    	eventNameLbl.setText(campaign.getEventName()); //get event name
  	    	createdLbl.setText(campaign.getCreated().toString()); //get created
			hostNameLbl.setText(campaign.getHostName()); //get host name
			isPlayingLbl.setText((campaign.userIsPlaying(LoginController.getUserName())?"Yes":"No")); //if user is playing
			progressPI.setProgress(campaign.getProgress()); //get progress
			//https://docs.oracle.com/javafx/2/ui_controls/progress.htm
			
			//add double-click event:
	        setOnMouseClicked(mouseClickedEvent -> {
                if (mouseClickedEvent.getButton().equals(MouseButton.PRIMARY) && mouseClickedEvent.getClickCount() == 2) {
                    System.out.println("double clicked: " + campaign.getId());    
                    
                    if(!campaign.getIsAllLoaded()){ //+++++++++++++++++++++++++++NEED TO STORE THIS IN CAMPAIGN CONTROLLER NOT CAMPAIGN!! :P
                    	  System.out.println("wasnt clicked ");
                    	  //////campaign. = true;
                    }else {
                    	  System.out.println("was clicked "); 
                    }
                    
                  //navigate to campaign:
                  Frameable.changeView(campaignsCtrlr.getRoot(), new CampaignController(campaign));
                  /////////////////////////https://www.geeksforgeeks.org/stack-contains-method-in-java-with-example/   
                   
                }
                /**https://stackoverflow.com/questions/51536489/how-can-i-detect-javafx-double-click-on-listview*/
            });
	        
	        setText(null); 
	        setGraphic(rootAP); //set this root element as the graphic
        }
    }
	
	

}