package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.ClientOrder;
import fr.gorisse.erp.backend.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = {"http://localhost:4200", "http://209.38.204.153:8080","https://209.38.204.153:8080", "http://209.38.204.153","https://209.38.204.153:80","http://thomasgorisse.com","https://thomasgorisse.com"})

public class OrderController implements DefaultController<ClientOrder> {
    @Autowired
    private ClientOrderService clientOrderService;

    @PostMapping("/add")
    @Transactional
    public ClientOrder create(@RequestBody ClientOrder clientOrder){
        return this.clientOrderService.create(clientOrder);
    }

    @Override
    @DeleteMapping("/delete")
    public void delete(@RequestBody ClientOrder entity) {
        this.clientOrderService.delete(entity);
    }

    @Override
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") int id) {
        this.clientOrderService.deleteById(id);
    }

    @GetMapping("/getClientOrders")
    public List<ClientOrder> getAll(){
        return this.clientOrderService.getAll();
    }
    @Override
    @GetMapping("/getClientOrder/{id}")
    public ClientOrder getById(@PathVariable int id){
        return this.clientOrderService.getEntityById(id);
    }
    @GetMapping("/getOrderOfClient/{id}")
    public ClientOrder getOrderOfClient(@PathVariable int id){
        return this.clientOrderService.getClientOrder(id);
    }
}
