package com.hsys.dv1.Services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hsys.dv1.Entities.User;
import com.hsys.dv1.Repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register (User user) {
        //Validar que no exista el user name
        if(userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        //codificar contraseña
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //Guardar el usuario
        userRepository.save(user);

    }
}
