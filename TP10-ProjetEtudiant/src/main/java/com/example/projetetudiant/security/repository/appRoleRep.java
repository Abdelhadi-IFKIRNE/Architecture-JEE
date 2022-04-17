package com.example.projetetudiant.security.repository;

import com.example.projetetudiant.security.entities.appRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface appRoleRep extends JpaRepository<appRole,Long> {
    appRole findByRolename(String rolename);
}
