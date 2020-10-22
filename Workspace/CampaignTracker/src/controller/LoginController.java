package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Rootable {
	
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
    
    //root element for this controller:
  	//private final Parent root = loadRoot.apply(
  			//this, new FXMLLoader(getClass().getResource("/view/login.fxml")));
    
    {
    	/*
    	try {
	    	//load properties:
			Properties properties = new Properties();	
			properties.load(new FileInputStream("configs/fxml/fxml_paths.properties"));
			System.out.println(properties.getProperty("login"));
	    }catch(Exception e) { e.printStackTrace(); }
    	*/
    }

  	private final Parent root = loadRoot.apply(this, "/view/login.fxml");
  			
	//controllers:
	private final FrameController frameC;
	private final CampaignsController cc;
	
	LoginController(FrameController frameC){
		this.frameC = frameC;  //assign frame controller
		this.cc = new CampaignsController(frameC);  //create campaigns controller
		
		/*
		//create loader:
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
		loader.setController(this); //set this class as the controller
		try {
			loader.load(); //load fxml tree
		} catch (IOException e) { e.printStackTrace(); }
		this.root = loader.getRoot(); //get root element
		*/
	}
	
	private void loginUsr(){
		System.out.println("login clicked");
		frameC.moveFwrd(cc.getRoot());
	}
	
	public Parent getRoot() { return this.root; }
	
	
	
	
	
	
	
	
	
}
