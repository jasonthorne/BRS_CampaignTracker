package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import animation.Fadeable;
import database.SelectPlayer;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import model.Player;
import model.Player.PlayerBuilder;
import view.FxmlPath;

public class LoginController implements Rootable, Fadeable, Frameable  {
	
	@FXML private ResourceBundle resources;
	@FXML private URL location;

	//root fxml element & children:
    @FXML private AnchorPane rootAP;
	@FXML private JFXTextField nameTxtFld;
	@FXML private JFXPasswordField pswrdTxtFld;
	@FXML private JFXButton loginBtn;
    
	@FXML
    void initialize() {
    	//set btn actions:
		loginBtn.setOnAction(event -> loginUsr());
    }
	
	//id of logged in player:
	private static int playerId;
	
	//fxml root node:
  	private Parent root;
	
	//controllers:
	private final CampaignsController campaignsCtrlr;
	
	//constructor:
	LoginController() {
		setRoot(); //set root node
		this.campaignsCtrlr = new CampaignsController(); //create campaigns controller
	}
	
	private void loginUsr() {
		
		//if name & password fields aren't empty: 
		/** ++++++if(!nameTxtFld.getText().trim().equals("") && !pswrdTxtFld.getText().trim().equals("")) { */
			
			//check db for player:
			SelectPlayer.select(
					new PlayerBuilder()
					.setName(nameTxtFld.getText().trim())
					.setPswrd(pswrdTxtFld.getText().trim())
					.build());
			
			//send an anonomous player\
			//add username to frame label:
			////////////////////////////////////frameCtrlr.setPlayerLbl("bob smith");
			
			Fadeable.fade(root, FadeOption.FADE_OUT); //fade out root
			FrameController.getFrameCtrlr().loginMove(campaignsCtrlr); //move to campaigns
			
		/** +++++++++}else { System.out.println("oi!"); } +++++TUTN ERROR HANDLING BACK ON LATER! +++++ */
			
		
	}
	
	public static int getPlayerId() { return playerId; } //get playerId
	
	@Override
	public void setRoot() { root = Rootable.getRoot(this, FxmlPath.LOGIN); } //set root
	@Override
	public Parent getRoot() { return root; } //get root
	
	@Override 
	public void setViewTitle() { }
	@Override 
	public String getViewTitle() { return "Login"; }

	
	
}
