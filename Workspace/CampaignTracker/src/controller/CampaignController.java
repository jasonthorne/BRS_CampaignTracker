package controller;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Campaign;
/*import model.Campaign.CampaignBuilder;*/
import model.Event;

public class CampaignController implements Rootable, Frameable{
	
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
   
    @FXML private AnchorPane rootAP;
    @FXML  private Label eventNameLbl;
    @FXML private JFXButton addUserBtn;
    @FXML private JFXListView<?> missionsLV;
    @FXML private JFXListView<?> playersLV;
    
   
    @FXML
    void initialize() {
    	
    	//+++++++++++++++++set to show this button only if user isn't a player. Use listeneer to do this!!
    	//add user to 
    	addUserBtn.setOnAction(event -> addUser());
    	
    }
	///////////////https://stackoverflow.com/questions/26313756/implementing-an-observablevalue
	
	////////////////private final Stage stage = new Stage(); //stage
    ///////////////private final Scene scene = new Scene(Rootable.getRoot(this, "/view/campaign.fxml")); //rooted scene
	
    //fxml root node:
  	private Parent root; 
  	
  	private final Campaign campaign;
  	private CampaignCellController campaignCellCtrlr;
    
  	//newly created campaign:
  	CampaignController(Campaign campaign){
  		setRoot(); //set root node
  		this.campaign = campaign;
  		setNewCampaign();
  	}
  	
	//campaign from list view cell selection:
	CampaignController(Campaign campaign, CampaignCellController campaignCellCtrlr){
		setRoot(); //set root node
		this.campaignCellCtrlr = campaignCellCtrlr;
		this.campaign = campaign;
		setExistingCampaign();
	}
	
	
	
	/*
	private BooleanProperty  wasCreated;
    private BooleanProperty finalValueProperty = new SimpleBooleanProperty(false);
    private BooleanProperty completedProperty = new SimpleBooleanProperty();
	*/
	
	private void setExistingCampaign() {
		
		//wasCreated  = new SimpleBooleanProperty(campaign.getWasCreated());
		
		
		//if players hasn't yet been updated:
		if(!campaign.getHasPlayersData()) {
			campaign.updatePlayers(); //update players data
			//++++++++++++++HERE WE NEED TO LOK FOR CAMPAIGN IN SAVED DATA IF THIS IS UNSUCCESSFUL< AND USE RTHAT ONE> AND INBFORM USER OF ERROR DOWNLOADING! 
		}
		
		if(!campaign.getUserIsPlaying(LoginController.getUserName())) {
			System.out.println("show add plyr btn");
		}else {
			System.out.println("show menu btn");
		}

		//========================================
		//campaign.updatePlayers2(this);
		//=======================================
		
		
	}
	
	private void setNewCampaign() {
		
	}
	

	//add user as player:
	private void addUser() {
		//make time stamp:
		Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		//add player to db:
		database.InsertPlayer.insert(campaign.getId(), LoginController.getUserId(), timestamp);
		//add player to campaign:
		campaign.addPlayer(LoginController.getUserName(), timestamp);
		//update campaign cell:
		campaignCellCtrlr.updateIsPlaying(campaign);
	}
	
	
	//show stage:
    /*void showStage() { 
    	System.out.println("showing campainCtrlr stage"); 
    	stage.showAndWait(); 
	}*/

    @Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/campaign.fxml"); } //set root
	@Override
	public Parent getRoot() { return root; } //get root
	@Override
	public String getViewTitle() { return "Campaign"; } 
		
	

}
