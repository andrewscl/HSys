package com.hsys.dv1.Services;

import org.springframework.stereotype.Service;

import com.hsys.dv1.Entities.Role;
import com.hsys.dv1.Repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public void createRol (Role role){
/*         //Validar que no exista el rol
        if(roleRepository.findByRole(role.getRole()) != null){
            throw new RuntimeException("Role already exists");
        }*/

        roleRepository.save(role);
    }
    
}
