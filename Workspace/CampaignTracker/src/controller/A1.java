package controller;

import com.jfoenix.controls.JFXButton;

import animation.Fadeable;
import animation.Fadeable.FadeOption;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import view.FxmlPath;

public class A1  implements Frameable,  Rootable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane rootAP;

    @FXML
    private JFXButton a2Btn;

    @FXML
    private JFXButton bBtn;

    @FXML
    void initialize() {
    	a2Btn.setOnAction(event -> goToA2());
    	bBtn.setOnAction(event -> goToB());
    }
    
  //root element for this controller:
  	private final Parent root = Rootable.getRoot(this, FxmlPath.a1);
    
    FrameController frameCtrlr;
    A2 a2 = new A2(frameCtrlr);
    B b = new B();
    
    
    void goToA2(){
    	//////////////super.changeView(root, new A2().getRoot()); /** +++++++++++++++ prob not, as db pulls will happen each time you renav to a page! ++++++++++ */
    	///////////super.changeView(root, a2);
    }
    
    
    void goToB(){
    	///////////////super.changeView(root, b.getRoot());
    	///////////super.changeView(root, b);
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
	void setViewTitle() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public
	String getViewTitle() {
		// TODO Auto-generated method stub
		return "A1";
	}


	
    
    
    
}
