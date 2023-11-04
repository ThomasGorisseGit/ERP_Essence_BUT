package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Client;
import fr.gorisse.erp.backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/add")
    public Client create(@RequestBody Client client){
        return this.clientService.create(client);
    }

    @GetMapping("/getClients")
    public List<Client> getClients(){
        return this.clientService.getAll();
    }
}
