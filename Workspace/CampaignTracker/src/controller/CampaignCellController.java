package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    
    @FXML
    void initialize() {

    }
    
    //root element for this controller:
  	private final Parent root = Rootable.getRoot(this, "/view/campaignCell.fxml");
  
  	//campaigns controller:
	private final CampaignsController campaignsCtrlr;
	
	//--------------------------
	private BooleanProperty isPlaying; //= new SimpleBooleanProperty(campaign.getWasCreated());
	//-------------------
	//constructor:
	CampaignCellController(CampaignsController campaignsCtrlr){
		this.campaignsCtrlr = campaignsCtrlr; 
	}
	
	//update cell with campaign data:
	@Override 
	protected void updateItem(Campaign campaign, boolean isEmpty) {
        super.updateItem(campaign, isEmpty);
        
  		if (isEmpty || campaign == null) {
  	        setText(null);
  	        setGraphic(null);
  	    } else {
  	    	
  	    	/*
  	    	isPlaying = new SimpleBooleanProperty(campaign.getUserIsPlaying(LoginController.getUserName())); //+++++++++++++++++
  	    	
  	    	isPlaying.addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
					System.out.println("isPlaying is now: " + isPlaying);
					
				}
  	    	});*/
  	    	
  	    	//populate cell with data from campaign:
  	    	eventNameLbl.setText(campaign.getEventName()); //get event name
  	    	createdLbl.setText(campaign.getCreated().toString()); //get created
			hostNameLbl.setText(campaign.getHostName()); //get host name
			isPlayingLbl.setText(campaign.getUserIsPlaying(LoginController.getUserName())?"Yes":"No"); //get if user is playing
			progressPI.setProgress(campaign.getProgress()); //get progress
		
			//add double-click event:
	        setOnMouseClicked(mouseClickedEvent -> {
                if (mouseClickedEvent.getButton().equals(MouseButton.PRIMARY) && mouseClickedEvent.getClickCount() == 2) {
                	Frameable.changeView(campaignsCtrlr.getRoot(), new CampaignController(campaign)); //navigate to campaign
                } /**https://stackoverflow.com/questions/51536489/how-can-i-detect-javafx-double-click-on-listview*/
            });
	        
	        //+++++++++++++++++++++++ADD SINGLE CLIKC EVENT SHOWING POP UP, TELLING USER TO DBL CLICK TO OPEN ++++++
	        setText(null); 
	        setGraphic(rootAP); //set this root element as the graphic
        }
    }
	
	
	
	
	
	
	
	
	
}