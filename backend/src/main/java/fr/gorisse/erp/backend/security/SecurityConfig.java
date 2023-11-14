package fr.gorisse.erp.backend.security;

import fr.gorisse.erp.backend.services.UserCheckingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {
   private final UserCheckingService userService;
   private final JwtFilter jwtFilter;
   public SecurityConfig(UserCheckingService userService,JwtFilter jwtFilter) {
       this.userService = userService;
       this.jwtFilter = jwtFilter;

   }
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(
                        this.jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(POST, "/auth/connect").permitAll() // We will authenticate the user
                        .requestMatchers(POST,"http://localhost:4200/user/auth").permitAll()
                        .requestMatchers(POST,"http://localhost:8080/user/auth").permitAll()
                        .requestMatchers(POST,"/user/auth").permitAll()
                        .requestMatchers(GET, "/user").permitAll()
                        .requestMatchers(POST,"/user/create").permitAll()

                        .anyRequest().authenticated()
                        
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )


                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.userService);
        daoAuthenticationProvider.setPasswordEncoder(this.bCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

}
