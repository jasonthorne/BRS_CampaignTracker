package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;

import animation.Fadeable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Campaign;
import view.FxmlPath;

public class CampaignsController extends FrameContent implements Rootable {
	
	//=================toggle testing =====================
	 @FXML
	 private JFXRadioButton testRadioBtn1;
	 @FXML
	 private JFXRadioButton testRadioBtn2;
	 @FXML
	 private ToggleGroup myToggleGroup;
	 
	 @FXML
	 private Label testLbl;
	//=================toggle testing =====================
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private JFXButton btnTest; /** ===================== */
    @FXML private JFXListView<Campaign> campaignsLV;
   
    @FXML
    void initialize() {
    	setCampaigns(); //populate campaigns
    	btnTest.setOnAction(event -> goToA1()); /** ==============delete laterz=========== */
    	btnTest.setOnAction(event -> 
    		super.changeView(root, SelectCampaignCtrlr.getRoot())
		);
    	
    	
    	//============toggle testing===================
    	// add a change listener 
        myToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
        	@Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldVal, Toggle newVal) {
            	
       
        		JFXRadioButton selected = (JFXRadioButton)newVal.getToggleGroup().getSelectedToggle(); 
               //////// System.out.println("Selected Radio Button - "+chk.getText());
        		/*
            	JFXRadioButton rb = (JFXRadioButton)myToggleGroup.getSelectedToggle(); 
            	
                if (rb != null) { 
                    String s = rb.getText(); 
                */
        		
        		test(selected.getText());
        		
        		/*
        		String s = selected.getText(); 
                    // change the label 
                    testLbl.setText(s + " selected"); 
                    
                    if(s.equals("1")) {
                    	System.out.println("found 1");
                    }
                    if(s.equals("2")) {
                    	System.out.println("found 2");
                    }*/
        		
                //} 
            } 
        }); 
    	
      //=======================================================
    	
    	
    	
    	
    	
    	
    }
    
    //observable list of campaigns:
    private ObservableList<Campaign>campaigns = FXCollections.observableArrayList();
    
    /**===================imaginary db data: =================================*/
    public static List<Campaign>cellItemsDB = new ArrayList<Campaign>();
   	/**=======================================================================*/
	
    //fxml root node:
	private Parent root; 
	
	//controllers:
	private final A1 a1; /** ==============delete laterz======================== */
	private final SelectCampaignController SelectCampaignCtrlr;
	
	//constructor:
	CampaignsController() {
		setRoot(); //set root node
		this.a1 = new A1(); /** ==============delete laterz======================== */
		this.SelectCampaignCtrlr = new SelectCampaignController();
	}

	
	/** ==============delete laterz======================== */
	void goToA1() {
		super.changeView(root, a1.getRoot());
	}
	/** ======================================= */
	
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
		campaigns.addAll(cellItemsDB);
		
		/** ========================================================*/
		//add campaigns to listView:
		campaignsLV.setItems(campaigns); 
		//set listView cellFactory to create CampaignCellControllers:
		campaignsLV.setCellFactory(CampaignCellController -> new CampaignCellController(this));
	}
	
	@Override
	void setRoot() { root = Rootable.getRoot(this, FxmlPath.CAMPAIGNS); } //set root
	@Override
	Parent getRoot() { return root; } //get root
	
	
	
	
	//===================================================================
	//TOGGLE TESTING! 
	
	 void test(String selected) {
		 System.out.println(selected + "was selected");
	 }
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
