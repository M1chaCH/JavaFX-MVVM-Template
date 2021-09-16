package ch.micha.javafx.mvvm.api;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * implements the basic usage of the PropertyChangeSupport since this is most likely going to be the same over all models.
 * @param <T> the Type of the Entity for the model.
 */
public abstract class AbstractModel<T extends IEntity> implements IModel<T>{
    /** intended to use ase the name of the fired event when the items (list/DB/...) changed */
    public static final String PROPERTY_ITEMS_CHANGED = "items-changed";
    /**
     * the {@link PropertyChangeSupport} for this model. <br>
     * intended to call all the listening viewModels (listeners can also be different classes)
     */
    protected final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    /** add a listener to the {@link PropertyChangeSupport} */
    public void addPropertyChangeListener(PropertyChangeListener l){
        changeSupport.addPropertyChangeListener(l);
    }

    /** removes a listener from the {@link PropertyChangeSupport} */
    public void removePropertyChangeListener(PropertyChangeListener l){
        changeSupport.removePropertyChangeListener(l);
    }
}
