package com.example.projetetudiant;

import com.example.projetetudiant.EnumType.genre;
import com.example.projetetudiant.entities.etudiant;
import com.example.projetetudiant.repositories.etudiantRepository;
import com.example.projetetudiant.security.services.iservice;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class ProjetEtudiantApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetEtudiantApplication.class, args);
    }

    // @Bean
    CommandLineRunner start(etudiantRepository etudiantRepository){
        return  args -> {

            Stream.of("mohammed","ahmed","kamal","farah","najat","fouad").forEach(name->{
                etudiant e=new etudiant();
                e.setNom(name);
                e.setPrenom("bentest");
                e.setEmail(name+"@gmail.com");
                e.setGenre(Math.random()<0.5?genre.MASCULIN:genre.FEMININ);
                e.setEnRegle(Math.random()<0.5?true:false);
                e.setDateNiassance(new Date());
                etudiantRepository.save(e);
            });
        };
    }
  // @Bean
    CommandLineRunner start1(iservice serviceImpl){
       return args -> {

         serviceImpl.addUser("abdelhadi","1234","1234");

//         serviceImpl.addRole("USER","");
//         serviceImpl.addRole("ADMIN","");
         serviceImpl.addRoleToUser("abdelhadi","USER");
         serviceImpl.addRoleToUser("abdelhadi","ADMIN");

       } ;
    }
    @Bean
   PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
   }

}
