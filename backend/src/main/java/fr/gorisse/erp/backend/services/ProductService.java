package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Provider;
import fr.gorisse.erp.backend.entity.Stock;
import fr.gorisse.erp.backend.repository.ProductRepository;
import fr.gorisse.erp.backend.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends ServiceMethods<Product> {
    private StockService stockService;
    private ProductRepository productRepository;
    private ProviderRepository providerRepository;
    @Autowired
    protected void setRepository(ProductRepository productRepository,StockService stockService,ProviderRepository providerRepository) {
        super.repository = productRepository;
        this.productRepository = productRepository;
        this.stockService = stockService;
        this.providerRepository = providerRepository;
    }
    @Override
    public Product create(Product entity){
        if(entity.getStock()==null){
            Stock defaultStock = new Stock(0);
            this.stockService.create(defaultStock);
            entity.setStock(defaultStock);
        }
        return super.create(entity);
    }
    public void createAll(List<Product> products){
        for (Product p: products ) {
            this.stockService.addStock(0,p);
        }
    }
    public long getNumberOfProductOpt() {
        return this.repository.count();
    }

    public List<Product>findByProviderIsNull(){
        return this.productRepository.findByProviderIsNull();
    }
    public Provider findProviderByProductId(int productId){
        return this.providerRepository.findByProductList_Id(productId);
    }

    @Override
    public List<Product> getAll(){
        return this.productRepository.findByProviderIsNotNull();
    }
    public List<Product> getProductWhereDeliveryListIsNotNull(){
        return this.productRepository.findByProviderIsNotNullAndStockIsNotNullAndStock_DeliveryListIsNotNull();
    }

    public List<Product> getProductsWithStocks() {
        return this.productRepository.findByStockIsNotNullAndStock_QuantityIsGreaterThan(0);
    }
}
