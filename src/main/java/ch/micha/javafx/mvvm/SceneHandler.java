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

/**
 * a Singleton Class that is responsible for starting the application and changing its scenes
 * to change a Scene use "SceneHandler.getInstance().{@link #showScene(String, Object...)}" as parameters give the
 * identifier of a SceneLoader and other parameters that should be given to the ViewModel defined in the SceneLoader.
 */
@SuppressWarnings("unused")
public class SceneHandler {
    /*values to change depending on the application*/
    public static final String APP_TITLE = "MVVM Template";
    public static final String PATH_TO_ICON = "img/icon.png"; //starting in resources
    public static final double DEFAULT_WIDTH = 600d;
    public static final double DEFAULT_HEIGHT = 400d;
    public static final double DEFAULT_MIN_WIDTH = 400d;
    public static final double DEFAULT_MIN_HEIGHT = 300d;

    /*in this example the model is stored here because we only want a single instance of it.*/
    private static final IModel<Entity> MAIN_MODEL = new LocalModel<>();
    /**
     * a {@link HashMap} containing all the SceneLoaders of the Project. <br>
     * Used to simplify the process of changing scenes (you dont have to remember the fxml file name of a scene, you can
     * just get its identifier) <br>
     * to add a Scene to the Application add its SceneLoader to this map in the constructor of this class
     */
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

        showScene(mainLoaderIdentifier);
        mainStage.show();
        hasLaunched = true;
    }

    /**
     * switch to the scene saved in the given SceneLoader <br>
     * NOTE: dont use this with a new SceneLoader. Try to keep only one instance of all the SceneLoaders and store this
     * instance in the {@link #viewLoaders}. If you dont have the SceneLoader use {@link #showScene(String, Object...)}
     *
     * @param loader the {@link AbstractSceneLoader} that contains the scene that you want to change to
     * @param args args to give to the ViewModel defined in the given SceneLoader
     */
    public void showScene(AbstractSceneLoader loader, Object... args){
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

    /**
     * searches the {@link AbstractSceneLoader} in the {@link #viewLoaders} by its identifier and runs
     * {@link #showScene(AbstractSceneLoader, Object...)}
     * @param identifier the identifier of the SceneLoader with the scene to switch to
     * @param args arguments to give to the ViewModel defined in the given SceneLoader
     */
    public void showScene(String identifier, Object... args){
        AbstractSceneLoader loader = viewLoaders.get(identifier);

        if(loader == null)
            throw new IllegalArgumentException("could not find GuiLoader from given identifier: " + identifier);
        else
            showScene(loader, args);
    }

    public static SceneHandler getInstance() {
        if(INSTANCE == null) INSTANCE = new SceneHandler();
        return INSTANCE;
    }

    public static IModel<Entity> getMainModel(){
        return MAIN_MODEL;
    }

    /** getter for {@link #viewLoaders} */
    public HashMap<String, AbstractSceneLoader> getViewLoaders() {
        return viewLoaders;
    }
}
