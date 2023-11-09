package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Subscription;
import fr.gorisse.erp.backend.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService extends ServiceMethods<Subscription> {
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    protected void setRepository(SubscriptionRepository subscriptionRepository) {
        super.repository = subscriptionRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription getFreePlan(){
        return this.subscriptionRepository.findByName("Free trial");
    }

    public List<Subscription> createAll(List<Subscription> list){
        return this.subscriptionRepository.saveAll(list);
    }
}
