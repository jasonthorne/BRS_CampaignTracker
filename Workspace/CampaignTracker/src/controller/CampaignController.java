package controller;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

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
import model.Player;

public class CampaignController implements Rootable, Frameable{
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
   
    @FXML private AnchorPane rootAP;
    @FXML  private Label eventNameLbl;
    @FXML private JFXButton addUserBtn;
    @FXML private JFXListView<Mission> missionsLV;
    @FXML private JFXListView<Player> playersLV;
    
    @FXML
    void initialize() {
    	addUserBtn.setOnAction(event -> addUser()); //set btn event
    	addUserBtn.setVisible(false); //hide from view
		addUserBtn.setDisable(false); //disable state
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
		campaign.setPairings();//++++++++++++++++TEST
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
