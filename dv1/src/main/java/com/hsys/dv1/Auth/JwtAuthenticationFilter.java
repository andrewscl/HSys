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

    /*
     * Este metodo es llamado automaicamente en cada solicitud
     * que a la aplicación
     */
    @Override
    protected void doFilterInternal (@NonNull HttpServletRequest request,    
                                     @NonNull HttpServletResponse response,
                                     @NonNull FilterChain filterChain)
                                    throws ServletException, IOException{

    /*
     * Leer el encabezado Authorization
     */
        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        /*
         * extrae el token JWT real quitando la palabra "Bearer " 
         */
        String token = authHeader.substring(7);
        /*
         *usamos el jwtService para obtener el username (subject del token) 
         */
        String username = jwtService.extractUsername(token);
        /*
         * Verificar si ya hay autorización 
         */
        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            /*
             * Carga el usuario desde la base de datos 
             */
            UserDetails userdetails = userDetailsServiceImpl.loadUserByUsername(username);
            /*
             * Validar el token
             */
            if(jwtService.isTokenValid(token, userdetails)){

            /*
            * Autenticar al usuario
            * crea un authentication con el usuraio y sus roles.
            * y lo coloca en el SecurityContext, lo que indica que el usuario
            * esta autenticado.
            */
                UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken
                (userdetails, null,userdetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        /*
         * Siempre se llama para continuar la ejecución de la solicitud, 
         * ya sea que se haya autenticado o no.
         */
        filterChain.doFilter(request, response);
    }
}


