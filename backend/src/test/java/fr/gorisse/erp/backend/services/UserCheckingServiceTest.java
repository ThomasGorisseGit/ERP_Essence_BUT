package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.exceptions.DataNotFounded;
import fr.gorisse.erp.backend.exceptions.InvalidInput;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserCheckingServiceTest {
    @Autowired
    private UserCheckingService userService;
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
    @AfterEach
    void destroy(){
        this.userService.delete(this.user);
    }
}