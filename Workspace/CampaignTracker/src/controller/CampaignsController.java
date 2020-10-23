package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import view.ViewPath;

public class CampaignsController implements Rootable{
	
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    
    @FXML
    private AnchorPane rootAP;
    @FXML
    private Label testLbl;
    @FXML
    private JFXButton btnTest;
    
    @FXML
    void initialize() {
    	btnTest.setOnAction(event -> goToA1());
    	
    }
	
    //root element for this controller:
	private final Parent root = loadRoot.apply(this, ViewPath.CAMPAIGNS_FXML);
	
	//controllers:
	private final FrameController frameCtrlr;
	private final A1 a1;
	
	CampaignsController(FrameController frameCtrlr) {
		this.frameCtrlr = frameCtrlr; //assign frame controller
		this.a1 = new A1(frameCtrlr);
	}

	
	Parent getRoot() { return this.root; } //get root
	
	
	
	int counter = 0;
	
	void goToA1() {
		frameCtrlr.moveFwrd(a1.getRoot());
	}
	
}
