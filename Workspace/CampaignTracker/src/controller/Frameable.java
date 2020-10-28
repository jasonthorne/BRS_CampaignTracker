package controller;

import animation.Fadeable;
import javafx.scene.Parent;

//public abstract class FrameContent implements Fadeable {
public interface Frameable extends Fadeable {
	
	//https://github.com/jasonthorne/BRS_CampaignTracker/tree/e6e35427a6abb839d39adb9d3a7582182f54fb13/Workspace/CampaignTracker/src
	
	//frame.fxml controller:
	//FrameController frameCtrlr;

	
	//set frame controller:
	/*default void setFrameController(FrameController frameCtrlr2) {
		frameCtrlr = frameCtrlr2; //+++++++++++WE WANT THIS FIRING ONLY ONCE!!++++++++++++
	}*/
	
	//get frame controller:
	/*FrameController getFrameController() {
		return FrameContent.frameCtrlr;
	} ++++++++++++++++++++try not to use thiS!! */
	
	/*
	//change to another view:
	//default void changeView(Parent thisRoot, Parent nextRoot) {
	default void changeView(Parent thisRoot, Parent nextRoot, FrameController frameCtrlr) {
		//////frameCtrlr.setViewLbl(viewTitle);
		Fadeable.fade(thisRoot, FadeOption.FADE_OUT); //fade out this root
		////=======================frameCtrlr.moveFwrd(nextRoot); //move to next root
		FrameController.getFrameCtrlr().moveFwrd(nextRoot);
	}*/
	
	
	
	//change to another view:
	static void changeView(Parent thisRoot, Frameable framable) {
		///////frameCtrlr.setViewLbl(frameContent.getViewTitle());
		Fadeable.fade(thisRoot, FadeOption.FADE_OUT); //fade out this root
		//frameCtrlr.moveFwrd(frameContent); //move to next root
		FrameController.getFrameCtrlr().moveFwrd(framable);
	}
	
		
	//force root getter/setter:
	abstract void setRoot(); 
	abstract Parent getRoot();
	
	//force title getter/setter:
	abstract void setViewTitle(); 
	abstract String getViewTitle();	
	
}