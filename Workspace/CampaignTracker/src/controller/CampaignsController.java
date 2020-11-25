package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import model.Campaign;

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
    	setCampaigns(); //populate campaigns list
    	setToggleListener(); //add change listener to toggle group
    	//set btn action:
    	////newCampaignBtn.setOnAction(event -> Frameable.changeView(root, eventsCtrlr));
    	
    	newCampaignBtn.setOnAction(event -> 
    		Frameable.changeView(root, new EventsController(this))); 
    	
    	
    }
    
    //observable list of campaigns:
    private final ObservableList<Campaign>observCampaigns = FXCollections.observableArrayList();
    
    //filtered list for filtering campaigns:
    private final FilteredList<Campaign> filteredCampaigns = new FilteredList<>(observCampaigns, str->true);
    
    /**===================imaginary db data: =================================*/
    public static List<Campaign>cellItemsDB = new ArrayList<Campaign>();
   	/**=======================================================================*/
	
    //fxml root node:
	private Parent root; 
	
	//controllers:
	private final EventsController eventsCtrlr; ////////????????? needed??? +++++++
	private A1 a1;
	
	//constructor:
	CampaignsController() {
		setRoot(); //set root node
		this.eventsCtrlr = new EventsController(this); ////////????????? needed??? +++++++++
		 a1 = new A1();
	}

	//populates campaigns with campaigns from db:
	private void setCampaigns() { /** ======= remember, this is pulling from db so may need called again for some reason =========*/
		
		/** ================== pull from db here instead! =========*/
		//populate imaginary db data:
    	cellItemsDB.add(new Campaign("1"));
    	cellItemsDB.add(new Campaign("1"));
    	cellItemsDB.add(new Campaign("1"));
    	cellItemsDB.add(new Campaign("1"));
    	cellItemsDB.add(new Campaign("2"));
    	cellItemsDB.add(new Campaign("2"));
    	cellItemsDB.add(new Campaign("2"));
    	cellItemsDB.add(new Campaign("2"));
    	
    	
		//add campaigns from db to campaigns:
    	observCampaigns.addAll(cellItemsDB);
		
		/** ========================================================*/
		//add campaigns to listView:
		campaignsLV.setItems(observCampaigns); 
		//set listView cellFactory to create CampaignCellControllers:
		campaignsLV.setCellFactory(CampaignCellController -> new CampaignCellController(this));
	}
	
	//add change listener to toggle group:
	private void setToggleListener() {
	
		// add change listener to radioBtn toggle group:
		radioBtnsTG.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			
			@Override //override changeListener's changed: 
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldVal, Toggle newVal) {
		 		 
				//get changed value from toggle group, cast to radioBtn:
				JFXRadioButton selected = (JFXRadioButton) newVal.getToggleGroup().getSelectedToggle();
				
				//filter listView according to selected radioBtn: 
		 	 	if(selected.getText().equals(playingRB.getText())) {
					//set filteredList predicate to filter by playingRB selection:
					filteredCampaigns.setPredicate(str -> str.getName().contains(playingRB.getText()));
					campaignsLV.setItems(filteredCampaigns); //set listView to filteredList
				}else if(selected.getText().equals(notPlayingRB.getText())) {
			 		//set filteredList predicate to filter by notPlayingRB selection:
					filteredCampaigns.setPredicate(str -> str.getName().contains(notPlayingRB.getText()));
					campaignsLV.setItems(filteredCampaigns); //set listView to filteredList
				}else {
					campaignsLV.setItems(observCampaigns);  //set listView to non-filtered campaigns list 
				}
			} 
		});
	}
	
	void createCampaign(String eventName) {
		//add campaign to db:
		database.InsertCampaign.insert(LoginController.getPlayerId(), eventName);
		
		//add to campaigns obsrv list
		//then:
		//campaignsLV.setItems(campaigns); 
		
		
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
