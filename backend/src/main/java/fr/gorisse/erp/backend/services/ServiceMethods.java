package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.repository.reporepo;
import fr.gorisse.erp.backend.services.interfaces.ServiceMethodsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceMethods<T> implements ServiceMethodsInterface<T> {
    JpaRepository<T,Integer> repository;

    @Override
    public T create(T entity) {
        return this.repository.save(entity);
    }

    @Override
    public T edit(T entity) {
        return this.repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        this.repository.delete(entity);
    }

    @Override
    public List<T> getAll() {
        return this.repository.findAll();
    }

    @Override
    public T getEntityById(int entity_id) {
        return this.repository.getReferenceById(entity_id);
    }

    @Override
    public int getNumberOfEntity() {
        return this.repository.findAll().size();
    }
}
