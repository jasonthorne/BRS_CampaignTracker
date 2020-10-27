package controller;

import animation.Fadeable;
import javafx.scene.Parent;

public abstract class FrameContent implements Fadeable {
	
	//frame.fxml controller:
	private static FrameController frameCtrlr;
	
	//set frame controller:
	void setFrameController(FrameController frameCtrlr) {
		FrameContent.frameCtrlr = frameCtrlr; /** +++++++++++WE WANT THIS FIRING ONLY ONCE!!++++++++++++ */
	}
	
	//get frame controller:
	/*FrameController getFrameController() {
		return FrameContent.frameCtrlr;
	} ++++++++++++++++++++try not to use thiS!! */
	
	//change to another view:
	void changeView(Parent thisRoot, Parent nextRoot, String viewTitle) {
		frameCtrlr.setViewLbl(viewTitle);
		Fadeable.fade(thisRoot, FadeOption.FADE_OUT); //fade out this root
		frameCtrlr.moveFwrd(nextRoot); //move to next root
	}
	
	//change to another view:
	void changeView(Parent thisRoot, FrameContent frameContent) {
		/////////////////+++++++++frameCtrlr.setViewLbl(viewTitle);
		Fadeable.fade(thisRoot, FadeOption.FADE_OUT); //fade out this root
		frameCtrlr.moveFwrd(frameContent); //move to next root
	}
		
	//force root getter/setter:
	abstract void setRoot(); 
	abstract Parent getRoot();
	
	//get child root:
	Parent getChildRoot() { return getRoot(); } //??????????? NEEDED?????????????
	
	
}