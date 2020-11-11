package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Event;

public class PlaneAvailabilitiesController implements Rootable {
	
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    
    @FXML
    private Label testLbl;

    
    @FXML
    void initialize() {
    	//testLbl.setText(event.getStartPeriod().toString());
    	testLbl.setText(airForceName);

    }
    
  	private final Stage stage = new Stage(); //stage
    private final Scene scene; //scene
    
    //private final Event event; //event covered
    private String airForceName; //airForce /////////////////////not needed here! :P
    
    //constructor:
    PlaneAvailabilitiesController(Event event, String airForceName) {
    	//this.event = event; //assign event covered
    	this.airForceName = airForceName;
    	//call select here, passing in values +++++++++++++++++
    	scene = new Scene(Rootable.getRoot(this, "/view/planeAvailabilities.fxml")); //add root to scene
    	stage.setScene(scene); //add scene to stage
    	
    }
    
    //show stage:
    void showStage() { stage.showAndWait(); }
    
        

}
