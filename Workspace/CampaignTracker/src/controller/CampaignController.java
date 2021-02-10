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
    @FXML private JFXButton addPlayerBtn;
    @FXML private JFXListView<?> missionsLV;
    @FXML private JFXListView<?> playersLV;
    
   
    @FXML
    void initialize() {
    	
    	//+++++++++++++++++set to show this button only if user isn't a player. Use listeneer to do this!!
    	//add user to 
    	addPlayerBtn.setOnAction(event -> addPlayer());
    	
    }
	///////////////https://stackoverflow.com/questions/26313756/implementing-an-observablevalue
	
	////////////////private final Stage stage = new Stage(); //stage
    ///////////////private final Scene scene = new Scene(Rootable.getRoot(this, "/view/campaign.fxml")); //rooted scene
	
    //fxml root node:
  	private Parent root; 
  	
  	private final Campaign campaign;
    
	//constructor:
	CampaignController(Campaign campaign){
		setRoot(); //set root node
		
		this.campaign = campaign;
		setCampaign(campaign);
	}
	
	private BooleanProperty  wasCreated;
    private BooleanProperty finalValueProperty = new SimpleBooleanProperty(false);
    private BooleanProperty completedProperty = new SimpleBooleanProperty();
	
	
	private void setCampaign(Campaign campaign) {
		
		wasCreated  = new SimpleBooleanProperty(campaign.getWasCreated());
		
		
		//if campaign wasn't just created, and players haven't yet been updated:
		if(!campaign.getWasCreated() && !campaign.getHasPlayersData()) {
			
			System.out.println("DOWNLOADING PLAYERS");
			
			campaign.updatePlayers(campaign); //update players data
			//++++++++++++++HERE WE NEED TO LOK FOR CAMPAIGN IN SAVED DATA IF THIS IS UNSUCCESSFUL< AND USE RTHAT ONE> AND INBFORM USER OF ERROR DOWNLOADING! 
			
		}else { //???????????????

			System.out.println("has downloaded all palyers:");
			
		}
		
		//========================================
		//campaign.updatePlayers2(this);
		//=======================================
		
		
	}
	
	//update player data from db:
	public void updatePlayers() {
		
	}
	
	
	//add player to campaign:
	private void addPlayer() {
		
		//get time stamp of creation:
		Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		//add player to db:
		database.InsertPlayer.insert(campaign.getId(), LoginController.getUserId(), timestamp);
				
		//add player to campaign:
		campaign.addPlayer(campaign, LoginController.getUserName(), timestamp);
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
