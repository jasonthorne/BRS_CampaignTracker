package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import animation.Fadeable;
import animation.Fadeable.FadeOption;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Campaign;
import view.FxmlPath;

public class CampaignsController implements Rootable, Fadeable{
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private JFXButton btnTest; /** ===================== */
    @FXML private JFXListView<Campaign> campaignsLV;
   
    @FXML
    void initialize() {
    	setCampaigns(); //populate campaigns
    	btnTest.setOnAction(event -> goToA1());
    }
    
    //observable list of campaigns:
    private ObservableList<Campaign>campaigns = FXCollections.observableArrayList();
    
    /**===================imaginary db data: =================================*/
    public static List<Campaign>cellItemsDB = new ArrayList<Campaign>();
   	/**=======================================================================*/
	
    //root element for this controller:
	private final Parent root = Rootable.getRoot(this, FxmlPath.CAMPAIGNS);
	
	//controllers:
	private final FrameController frameCtrlr;
	private final A1 a1;
	
	//constructor:
	CampaignsController(FrameController frameCtrlr) {
		this.frameCtrlr = frameCtrlr; //assign frame controller
		this.a1 = new A1(frameCtrlr);
	}

	Parent getRoot() { return root; } //get root
	
	/** ==============delete laterz======================== */
	void goToA1() {
		Fadeable.fadeNode(root, FadeOption.FADE_OUT);
		frameCtrlr.moveFwrd(a1.getRoot());
	}
	/** ======================================= */
	
	//populates campaigns with campaigns from db:
	private void setCampaigns() { /** ======= remember, this is pulling from db so may need called again for some reason =========*/
		
		/** ================== pull from db here instead! =========*/
		//populate imaginary db data:
    	cellItemsDB.add(new Campaign("campaign 1"));
    	cellItemsDB.add(new Campaign("campaign 2"));
    	cellItemsDB.add(new Campaign("campaign 3"));
    	cellItemsDB.add(new Campaign("campaign 4"));
    	cellItemsDB.add(new Campaign("campaign 5"));
    	cellItemsDB.add(new Campaign("campaign 6"));
    	cellItemsDB.add(new Campaign("campaign 7"));
    	cellItemsDB.add(new Campaign("campaign 8"));
    	
		//add campaigns from db to campaigns:
		campaigns.addAll(cellItemsDB);
		
		/** ========================================================*/
		//add campaigns to listView:
		campaignsLV.setItems(campaigns); 
		//set listView cellFactory to create CampaignCellControllers:
		campaignsLV.setCellFactory(CampaignCellController -> new CampaignCellController(this));
	}
	
}
