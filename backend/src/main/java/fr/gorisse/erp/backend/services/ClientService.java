package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Client;
import fr.gorisse.erp.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends ServiceMethods<Client>{
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    @Override
    protected void setRepository() {
        super.repository = clientRepository;
    }
}
