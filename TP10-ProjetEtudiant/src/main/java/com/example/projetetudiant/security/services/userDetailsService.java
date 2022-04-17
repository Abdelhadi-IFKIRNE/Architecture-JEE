package com.example.projetetudiant.security.services;

import com.example.projetetudiant.security.entities.appUser;
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
public class userDetailsService implements UserDetailsService {
          private iservice service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        appUser user=service.loadUserByUsername(username);
        Collection<GrantedAuthority> authorities=user.getListRoles().stream().map(role->new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
        User user1=new User(user.getUsername(),user.getPassword(),authorities);
        return user1;
    }
}
