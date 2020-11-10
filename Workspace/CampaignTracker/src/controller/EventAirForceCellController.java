package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class EventAirForceCellController {
	
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    
    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private Label nameLbl;
    @FXML private Label homeAdvLbl;
    @FXML private JFXButton planesBtn;

    @FXML
    void initialize() {
      
    }
    
    

}
