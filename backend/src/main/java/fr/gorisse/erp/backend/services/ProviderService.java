package fr.gorisse.erp.backend.services;

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
    @Autowired
    protected void setRepository(ProviderRepository providerRepository, ProductRepository productRepository) {
        this.repository = providerRepository;
        this.providerRepository = providerRepository;
        this.productRepository = productRepository;
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

    public List<Product> getProductList(Provider provider){
        return this.productRepository.findByProviderId(provider.getId());
    }
    public List<Product> getProductListByID(int id){
        return this.productRepository.findByProviderId(id);
    }
    public List<Provider> getProviderFullInformations(){
        return this.providerRepository.findByProductListIsNotNullAndProductListNotEmptyAndProductList_StockIsNotNullAndProductList_Stock_DeliveryListIsNotNull();
    }
}
