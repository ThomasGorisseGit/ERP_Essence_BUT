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

    @PostMapping("/order/add")
    @Transactional
    public ClientOrder create(@RequestBody ClientOrder clientOrder){
        return this.clientOrderService.create(clientOrder);
    }

    @GetMapping("/getClientOrders")
    public List<ClientOrder> getAll(){
        return this.clientOrderService.getAll();
    }
    @GetMapping("/getClientOrder/{id}")
    public ClientOrder getClientOrder(@PathVariable int order_id){
        return this.clientOrderService.getEntityById(order_id);
    }
    @GetMapping("/getOrderOfClient/{id}")
    public ClientOrder getOrderOfClient(@PathVariable int client_id){
        return this.clientOrderService.getClientOrder(client_id);
    }
}
