package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Event;
import model.Event.EventBuilder;
import model.Period;
import model.Plane;
import model.AirForce;

public class AirForceCellController extends JFXListCell<AirForce> implements Rootable {
	
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    
    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private Label airForceNameLbl;
    @FXML private Label hasHomeAdvLbl;
    @FXML private JFXButton planesBtn;

    @FXML
    void initialize() {
    	
    	//show planes available:
    	planesBtn.setOnAction(event -> {
    		//planesTableCtrlr.showPlanes(planes);
    	   new PlanesTableController(planes).showStage();
    	});
    }
    
    //root element for this controller:
  	private final Parent root = Rootable.getRoot(this, "/view/airForceCell.fxml");
  			
  	//air force planes:
  	private List<Plane>planes;
  	
    //update cell with air force data:
	@Override 
  	protected void updateItem(AirForce airForce, boolean isEmpty) {
  		super.updateItem(airForce, isEmpty);
	  	
		if (isEmpty || airForce == null) {
	        setText(null);
	        setGraphic(null);
	    } else {
	    	
			//populate cell with data from air force:
	    	airForceNameLbl.setText(airForce.getAirForceName()); 
			hasHomeAdvLbl.setText(Boolean.toString(airForce.getHasHomeAdv()));
			
			planes = airForce.getAirForcePlanes(); //get air force planes
	
			setText(null); 
			setGraphic(rootAP); //set this root element as the graphic	
	    }
	}
}
