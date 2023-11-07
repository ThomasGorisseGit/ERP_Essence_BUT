package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Subscription;
import fr.gorisse.erp.backend.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService extends ServiceMethods<Subscription> {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Override
    @Autowired
    protected void setRepository() {
        super.repository = subscriptionRepository;
    }

    public List<Subscription> createAll(List<Subscription> list){
        return this.subscriptionRepository.saveAll(list);
    }
}
