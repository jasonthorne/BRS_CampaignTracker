package controller;

import animation.Fadeable;
import animation.Fadeable.FadeOption;
import javafx.scene.Parent;

public abstract class FrameChild {
	
	//frame controller:
	private FrameController frameCtrlr;
	
	//change to another view:
	void changeView(Parent thisRoot, Parent nextRoot) {
		Fadeable.fade(thisRoot, FadeOption.FADE_OUT); //fade out this root
    	frameCtrlr.moveFwrd(nextRoot); //move to next root
	}
	
	//set frame controller:
	void setFrameController(FrameController frameCtrlr) {
		this.frameCtrlr = frameCtrlr;
	}
	
	//get frame controller:
	FrameController getFrameController() {
		return this.frameCtrlr;
	}
	
	//force root getter/setter:
	abstract void setRoot(); 
	abstract Parent getRoot();
	
}