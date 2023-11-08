package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Client;
import fr.gorisse.erp.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends ServiceMethods<Client>{
    private ClientRepository clientRepository;
    @Autowired
    protected void setRepository(ClientRepository clientRepository) {
        super.repository = clientRepository;
        this.clientRepository = clientRepository;
    }
}
