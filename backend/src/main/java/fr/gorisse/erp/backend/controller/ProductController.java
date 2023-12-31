package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Provider;
import fr.gorisse.erp.backend.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:4200", "http://209.38.204.153:8080","https://209.38.204.153:8080", "http://209.38.204.153","https://209.38.204.153:80","http://thomasgorisse.com","https://thomasgorisse.com"})

public class ProductController implements DefaultController<Product>{

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    @Transactional
    public Product create(@RequestBody Product product){
        return this.productService.create(product);
    }

    @PostMapping("/edit")
    @Transactional
    public Product editProduct(@RequestBody Product product) {
        return this.productService.edit(product);
    }
    @DeleteMapping("/delete")
    @Transactional
    public void delete(@RequestBody Product product){
        this.productService.delete(product);
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteById(@PathVariable("id") int product_id){
        this.productService.deleteById(product_id);
    }
    @GetMapping("/getProducts")
    public List<Product> getAll(){
        return this.productService.getAll();
    }
    @Override
    @GetMapping("/getProductById/{id}")
    public Product getById(@PathVariable("id") int product_id){
        return this.productService.getEntityById(product_id);
    }
    @GetMapping("/getNumberOfProducts")
    public int getNumberOfProducts(){
        return this.productService.getNumberOfEntity();
    }

    @GetMapping("/findByProviderIsNull")
    public List<Product> findByProviderIsNull(){
        return this.productService.findByProviderIsNull();
    }

    @GetMapping("/getProvider/{id}")
    public Provider findProviderByProductId(@PathVariable("id")int providerId){
        return this.productService.findProviderByProductId(providerId);
    }
    @GetMapping("/ProductContainsAll")
    public List<Product> getProductWhereDeliveryListIsNotNull(){
        return this.productService.getProductWhereDeliveryListIsNotNull();
    }
    @GetMapping("/ProductWithStocks")
    public List<Product> productWithStocks(){
        return this.productService.getProductsWithStocks();
    }
}
