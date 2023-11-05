package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Stock;
import fr.gorisse.erp.backend.services.ProductService;
import fr.gorisse.erp.backend.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private ProductService productService;

    @PostMapping("/edit")
    private Stock edit(@RequestBody Stock stock){
        stock = this.stockService.getEntityById(stock.getId());
        return this.stockService.edit(stock);
    }

    @GetMapping("/edit/{product_id}/{qte}")
    private Stock editByProductId(@PathVariable("product_id") int product_id,@PathVariable("qte") int qte) {
        Product product = this.productService.getEntityById(product_id);
        Stock s = product.getStock();
        s.setQuantity(qte);
        return this.stockService.edit(s);
    }

    @DeleteMapping("/clear")
    private int clear(){
        int numberOfElementsDeleted = 0;
        List<Stock> sList = this.stockService.getAll();
        for(Stock s : sList){
            if(s.getProduct() == null){
                numberOfElementsDeleted ++;
                this.stockService.delete(s);
            }
        }
        return numberOfElementsDeleted;
    }
    @GetMapping("/add/{product_id}/{qte}")
    private Stock addByProductId(@PathVariable("product_id") int product_id,@PathVariable("qte") int qte) {
        return this.stockService.addStock(qte, this.productService.getEntityById(product_id));
    }

}
