package com.hsys.dv1.Api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsys.dv1.Auth.AuthRequest;
import com.hsys.dv1.Auth.AuthResponse;
import com.hsys.dv1.Security.JwtService;
import com.hsys.dv1.Services.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/api/auth")
@RequiredArgsConstructor
public class AuthJwtController {
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping ("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword())
            );
            System.out.println("Intentando autenticación para: "
                + request.getUsername());

        } catch (Exception e) {
            //Logging en consola, sin enviar al cliente.
            System.err.println("Error de autenticación: " +
                    e.getClass().getSimpleName() + " - " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Causa interna: " + e.getCause().getClass().getSimpleName() + " - " + e.getCause().getMessage());
                e.getCause().printStackTrace();
            } else {
                e.printStackTrace();
            }

            return ResponseEntity
                .status(401)
                .body("Error interno: " + e.getClass().getSimpleName() + " - " + e.getMessage()); 
        }

        var userDetails = userDetailsServiceImpl
            .loadUserByUsername(request.getUsername());
        var jwtToken = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwtToken));
    }

}
