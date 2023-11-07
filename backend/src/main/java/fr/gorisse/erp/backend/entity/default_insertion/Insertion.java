package fr.gorisse.erp.backend.entity.default_insertion;

import fr.gorisse.erp.backend.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Insertion implements ApplicationRunner {
    private final SubscriptionService subscriptionService;
    @Autowired
    public Insertion(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(this.subscriptionService.getNumberOfEntity()!=5){
            Plans p = new Plans();
            this.subscriptionService.createAll(p);
        }
    }
}
