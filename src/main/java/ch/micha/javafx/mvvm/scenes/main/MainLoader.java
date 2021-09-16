package ch.micha.javafx.mvvm.scenes.main;

import ch.micha.javafx.mvvm.api.AbstractSceneLoader;

public class MainLoader extends AbstractSceneLoader {
    public static final String IDENTIFIER = "main";

    @Override public void updateValues(Object[] args) {

    }

    @Override public String getIdentifier() {
        return IDENTIFIER;
    }
}
