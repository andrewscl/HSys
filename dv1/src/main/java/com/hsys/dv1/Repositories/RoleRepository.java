package com.hsys.dv1.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hsys.dv1.Entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByRole(String role);
}
