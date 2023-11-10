package fr.gorisse.erp.backend.security;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.services.UserCheckingService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class JwtService {
    @Autowired
    private UserCheckingService userService;



    @Value("${jwt.secret}")
    private String SECRET;
    private final long EXPIRATION = 5 * 60 * 60 * 1000;
    public Map<String,String> generateToken(String login){
        User user = this.userService.getUserByLogin(login);


        return this.generateJwt(user);
    }

    private Key getKey(){
        final byte[] decoder = Decoders.BASE64.decode(this.SECRET);
        return Keys.hmacShaKeyFor(decoder);
    }
    private Map<String, String> generateJwt(User user) {

        final long currentTime = System.currentTimeMillis();
        final Map<String, Object> claims = Map.of(
                "firstName", user.getFirstName(),
                "lastName",user.getLastName(),
                 "login",user.getLogin().getLogin(),
                 "status",user.getStatus().getUserStatus(),
                "salary",user.getSalary().toString()
                );

        String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(EXPIRATION))
                .setSubject(user.getLogin().getLogin())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, getKey())
                .compact();
        return Map.of("bearer",bearer);
        //https://www.youtube.com/watch?v=-k1x1EYqlRI&ab_channel=chillotech 42:23
    }

}
