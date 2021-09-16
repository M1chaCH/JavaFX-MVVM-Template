package ch.micha.javafx.mvvm.api;

import ch.micha.javafx.mvvm.SceneHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public abstract class AbstractSceneLoader {

    protected FXMLLoader fxmlLoader;
    protected Scene scene;
    protected boolean isLoaded = false;
    protected boolean isBound = false;

    public void loadGUI(Parent root, String identifier) throws IOException{
        fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/" + identifier + ".fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root, SceneHandler.DEFAULT_WIDTH, SceneHandler.DEFAULT_HEIGHT);

        isLoaded = true;
    }

    /**
     * set everything (viewModel with model, viewModel withController...), bind, provide args if given
     * @param args args to give to the viewModel
     */
    public abstract void updateValues(Object[] args);

    public Scene getScene(){
        return scene;
    }

    public boolean hasLoaded(){
        return isLoaded;
    }

    public abstract String getIdentifier();
}
