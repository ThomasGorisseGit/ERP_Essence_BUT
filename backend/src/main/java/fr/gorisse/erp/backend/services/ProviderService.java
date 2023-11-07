package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Provider;
import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.entity.valueObject.Siret;
import fr.gorisse.erp.backend.repository.ProductRepository;
import fr.gorisse.erp.backend.repository.ProviderRepository;
import fr.gorisse.erp.backend.services.interfaces.ServiceMethodsInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService extends ServiceMethods<Provider>{
    private ProviderRepository providerRepository;
    private ProductRepository productRepository;
    @Autowired
    protected void setRepository(ProviderRepository providerRepository, ProductRepository productRepository) {
        this.repository = providerRepository;
        this.providerRepository = providerRepository;
        this.productRepository = productRepository;
    }
    @Override
    public Provider create(Provider provider){
        provider = super.create(provider);

        if(provider.getProductList() != null && !provider.getProductList().isEmpty()){
            for(Product p : provider.getProductList()){
                p.setProvider(provider);
            }
            this.productRepository.saveAll(provider.getProductList());
        }
        return provider;
    }

    public List<Product> getProductList(Provider provider){
        return this.productRepository.findByProviderId(provider.getId());
    }
    public List<Product> getProductListByID(int id){
        return this.productRepository.findByProviderId(id);
    }
}
