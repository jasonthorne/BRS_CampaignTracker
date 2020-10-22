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
    	btnTest.setOnAction(event -> test());
    	
    }
	
    //root element for this controller:
	private final Parent root = loadRoot.apply(this, "/view/campaigns.fxml");
	
	//controllers:
	private final FrameController fc;
	
	CampaignsController(FrameController fc) {
		this.fc = fc; //assign frame controller
	}

	
	public Parent getRoot() { return this.root; } //get root
	
	
	
	int counter = 0;
	
	void test() {
		counter++;
		testLbl.setText("yo: " + counter);
	}
	
}
