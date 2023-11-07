package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.ClientOrder;
import fr.gorisse.erp.backend.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private ClientOrderService clientOrderService;

    @PostMapping("/add")
    @Transactional
    public ClientOrder create(@RequestBody ClientOrder clientOrder){
        return this.clientOrderService.create(clientOrder);
    }

    @GetMapping("/getClientOrders")
    public List<ClientOrder> getAll(){
        return this.clientOrderService.getAll();
    }
    @GetMapping("/getClientOrder/{id}")
    public ClientOrder getClientOrder(@PathVariable int id){
        return this.clientOrderService.getEntityById(id);
    }
    @GetMapping("/getOrderOfClient/{id}")
    public ClientOrder getOrderOfClient(@PathVariable int id){
        return this.clientOrderService.getClientOrder(id);
    }
}
