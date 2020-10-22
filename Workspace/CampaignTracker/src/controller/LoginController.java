package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class LoginController {
	
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
	
    private static final String TITLE = "Login"; //???????????????
	private final Parent root;
	
	//controllers:
	private final FrameController frameCtrlr;
	private final CampaignsController campaignsCtrlr;
	
	LoginController(FrameController frameCtrlr){
		this.frameCtrlr = frameCtrlr;  //assign frame controller
		this.campaignsCtrlr = new CampaignsController(frameCtrlr);  //create campaigns controller
		
		//create loader:
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		loader.setController(this); //set this class as the controller
		try {
			loader.load(); //load fxml tree
		} catch (IOException e) { e.printStackTrace(); }
		this.root = loader.getRoot(); //set root element
	}
	
	private void loginUsr(){
		System.out.println("login clicked");
		frameCtrlr.moveFwrd(campaignsCtrlr);
	}
	
	public Parent getRoot() { return root; }
	
	
	
	
	
	
	
	
	
}
