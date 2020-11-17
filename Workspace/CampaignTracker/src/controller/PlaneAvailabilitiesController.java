package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Event;
import model.Period;

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
    	testLbl.setText(startPeriod.toString() + " " + endPeriod.toString());
    	
    	//System.out.println(event.getEventAirForce());

    }
    
  	private final Stage stage = new Stage(); //stage
    private final Scene scene; //scene
    
    private Period startPeriod; //start period
    private Period endPeriod; //end period
    
  
    //constructor:
	PlaneAvailabilitiesController(Period start, Period end) {
		
		setPeriods(start, end); //set periods
		
		//add root to scene:
		scene = new Scene(Rootable.getRoot(this, "/view/planeAvailabilities.fxml")); 
    	stage.setScene(scene); //add scene to stage
		
    	
    	
    	//showStage();
    	
    }
	
	
	//set start & end periods:
	void setPeriods(Period start, Period end) {
		startPeriod = new Period(start.getBlock(), start.getYear());
		endPeriod = new Period(end.getBlock(), end.getYear());
	}
	
    
	
    //show stage:
    void showStage() { stage.showAndWait(); }
    
        

}
