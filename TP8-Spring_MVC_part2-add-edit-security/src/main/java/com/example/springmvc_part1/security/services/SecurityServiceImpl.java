package com.example.springmvc_part1.security.services;

import com.example.springmvc_part1.security.Repository.appRoleRepository;
import com.example.springmvc_part1.security.Repository.appUserRepository;
import com.example.springmvc_part1.security.entities.appRole;
import com.example.springmvc_part1.security.entities.appUser;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@Transactional
@AllArgsConstructor
public class SecurityServiceImpl implements securityService {
    private appRoleRepository appRoleRepository;
    private appUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public appUser saveNewUser(String username, String password, String rePassword) {
        appUser appUser=appUserRepository.findByUserName(username);
        if(appUser!=null){
            throw new RuntimeException("User already exist !");
        }else{
            if(!password.equals(rePassword)) throw new RuntimeException("Password not match ");
            appUser=new appUser();
            appUser.setId(UUID.randomUUID().toString());
            appUser.setUserName(username);
            appUser.setPassword(passwordEncoder.encode(password));
           return appUserRepository.save(appUser);
        }

    }

    @Override
    public appRole saveNewRole(String rolename, String description) {
        appRole appRole=appRoleRepository.findByRoleName(rolename);
        if(appRole!=null)throw  new RuntimeException("Role already exist !");
        appRole =new appRole();
        appRole.setRoleName(rolename);
        appRole.setRoleDescription(description);
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
              appUser appUser1=appUserRepository.findByUserName(username);
              if(appUser1==null)throw new RuntimeException("User Not Found");
              appRole appRole1=appRoleRepository.findByRoleName(rolename);
              if(appRole1==null) throw new RuntimeException("Role Not Found");
              appUser1.getUserRoles().add(appRole1);
    }

    @Override
    public void removeRoleFromUser(String username, String rolename) {
        appUser appUser1=appUserRepository.findByUserName(username);
        if(appUser1==null)throw new RuntimeException("User Not Found");
        appRole appRole1=appRoleRepository.findByRoleName(rolename);
        if(appRole1==null) throw new RuntimeException("Role Not Found");
        appUser1.getUserRoles().remove(appRole1);
    }

    @Override
    public appUser loadUserByUserName(String username) {
        appUser appUser=appUserRepository.findByUserName(username);
        if(appUser==null) throw new RuntimeException("User Not Found");
        return appUser;
    }

}
