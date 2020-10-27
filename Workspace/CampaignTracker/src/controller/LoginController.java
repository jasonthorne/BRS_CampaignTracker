package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import animation.Fadeable;
import animation.Fadeable.FadeOption;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import view.FxmlPath;

public class LoginController extends FrameContent implements Rootable, Fadeable {
	
	@FXML private ResourceBundle resources;
	@FXML private URL location;

	//root fxml element & children:
    @FXML private AnchorPane rootAP;
	@FXML private JFXTextField usrnameTxtFld;
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
	private final FrameController frameCtrlr;
	private final CampaignsController campaignsCtrlr;
	
	//constructor:
	LoginController(FrameController frameCtrlr) {
		setRoot(); //set root node
		this.frameCtrlr = frameCtrlr; //assign frame controller (for loginUsr)
		this.campaignsCtrlr = new CampaignsController(); //create campaigns controller
		super.setFrameController(frameCtrlr); //store frame controller in super
	}
	
	private void loginUsr() {
		
		//check db for username & pwrd
		
		//send an anonomous player
		//add username to frame label:
		frameCtrlr.setPlayerLbl("bob smith");
		
		Fadeable.fade(root, FadeOption.FADE_OUT); //fade out root
		frameCtrlr.loginMove(campaignsCtrlr.getRoot()); //move to campaigns
		//////////////////frameCtrlr.loginMove(campaignsCtrlr); //move to campaigns
	}
	
	@Override
	void setRoot() { root = Rootable.getRoot(this, FxmlPath.LOGIN); } //set root
	
	@Override
	Parent getRoot() { return root; } //get root
	
	public static int getPlayerId() { return playerId; } //get playerId

	@Override
	void setViewTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	String getViewTitle() {
		// TODO Auto-generated method stub
		return "Login";
	}

	
	
}
