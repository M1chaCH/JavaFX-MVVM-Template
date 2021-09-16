package ch.micha.javafx.mvvm.api;

import java.util.UUID;

/**
 * simple preset for an entity <br>
 * used for the model so it can make sure it is able to compare entities by their ids.
 */
public interface IEntity {
    UUID getUUID();
}
