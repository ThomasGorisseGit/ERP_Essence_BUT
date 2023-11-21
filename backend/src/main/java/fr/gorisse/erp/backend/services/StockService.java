package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Delivery;
import fr.gorisse.erp.backend.entity.Product;
import fr.gorisse.erp.backend.entity.Stock;
import fr.gorisse.erp.backend.repository.DeliveryRepository;
import fr.gorisse.erp.backend.repository.ProductRepository;
import fr.gorisse.erp.backend.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class StockService extends ServiceMethods<Stock>{
    private ProductRepository productRepository;
    private DeliveryRepository deliveryRepository;
    private StockRepository stockRepository;
    @Autowired
    protected void setRepository(StockRepository stockRepository, ProductRepository productRepository,DeliveryRepository deliveryRepository) {
        super.repository = stockRepository;
        this.stockRepository = stockRepository;
        this.productRepository = productRepository;
        this.deliveryRepository = deliveryRepository;
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
    private Date randomDate() {
        // Obtenez la date actuelle
        Date dateActuelle = new Date();

        // Créez un objet Calendar et initialisez-le avec la date actuelle
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateActuelle);

        // Ajoutez un jour à la date actuelle pour obtenir demain
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date demain = calendar.getTime();

        // Générez un nombre aléatoire de jours entre 1 et 30 (pour un mois)
        Random random = new Random();
        int joursAleatoires = random.nextInt(30) + 1;

        // Ajoutez le nombre aléatoire de jours à la date de demain
        calendar.setTime(demain);
        calendar.add(Calendar.DAY_OF_MONTH, joursAleatoires);
        Date dateAleatoire = calendar.getTime();

        // Affichez la date générée aléatoirement
        return dateAleatoire;
    }
    public Delivery addDelivery(int product_id, int quantity){

        Product product = this.productRepository.findById(product_id).get();

        Stock s = product.getStock();

        Delivery delivery = new Delivery();

        delivery.setQuantity(quantity);
        delivery.setDate(randomDate());
        delivery.setState("En cours");
        delivery.setStock(s);

        this.updateStocks();

        return this.deliveryRepository.save(delivery);

    }

    public List<Delivery> getAllDeliveries(){
        this.updateStocks();
        return this.deliveryRepository.findAllByOrderByDateDesc();
    }
    public void updateStocks(){

        List< Delivery > deliveryList = this.deliveryRepository.findDeliveriesByDateAndState(new Date(),"En cours");
        for (Delivery d : deliveryList){
            Stock s = d.getStock();
            s.setQuantity(d.getQuantity() + s.getQuantity());
            this.repository.save(s);
            d.setState("Livré");
            this.deliveryRepository.save(d);
        }
        this.deleteDelivery();
    }
    //delete delivery when date is passed for one month
    public void deleteDelivery(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date oneMonthAgo = calendar.getTime();
        List<Delivery> oldDeliveries = deliveryRepository.findDeliveriesByDateBefore(oneMonthAgo);
        this.deliveryRepository.deleteAll(oldDeliveries);

    }

    @Override
    public List<Stock> getAll() {
        this.updateStocks();
        return this.stockRepository.findStockByQuantityGreaterThan(0);
    }
}
