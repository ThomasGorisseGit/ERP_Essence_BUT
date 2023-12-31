package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.security.JwtService;
import fr.gorisse.erp.backend.services.UserCheckingService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200", "http://209.38.204.153:8080","https://209.38.204.153:8080", "http://209.38.204.153","https://209.38.204.153:80","http://thomasgorisse.com","https://thomasgorisse.com"})

public class UserController implements DefaultController<User> {
    @Autowired
    private UserCheckingService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

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
    public void deleteById(@RequestBody int user_id){
        this.userService.deleteById(user_id);
    }
    @DeleteMapping("/delete")
    @Transactional
    public void delete(@RequestBody User user){
        this.userService.delete(user);
    }
    @DeleteMapping("/delete/{id}")
    @Transactional
    public void delete(@PathVariable("id") int user_id){
        this.userService.deleteById(user_id);
    }
    @GetMapping("/getUsers")
    public List<User> getAll(){
        return this.userService.getAll();
    }
    @Override
    @GetMapping("/getUserById")
    public User getById(@RequestBody int user_id){
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

    @PostMapping("/auth")
    public Map<String, String> connect(@RequestBody User user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getLogin().toString(), user.getPassword())
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getLogin().getLogin());
        }
        return null;
    }

}
