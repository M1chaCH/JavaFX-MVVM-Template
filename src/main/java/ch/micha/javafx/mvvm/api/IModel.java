package ch.micha.javafx.mvvm.api;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.UUID;

/**
 * the model that handles the data flow and in some cases also the business logic. <br>
 * contains CRUD methods and methods to use with the {@link java.beans.PropertyChangeSupport}
 * @param <T> the type of the Entity that the Model is handling. Required because else you had to parse stuff every time
 *           you call any of the methods in this class.
 */
public interface IModel<T extends IEntity> {

    /** add the Entity {@link T} to the implemented data source*/
    void add(T toAdd);
    /** add all the Entities {@link T} to the implemented data source*/
    void addAll(List<T> toAdd);
    /** update a already existing entity of type {@link T} in the data source */
    void save(T toSave);
    /** return an entity of type {@link T} searched by its UUID from the data source */
    T get(UUID id);
    /** get all entities of type {@link T} in the data source */
    List<T> getAll();
    /** remove an entity of type {@link T} form the data source */
    void remove(T toRemove);
    /** remove an entity of type {@link T} form the data source searched by its UUID*/
    void remove(UUID id);
    /** remove a list of entities of type {@link T} form the data source*/
    void removeAll(List<T> toRemove);
    /** remove a list of entities of type {@link T} form the data source searched by its UUID*/
    void removeAllById(List<UUID> ids);

    /** add a {@link PropertyChangeListener} to an {@link java.beans.PropertyChangeSupport} */
    void addPropertyChangeListener(PropertyChangeListener l);
    /** remove a {@link PropertyChangeListener} from a {@link java.beans.PropertyChangeSupport} */
    void removePropertyChangeListener(PropertyChangeListener l);
}
