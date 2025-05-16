package com.hsys.dv1.Services;


//import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hsys.dv1.Entities.User;
import com.hsys.dv1.Repositories.UserRepository;
import com.hsys.dv1.Security.SecurityUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username)
                        .orElseThrow(() -> {
                            return new UsernameNotFoundException("User not found with username " + username);
                        });

        return new SecurityUser(user);
    }
}