package controller;

import com.jfoenix.controls.JFXButton;

import animation.Fadeable;
import animation.Fadeable.FadeOption;
import database.SelectUserID;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Player;
import database.SelectUserID;

public class FrameController implements Rootable, Fadeable {

	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    //root fxml element & children:
    @FXML private BorderPane rootBP;
	@FXML private BorderPane headerBP;
	@FXML private AnchorPane headerTopAP;
	@FXML private JFXButton signupBtn;
	@FXML private Label playerLbl;
	@FXML private Label appLbl;
	@FXML private JFXButton rulesBtn;
	@FXML private AnchorPane headerBtmAP;
	@FXML private Label viewLbl;
	@FXML private JFXButton backBtn;
	@FXML private JFXButton fwrdBtn;
	@FXML private AnchorPane bodyAP;
	@FXML private AnchorPane footerAP;
    
    @FXML
    void initialize() {
    	//set button actions:
    	signupBtn.setOnAction(event -> signupUsr());
    	rulesBtn.setOnAction(event -> System.out.println("rules btn"));
    	backBtn.setOnAction(event -> moveBkwrd());
    	fwrdBtn.setOnAction(event -> moveFwrd());
    	
    	//initially disable btns:
		backBtn.setDisable(true);
    	fwrdBtn.setDisable(true);
    	
    	//add login.fxml to body:
    	addRootToBody(loginCtrlr.getRoot());
    	
    	//set view title:
    	setViewLbl(loginCtrlr.getViewTitle());
    	
    	//setPreLoginFrameable(loginCtrlr);
    }
    
    //====================================
    
    /*void setPreLoginFrameable(Frameable frameable) {
    	addRootToBody(frameable.getRoot());
    	setViewLbl(frameable.getViewTitle()); 
    }*/
    
    /*
    void moveToSignup(Frameable frameable) {
    	//turn on back button:
  		if(backBtn.isDisabled()) { backBtn.setDisable(false); }
    	fwrdMoves.push(frameable); //mark as forward move
    	addRootToBody(frameable.getRoot());
    	setViewLbl(frameable.getViewTitle()); 
    }
    */
    
    //====================================
    
    private final Stage stage = new Stage(); //stage
    private final Scene scene; //scene
    
    //frame.fxml controller:
  	private static FrameController singleFrameCtrlr = null;
    
    //login.fxml controller:
    private final LoginController loginCtrlr;
   
    //stacks for navigating controllers who's views are added to this controller's view: 
  	private final Stack<Frameable>fwrdMoves = new Stack<Frameable>(); //records forward moves
  	private final Stack<Frameable>bkwrdMoves = new Stack<Frameable>(); //records backward moves
  	
  	//constructor:
    private FrameController(){
    	loginCtrlr = new LoginController(); //instantiate login controller
    	scene = new Scene(Rootable.getRoot(this, "/view/frame.fxml")); //add root to scene
    	stage.setScene(scene); //add scene to stage
    	
    	//==============================
		//do some initial connection to db, to push your ip for example 
    	//this helps spped up initial login push too.
    	//================================
    }	
  	 
  	//get frame controller:
    public static FrameController getFrameCtrlr() {
    	//create singleton if necessary:
        if (singleFrameCtrlr == null) { singleFrameCtrlr = new FrameController(); }
        return singleFrameCtrlr; 
    }
    
    //show stage:
    public void showStage() { stage.showAndWait(); }
    
    //add root to body AnchorPane:
  	private void addRootToBody(Parent root){
  		root.setOpacity(0.0); //hide root from view
  		bodyAP.getChildren().setAll(root); //replace bodyAP's children with root
  		Fadeable.fade(root, FadeOption.FADE_IN); //fade in root
  	}
  	
  	private void signupUsr() {
  		signupBtn.setDisable(true); //disable btn
		/** ++++++++ make back button turn on (but obv remove when back at login!! */
  		SignupController signupCtrlr = new SignupController(loginCtrlr);
  		setViewLbl(signupCtrlr.getViewTitle());
		addRootToBody(signupCtrlr.getRoot()); //quick fix!!! +++++++
	}
  	
  	//move to first logged in view:
	void loginMove(Frameable frameable) { //++++++++++ INNIT MOVE or somethig!
  		fwrdMoves.push(frameable); //mark as forward move
    	addRootToBody(frameable.getRoot()); //add root to bodyAP
    	setViewLbl(frameable.getViewTitle()); //add view title
  	}
  	
  	//move forward to new view:
	void moveFwrd(Frameable frameable) {
  		
  		//turn on back button:
  		if(backBtn.isDisabled()) { backBtn.setDisable(false); }
  		
  		//if there are stored bkwrdMoves:
    	if(!bkwrdMoves.isEmpty()) {
    		bkwrdMoves.pop(); //remove obsolete element (as traversing a new path)
        	//turn off fwrd buttons if all bkwrdMoves are removed:
        	if(bkwrdMoves.isEmpty()) { fwrdBtn.setDisable(true); }
    	}
    	
    	fwrdMoves.push(frameable); //mark as forward move
    	addRootToBody(frameable.getRoot()); //add root to bodyAP
    	setViewLbl(frameable.getViewTitle()); //add view title
	}
  	
  	//move forward to previous view:
	private void moveFwrd() {
		
		//turn on back button:
    	if(backBtn.isDisabled()) { backBtn.setDisable(false); } 
    	fwrdMoves.push(bkwrdMoves.pop()); //return previous view to fwrdMoves
    	//disable fwrd btn if you've reached end of traversed path:
    	if(bkwrdMoves.isEmpty()) { fwrdBtn.setDisable(true); } 
    	
    	addRootToBody(fwrdMoves.peek().getRoot()); //add root to frame
    	setViewLbl(fwrdMoves.peek().getViewTitle()); //add view title
	}
	
	//move backwards to previous view:
	private void moveBkwrd() {

		//turn on fwrd button:
    	if(fwrdBtn.isDisabled()) { fwrdBtn.setDisable(false); }
    	//move current fwrd move to bkwrdMoves:
    	bkwrdMoves.push(fwrdMoves.pop()); 
    	//disable back btn if now at last element in stack:
    	if(fwrdMoves.size() == 1) { backBtn.setDisable(true); }
    		
    	addRootToBody(fwrdMoves.peek().getRoot()); //add root to frame
    	setViewLbl(fwrdMoves.peek().getViewTitle()); //add view title
	}
	
	/** +++++++++++++++ figure out better fades for both of these below!! +++++++++++++++:P */
	//set player name label:
	void setPlayerLbl(String playerName) {
		Fadeable.fade(playerLbl, FadeOption.FADE_OUT); //fade out label
		playerLbl.setText(playerName); //change label text
		Fadeable.fade(playerLbl, FadeOption.FADE_IN); //fade in label
	}
	
	//set view label:
	void setViewLbl(String viewTitle) {
		Fadeable.fade(viewLbl, FadeOption.FADE_OUT); //fade out label
		viewLbl.setText(viewTitle); //change label text
		Fadeable.fade(viewLbl, FadeOption.FADE_IN); //fade in label
	}
}
