package controller;

import java.io.IOException;
import java.util.function.BiFunction;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import view.FxmlPath;

/** serves controller classes in need of fxml root elements */

public interface Rootable {
	
	BiFunction<Rootable, FxmlPath, Parent> loadRoot = (rootable, path) -> {
		//create loader:
		FXMLLoader loader = new FXMLLoader(rootable.getClass().getResource(path.toString())); 
		loader.setController(rootable); //set this class as the controller
		try {
			loader.load(); //load fxml tree
		} catch (IOException e) { e.printStackTrace(); }
		
		return loader.getRoot(); //return root element
	};
}


/*
try {
	//load properties:
	Properties properties = new Properties();	
	properties.load(new FileInputStream("configs/fxml/fxml_paths.properties"));
	System.out.println(properties.getProperty("login"));
}catch(Exception e) { e.printStackTrace(); }
*/


