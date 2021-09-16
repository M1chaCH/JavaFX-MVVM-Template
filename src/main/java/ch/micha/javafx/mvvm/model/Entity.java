package ch.micha.javafx.mvvm.model;

import ch.micha.javafx.mvvm.api.IEntity;

import java.util.UUID;

public class Entity implements IEntity {
    private final UUID uuid = UUID.randomUUID();

    private String name;

    public Entity(String name) {
        this.name = name;
    }

    public Entity(){
        this.name = "some name";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public UUID getUUID() {
        return uuid;
    }
}
