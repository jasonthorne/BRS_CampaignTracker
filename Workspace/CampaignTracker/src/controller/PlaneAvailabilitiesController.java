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
    	testLbl.setText(event.getEndPeriod().toString());
    	
    	//System.out.println(event.getEventAirForce());
    	//System.out.println(event);

    }
    
  	private final Stage stage = new Stage(); //stage
    private final Scene scene; //scene
    
    private final Event event; //event covered ++++++++++++++shouldn't be needed! 
    
    //constructor:
	PlaneAvailabilitiesController(Event event) {
		this.event = event; //assign event covered //++++++++++++++shouldn't be needed! 
		//add root to scene:
		scene = new Scene(Rootable.getRoot(this, "/view/planeAvailabilities.fxml")); 
    	stage.setScene(scene); //add scene to stage
		
		
    	//call select here, passing in values +++++++++++++++++
    	showPlanes();
		
    }
	
	private void showPlanes() { //
		
	}
    
    //show stage:
    void showStage() { stage.showAndWait(); }
    
        

}
