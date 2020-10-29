package application;

import controller.FrameController;
import database.InsertPlayer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application implements InsertPlayer{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	InsertPlayer.insert("jo", "wood");
    	
    	FrameController frameCtrlr = FrameController.getFrameCtrlr();
    	frameCtrlr.showStage();
    	
    	
    }
}