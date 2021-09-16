package ch.micha.javafx.mvvm.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LocalModel<T extends IEntity> extends AbstractModel<T> {

    private final List<T> items = new ArrayList<>();

    private void itemsChanged(){
        changeSupport.firePropertyChange(PROPERTY_ITEMS_CHANGED, null, items);
    }

    @Override public void add(T toAdd) {
        items.add(toAdd);
        itemsChanged();
    }

    @Override public void addAll(List<T> toAdd) {
        items.addAll(toAdd);
        itemsChanged();
    }

    @Override public void save(T toSave) {
        if(toSave == null) throw new IllegalArgumentException("Entity to save can not be null");

        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getUUID().equals(toSave.getUUID())){
                items.set(i, toSave);
                itemsChanged();
                break;
            }
        }
    }

    @Override public T get(UUID id) {
        if(id == null) throw new IllegalArgumentException("Entity to remove can not be null");

        for (T item : items) {
            if(item.getUUID().equals(id)) return item;
        }
        return null;
    }

    @Override public List<T> getAll() {
        return items;
    }

    @Override public void remove(T toRemove) {
        items.remove(toRemove);
        itemsChanged();
    }

    @Override public void remove(UUID id) {
        if(id == null) throw new IllegalArgumentException("Entity to remove can not be null");

        for (T item : items) {
            if(item.getUUID().equals(id)){
                remove(item);
                break;
            }
        }
    }

    @Override public void removeAll(List<T> toRemove) {
        items.removeAll(toRemove);
        itemsChanged();
    }

    @Override public void removeAllById(List<UUID> ids) {
        if(ids == null || ids.size() < 1)
            throw new IllegalArgumentException("List with UUIDs to remove can't be null and has to have at least one value");

        List<T> entitiesToRemove = new ArrayList<>();
        for (UUID id : ids) {
            entitiesToRemove.add(get(id));
        }

        removeAll(entitiesToRemove);
    }
}
