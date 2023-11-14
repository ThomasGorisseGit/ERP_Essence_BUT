package fr.gorisse.erp.backend.controller;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @PostMapping("/connect")
    @CrossOrigin(origins = "http://localhost:4200")
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
