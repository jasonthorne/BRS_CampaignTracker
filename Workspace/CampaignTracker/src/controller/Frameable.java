package controller;

import animation.Fadeable;
import javafx.scene.Parent;

/** serves controller classes who's views are displayed by adding their root nodes to frame.fxml */

public interface Frameable extends Fadeable {
	
	//change to another view:
	static void changeView(Parent thisRoot, Frameable nextFrameable) {
		Fadeable.fade(thisRoot, FadeOption.FADE_OUT); //fade out this root
		FrameController.getFrameCtrlr().moveFwrd(nextFrameable); //move to nextFrameable
	}
	
	//force root getter/setter:
	abstract void setRoot(); 
	abstract Parent getRoot();
	
	//force title getter:
	abstract String getViewTitle();	
}