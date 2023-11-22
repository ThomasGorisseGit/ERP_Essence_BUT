package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Fuel;
import fr.gorisse.erp.backend.repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelService extends ServiceMethods<Fuel>{
    @Autowired
    public void setRepository(FuelRepository fuelRepository){
        super.repository = fuelRepository;
    }

    public void createAll(List<Fuel> fuelList) {
        super.repository.saveAll(fuelList);
    }
}
