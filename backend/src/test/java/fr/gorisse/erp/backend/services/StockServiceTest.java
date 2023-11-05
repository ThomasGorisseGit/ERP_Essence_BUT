package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Stock;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Autowired
    private ProductService productService;

    private Stock stock;
    private Product product;
    private int number;

    @BeforeEach
    public void init(){
        this.stock = new Stock();

        this.number= this.stockService.getNumberOfEntity();
        this.stock.setProduct(null);
        this.stockService.create(stock);
    }
    @Test
    public void testInsertion(){
        assertNotEquals(this.number,this.stockService.getNumberOfEntity());
    }

    @Test
    public void addQuantity(){
        this.product = this.productService.create(new Product(1.00,"Desc","item"));

        this.stock.setQuantity(100);
        this.product.setStock(this.stock);
        this.productService.edit(this.product);
        assertEquals(
                this.product.getStock().getId(),
                this.stockService.getEntityById(this.product.getStock().getId()).getId()
        );
        assertEquals(this.product.getStock().getQuantity(),100);
        this.stockService.addStock(3,this.product);
        assertEquals(this.product.getStock().getQuantity(),103);

        this.productService.delete(product);
    }
    @AfterEach
    public void destroy(){
        this.stockService.delete(this.stock);

    }
}