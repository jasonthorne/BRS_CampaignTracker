package controller;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
		
		//========================
		String a = "a";
		String CONST = "b";
		
		/*
		System.out.println("a.hashCode() " + a.hashCode());
		System.out.println("CONST.hashCode() " + CONST.hashCode());
		
		System.out.println("Objects.hash(CONST) " + Objects.hash(CONST)); //NOT THE SAME AS .hashCode()!! 
		
		System.out.println("Objects.hashCode() " + Objects.hashCode(CONST));
		
		System.out.println("Objects.hash(a, CONST) " + Objects.hash(a, CONST));
		
		TEST.put(Objects.hash(a, CONST), Arrays.asList(a, "unknown var"));
		
		System.out.println("TEST: " + TEST);
		
		System.out.println("TEST.get:" +  TEST.get(Objects.hash(a, CONST)));
		*/
		
		//READ ALL THESE NOTES :P +++++++++++++++++++++
		//NOW WHAT ABOUT A HASH KEY for no bye??
		//and what if player to find (leave) had a bye this round?? - check reserves for player first maybe :P?????
		
		//so if there IS a bye, the the key is only made from hashing a BYE. 
		//If there ISNT a bye, then key is made from name + hashing a NO_BYE
		//BOTH OF THESZE CHECKS WILL NEED PERFORMED AS WE DONT KNOW WHICH CONDITION WILL EXIST FOR TARGET PLAYER EACH ROUND ++++++++++
		//actually, will they!! WE know if were looking for a bye key by the length of the players list!! :P
		//USE DoubleKey object NOT Strings in map :P and override the hashcode method as per eclipse recommendation! 
		
		//EXTRA NOTES:
		/*
		 * each players key is hashed from their name & either BYE or NO_BYE
		 * when searching for a player and there is odd number:
		 * 	check each round of pairings for their name without bye THEN with bye - UNTILL THE BYE FORTHAT ROUND IS FOUND
		 * if even number of players, then just check for that player without bye.  
		 */
		
		/*
		 * Above is WRONG
		 * player is given their name + NO_BYE hash OR a BYE Hash.
		 * to remove player with odd:
		 * check a round for name + NO_BYE hash. If not their then USE the BYE key to grab player element for removal.
		 * 
		 * If even number of players, then just check each round for name + NO_BYE hash to grab player element for removal.
		 * 
		 * if adding new player:
		 * if odd number of players - grab each rounds BYE hash and replace element with new player name.
		 * If even number of players: assign bye to new player, then for each round add a new pairing with exswting player & new player
		 * 
		 * BOOM!! 
		 *  
		 * 
		 */
		
		//IF LOOKING FOR 'BYE' part of key for adding a new player:
		//ummmmm..........
		
		//==========================
		
		
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
			
			if((LoginController.getUserName().equals(campaign.getHostName()) && !campaign.hasMissionsTEST())) {
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
		campaign.setPairingsTEST();
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
