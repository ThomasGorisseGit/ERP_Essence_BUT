package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Fuel;
import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Provider;
import fr.gorisse.erp.backend.repository.ProductRepository;
import fr.gorisse.erp.backend.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService extends ServiceMethods<Provider>{
    private ProductRepository productRepository;
    private ProviderRepository providerRepository;
    private FuelService fuelService;
    @Autowired
    protected void setRepository(ProviderRepository providerRepository, ProductRepository productRepository,FuelService fuelService){
        this.repository = providerRepository;
        this.providerRepository = providerRepository;
        this.productRepository = productRepository;
        this.fuelService = fuelService;
    }

    @Override
    public Provider create(Provider provider){
        provider = super.create(provider);
        for (Product p: provider.getProductList()) {
            p= this.productRepository.getReferenceById(p.getId());
            p.setProvider(provider);
            this.productRepository.save(p);
        }
        return provider;
    }

    public List<Provider> getProviderWithProductList(){
        return this.providerRepository.findByProductListIsNotNullAndProductListIsNotEmpty();
    }
    public List<Provider> getProviderWithFuelList(){
        return this.providerRepository.findByFuelListIsNotNullAndFuelListIsNotEmpty();
    }
    public List<Product> getProductList(Provider provider){
        return this.productRepository.findByProviderId(provider.getId());
    }
    public List<Product> getProductListByID(int id){
        return this.productRepository.findByProviderId(id);
    }
    public List<Provider> getProviderFullInformations(){
        return this.providerRepository.findByProductListIsNotNullAndProductListNotEmptyAndProductList_StockIsNotNullAndProductList_Stock_DeliveryListIsNotNullAndProductList_Stock_DeliveryListIsNotEmpty();
    }
    public int getNumberOfFuelProvider(){
        return this.providerRepository.countByFuelListIsNotNullAndFuelListIsNotEmpty();
    }
    public void createAll(List<Provider> providerList){
        this.providerRepository.saveAll(providerList);

        for (Provider p : providerList) {
            if(!p.getFuelList().isEmpty()){
                for (Fuel f: p.getFuelList()) {
                    f.setProvider(p);
                }
                this.fuelService.createAll(p.getFuelList());
            }
        }
    }


}
