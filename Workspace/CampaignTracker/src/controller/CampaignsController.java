package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.Campaign;
import view.FxmlPath;

public class CampaignsController implements Rootable{
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    @FXML private AnchorPane rootAP;
    @FXML private JFXButton btnTest; /** ===================== */
    @FXML private JFXListView<Campaign> campaignsLV;
   
    @FXML
    void initialize() {
    	System.out.println("innit"); 
    	populateList();
    	btnTest.setOnAction(event -> goToA1());
    	
    }
    
    //observable list of campaigns:
    private ObservableList<Campaign>campaigns = FXCollections.observableArrayList();
    
    /**===================imaginary db data: =================================*/
    public static List<Campaign>cellItemsDB = new ArrayList<Campaign>();
   	/**=======================================================================*/
	
    //root element for this controller:
	private final Parent root = loadRoot.apply(this, FxmlPath.CAMPAIGNS_FXML);
	
	//controllers:
	private final FrameController frameCtrlr;
	private final A1 a1;
	
	//constructor:
	CampaignsController(FrameController frameCtrlr) {
		this.frameCtrlr = frameCtrlr; //assign frame controller
		this.a1 = new A1(frameCtrlr);
	}

	Parent getRoot() { return this.root; } //get root
	
	/** ==============delete laterz======================== */
	void goToA1() { frameCtrlr.moveFwrd(a1.getRoot()); }
	/** ======================================= */
	
	private void populateList() {
		
		/** ================== pull from db here instead! =========*/
		//populate imaginary db data:
    	cellItemsDB.add(new Campaign("campaign 1"));
    	cellItemsDB.add(new Campaign("campaign 2"));
    	cellItemsDB.add(new Campaign("campaign 3"));
    	
		//add campaigns from db to campaigns:
		campaigns.addAll(cellItemsDB);
		
		/** ========================================================*/
		//add campaigns to list view:
		campaignsLV.setItems(campaigns); 
		System.out.println(campaigns);
		System.out.println(campaignsLV);
		//set list view cellFactory to create CampaignCellController CellItems:
		campaignsLV.setCellFactory(CampaignCellController -> new CampaignCellController(this));
		
	}
	
}
