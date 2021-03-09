package controller;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.TreeMap;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Campaign;
/*import model.Campaign.CampaignBuilder;*/
import model.Event;
import model.Mission;
import model.Pairing;
import model.PairingOLD;
import model.Player;

public class CampaignController implements Rootable, Frameable, Disableable{
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
   
    @FXML private AnchorPane rootAP;
    @FXML private Label eventNameLbl;
    @FXML private JFXButton addUserBtn;
    @FXML private JFXButton makePairingsBtn;
    @FXML private JFXListView<Mission> missionsLV;
    @FXML private JFXListView<Player> playersLV;
    
    private final Map<Integer, List<String>>TEST = new HashMap<Integer, List<String>>(); //++++++++++++++TEST
    
    @FXML
    void initialize() {
    	addUserBtn.setOnAction(event -> addUser()); //set btn event
    	Disableable.disableJfxBtn(addUserBtn); //disable btn
    	
		makePairingsBtn.setOnAction(event -> makePairings());
		Disableable.disableJfxBtn(makePairingsBtn); //disable btn
    }
	
    //fxml root node:
  	private Parent root; 
  	
  	private final Campaign campaign;
  	private CampaignCellController campaignCellCtrlr;
  	
  	//observable list of players:
    private final ObservableList<Player>observPlayers = FXCollections.observableArrayList();
    
  	//newly created campaign:
  	CampaignController(Campaign campaign){
  		setRoot(); //set root node
  		this.campaign = campaign; //assign campaign
  		setListViews(); //set list views 
  	}
  	
	//campaign from list view cell selection:
	CampaignController(Campaign campaign, CampaignCellController campaignCellCtrlr){
		this(campaign);
		this.campaignCellCtrlr = campaignCellCtrlr;
		initCampaign();	//initialize campaign
		
	}
	
	private void setListViews() {
		 //add players to observable list:
		observPlayers.addAll(campaign.getPlayers());
		//add observable events to listView:
		playersLV.setItems(observPlayers);
		//set listView cellFactory to create PlayerCellControllers:
		playersLV.setCellFactory(PlayerCellController -> new PlayerCellController());
	}
	
	//initialize campaign:
	private void initCampaign() { //++++++++++++++NEEDS BETTER NAME :P +++++++++
		
		//++++++++++++I THINK all of these chacks can be doen with a boolean listener. I think!! 
		//++++++++++++++HERE WE NEED TO LOK FOR CAMPAIGN IN SAVED DATA IF THIS IS UNSUCCESSFUL< AND USE RTHAT ONE> AND INBFORM USER OF ERROR DOWNLOADING! 
		//update players data if needed:
		if(!campaign.getHasPlayersData()) { campaign.updatePlayers(); }
		
		//if user isn't playing:
		if(!campaign.getUserIsPlaying(LoginController.getUserName())) {
			addUserBtn.setVisible(true); //show addUserBtn
			addUserBtn.setDisable(false); //enable addUserBtn
		}else {
			System.out.println("show menu btn");
			//++++++++++show menu button
			
			//if user is host & first pairings haven't yet been created & there are other players:
			if((LoginController.getUserName().equals(campaign.getHostName())
					&& !campaign.hasMissionsTEST()) && (observPlayers.size()>1)){
				Disableable.enableJfxBtn(makePairingsBtn); //enable makePairingsBtn
			}
		}
	}
	
	
	//add user as player:
	private void addUser() {
		//make time stamp:
		Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		//add player to db:
		database.InsertPlayer.insert(campaign.getId(), LoginController.getUserId(), timestamp);
		//create local player:
		Player player = new Player(LoginController.getUserName(), timestamp); 
		
		campaign.addPlayer(player); //add player to campaign
		observPlayers.add(player); //add player to observable players
		campaignCellCtrlr.updateIsPlaying(campaign); //update campaign cell
	}
	
	//make player pairings:
	private void makePairings() {
		
		//+++++++++++show alert asking t oconfirm make pairings. then...s++++
		///////////campaign.setPairings();
		campaign.setPairings();
	}
	
	
    @Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/campaign.fxml"); } //set root
	@Override
	public Parent getRoot() { return root; } //get root
	@Override
	public String getViewTitle() { return "Campaign"; } 
		
	///////////////https://stackoverflow.com/questions/26313756/implementing-an-observablevalue
		
	////////////////private final Stage stage = new Stage(); //stage
	///////////////private final Scene scene = new Scene(Rootable.getRoot(this, "/view/campaign.fxml")); //rooted scene
	//show stage:
    /*void showStage() { 
    	System.out.println("showing campainCtrlr stage"); 
    	stage.showAndWait(); 
	}*/

}
