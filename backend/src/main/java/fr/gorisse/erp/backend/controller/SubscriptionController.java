package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Subscription;
import fr.gorisse.erp.backend.services.SubscriptionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
@CrossOrigin(origins = {"http://localhost:4200", "http://209.38.204.153:8080","https://209.38.204.153:8080", "http://209.38.204.153","https://209.38.204.153:80","http://thomasgorisse.com","https://thomasgorisse.com"})

public class SubscriptionController implements DefaultController<Subscription> {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/add")
    @Transactional
    public Subscription create(@RequestBody Subscription subscription){
        return this.subscriptionService.create(subscription);
    }

    @Override
    @DeleteMapping("/delete")
    @Transactional
    public void delete(Subscription entity) {
        this.subscriptionService.delete(entity);
    }


    @GetMapping("/getSubscriptions")
    public List<Subscription> getAll(){
        return this.subscriptionService.getAll();
    }

    @PostMapping("/edit")
    @Transactional
    public Subscription edit(@RequestBody Subscription subscription){
        return this.subscriptionService.edit(subscription);
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteById(@PathVariable("id") int id){
        this.subscriptionService.deleteById(id);
    }

    @GetMapping("/getSubscriptionById/{id}")
    public Subscription getById(@PathVariable("id")int id){
        return this.subscriptionService.getEntityById(id);
    }
}
