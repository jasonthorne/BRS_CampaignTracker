package controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
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

public class FrameController {

	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    //root fxml element & children:
    @FXML private BorderPane rootBP;
    	@FXML private BorderPane headerBP;
    		@FXML private AnchorPane headerTopAP;
    			@FXML private Label headerTopLbl;
    		@FXML private AnchorPane headerBtmAP;
    			@FXML private Label headerBtmLbl;
    			@FXML private JFXButton headerBtmBackBtn;
    			@FXML private JFXButton headerBtmFwrdBtn;
    	@FXML private AnchorPane bodyAP;
    	@FXML private AnchorPane footerAP;
    
    @FXML
    void initialize() {
    	//set btn actions:
    	headerBtmBackBtn.setOnAction(event -> moveBkwrd());
    	headerBtmFwrdBtn.setOnAction(event -> moveFwrd());
    	addRootToBody(loginCtrlr.getRoot()); //add login.fxml to body
    }
    
    private final Stage stage = new Stage(); //stage
    private Scene scene; //scene
    
    private final LoginController loginCtrlr; //login.fxml controller
    
  	//user traversal of bodyAP root controllers:
  	private Stack<Traversable>fwrdMoves = new Stack<Traversable>(); 
  	private Stack<Traversable>bkwrdMoves = new Stack<Traversable>();
  	private Traversable currntCtrlr; //current controller of bodyAP root
  	
    //constructor:
    public FrameController(){
         loginCtrlr = new LoginController(this);  //create login controller
        
         //fwrdMoves.push(Campaigns); //add logged controller as first element
         
        //load the fxml file:
        try {
        	//create loader:
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/frame.fxml")); 
        	loader.setController(this);//set this class as the controller
        	scene = new Scene(loader.load()); //load fxml into scene
        	stage.setScene(scene); //add scene to stage
        }catch (IOException e) { e.printStackTrace(); }
    }
    
    //show stage:
    public void showStage() { stage.showAndWait(); }
    
    //add root to body AnchorPane:
  	void addRootToBody(Parent root){
  		//clears the children of this element & replaces with root:
  		bodyAP.getChildren().setAll(root);
  	}
  	
  	void moveFwrd(Traversable targetPos) {
  		System.out.println("moveFwrd(Traversable)");
	}
	
	private void moveFwrd() {
		System.out.println("moveFwrd");
	}
	
	private void moveBkwrd() {
		System.out.println("moveBkwrd");
		
	}
  	
    
    
}
