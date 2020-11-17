package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Event;
import model.Period;
import model.Plane;

public class PlaneAvailabilitiesController implements Rootable {
	
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    
    @FXML
    private Label testLbl;

    
    @FXML
    void initialize() {
    	testLbl.setText(startPeriod.toString() + " " + endPeriod.toString());
    }
    
  	private final Stage stage = new Stage(); //stage
    private final Scene scene; //scene
    
    private final Period startPeriod; //start period
    private final Period endPeriod; //end period
    
    //constructor:
	PlaneAvailabilitiesController(Period start, Period end) {
		startPeriod = start; //set start period
		endPeriod = end; //set end period
		
		//add root to scene:
		scene = new Scene(Rootable.getRoot(this, "/view/planeAvailabilities.fxml")); 
    	stage.setScene(scene); //add scene to stage
    }
	
	
    void showPlanes(List<Plane>planes){
    	System.out.println(planes);
    }
	
    //show stage:
    private void showStage() { stage.showAndWait(); }
    
        

}
