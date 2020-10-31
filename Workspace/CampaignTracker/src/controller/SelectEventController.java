package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import model.Event;

public class SelectEventController implements Frameable, Rootable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML private AnchorPane rootAP;
    @FXML private AnchorPane listViewAP;
    @FXML private JFXListView<Event> eventsLV;
    @FXML private AnchorPane eventAP;

    @FXML
    void initialize() {
     
    }
    
    //fxml root node:
  	private Parent root; 

    //constructor:
    SelectEventController(FrameController frameCtrlr) {
    	setRoot(); //set root node
    }
    
    SelectEventController() {
    	setRoot(); //set root node
    }
    
    
    @Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/selectEvent.fxml"); } //set root
	@Override 
	public Parent getRoot() { return root; } //get root

	@Override 
	public String getViewTitle() { return "Select Event"; } //get title
}
