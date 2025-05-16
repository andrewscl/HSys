package com.hsys.dv1.Auth;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hsys.dv1.Security.JwtService;
import com.hsys.dv1.Services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal (@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
                                    throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        System.out.println("Solicitud entrante: " + request.getRequestURI());
        System.out.println("Authorization Header: " + authHeader);

        if ( authHeader == null || !authHeader.startsWith("Bearer ")){
            System.out.println("No se encontro un token valido en el header.");
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        System.out.println("Usuario extraido del JWT: " + username);

        if(username != null && 
                SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = 
                this.userDetailsServiceImpl.loadUserByUsername(username);
            
            if(jwtService.isTokenValid(jwt, userDetails)) {
                System.out.println("JWT valido. Autenticando usuario...");
                UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                System.out.println("El token no es valido para el usuario.");
            }
        }

        filterChain.doFilter(request, response);

    }
}
