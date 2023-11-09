package fr.gorisse.erp.backend.controller;

import java.util.List;

public interface DefaultController<T> {
    T create(T entity);

    void delete(T entity);

    void deleteById(int id);

    T getById(int id);

    List<T> getAll();

}
