package ch.micha.javafx.mvvm.api;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.UUID;

public interface IModel<T extends IEntity> {

    void add(T toAdd);
    void addAll(List<T> toAdd);
    void save(T toSave);
    T get(UUID id);
    List<T> getAll();
    void remove(T toRemove);
    void remove(UUID id);
    void removeAll(List<T> toRemove);
    void removeAllById(List<UUID> ids);

    void addPropertyChangeListener(PropertyChangeListener l);
    void removePropertyChangeListener(PropertyChangeListener l);
}
