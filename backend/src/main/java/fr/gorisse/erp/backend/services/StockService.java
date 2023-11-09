package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Stock;
import fr.gorisse.erp.backend.repository.ProductRepository;
import fr.gorisse.erp.backend.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService extends ServiceMethods<Stock>{
    private ProductRepository productRepository;

    @Autowired
    protected void setRepository(StockRepository stockRepository, ProductRepository productRepository) {
        super.repository = stockRepository;
        this.productRepository = productRepository;
    }

    public Stock addStock(int quantity, Product product){
        Stock s = product.getStock();
        if(s==null){
            s = new Stock();
            s.setProduct(product);
            s.setQuantity(0);
        }
        s.setQuantity(s.getQuantity()+quantity);
        product.setStock(s);
        productRepository.save(product);
        return this.edit(s);
    }

}
