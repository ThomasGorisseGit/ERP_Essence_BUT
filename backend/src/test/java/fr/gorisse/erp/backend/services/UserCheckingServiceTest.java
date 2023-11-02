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

        this.numberOfUsers = this.userService.getNumberOfUsers();
    }
    @Test
    void create() {
        Assertions.assertEquals(userService.getNumberOfUsers(),this.numberOfUsers);
        userService.create(user);
        Assertions.assertEquals(userService.getNumberOfUsers(),this.numberOfUsers+1);
        this.userService.deleteUser(user);
        Assertions.assertEquals(userService.getNumberOfUsers(),this.numberOfUsers);

    }
    @Test
    void getUsers(){
        Assertions.assertEquals(userService.getNumberOfUsers(),this.numberOfUsers);

        this.userService.create(user);
        Assertions.assertEquals(userService.getNumberOfUsers(),this.numberOfUsers+1);

        ArrayList<User> list = this.userService.getUsers();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(this.user.getId(),list.get(list.size()-1).getId());

    }
    @Test
    void getUserById(){
        Assertions.assertEquals(userService.getNumberOfUsers(), this.numberOfUsers);
        assertThrows(DataNotFounded.class,()->userService.getUserById(-1));
        this.userService.create(user);
        Assertions.assertEquals(userService.getNumberOfUsers(), this.numberOfUsers+1);
        User fetchedUser = this.userService.getUserById(this.user.getId());
        assertNotNull(fetchedUser);
        assertEquals(user.getId(),fetchedUser.getId());
    }
    @Test
    void getUserByLogin(){
        Assertions.assertEquals(userService.getNumberOfUsers(), this.numberOfUsers);
        assertThrows(InvalidInput.class,()->userService.getUserByLogin("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertThrows(InvalidInput.class,()->userService.getUserByLogin("Thomas}"));
        assertThrows(DataNotFounded.class,()->userService.getUserByLogin("Thomas@Gorisse"));

        this.userService.create(user);
        Assertions.assertEquals(userService.getNumberOfUsers(), this.numberOfUsers+1);
        User fetchedUser = this.userService.getUserByLogin(this.user.getLogin().toString());
        assertNotNull(fetchedUser);
        assertEquals(user.getId(),fetchedUser.getId());
    }
    @AfterEach
    void destroy(){
        this.userService.deleteUser(this.user);
    }
}