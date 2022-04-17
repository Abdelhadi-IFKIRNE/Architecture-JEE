package com.example.projetetudiant.security.repository;

import com.example.projetetudiant.security.entities.appUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface appUserRep extends JpaRepository<appUser,String> {
    appUser findByUsername(String username);
}
