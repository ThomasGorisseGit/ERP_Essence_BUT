package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Client;
import fr.gorisse.erp.backend.entity.Fuel;
import fr.gorisse.erp.backend.entity.Subscription;
import fr.gorisse.erp.backend.services.ClientService;
import fr.gorisse.erp.backend.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController implements DefaultController<Client> {
    @Autowired
    private ClientService clientService;
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/add")
    @Transactional
    public Client create(@RequestBody Client client){
        return this.clientService.create(client);
    }

    @GetMapping("/getClients")
    public List<Client> getAll(){
        return this.clientService.getAll();
    }

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

         Client cli = this.clientService.getEntityById(client_id);
         subscription = this.subscriptionService.getEntityById(subscription.getId());
         cli.setSubscription(subscription);
         return this.clientService.edit(cli);
    }
}
