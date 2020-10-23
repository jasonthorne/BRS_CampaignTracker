package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import view.FxmlPath;

public class B implements Rootable {

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
  	private final Parent root = loadRoot.apply(this, FxmlPath.b);
    
  	
  	Parent getRoot() { return this.root; }
    
}