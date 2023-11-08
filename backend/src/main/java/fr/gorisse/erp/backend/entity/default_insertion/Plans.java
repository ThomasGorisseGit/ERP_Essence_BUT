package fr.gorisse.erp.backend.entity.default_insertion;

import fr.gorisse.erp.backend.entity.Subscription;
import fr.gorisse.erp.backend.entity.valueObject.Discount;
import fr.gorisse.erp.backend.entity.valueObject.SubscriptionPrice;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Plans extends ArrayList<Subscription> {
    public Plans() {
        this.add(new Subscription("Free trial", SubscriptionPrice.create(0.00), Discount.create(0)));
        this.add(new Subscription("Beginner plan", SubscriptionPrice.create(29.99), Discount.create(10)));
        this.add(new Subscription("Medium plan", SubscriptionPrice.create(39.99), Discount.create(20)));
        this.add(new Subscription("Professional plan", SubscriptionPrice.create(49.99), Discount.create(30)));

        this.add(new Subscription("Business plan", SubscriptionPrice.create(54.99), Discount.create(50)));
    }
}
