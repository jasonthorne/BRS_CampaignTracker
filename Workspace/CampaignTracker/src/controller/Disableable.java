package controller;

import com.jfoenix.controls.JFXButton;

/** serves controller classes with FXML elements requiring disabled states */

public interface Disableable {
	
	//disable JFX Button:
	static void disableJfxBtn(JFXButton btn) {
		btn.setVisible(false); //hide from view
		btn.setDisable(true); //disable state
	}
	
	//enable JFX Button:
	static void enableJfxBtn(JFXButton btn) {
		btn.setVisible(true); //show in view
		btn.setDisable(false); //enable state
	}
}
