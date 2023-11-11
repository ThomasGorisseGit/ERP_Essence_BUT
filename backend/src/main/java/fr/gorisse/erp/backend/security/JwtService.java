package fr.gorisse.erp.backend.security;

import fr.gorisse.erp.backend.entity.User;
import fr.gorisse.erp.backend.services.UserCheckingService;
import io.jsonwebtoken.Claims;
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
import java.util.function.Function;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class JwtService {
    @Autowired
    private UserCheckingService userService;
    @Value("${jwt.secret}")
    private String SECRET;
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
        final long EXPIRATION = 5 * 60 * 60 * 1000 + currentTime;
        final Map<String, Object> claims = Map.of(
                "firstName", user.getFirstName(),
                "lastName",user.getLastName(),
                "login",user.getLogin().getLogin(),
                "status",user.getStatus().getUserStatus(),
                "salary",user.getSalary().toString(),
                Claims.EXPIRATION, new Date(EXPIRATION),
                Claims.SUBJECT, user.getLogin().toString()
                );
        String bearer = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(EXPIRATION))
                .setSubject(user.getLogin().getLogin())
                .setClaims(claims)
                .signWith(getKey(),SignatureAlgorithm.HS256)
                .compact();
        return Map.of("bearer",bearer);
    }
    public String getUsername(String token){
        return this.getClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDate(token);

        return expirationDate.before(new Date());
    }

    private Date getExpirationDate(String token) {
        return this.getClaim(token, Claims::getExpiration);
    }
    private <T> T getClaim(String token, Function<Claims, T> function){
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return function.apply(claims);
    }
}
