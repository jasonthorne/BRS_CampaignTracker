package controller;

import java.net.URL;
import java.sql.Connection;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
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
import database.ConnectDB;
import database.SelectPlayerID;
import executor.Taskable;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Player;
import model.Player.PlayerBuilder;

public class LoginController implements Rootable, Fadeable, Frameable, Taskable  {
	
	@FXML private ResourceBundle resources;
	@FXML private URL location;

	//root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private Label errorLbl;
    @FXML private JFXButton signupBtn;
	@FXML private JFXTextField nameTxtFld;
	@FXML private JFXPasswordField pswrdTxtFld;
	@FXML private JFXButton loginBtn;
    
	@FXML
    void initialize() {
		//initially hide error label:
		errorLbl.setVisible(false);  //+++++++++++++make this better! :P
    	//set btn actions:
		signupBtn.setOnAction(event -> signupUsr());
		loginBtn.setOnAction(event -> loginUsr());
    }
	
	//id of logged in player:
	private static int playerId;
	
	//fxml root node:
  	private Parent root;
	
	//controllers:
	private final CampaignsController campaignsCtrlr;
	private final SignupController signupCtrlr;
	/*
	//callable to check db for id of player with given name & password:
	Future<Integer>futurePlayerId = Taskable.singleThreadExec.submit(()->{
		return SelectPlayerID.select( //call method which invokes select statement
				nameTxtFld.getText().trim(), //pass entered name into statement
				pswrdTxtFld.getText().trim()); //pass entered pswrd into statement
	});
	*/
	//constructor:
	LoginController() {
		setRoot(); //set root node
		this.campaignsCtrlr = new CampaignsController(); //create campaigns controller
		this.signupCtrlr = new SignupController(); //create signup controller
		
		//========================================
		/** this is just a placeholder for pushing to db, before login.
		 * should be a push done in frameController first to add ip to db, for socket creation.*/
		new Thread(() ->  {
			SelectPlayerID.select(
					nameTxtFld.getText().trim(), 
					pswrdTxtFld.getText()); 	
		}).start();
		//======================================
	}
	
	private void signupUsr() {
		/** ++++++++ make back button turn on (but obv remove when back at login!! */
		FrameController.getFrameCtrlr().setPreLoginFrameable(signupCtrlr);
	}
	
	private void loginUsr() {
		
		//look into this a little more +++++++++++++++++++++++++++++++++++++but not too much :P 
		ExecutorService singleThreadExec = Executors.newSingleThreadExecutor();
		
		//callable to check db for id of player with given name & password:
		Future<Integer>futurePlayerId = singleThreadExec.submit(()->{
			return SelectPlayerID.select( //call method which invokes select statement
					nameTxtFld.getText().trim(), //pass entered name into statement
					pswrdTxtFld.getText().trim()); //pass entered pswrd into statement
		});
		
		/** javadocs tut - START HERE! */
		//https://docs.oracle.com/javafx/2/threads/jfxpub-threads.htm
		//https://www.genuinecoder.com/javafx-splash-screen-loading-screen/
		
		errorLbl.setVisible(false); //ensure error label is off
		
		//if name & password fields aren't empty: 
		if(!nameTxtFld.getText().trim().equals("") && !pswrdTxtFld.getText().trim().equals("")) {
			
			/* get futurePlayerId callable result, 
			 * which checks db for id of player with given name & password */
			/*try {
				playerId = (int) futurePlayerId.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}finally { shutDownExec(); }  //shut down executor service*/
				
			
			playerId = SelectPlayerID.select( //call method which invokes select statement
					nameTxtFld.getText().trim(), //pass entered name into statement
					pswrdTxtFld.getText().trim()); //pass entered pswrd into statement
					
			//if result is > 0 then a valid id was found:
			if (playerId > 0) {
				
				FrameController.getFrameCtrlr().setPlayerLbl(nameTxtFld.getText().trim()); //set name
				Fadeable.fade(root, FadeOption.FADE_OUT); //fade out this view
				FrameController.getFrameCtrlr().loginMove(campaignsCtrlr); //move to campaigns view
				
			}else {
				//warn user with error msg that account not found.
				System.out.println("no dice");
				//shutDownExec();
			}
		
		}else { // a field was blank:
			/** +++++++++++++++++ make this label thing better :P +++++++++ */
			//errorLbl.setVisible(true); //inform user with label
			FrameController.getFrameCtrlr().setViewLbl("Enter Username and Password");
			//shake elements:
			Arrays.asList(nameTxtFld, pswrdTxtFld).forEach(fxml -> Shakeable.shake(fxml));
		} 
			
	}
	
	@Override 
	public void shutDownExec() { //shut down executor service
		if(!Taskable.singleThreadExec.isShutdown()) {
			Taskable.singleThreadExec.shutdown(); } 
	}
	
	public static int getPlayerId() { return playerId; } //get playerId
	
	@Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/login.fxml"); } //set root
	@Override
	public Parent getRoot() { return root; } //get root
	
	@Override 
	public String getViewTitle() { return "Login"; } //get title

}
