package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class LoginController {
	
	
	@FXML private ResourceBundle resources;
	@FXML private URL location;

    @FXML private AnchorPane rootAP;
    
    private static final String TITLE = "Login"; 
	private final Parent root;
	private final FrameController frameCtrlr;
	
	LoginController(FrameController frameCtrlr){
		this.frameCtrlr = frameCtrlr; //set frame controller;
		
		//load the fxml file:
       	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
       	loader.setController(this); //set this class as the controller
       	
       	try {
			loader.load(); //load fxml tree
		} catch (IOException e) { e.printStackTrace(); }
		 
       	root = loader.getRoot(); //get root element 
		
       	
       	/*
       	  //load the fxml file:
        try {
        	//create loader:
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/frame.fxml")); 
        	loader.setController(this);//set this class as the controller
        	scene = new Scene(loader.load()); //load fxml into scene
        	stage.setScene(scene); //add scene to stage
        }catch (IOException e) { e.printStackTrace(); }
       	 */
	}
	
	public Parent getRoot() { return root; }

}
