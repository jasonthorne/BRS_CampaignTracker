package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import animation.Fadeable;
import animation.Fadeable.FadeOption;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Event;
import model.Period;
import model.Plane;

public class PlaneAvailabilitiesController implements Rootable {
	
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    
    //root fxml element & children:
    @FXML private VBox rootVB;
  

    @FXML
    void initialize() {
    	////////testLbl.setText(startPeriod.toString() + " " + endPeriod.toString());
    }
    
    //++++++++++++++++++++++++++++++++++++++
    
    //BUG - button needs disabled when table is opened! then enabled on close of table
    //+++++++++++++++++++++++++++++++++++++
    
    //----------------------
    
    private TableView table = new TableView();
    TableColumn firstNameCol = new TableColumn("First Name");
    TableColumn lastNameCol = new TableColumn("Last Name");
    TableColumn emailCol = new TableColumn("Email");
    
   
    
    //---------------------
    
    private final Period startPeriod; //start period
    private final Period endPeriod; //end period
    
  	private final Stage stage = new Stage(); //stage
    private final Scene scene; //scene
    
    //constructor:
	PlaneAvailabilitiesController(Period start, Period end) {
		startPeriod = start; //set start period
		endPeriod = end; //set end period
		
		
		//add root to scene:
		scene = new Scene(Rootable.getRoot(this, "/view/planeAvailabilities.fxml")); 
    	stage.setScene(scene); //add scene to stage	
    }
	
	
    void showPlanes(List<Plane>planes){ //airForceName???????????????
    	//System.out.println(airForceName);
    	
    	//----------------------------
    	
		table.getColumns().setAll(firstNameCol, lastNameCol, emailCol);
		rootVB.getChildren().setAll(table);
		//-----------------------------
    	
    	showStage();
    }
    
    
    
    
  
    
    
	
    //show stage:
    private void showStage() { stage.showAndWait(); }
    
        

}
