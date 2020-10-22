package controller;

import java.io.IOException;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//serves controller classes in need of their fxml root elements ++++++++++++++++??????????
public interface Rootable {
	
	/*
	BiFunction<Rootable, FXMLLoader, Parent> loadRoot = (rootable, loader) -> {
		
		loader.setController(rootable); //set rootable as the controller
		try {
			loader.load(); //load fxml tree
		} catch (IOException e) { e.printStackTrace(); }
		
		return loader.getRoot(); //return root element
	};*/
	
	
	BiFunction<Rootable, String, Parent> loadRoot = (rootable, url) -> {
		
		//create loader:
		FXMLLoader loader = new FXMLLoader(rootable.getClass().getResource(url)); 
		loader.setController(rootable); //set this class as the controller
		try {
			loader.load(); //load fxml tree
		} catch (IOException e) { e.printStackTrace(); }
		
		return loader.getRoot(); //return root element
	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}


