package com.example.springmvc_part1.security.services;

import com.example.springmvc_part1.security.entities.appRole;
import com.example.springmvc_part1.security.entities.appUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface securityService {

    appUser saveNewUser(String username,String password,String rePassword);
    appRole  saveNewRole(String rolename,String description);
    void addRoleToUser(String username, String rolename);
    void removeRoleFromUser(String username, String rolename);
    appUser loadUserByUserName(String username);

}
