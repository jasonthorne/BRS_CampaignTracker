package controller;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;

import animation.Fadeable;
import animation.Fadeable.FadeOption;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import model.AirForce;
import model.Campaign;
import model.Event;
import model.Period;
/*import model.Campaign.CampaignBuilder;*/

public class CampaignsController implements Frameable, Rootable {
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private JFXListView<Campaign> campaignsLV;
	@FXML private ToggleGroup radioBtnsTG;
	@FXML private JFXRadioButton playingRB;
	@FXML private JFXRadioButton notPlayingRB;
	@FXML private JFXRadioButton allRB;
	@FXML private JFXButton newCampaignBtn;
   
    @FXML	
    void initialize() {
    	setToggleListener(); //add listener to radio buttons toggle group
    	//set btn action:
    	newCampaignBtn.setOnAction(event -> Frameable.changeView(root, eventsCtrlr));
    }
    
    //observable list of campaigns:
    private final ObservableList<Campaign>observCampaigns = FXCollections.observableArrayList();
    
    //filtered list for filtering campaigns:
    private final FilteredList<Campaign> filteredCampaigns = new FilteredList<>(observCampaigns, cmp->true);
    
    //fxml root node:
	private Parent root; 
	
	//controllers:
	private EventsController eventsCtrlr; 
	
	//constructor:
	CampaignsController() {
		setRoot(); //set root node
		eventsCtrlr = new EventsController(this);
	}

	//populates campaigns with campaigns from db:
	void setCampaigns(Map<String,Event>nameToEvent) { 
		
		//executor service for task thread:
    	ExecutorService service = Executors.newSingleThreadExecutor(); 
    	
    	//future list of campaigns pulled from db, returned from service task thread:
    	Future<List<Campaign>>futureCampaigns = service.submit(() -> database.SelectCampaigns.select(nameToEvent));
    	
    	//keeping future.get() separate from application thread:
    	new Thread(() -> {
	    	try {System.out.println("dawg");
	    		//add campaigns from db to observCampaigns:
	    		observCampaigns.addAll(futureCampaigns.get());
	    	}catch(Exception e) {
				e.printStackTrace();
			}finally { 
				//shut down service thread:
				if(!service.isShutdown()) { service.shutdown(); } 
			}
    	}).start();
    	
		//add observable campaigns to listView:
		campaignsLV.setItems(observCampaigns); 
		//set listView cellFactory to create CampaignCellControllers:
		campaignsLV.setCellFactory(CampaignCellController -> new CampaignCellController(this));
	}
	
	//add change listener to toggle group:
	private void setToggleListener() {
	
		//add change listener to radioBtn toggle group:
		radioBtnsTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			
			@Override //override changeListener's changed: 
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldVal, Toggle newVal) {
		 		 
				//get changed value from toggle group, cast to radioBtn:
				JFXRadioButton selected = (JFXRadioButton) newVal.getToggleGroup().getSelectedToggle();
				
				//filter listView according to selected radioBtn: 
		 	 	if(selected.getText().equals(playingRB.getText())) {
					//set filteredList predicate to filter by playingRB selection:
					filteredCampaigns.setPredicate(cmp -> cmp.getUserIsPlaying(LoginController.getUserName())); 
					campaignsLV.setItems(filteredCampaigns); //set listView to filteredList
				}else if(selected.getText().equals(notPlayingRB.getText())) {
			 		//set filteredList predicate to filter by notPlayingRB selection:
					filteredCampaigns.setPredicate(cmp -> !cmp.getUserIsPlaying(LoginController.getUserName())); 
					campaignsLV.setItems(filteredCampaigns); //set listView to filteredList
				}else {
					campaignsLV.setItems(observCampaigns);  //set listView to non-filtered campaigns list 
				}
			} 
		});
	}
	
	//create a new campaign:
	void createCampaign(Event event) {
		
		//get time stamp of creation:
		Timestamp timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
		//++++++++++++++++++++++++++++++++++++++++++++++++HAVE ISERT BELOW BE IN A TRY, AND IF NOT SUCCEFULL INFORM USER & 
		//insert campaign to db, storing returned id:
		int campaignId = database.InsertCampaign.insert(event.getName(), LoginController.getUserId(), timestamp); 
		
		//create local campaign:
		Campaign campaign = new Campaign(campaignId, event, timestamp, LoginController.getUserName());
		
		//add campaign to observable list of campaigns:
		observCampaigns.add(campaign);
		
		//navigate to campaign:
		Frameable.changeView(root, new CampaignController(campaign)); //++++++++++++++++++++REMEMBER TO FIX NAV BUTTONS HERE!! ++++
	}
	
	
	@Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/campaigns.fxml"); } //set root
	@Override
	public Parent getRoot() { return root; } //get root

	@Override 
	public String getViewTitle() { return "Campaigns"; }

	
	/////////https://stackoverflow.com/questions/28448851/how-to-use-javafx-filteredlist-in-a-listview
	
	//accordian: 
		//http://tutorials.jenkov.com/javafx/accordion.html
	
}
