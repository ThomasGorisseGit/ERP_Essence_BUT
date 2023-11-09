package fr.gorisse.erp.backend.repository;


import fr.gorisse.erp.backend.entity.Subscription;

public interface SubscriptionRepository extends DefaultRepository<Subscription> {
    Subscription findByName(String name);
}
