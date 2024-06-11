package com.teste.monteste.dao;

import com.teste.monteste.bo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Trouver un rôle par son nom
    Role findByNomRole(String nomRole);
}

