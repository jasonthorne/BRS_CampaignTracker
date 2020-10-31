package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import model.Campaign;
import model.Event;
import model.Event.EventBuilder;

public class SelectEventController implements Frameable, Rootable {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private AnchorPane listViewAP;
    @FXML private JFXListView<Event> eventsLV;
    @FXML private AnchorPane eventAP;

    @FXML
    void initialize() {
    	setEvents(); //populate events list
    }
    
    //observable list of events:
    private final ObservableList<Event>events = FXCollections.observableArrayList();
    
    /**===================imaginary db data: =================================*/
    public static List<Event>cellItemsDB = new ArrayList<Event>();
   	/**=======================================================================*/
    
    //fxml root node:
  	private Parent root; 

    //constructor:
    SelectEventController() {
    	setRoot(); //set root node
    }
    
    //populates events with events from db:
    private void setEvents() { /** ======= remember, this is pulling from db so may need called again for some reason =========*/
    	
    	/** ================== pull from db here instead! =========*/
		//populate imaginary db data:
    	cellItemsDB.add(new Event.EventBuilder().setName("event1").build());
    	cellItemsDB.add(new Event.EventBuilder().setName("event2").build());
    	cellItemsDB.add(new Event.EventBuilder().setName("event3").build());
    	cellItemsDB.add(new Event.EventBuilder().setName("event4").build());
    	cellItemsDB.add(new Event.EventBuilder().setName("event5").build());
    	cellItemsDB.add(new Event.EventBuilder().setName("event6").build());
    	
  
		//add events from db to events:
		events.addAll(cellItemsDB);
		
		/** ========================================================*/
		//add events to listView:
		eventsLV.setItems(events); 
		//set listView cellFactory to create CampaignCellControllers:
		eventsLV.setCellFactory(EventCellController -> new EventCellController(this));
    	
    }
    
    @Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/selectEvent.fxml"); } //set root
	@Override 
	public Parent getRoot() { return root; } //get root

	@Override 
	public String getViewTitle() { return "Select Event"; } //get title
}
