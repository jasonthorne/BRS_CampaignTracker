package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;

import database.SelectEvents;
import database.SelectPlayerID;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Campaign;
import model.Event;
import model.Event.EventBuilder;
import model.AirForce;

public class EventsController implements Frameable, Rootable {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private JFXListView<Event> eventsLV;
    @FXML private AnchorPane eventAP;
    @FXML private Label eventNameLbl;
    @FXML private Label startPeriodLbl;
    @FXML private Label endPeriodLbl;
    @FXML private Label turnSizeLbl;
    @FXML private JFXButton selectEventBtn;
    @FXML private JFXListView<AirForce> airForcesLV;

    @FXML
    private StackPane testStack2; //+++++++++++++test stack (which rootAP ids wrapped in for displaying JFXDialog) +++++++
    
    @FXML
    void initialize() {
    	setEvents(); //populate events list
    	setEventsListener(); //add change listener to events list view
    	setAirForcesFactory();  //set airForcesLV cell factory
    	
    	selectEventBtn.setOnAction(event -> {
    		
    		//inform user they're going to create a new campaign with this event:
    		/////++++++++testJFXDialog();
    		//if they're cool (click ok button instead of cancel/x button): create new campaign with this event in campaign ctrlr:
    		
    		campaignsCtrlr.createCampaign(eventNameLbl.getText());
    		
    		//then navigate user to that campaign page
    		
    		//Frameable.changeView(root, campaignCtrlr));
    		
    		//////https://stackoverflow.com/questions/50933321/how-to-show-a-dialog-with-jfoenix-javafx
    		
    		//more detailed: https://stackoverflow.com/questions/38830883/how-to-create-a-dialog-using-jfxdialog-of-jfoenix-in-javafx
    		
    	});
    	
    }
    
    //----------------------------------------
    public void testJFXDialog() {
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("Header"));
        layout.setBody( /*new Label("Header")*/ new JFXButton("yo"));
       

        JFXDialog dialog = new JFXDialog(testStack2, layout, JFXDialog.DialogTransition.CENTER);
        dialog.show();
        
       //JFXDialog dialog = new JFXDialog();
    }
    //---------------------------------------------
    
   
    //observable list of events:
    private final ObservableList<Event>events = FXCollections.observableArrayList();
    
    //observable list of event's air forces:
    private ObservableList<AirForce>airForces;
    
    //fxml root node:
  	private Parent root; 
  	
  	private final CampaignsController campaignsCtrlr;

    //constructor:
    EventsController(CampaignsController campaignsCtrlr) {
    	setRoot(); //set root node
    	this.campaignsCtrlr = campaignsCtrlr;
    }
    
    //populates events with events from db:
    private void setEvents() {
    	
    	//executor service for task thread:
    	ExecutorService service = Executors.newSingleThreadExecutor(); 
    	
    	//future list of events pulled from db, returned from service task thread:
    	Future<List<Event>>futureEvents = service.submit(() -> database.SelectEvents.select());
    	
    	//keeping future.get() separate from application thread:
    	new Thread(() -> {
    		
	    	try {
	    		//add events from db to events:
	    		events.addAll(futureEvents.get());
	    	}catch(Exception e) {
				e.printStackTrace();
			}finally { 
				//shut down service thread:
				if(!service.isShutdown()) { service.shutdown(); } 
			}
	    	
    	}).start();
    	
    	//add events to listView:
		eventsLV.setItems(events);
		
		//set listView cellFactory to create EventCellControllers:
		eventsLV.setCellFactory(EventCellController -> new EventCellController(/*this*/));
    }
    
    //add change listener to list view:
    private void setEventsListener() {
	/**https://stackoverflow.com/questions/12459086/how-to-perform-an-action-by-selecting-an-item-from-listview-in-javafx-2	*/
    	eventsLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
    		
    		@Override  //override change listener's changed: 
    	    public void changed(ObservableValue<? extends Event> observable, Event oldVal, Event newVal) {
    			
    			//populate fxml elements with values from selected event:
    	        eventNameLbl.setText(newVal.getEventName());
    	        startPeriodLbl.setText(newVal.getStartPeriod().toString());
    	        endPeriodLbl.setText(newVal.getEndPeriod().toString());
    	        turnSizeLbl.setText(String.valueOf(newVal.getTurnSize()));
    	        
    	        //add selected event's air forces to observable airForces:
    	        airForces = FXCollections.observableArrayList(newVal.getEventAirForces());
    	        airForcesLV.setItems(airForces); //set list view with airForces
    	    }
    	});
    } 
   
    //set airForcesLV cell factory:
    private void setAirForcesFactory() {
        //set cell factory to create air force cell controllers:
        airForcesLV.setCellFactory(AirForceCellController ->  new AirForceCellController());
    }
    
    @Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/events.fxml"); } //set root
	@Override 
	public Parent getRoot() { return root; } //get root
	@Override 
	
	public String getViewTitle() { return "Select an Event"; } //get title
}
