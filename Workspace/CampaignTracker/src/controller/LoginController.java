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

public class LoginController implements Rootable, Fadeable {
	
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
	
    //root element for this controller:
  	private final Parent root = Rootable.getRoot(this, FxmlPath.LOGIN);
	
	//controllers:
	private final FrameController frameCtrlr;
	private final CampaignsController campignsCtrlr;
	
	//constructor:
	LoginController(FrameController frameCtrlr) {
		this.frameCtrlr = frameCtrlr;  //assign frame controller
		this.campignsCtrlr = new CampaignsController(frameCtrlr);  //create campaigns controller
	}
	
	private void loginUsr() {
		Fadeable.fade(root, FadeOption.FADE_OUT); //fade out root
		frameCtrlr.loginMove(campignsCtrlr.getRoot()); //move to campaigns
	}
	
	Parent getRoot() { return this.root; }
	
	
	
}
