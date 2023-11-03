package fr.gorisse.erp.backend.services.interfaces;

import java.util.List;

public interface ServiceMethodsInterface<T> {
    T create(T entity);
    T edit(T entity);
    void delete(T entity);
    List<T> getAll();
    T getEntityById(int entity_id);
    int getNumberOfEntity();
}
