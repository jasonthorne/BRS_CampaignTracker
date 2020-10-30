package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import animation.Fadeable;
import animation.Shakeable;

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
    	
    	//if name & password fields aren't empty: 
		if(!nameTxtFld.getText().trim().equals("") && !pswrdTxtFld.getText().trim().equals("")) {
			
			
			/** +++++++++++++ check that username isnt taken +++++++*/
			
			/** +++++++++++++ check that password isnt taken +++++++*/
		
			/**++++++++ then:  +++++++*/
		
			//insert player into db, and set playerId with the returned id of said player:
			LoginController.setPlayerId(database.InsertPlayer.insert(
					nameTxtFld.getText().trim(), pswrdTxtFld.getText().trim())); 
		
		
		}else { // a field was blank:
			/** +++++++++++++++++ make this label thing better :P +++++++++ */
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

