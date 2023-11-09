package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import fr.gorisse.erp.backend.exceptions.InvalidInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCheckingServiceTest {
    @Autowired
    private UserCheckingService userService;
    @Autowired
    private SubscriptionService subscriptionService;
    private User user;
    private int numberOfUsers;
    @BeforeEach
    void init(){
        this.user = new User("Thomas","Gorisse","Pwd");
        this.numberOfUsers = this.userService.getNumberOfEntity();
    }
    @Test
    void create() {
        Assertions.assertEquals(userService.getNumberOfEntity(),this.numberOfUsers);
        userService.create(user);
        Assertions.assertEquals(userService.getNumberOfEntity(),this.numberOfUsers+1);
        this.userService.delete(user);
        Assertions.assertEquals(userService.getNumberOfEntity(),this.numberOfUsers);

    }
    @Test
    void getUsers(){
        Assertions.assertEquals(userService.getNumberOfEntity(),this.numberOfUsers);
        this.userService.create(user);
        Assertions.assertEquals(userService.getNumberOfEntity(),this.numberOfUsers+1);
        ArrayList<User> list = (ArrayList<User>) this.userService.getAll();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(this.user.getId(),list.get(list.size()-1).getId());
    }
    @Test
    void getUserById(){
        Assertions.assertEquals(userService.getNumberOfEntity(), this.numberOfUsers);
        assertThrows(DataNotFounded.class,()->userService.getEntityById(-1));
        this.userService.create(user);
        Assertions.assertEquals(userService.getNumberOfEntity(), this.numberOfUsers+1);
        User fetchedUser = this.userService.getEntityById(this.user.getId());

        assertNotNull(fetchedUser);
        assertEquals(user.getId(),fetchedUser.getId());
    }
    @Test
    void getUserByLogin(){
        Assertions.assertEquals(userService.getNumberOfEntity(), this.numberOfUsers);
        assertThrows(InvalidInput.class,()->userService.getUserByLogin("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertThrows(InvalidInput.class,()->userService.getUserByLogin("Thomas}"));
        assertThrows(DataNotFounded.class,()->userService.getUserByLogin("Thomas@Gorisse"));

        this.userService.create(user);
        Assertions.assertEquals(userService.getNumberOfEntity(), this.numberOfUsers+1);
        User fetchedUser = this.userService.getUserByLogin(this.user.getLogin().toString());
        assertNotNull(fetchedUser);
        assertEquals(user.getId(),fetchedUser.getId());
    }
    @Test
    void insertBasicUser(){
        User emptyUser = new User();
        assertNotNull(emptyUser);
        User emptyUserWithId = this.userService.create(emptyUser);

        assertEquals(emptyUserWithId.getLogin().toString(), emptyUser.getLogin().toString());
        assertEquals(emptyUserWithId.getId(),emptyUser.getId());
        assertEquals(emptyUserWithId.getLogin().toString(), "null@null");

        this.userService.delete(emptyUser);
        this.userService.delete(emptyUserWithId);

    }

    @Test
    void testDefaultValues(){
        User newUser = new User();
        assertNull(newUser.getStatus());
        assertNull(newUser.getLogin());

        newUser = this.userService.create(newUser);

        assertNotNull(newUser.getLogin());
        assertNotNull(newUser.getStatus());

        this.userService.delete(newUser);

    }
    @AfterEach
    void destroy(){
        this.userService.delete(this.user);
    }
}