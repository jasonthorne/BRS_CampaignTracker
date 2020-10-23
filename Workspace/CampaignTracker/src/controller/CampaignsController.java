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
import model.CellItem;
import view.FxmlPath;

public class CampaignsController implements Rootable{
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    @FXML private AnchorPane rootAP;
    @FXML private JFXButton btnTest; /** ===================== */
    @FXML private JFXListView<?> campaignsLV;
   
    @FXML
    void initialize() {
    	btnTest.setOnAction(event -> goToA1());
    	
    }
    
    //observable list of campaigns:
    private ObservableList<Campaign>observableList = FXCollections.observableArrayList();
    
   /**===================imaginary db data: =================================*/
   public static List<Campaign>cellItemsDB = new ArrayList<Campaign>();
   /**=======================================================================*/
	
    //root element for this controller:
	private final Parent root = loadRoot.apply(this, FxmlPath.CAMPAIGNS_FXML);
	
	//controllers:
	private final FrameController frameCtrlr;
	private final A1 a1;
	
	CampaignsController(FrameController frameCtrlr) {
		this.frameCtrlr = frameCtrlr; //assign frame controller
		this.a1 = new A1(frameCtrlr);
	}

	Parent getRoot() { return this.root; } //get root
	
	/** ======================================= */
	void goToA1() { frameCtrlr.moveFwrd(a1.getRoot()); }
	/** ======================================= */
	
}
