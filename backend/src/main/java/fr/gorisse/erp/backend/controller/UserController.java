package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.services.UserCheckingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserCheckingService userService;

    @PostMapping("/create")
    @Transactional
    public User create(@RequestBody User user){
        return this.userService.create(user);
    }
    @PostMapping("/edit")
    @Transactional
    public User edit(@RequestBody User user){
        return this.userService.create(user);
    }
    @DeleteMapping("/deleteById")
    @Transactional
    public User deleteById(@RequestBody int user_id){
        return this.userService.deleteById(user_id);
    }
    @DeleteMapping("/delete")
    @Transactional
    public User delete(@RequestBody User user){
        this.userService.delete(user);
        return user;
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public User delete(@PathVariable("id") int user_id){
        return this.userService.deleteById(user_id);
    }
    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return this.userService.getAll();
    }
    @GetMapping("/getUserById")
    public User getUserById(@RequestBody int user_id){
        return this.userService.getEntityById(user_id);
    }
    @GetMapping("/getUserByLogin")
    public User getUserByLogin(@RequestBody String login){
        return this.userService.getUserByLogin(login);
    }
    @GetMapping("/getNumberOfUsers")
    public int getNumberOfUsers(){
        return this.userService.getNumberOfEntity();
    }

}
