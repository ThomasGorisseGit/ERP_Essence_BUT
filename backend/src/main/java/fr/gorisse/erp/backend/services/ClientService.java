package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.Client;
import fr.gorisse.erp.backend.entity.Subscription;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import fr.gorisse.erp.backend.repository.ClientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends ServiceMethods<Client> {
    private SubscriptionService subscriptionService;

    @Autowired
    protected void setRepository(ClientRepository clientRepository, SubscriptionService subscriptionService) {
        super.repository = clientRepository;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public Client create(Client client) {
        defaultValues(client);
        return super.create(client);
    }

    private void setSubscription(@NotNull Client client) {
        Subscription sub;
        if (client.getSubscription() == null) {
            sub = subscriptionService.getFreePlan();
        } else {
            try {
                sub = subscriptionService.getEntityById(client.getSubscription().getId());
            } catch (DataNotFounded e) {
                sub = subscriptionService.getFreePlan();
            }

        }
        client.setSubscription(sub);
    }

    private void defaultValues(Client client) {
        setSubscription(client);
        if (client.getFirstName() == null) client.setFirstName("");
        if (client.getLastName() == null) client.setLastName("");
    }
}
