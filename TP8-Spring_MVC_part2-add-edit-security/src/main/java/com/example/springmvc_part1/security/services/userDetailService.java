package com.example.springmvc_part1.security.services;

import com.example.springmvc_part1.security.Repository.appUserRepository;
import com.example.springmvc_part1.security.entities.appUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class userDetailService implements UserDetailsService {
    private securityService service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        appUser appUser1=service.loadUserByUserName(username);
        Collection<GrantedAuthority> authorities=appUser1.getUserRoles().stream().map(role->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        User user=new User(appUser1.getUserName(),appUser1.getPassword(),authorities);
        return user;
    }
}
