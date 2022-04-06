package com.example.springmvc_part1.security.Repository;

import com.example.springmvc_part1.security.entities.appRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface appRoleRepository extends JpaRepository<appRole,Long> {
    appRole findByRoleName(String roleName);
}
