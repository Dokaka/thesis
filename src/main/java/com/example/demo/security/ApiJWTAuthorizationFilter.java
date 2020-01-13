package com.example.demo.security;

import com.example.demo.util.JwtUltis;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import io.jsonwebtoken.Claims;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ApiJWTAuthorizationFilter extends BasicAuthenticationFilter {

    ApiJWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Check token
        Claims claims = JwtUltis.verifyToken(request);
        if (claims == null) {
            chain.doFilter(request, response);
            return;
        }

        // Add object Authentication to SecurityContext
        // Controller can get user info, who is logging in, to use
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(claims);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(Claims claims) {
        // Get phone of user
        String phone = claims.getSubject();
        // Get role info
        ArrayList<String> roles = (ArrayList<String>) claims.get("roles");
        // Convert role array to GrantedAuthority array and check authentication manager
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null) {
            for (String role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role);
                authorities.add(authority);
            }
        }
        if (phone != null) {
            // Return Authentication object which has username, login info ,roles
            return new UsernamePasswordAuthenticationToken(phone, null, authorities);
        }
        return null;
    }
}
