package controller;

import java.net.URL;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;

import animation.Fadeable;
import animation.Shakeable;
import animation.Fadeable.FadeOption;
import database.SelectPlayerID;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Player;
import model.Player.PlayerBuilder;
import view.FxmlPath;

public class LoginController implements Rootable, Fadeable, Frameable  {
	
	@FXML private ResourceBundle resources;
	@FXML private URL location;

	//root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private Label errorLbl;
	@FXML private JFXTextField nameTxtFld;
	@FXML private JFXPasswordField pswrdTxtFld;
	@FXML private JFXButton loginBtn;
    
	@FXML
    void initialize() {
		//initially hide error label:
		errorLbl.setVisible(false); 
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
		
		
		errorLbl.setVisible(false); //inform user with label
		
		//https://www.genuinecoder.com/javafx-splash-screen-loading-screen/
		
	
		//if name & password fields aren't empty: 
		if(!nameTxtFld.getText().trim().equals("") && !pswrdTxtFld.getText().trim().equals("")) {
			
	
		//password stuff.
		////https://docs.oracle.com/javase/tutorial/uiswing/components/passwordfield.html
		
		
		
		/** SPLASH SCREEN +++++++++++++++++ */
		//++++++++++++++++++https://www.genuinecoder.com/javafx-splash-screen-loading-screen/
			
		/*	
		System.out.println(pswrdTxtFld.getText().trim().toCharArray().toString()); //+++++++++opps!
		
		if(pswrdTxtFld.getText().trim().toCharArray().length > 0) {
			System.out.println("full");
		}*/
	
		
		//check db for id of player with given name & password: 
		int idCheck = SelectPlayerID.select(
				nameTxtFld.getText().trim(), 
				pswrdTxtFld.getText()); 
				
		//if result is > 0 then it's a valid id:
		if (idCheck > 0) {
			playerId = idCheck; //store id
			
			//ew Thread(() -> {
				FrameController.getFrameCtrlr().setPlayerLbl(nameTxtFld.getText().trim()); //set name
				Fadeable.fade(root, FadeOption.FADE_OUT); //fade out this view
				FrameController.getFrameCtrlr().loginMove(campaignsCtrlr); //move to campaigns view
			//}).start();
		}else {
			//warn user 
			System.out.println("no dice");
		}
		
			//Fadeable.fade(root, FadeOption.FADE_OUT); //fade out root
			//FrameController.getFrameCtrlr().loginMove(campaignsCtrlr); //move to campaigns
		
		
		}else { // a field was blank:
			/** +++++++++++++++++ make this label thing better :P +++++++++ */
			errorLbl.setVisible(true); //inform user with label
			//shake elements:
			Arrays.asList(nameTxtFld, pswrdTxtFld).forEach(fxml -> Shakeable.shake(fxml));
		} 
			
		
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
