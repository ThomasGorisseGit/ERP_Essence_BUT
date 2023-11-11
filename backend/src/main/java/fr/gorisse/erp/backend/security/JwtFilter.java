package fr.gorisse.erp.backend.security;

import fr.gorisse.erp.backend.services.UserCheckingService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Service
public class JwtFilter extends OncePerRequestFilter {
    private UserCheckingService userCheckingService;
    private JwtService jwtService;
    public JwtFilter( UserCheckingService userCheckingService,JwtService jwtService){
        this.userCheckingService = userCheckingService;
        this.jwtService = jwtService;
    }
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token;
        String username = null;
        boolean isTokenExpired = true;


        final String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer ")){

            token = authorization.substring(7); // We remove the "Bearer " part
            isTokenExpired = jwtService.isTokenExpired(token);
            username = jwtService.getUsername(token);

        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null && !isTokenExpired){
            UserDetails user = this.userCheckingService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
