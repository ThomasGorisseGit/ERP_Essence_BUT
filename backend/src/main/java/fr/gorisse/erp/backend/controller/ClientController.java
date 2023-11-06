package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Client;
import fr.gorisse.erp.backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/add")
    @Transactional
    public Client create(@RequestBody Client client){
        return this.clientService.create(client);
    }

    @GetMapping("/getClients")
    public List<Client> getClients(){
        return this.clientService.getAll();
    }

    @GetMapping("/getClientById")
    public Client getClientById(@RequestBody int id){
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
    public void deletePath(@PathVariable("id") int client_id){
        this.clientService.deleteById(client_id);
    }
}
