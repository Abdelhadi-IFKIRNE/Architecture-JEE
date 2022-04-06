package com.example.springmvc_part1.security.Repository;

import com.example.springmvc_part1.security.entities.appUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface appUserRepository extends JpaRepository<appUser,String> {
    appUser findByUserName(String username);
}
