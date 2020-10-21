package controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FrameController extends Traverse{

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
    	//set traverse buttons:
    	setTraverseBtns(headerBtmBackBtn, headerBtmFwrdBtn);
    	
    }
    
    private final Stage stage;
    private Scene scene;
    
    //constructor:
    public FrameController(){
    	
    	//create stage:
        stage = new Stage(); 
        
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
    
    
    
    
}
