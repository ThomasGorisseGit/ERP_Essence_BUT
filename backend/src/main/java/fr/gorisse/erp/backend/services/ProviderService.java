package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Provider;
import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.repository.ProviderRepository;
import fr.gorisse.erp.backend.services.interfaces.ServiceMethodsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService implements ServiceMethodsInterface<Provider> {
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Provider create(Provider entity) {
        return null;
    }

    @Override
    public Provider edit(Provider entity) {
        return null;
    }

    @Override
    public void delete(Provider entity) {

    }

    @Override
    public List<Provider> getAll() {
        return null;
    }

    @Override
    public Provider getEntityById(int entity_id) {
        return null;
    }

    @Override
    public int getNumberOfEntity() {
        return 0;
    }
}
