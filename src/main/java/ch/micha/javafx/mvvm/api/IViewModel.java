package ch.micha.javafx.mvvm.api;

import java.beans.PropertyChangeListener;

public interface IViewModel extends PropertyChangeListener {
    void updateArgs(Object[] args);
}
