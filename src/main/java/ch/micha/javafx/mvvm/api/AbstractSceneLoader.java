package ch.micha.javafx.mvvm.api;

import ch.micha.javafx.mvvm.SceneHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

/**
 * used for the {@link SceneHandler}. Helps the SceneHandler when changing a UI. <br>
 * When implementing this class add an attribute called IDENTIFIER and store the fxml file name in it (without .fxml)
 */
public abstract class AbstractSceneLoader {

    protected FXMLLoader fxmlLoader;
    protected Scene scene;
    protected boolean isLoaded = false;
    protected boolean isBound = false;

    /**
     * loads the fxml file<br>
     * this is done so the fxml file only has to be loaded once. This methode is only called if this is not loaded. <br>
     * if you want to do something every time the Stage switches to this Scene use {@link #updateValues(Object[])}.
     */
    public void loadGUI(Parent root, String identifier) throws IOException{
        fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/" + identifier + ".fxml"));
        root = fxmlLoader.load();
        scene = new Scene(root, SceneHandler.DEFAULT_WIDTH, SceneHandler.DEFAULT_HEIGHT);

        isLoaded = true;
    }

    /**
     * is called every time the {@link SceneHandler} switches the scene to this one.
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

    /**
     * used to dynamically get the fxml file name.
     */
    public abstract String getIdentifier();
}
