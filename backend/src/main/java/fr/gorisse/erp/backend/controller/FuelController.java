package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Fuel;
import fr.gorisse.erp.backend.services.FuelService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fuel")
@CrossOrigin(origins = {"http://localhost:4200", "http://209.38.204.153:8080","https://209.38.204.153:8080", "http://209.38.204.153","https://209.38.204.153:80","http://thomasgorisse.com","https://thomasgorisse.com"})

public class FuelController implements DefaultController<Fuel> {

    @Autowired
    private FuelService fuelService;

    @PostMapping("/add")
    @Transactional
    public Fuel create(@RequestBody Fuel fuel){
        return this.fuelService.create(fuel);
    }

    @GetMapping("/getFuels")
    public List<Fuel> getAll(){
        return this.fuelService.getAll();
    }

    @Override
    @GetMapping("/getFuelById/{id}")
    public Fuel getById(@PathVariable("id")int id){
        return this.fuelService.getEntityById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id")int id){
        this.fuelService.deleteById(id);
    }

    @DeleteMapping("/delete")
    @Transactional
    public void delete(@RequestBody Fuel fuel){
        this.fuelService.delete(fuel);
    }


    @PostMapping("/addQte/{id}")
    @Transactional
    public Fuel addQte(@RequestBody int quantity, @PathVariable("id")int id){
        return this.fuelService.addQte(quantity,id);
    }
}
