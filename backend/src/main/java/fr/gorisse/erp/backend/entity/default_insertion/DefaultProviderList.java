package fr.gorisse.erp.backend.entity.default_insertion;

import fr.gorisse.erp.backend.entity.Fuel;
import fr.gorisse.erp.backend.entity.Provider;

import java.util.ArrayList;

public class DefaultProviderList extends ArrayList<Provider> {
    public DefaultProviderList(){
        this.add(new Provider("12345678912345","Michel","Jasper",new ArrayList<>(){{
            add(new Fuel(0,"Essence SP95",0,1.90,null,null));
            add(new Fuel(0,"Essence SP98",0,1.99,null,null));
        }}));
        this.add(new Provider("12678913434525","John","Bram",new ArrayList<>(){{
            add(new Fuel(0,"Diesel",0,1.78,null,null));
        }}));
        this.add(new Provider("12145827359634","Miguel","Lincoln",new ArrayList<>(){{
            add(new Fuel(0,"Bio Ethanol",0,1.66,null,null));
        }}));
    }
}
