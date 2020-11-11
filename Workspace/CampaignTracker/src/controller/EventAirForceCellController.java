package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListCell;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Event;
import model.EventAirForce;

public class EventAirForceCellController extends JFXListCell<EventAirForce> implements Rootable {
	
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
      //????????????+++++++++++
    	//show planes available:
    	planesBtn.setOnAction(event -> {
    		new PlaneAvailabilitiesController(this.event, nameLbl.getText()).showStage();
    	});
    }
    
    //root element for this controller:
  	private final Parent root = Rootable.getRoot(this, "/view/eventAirForceCell.fxml");
  	
  	private final Event event; //event covered
  	
  	//constructor:
  	EventAirForceCellController(Event event) {  //++++++++++++++++++++think about passing an eventBuilder instead!! 
  		this.event = event; //set event covered
    }
    
    
    //update cell with eventAirForce data:
  	@Override 
  	protected void updateItem(EventAirForce eventAirForce, boolean isEmpty) {
  		super.updateItem(eventAirForce, isEmpty);
	  	
		if (isEmpty || eventAirForce == null) {
	        setText(null);
	        setGraphic(null);
	    } else {
			//populate cell with data from eventAirForce:
			nameLbl.setText(eventAirForce.getAirForceName()); 
			homeAdvLbl.setText(Boolean.toString(eventAirForce.getHasHomeAdv()));
			
			setText(null); 
			setGraphic(rootAP); //set this root element as the graphic	
	    }
  }
    
    

}
