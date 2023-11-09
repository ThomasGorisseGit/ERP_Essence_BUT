package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Fuel;
import fr.gorisse.erp.backend.services.FuelService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fuel")
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
}
