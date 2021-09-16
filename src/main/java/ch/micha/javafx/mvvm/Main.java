package ch.micha.javafx.mvvm;

import ch.micha.javafx.mvvm.scenes.main.MainLoader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneHandler.getInstance().launchFirstTime(primaryStage, MainLoader.IDENTIFIER, SceneHandler.APP_TITLE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
