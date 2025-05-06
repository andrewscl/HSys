package com.hsys.dv1.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hsys.dv1.Entities.User;
import com.hsys.dv1.Entities.SecurityUser;
import com.hsys.dv1.Repositories.UserRepository;

import lombok.AllArgsConstructor;

/*
 * La clase UserDetailsService carga un usuario desde la base de datos y
 * lo adapta a un objeto UserDetails mediante la clase SecurityUser,
 * que es una envoltura (wrapper) sibre tu entidad User.
 */

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
    
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
            if(user == null) {
                throw new UsernameNotFoundException("User not found");
            }
        return new SecurityUser (user);
    }

}
