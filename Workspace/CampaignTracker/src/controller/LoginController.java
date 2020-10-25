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
	
	//fxml root element:
  	private Parent root; 
	
	//controllers:
	private final FrameController frameCtrlr;
	private final CampaignsController campaignsCtrlr;
	
	//constructor:
	LoginController(FrameController frameCtrlr) {
		setRoot(); //set the fxml root element for this controller
		this.frameCtrlr = frameCtrlr; //assign frame controller (for loginUsr)
		this.campaignsCtrlr = new CampaignsController(); //create campaigns controller
		super.setFrameController(frameCtrlr); //store frame controller in super
	}
	
	private void loginUsr() {
		Fadeable.fade(root, FadeOption.FADE_OUT); //fade out root
		frameCtrlr.loginMove(campaignsCtrlr.getRoot()); //move to campaigns
	}
	
	@Override
	void setRoot() { root = Rootable.getRoot(this, FxmlPath.LOGIN); } //set root
	@Override
	Parent getRoot() { return root; } //get root
	
}
