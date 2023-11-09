package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.*;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceTest {

    @Autowired
    private ClientService clientService;
    @Autowired
    private SubscriptionService subscriptionService;

    private int numberOfClients;
    private Client client;

    @BeforeEach
    public void init(){
        this.client = new Client();
        this.numberOfClients = this.clientService.getNumberOfEntity();
    }
    @Test
    public void testBasicClientInsertion(){
        int tempNumberOfUsers = this.clientService.getNumberOfEntity();

        assertEquals(tempNumberOfUsers,this.numberOfClients);
        assertThrows(DataNotFounded.class,()->this.clientService.getEntityById(this.client.getId()));
        Client insertedClient = this.clientService.create(this.client);
        tempNumberOfUsers = this.clientService.getNumberOfEntity();

        assertNotEquals(tempNumberOfUsers,this.numberOfClients);
        assertDoesNotThrow(()->this.clientService.getEntityById(this.client.getId()));
        assertEquals(insertedClient.getId(),this.client.getId());
    }
    @Test
    public void testSubscription(){
        this.client = this.clientService.create(this.client);
        Subscription subscription = this.subscriptionService.getFreePlan();
        this.client.setSubscription(subscription);
        assertDoesNotThrow(()->this.clientService.edit(this.client));
        assertEquals(this.client.getSubscription().getId(),subscription.getId());
    }

    @Test
    public void testDefaultValues(){
        assertNull(this.client.getSubscription());
        assertNull(this.client.getFirstName());
        assertNull(this.client.getLastName());
        this.client = this.clientService.create(this.client);
        assertNotNull(this.client.getSubscription());
        assertNotNull(this.client.getFirstName());
        assertNotNull(this.client.getLastName());

    }

    @AfterEach
    public void destroy(){
        this.clientService.delete(client);
    }
}