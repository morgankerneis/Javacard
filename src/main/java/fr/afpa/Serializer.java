package fr.afpa;

import java.util.List;

public interface Serializer<T> {
    public abstract void saveList(String filePath, List<T> objectsToSave);

    public abstract void save(String filePath, T object);
}
