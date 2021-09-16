package ch.micha.javafx.mvvm.api;

import java.beans.PropertyChangeListener;

/**
 * A blueprint for the ViewModel. A ViewModel has to have the method {@link #updateArgs(Object[])} because otherwise
 * the {@link AbstractSceneLoader} could not be done this abstract. And a ViewModel should always be connected to the
 * model via a PropertyChangeListener.
 */
public interface IViewModel extends PropertyChangeListener {
    /**
     * called every time the {@link ch.micha.javafx.mvvm.SceneHandler} changes the stage to this scene.
     * @param args arguments that you want to send to the scene
     */
    void updateArgs(Object[] args);
}
