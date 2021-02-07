package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Campaign;
/*import model.Campaign.CampaignBuilder;*/
import model.Event;

public class CampaignController implements Rootable, Frameable{
	
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
   
    @FXML private AnchorPane rootAP;
    @FXML  private Label eventNameLbl;
    @FXML private JFXButton addUserBtn;
    @FXML private JFXListView<?> missionsLV;
    @FXML private JFXListView<?> playersLV;
    
   
    @FXML
    void initialize() {
    	
    	addUserBtn.setOnAction(event -> System.out.println("yo dawg!"));
    	
    }
	///////////////https://stackoverflow.com/questions/26313756/implementing-an-observablevalue
	
	////////////////private final Stage stage = new Stage(); //stage
    ///////////////private final Scene scene = new Scene(Rootable.getRoot(this, "/view/campaign.fxml")); //rooted scene
	
    //fxml root node:
  	private Parent root; 
  	
  	private final Campaign campaign;
    
	//constructor:
	CampaignController(Campaign campaign){
		setRoot(); //set root node
		///setCampaign(campaign);
		this.campaign = campaign;
	}
	
	private BooleanProperty  wasCreated;
    private BooleanProperty finalValueProperty = new SimpleBooleanProperty(false);
    private BooleanProperty completedProperty = new SimpleBooleanProperty();
	
	
	private void setCampaign(Campaign campaign) {
		
		wasCreated  = new SimpleBooleanProperty(campaign.getWasCreated());
		
		
		//if campaign wasn't just created, and hasn't been fully downloaded:
		if(!campaign.getWasCreated() && !campaign.getHasPlayersData()) {
			
			System.out.println("DOWNLOADING PLAYERS");
			
			campaign.updateNameToPlayer(campaign);
			//++++++++++++++HERE WE NEED TO LOK FOR CAMPAIGN IN SAVED DATA IF THIS IS UNSUCCESSFUL< AND USE RTHAT ONE> AND INBFORM USER OF ERROR DOWNLOADING! 
			
		}else { //???????????????

			System.out.println("has downloaded all palyers:");
			
		}
		
		

	}
	
	
	
	
	
	//show stage:
    /*void showStage() { 
    	System.out.println("showing campainCtrlr stage"); 
    	stage.showAndWait(); 
	}*/

    @Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/campaign.fxml"); } //set root
	@Override
	public Parent getRoot() { return root; } //get root
	@Override
	public String getViewTitle() { return "Campaign"; } 
		
	

}
