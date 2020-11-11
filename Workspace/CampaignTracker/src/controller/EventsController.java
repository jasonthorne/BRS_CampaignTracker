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
import com.jfoenix.controls.JFXListView;

import database.SelectEvents;
import database.SelectPlayerID;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Campaign;
import model.Event;
import model.Event.EventBuilder;
import model.EventAirForce;

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
    @FXML private JFXButton selectEventBtn;
    @FXML private JFXListView<EventAirForce> airForcesLV;

    @FXML
    void initialize() {
    	setEvents(); //populate events list
    	setLViewListener(); //add change listener to list view
    }
    
    //observable list of events:
    private final ObservableList<Event>events = FXCollections.observableArrayList();
    
    //observable list of event's air forces::
    private ObservableList<EventAirForce>eventAirforces;
    
    //fxml root node:
  	private Parent root; 

    //constructor:
    EventsController() {
    	setRoot(); //set root node
    }
    
    //populates events with events from db:
    private void setEvents() {
    	
    	//executor service for task thread:
    	ExecutorService service = Executors.newSingleThreadExecutor(); 
    	
    	//future list of events pulled from db, returned from service task thread:
    	Future<List<Event>>futureEvents = service.submit(()->database.SelectEvents.select());
    	
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
		eventsLV.setCellFactory(EventCellController -> new EventCellController(this));
    }
    
    //add change listener to list view:
    private void setLViewListener() {
    	
    	eventsLV.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
    		
    		@Override  //override changeListener's changed: 
    	    public void changed(ObservableValue<? extends Event> observable, Event oldVal, Event newVal) {
    			
    			//populate fxml elements with values from selected event:
    	        eventNameLbl.setText(newVal.getEventName());
    	        startPeriodLbl.setText(newVal.getStartPeriod().toString());
    	        endPeriodLbl.setText(newVal.getEndPeriod().toString());
    	        
    	        //add selected event's air forces to observable eventAirforces:
    	        eventAirforces = FXCollections.observableArrayList(newVal.getEventAirForces());
    	        airForcesLV.setItems(eventAirforces); //set list view with eventAirforces
    	        //+++++++++++++++++make an eventbuilder here, using newVal to pull its name and periods. then pass it to event airforce controller to add to it there!! 
    	        ///////////airForcesLV.setCellFactory(EventAirForceCellController -> new EventAirForceCellController(newVal));
    	        //set cell factory to create controller classes with an eventBuilder holding eventName & periods of selected event:
    	       airForcesLV.setCellFactory(
    	        		EventAirForceCellController -> new EventAirForceCellController(
	    	        		new Event.EventBuilder()
								.setEventName(newVal.getEventName())
								.setStartPeriod(newVal.getStartPeriod())
								.setEndPeriod(newVal.getEndPeriod()))
    	        		);
    	      
    	    }
    	});
    }
    //https://stackoverflow.com/questions/12459086/how-to-perform-an-action-by-selecting-an-item-from-listview-in-javafx-2
    
    @Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/events.fxml"); } //set root
	@Override 
	public Parent getRoot() { return root; } //get root
	@Override 
	
	public String getViewTitle() { return "Select Event"; } //get title
}
