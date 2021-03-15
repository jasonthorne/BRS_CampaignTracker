package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

public class RulesController {
	
	@FXML private ResourceBundle resources;
    @FXML private URL location;
    
    //root fxml element & children:
    @FXML private SplitPane rootSP;
    @FXML private AnchorPane headingsAP;
    @FXML private ListView<?> headingsLV;
    @FXML private AnchorPane contenrAP;
    @FXML private Label testLbl;

    @FXML
    void initialize() {  }

}
