package com.example.springmvc_part1.security;

import com.example.springmvc_part1.security.services.userDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class configSecurity extends WebSecurityConfigurerAdapter {
                 private userDetailService userDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder passwordEncoder=getPasswordEncoder();
//        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("USER");
//        auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN");
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();

        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/user/**").hasAuthority("USER");
        http.exceptionHandling().accessDeniedPage("/403");
        http.authorizeRequests().anyRequest().authenticated();
    }

}
