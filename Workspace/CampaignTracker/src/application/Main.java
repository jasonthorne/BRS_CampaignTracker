package application;

import controller.FrameController;
import controller.Test1;
import controller.FrameChild;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	FrameController frameCtrlr = new FrameController();
    	frameCtrlr.showStage();
    }
}