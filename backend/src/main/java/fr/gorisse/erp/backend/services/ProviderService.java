package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Provider;
import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.repository.ProviderRepository;
import fr.gorisse.erp.backend.services.interfaces.ServiceMethodsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService extends ServiceMethods<Provider>{
    @Autowired
    private ProviderRepository providerRepository;

    @Override
    @Autowired
    protected void setRepository() {
        this.repository = providerRepository;
    }
}
