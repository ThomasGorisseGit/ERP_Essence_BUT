package fr.gorisse.erp.backend.entity.default_insertion;

import fr.gorisse.erp.backend.services.ProductService;
import fr.gorisse.erp.backend.services.StockService;
import fr.gorisse.erp.backend.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Insertion implements ApplicationRunner {
    private final SubscriptionService subscriptionService;
    private final ProductService productService;

    private final StockService stockService;
    @Autowired
    public Insertion(SubscriptionService subscriptionService, ProductService productService, StockService stockService) {
        this.subscriptionService = subscriptionService;
        this.productService = productService;
        this.stockService = stockService;
    }
    @Override
    public void run(ApplicationArguments args) {

        if(this.subscriptionService.getNumberOfEntity()!=5){
            Plans p = new Plans();
            this.subscriptionService.createAll(p);
        }
        long nbProduct= this.productService.getNumberOfProductOpt();
        if(nbProduct<50){
           this.productService.createAll(new ProductList());
        }
        this.stockService.updateStocks();
    }


}
