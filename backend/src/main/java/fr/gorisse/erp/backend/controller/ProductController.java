package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.services.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
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

}
