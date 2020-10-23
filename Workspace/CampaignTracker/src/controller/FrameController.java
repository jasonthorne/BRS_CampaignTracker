package controller;

import com.jfoenix.controls.JFXButton;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Stack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.FxmlPath;

public class FrameController implements Rootable{

	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    //root fxml element & children:
    @FXML private BorderPane rootBP;
	@FXML private BorderPane headerBP;
	@FXML private AnchorPane headerTopAP;
	@FXML private Label appLbl;
	@FXML private AnchorPane headerBtmAP;
	@FXML private Label pageLbl;
	@FXML private JFXButton backBtn;
	@FXML private JFXButton fwrdBtn;
	@FXML private AnchorPane bodyAP;
	@FXML private AnchorPane footerAP;
    
    @FXML
    void initialize() {
    	//set button actions:
    	backBtn.setOnAction(event -> moveBkwrd());
    	fwrdBtn.setOnAction(event -> moveFwrd());
    	
    	//initially disable btns:
		backBtn.setDisable(true);
    	fwrdBtn.setDisable(true);
    	
    	//add login.fxml to body:
    	addRootToBody(loginCtrlr.getRoot()); 
    }
    
    private final Stage stage = new Stage(); //stage
    private Scene scene; //scene
    
    //login.fxml controller:
    private final LoginController loginCtrlr = new LoginController(this);
    
    //stacks of parent nodes. One for forward moves, one for backward moves:
  	private final Stack<Parent>fwrdMoves = new Stack<Parent>(); 
  	private final Stack<Parent>bkwrdMoves = new Stack<Parent>();
  	
    //constructor:
    public FrameController(){
    	//create scene with fxml root:
    	scene = new Scene(loadRoot.apply(this, FxmlPath.FRAME_FXML));
    	stage.setScene(scene); //add scene to stage
    }	
    
    //show stage:
    public void showStage() { stage.showAndWait(); }
    
    //add root to body AnchorPane:
  	private void addRootToBody(Parent root){
  		//clears the children of this element & replaces with root:
  		bodyAP.getChildren().setAll(root);
  	}
  	
  	//move to first logged in view:
  	void loginMove(Parent root) {
  		fwrdMoves.push(root); //mark root as fwrd move
    	addRootToBody(root); //add root to bodyAP
  	}
  	
  	//move forward to new view:
  	void moveFwrd(Parent root) {
  		
  		//turn on back button:
  		if(backBtn.isDisabled()) { backBtn.setDisable(false); }
  		
  		//if there are stored bkwrdMoves:
    	if(!bkwrdMoves.isEmpty()) {
    		//turn on back button if disabled:
        	if(backBtn.isDisabled()) { backBtn.setDisable(false); } 
    		bkwrdMoves.pop(); //remove obsolete element (as traversing a new path)
        	//turn off fwrd buttons if all bkwrdMoves are removed:
        	if(bkwrdMoves.isEmpty()) { fwrdBtn.setDisable(true); }
    	}
    	
    	fwrdMoves.push(root); //mark root as fwrd move
    	addRootToBody(root); //add root to bodyAP
	}
	
  	//move forward to previous view:
	private void moveFwrd() {
		
		//turn on back button:
    	if(backBtn.isDisabled()) { backBtn.setDisable(false); } 
    	
    	fwrdMoves.push(bkwrdMoves.pop()); //return previous view to fwrdMoves
    	
    	//disable fwrd btn if you've reached end of traversed path:
    	if(bkwrdMoves.isEmpty()) { fwrdBtn.setDisable(true); } 
    	
    	addRootToBody(fwrdMoves.peek()); //add root to frame
	}
	
	//move backwards to previous view:
	private void moveBkwrd() {
		
		//turn on fwrd button:
    	if(fwrdBtn.isDisabled()) { fwrdBtn.setDisable(false); }
    	
    	//move current fwrd move to bkwrdMoves:
    	bkwrdMoves.push(fwrdMoves.pop()); 
    	
    	//disable back btn if now at last element in stack:
    	if(fwrdMoves.size() == 1) { backBtn.setDisable(true); }
    		
    	addRootToBody(fwrdMoves.peek()); //add root to frame
	}
}
