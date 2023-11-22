package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Fuel;
import fr.gorisse.erp.backend.exceptions.DataIncorrect;
import fr.gorisse.erp.backend.repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FuelService extends ServiceMethods<Fuel>{
    @Autowired
    public void setRepository(FuelRepository fuelRepository){
        super.repository = fuelRepository;
    }

    public void createAll(List<Fuel> fuelList) {
        super.repository.saveAll(fuelList);
    }
    @Override
    public List<Fuel> getAll(){
        return this.lowStock();
    }

    /**
     * Automatically decrease the quantity of fuel
     * @return List<Fuel> the list of fuel with a potentially decreased quantity
     */
     private List<Fuel> lowStock(){
        List<Fuel> fuelLIst = super.getAll();
        for(Fuel f : fuelLIst){
            Random r = new Random();
            int rdm = r.nextInt(100);
            if(rdm <30 && f.getQuantity()>rdm){
                f.setQuantity(f.getQuantity()-rdm);
            }
        }
        return super.repository.saveAll(fuelLIst);
    }
    public Fuel addQte(int quantity, int id){
        Fuel f = super.getEntityById(id);

        f.setQuantity(f.getQuantity()+quantity);
        if(f.getQuantity()>500){
            throw new DataIncorrect("The quantity of fuel can't be more than 500L");
        }
        return super.edit(f);
    }

}
