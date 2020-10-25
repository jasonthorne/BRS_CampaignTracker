package controller;

import animation.Fadeable;
import animation.Fadeable.FadeOption;
import javafx.scene.Parent;

public abstract class FrameContent {
	
	//frame.fxml controller:
	private static FrameController frameCtrlr;
	
	//set frame controller:
	void setFrameController(FrameController frameCtrlr) {
		FrameContent.frameCtrlr = frameCtrlr;
	}
	
	//get frame controller:
	FrameController getFrameController() {
		return FrameContent.frameCtrlr;
	}
	
	//change to another view:
	void changeView(Parent thisRoot, Parent nextRoot) {
		Fadeable.fade(thisRoot, FadeOption.FADE_OUT); //fade out this root
		FrameContent.frameCtrlr.moveFwrd(nextRoot); //move to next root
	}
		
	//force root getter/setter:
	abstract void setRoot(); 
	abstract Parent getRoot();
}