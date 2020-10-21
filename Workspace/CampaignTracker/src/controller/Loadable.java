package controller;

import java.util.function.Function;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public interface Loadable {
	
	/** nice idea, but needs a lot of looking at! :P */
	
	/*
	Function<Loadable, Stage> setStage = (loadable) -> {
		
		
		//create loader:
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/frame.fxml")); 
    	loader.setController(loadable);//set this class as the controller
    	Scene scene = new Scene(loader.load()); //load fxml into scene
    	stage.setScene(scene); //add scene to stage
		
		return null;
		
	};*/

}
