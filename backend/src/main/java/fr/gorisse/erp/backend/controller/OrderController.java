package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.ClientOrder;
import fr.gorisse.erp.backend.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
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
    @GetMapping("/getClientOrder/{id}")
    public ClientOrder getById(@PathVariable int id){
        return this.clientOrderService.getEntityById(id);
    }
    @GetMapping("/getOrderOfClient/{id}")
    public ClientOrder getOrderOfClient(@PathVariable int id){
        return this.clientOrderService.getClientOrder(id);
    }
}
