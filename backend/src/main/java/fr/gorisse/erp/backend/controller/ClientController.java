package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Client;
import fr.gorisse.erp.backend.entity.Subscription;
import fr.gorisse.erp.backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = {"http://localhost:4200", "http://209.38.204.153:8080","https://209.38.204.153:8080", "http://209.38.204.153","https://209.38.204.153:80","http://thomasgorisse.com","https://thomasgorisse.com"})
public class ClientController implements DefaultController<Client> {
    @Autowired
    private ClientService clientService;

    @PostMapping("/add")
    @Transactional
    public Client create(@RequestBody Client client){
        return this.clientService.create(client);
    }

    @GetMapping("/getClients")
    public List<Client> getAll(){
        return this.clientService.getAll();
    }

    @Override
    @GetMapping("/getClientById")
    public Client getById(@RequestBody int id){
        return this.clientService.getEntityById(id);
    }
    @GetMapping("/getClientById/{id}")
    public Client getClientByIdPath(@PathVariable("id") int id){
        return this.clientService.getEntityById(id);
    }
    @DeleteMapping("/delete")
    @Transactional
    public void delete(@RequestBody Client client){
        this.clientService.delete(client);
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteById(@PathVariable("id") int client_id){
        this.clientService.deleteById(client_id);
    }

    @PostMapping("/setSubscription/{client_id}")
    @Transactional
    public Client setSub(@PathVariable("client_id")int client_id, @RequestBody Subscription subscription) {
        return this.clientService.setSubscription(client_id, subscription);
    }
}
