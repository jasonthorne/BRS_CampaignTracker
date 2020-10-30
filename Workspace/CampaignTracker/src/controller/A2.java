package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class A2 implements Frameable,  Rootable {

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
    private final Parent root = Rootable.getRoot(this, "/view/a2.fxml");
    
    A2(){
    	
    }
    
    @Override
    public Parent getRoot() { return this.root; }

	@Override
	public
	void setRoot() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public
	String getViewTitle() {
		// TODO Auto-generated method stub
		return "A2";
	}

}
