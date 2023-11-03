package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService extends ServiceMethods<Product> {
    @Autowired
    private ProductRepository productRepository;
    @Override
    protected void setRepository() {
        super.repository = this.productRepository;
    }
}
