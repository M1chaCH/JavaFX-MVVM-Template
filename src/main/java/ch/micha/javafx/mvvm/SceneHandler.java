package ch.micha.javafx.mvvm;

import ch.micha.javafx.mvvm.api.IModel;
import ch.micha.javafx.mvvm.api.LocalModel;
import ch.micha.javafx.mvvm.api.AbstractSceneLoader;
import ch.micha.javafx.mvvm.scenes.main.MainLoader;
import ch.micha.javafx.mvvm.model.Entity;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

@SuppressWarnings("unused")
public class SceneHandler {
    public static final String APP_TITLE = "MVVM Template";
    public static final String PATH_TO_ICON = "img/icon.png"; //starting in resources
    public static final double DEFAULT_WIDTH = 600d;
    public static final double DEFAULT_HEIGHT = 400d;
    public static final double DEFAULT_MIN_WIDTH = 400d;
    public static final double DEFAULT_MIN_HEIGHT = 300d;

    private static final IModel<Entity> MAIN_MODEL = new LocalModel<>();
    private final HashMap<String, AbstractSceneLoader> viewLoaders;

    private static SceneHandler INSTANCE;
    private Parent root;
    private Stage mainStage;
    private boolean hasLaunched = false;

    private SceneHandler() {
        viewLoaders = new HashMap<>();
        viewLoaders.put(MainLoader.IDENTIFIER, new MainLoader());
    }

    public void launchFirstTime(Stage stage, String mainLoaderIdentifier, String stageTitle) {
        if(hasLaunched) throw new IllegalStateException("this methode can only be called once");
        this.mainStage = stage;

        mainStage.getIcons().add(new Image(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(PATH_TO_ICON))));
        mainStage.setMinWidth(DEFAULT_MIN_WIDTH);
        mainStage.setMinHeight(DEFAULT_MIN_HEIGHT);
        mainStage.setTitle(stageTitle);
        mainStage.setOnCloseRequest(e -> System.exit(1));

        showGui(mainLoaderIdentifier);
        mainStage.show();
        hasLaunched = true;
    }

    public void showGui(AbstractSceneLoader loader, Object... args){
        try {
            if (!loader.hasLoaded()) {
                loader.loadGUI(root, loader.getIdentifier());
            }
            loader.updateValues(args);
            mainStage.setScene(loader.getScene());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showGui(String identifier, Object... args){
        AbstractSceneLoader loader = viewLoaders.get(identifier);

        if(loader == null)
            throw new IllegalArgumentException("could not find GuiLoader from given identifier: " + identifier);
        else
            showGui(loader, args);
    }

    public static SceneHandler getInstance() {
        if(INSTANCE == null) INSTANCE = new SceneHandler();
        return INSTANCE;
    }

    public static IModel<Entity> getMainModel(){
        return MAIN_MODEL;
    }

    public HashMap<String, AbstractSceneLoader> getViewLoaders() {
        return viewLoaders;
    }
}
