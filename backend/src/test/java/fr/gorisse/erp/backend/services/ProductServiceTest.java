package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Provider;
import fr.gorisse.erp.backend.entity.Stock;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private StockService stockService;

    private Product product;
    private int number;

    @BeforeEach
    public void init(){
        this.number = this.productService.getNumberOfEntity();
        this.product = this.productService.create(new Product(12.00,"Description","Name"));
    }
    @Test
    public void testInsertion(){
        assertEquals(this.number+1,this.productService.getNumberOfEntity());
        Product check = this.productService.getEntityById(this.product.getId());
        assertEquals(
                this.product.getId(),
                check.getId()
        );
    }
    @Test
    public void fetch(){
        assertNotEquals(this.number,this.productService.getNumberOfEntity());
        assertDoesNotThrow(()->this.productService.getEntityById(this.product.getId()));
        List<Product> list = productService.getAll();
        assertTrue(
                list.stream().anyMatch(product -> product.getId() == this.product.getId() )
        );
        assertEquals(
                this.productService.getEntityById(this.product.getId()).getId(),
                this.product.getId()
        );

        this.destroy(); // destroy the value
        assertEquals(this.number,this.productService.getNumberOfEntity());
        assertFalse(this.productService.getAll().contains(this.product));
        assertThrows(DataNotFounded.class,()->this.productService.getEntityById(this.product.getId()));
    }
    @Test
    public void testValueObject(){
        assertEquals(this.number+1,this.productService.getNumberOfEntity());
        Provider p = new Provider("11111111111111","aaaa","aaaa");
         p = this.providerService.create(p);
        Stock s = this.stockService.create(new Stock());


        this.product.setStock(s);
        this.product.setProvider(p);

        assertNull(this.productService.getEntityById(this.product.getId()).getProvider());
        assertNull(this.productService.getEntityById(this.product.getId()).getStock());

        assertNotNull(this.product.getProvider());
        assertNotNull(this.product.getStock());

        this.product = this.productService.edit(this.product);
        assertNotNull(this.productService.getEntityById(this.product.getId()).getProvider());
        assertNotNull(this.productService.getEntityById(this.product.getId()).getStock());

        assertEquals(
                this.product.getProvider().getId(),
                this.productService.getEntityById(this.product.getId()).getProvider().getId()
        );

        assertEquals(
                this.product.getStock().getId(),
                this.productService.getEntityById(this.product.getId()).getStock().getId()
        );

        assertNotEquals(0,this.product.getStock().getId());
        assertNotEquals(0,this.product.getProvider().getId());


    }


    @AfterEach
    public void destroy(){
        this.productService.delete(this.product);
    }
}