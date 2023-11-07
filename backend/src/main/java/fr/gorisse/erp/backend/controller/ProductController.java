package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Stock;
import fr.gorisse.erp.backend.services.ProductService;
import fr.gorisse.erp.backend.services.StockService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    @Transactional
    public Product addProduct(@RequestBody Product product){
        return this.productService.create(product);
    }

    @PostMapping("/edit")
    @Transactional
    public Product editProduct(@RequestBody Product product) {
        return this.productService.edit(product);
    }
    @DeleteMapping("/delete")
    @Transactional
    public Product deleteProduct(@RequestBody Product product){
        this.productService.delete(product);
        return product;
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public Product deleteProduct(@PathVariable("id") int product_id){
        return this.productService.deleteById(product_id);

    }

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return this.productService.getAll();
    }
    @GetMapping("/getProductById")
    public Product getProduct(@RequestBody int product_id){
        return this.productService.getEntityById(product_id);
    }
    @GetMapping("/getNumberOfProducts")
    public int getNumberOfProducts(){
        return this.productService.getNumberOfEntity();
    }

}
