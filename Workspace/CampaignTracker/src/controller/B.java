package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import view.FxmlPath;

public class B extends FrameContent implements Rootable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAP;

    @FXML
    void initialize() {

    }
    
    //root element for this controller:
  	private final Parent root = Rootable.getRoot(this, FxmlPath.b);
    
  	
  	Parent getRoot() { return this.root; }


	@Override
	void setRoot() {
		// TODO Auto-generated method stub
		
	}


	@Override
	void setViewTitle() {
		// TODO Auto-generated method stub
		
	}


	@Override
	String getViewTitle() {
		// TODO Auto-generated method stub
		return "B";
	}
    
}