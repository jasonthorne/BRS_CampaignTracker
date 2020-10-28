package application;

import controller.FrameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	//////========FrameController frameCtrlr = new FrameController();
    	
    	FrameController frameCtrlr = FrameController.getFrameCtrlr();
    	frameCtrlr.showStage();
    	
    }
}