package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListCell;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import model.Campaign;
import model.Player;

public class PlayerCellController extends JFXListCell<Player> implements Rootable {
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;

    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private Label nameLbl;
    @FXML private Label scoreLbl;
    @FXML private ImageView pilotIV;

    @FXML
    void initialize() {
  
    }
    
    //root element for this controller:
    private final Parent root = Rootable.getRoot(this, "/view/playerCell.fxml");
    
    //update cell with player data:
  	@Override 
  	protected void updateItem(Player player, boolean isEmpty) {
  		super.updateItem(player, isEmpty);
        
		if (isEmpty || player == null) {
	        setText(null);
	        setGraphic(null);
	    } else {
	    	
	    	//populate cell with data from player:
	    	nameLbl.setText(player.getName()); //get player name
	    	scoreLbl.setText(String.valueOf(player.getScore())); //get player score
	    	
	    	setText(null); 
	    	setGraphic(rootAP); //set this root element as the graphic
	    }
  	}

}
