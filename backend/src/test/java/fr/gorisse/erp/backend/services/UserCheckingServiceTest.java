package fr.gorisse.erp.backend.services;

import fr.gorisse.erp.backend.entity.User;
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
        this.numberOfUsers = this.userService.getNumberOfUsers();
    }
    @Test
    void create() {
        this.user = new User("Thomas","Gorisse","Pwd");


        Assertions.assertEquals(userService.getNumberOfUsers(),this.numberOfUsers);
        userService.create(user);
        Assertions.assertEquals(userService.getNumberOfUsers(),this.numberOfUsers+1);
        this.userService.deleteUser(user);
        Assertions.assertEquals(userService.getNumberOfUsers(),this.numberOfUsers);

    }
    @Test
    void fetch(){
         this.user = new User("Thomas","Gorisse","Pwd");
        Assertions.assertEquals(userService.getNumberOfUsers(),this.numberOfUsers);
        this.userService.create(user);
        Assertions.assertEquals(userService.getNumberOfUsers(),this.numberOfUsers+1);

        ArrayList<User> list = this.userService.getUsers();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(this.user.getUser_id(),list.get(list.size()-1).getUser_id());

    }
    @AfterEach
    void destroy(){
        this.userService.deleteUser(this.user);
    }
}