package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import animation.Fadeable;
import animation.Shakeable;
import animation.Fadeable.FadeOption;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SignupController implements Rootable, Fadeable, Frameable {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
   
    //root fxml element & children:
    @FXML private AnchorPane rootAP;
    @FXML private Label errorLbl;
    @FXML private JFXTextField nameTxtFld;
    @FXML private JFXPasswordField pswrdTxtFld;
    @FXML private JFXButton signupBtn; //remove back button option from stack when this is pressed
    
    @FXML
    void initialize() {
    	errorLbl.setVisible(false);  //+++++++++++++make this better! :P
    	//set btn actions:
    	signupBtn.setOnAction(event -> signupUsr());
    }

  //fxml root node:
  	private Parent root;
  	
    //constructor:
    SignupController() {
    	setRoot(); //set root node
    }
    
    private void signupUsr() {
    	
    	//trim name & password fields:
		String name = nameTxtFld.getText().trim();
		String password = pswrdTxtFld.getText().trim();
    	
    	//if fields aren't empty: 
		if(!name.equals("") && !password.equals("")) {
			
			//try insert player into db, setting idCheck with the returned value:
			int idCheck = database.InsertUser.insert(name, password);
			
			//if result is > 0 then player was inserted (as valid id was returned)
			if (idCheck > 0) {
				LoginController.setUserVals(idCheck, name); //set logged in user with id & name
				FrameController.getFrameCtrlr().setPlayerLbl(name); //set frame's name label with name
				Fadeable.fade(root, FadeOption.FADE_OUT); //fade out this view
				//FrameController.getFrameCtrlr().loginMove(campaignsCtrlr); //move to campaigns view
			}else {
				//warn user with error msg that username or password was taken
				System.out.println("username or password taken");
			}
			
		}else { // a field was blank:
			/** +++++++++++++++++ make this label thing better :P BY SHOWING ALERT BOX INSTEAD!!  +++++++++ */
			//errorLbl.setVisible(true); //inform user with label
			//shake elements:
			Arrays.asList(nameTxtFld, pswrdTxtFld).forEach(fxml -> Shakeable.shake(fxml));
		} 
    }
    
	@Override
	public void setRoot() { root = Rootable.getRoot(this, "/view/signup.fxml"); } //set root
	@Override
	public Parent getRoot() { return root; } //get root

	@Override
	public String getViewTitle() { return "Signup"; } //get title
}

