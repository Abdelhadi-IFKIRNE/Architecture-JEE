package com.example.springmvc_part1;

import com.example.springmvc_part1.Repository.PateintRepository;
import com.example.springmvc_part1.entities.Patient;
import com.example.springmvc_part1.security.services.securityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class SpringMvcPart1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcPart1Application.class, args);
    }

//    @Bean
    CommandLineRunner start(PateintRepository pateintRepository){
       return args->{
           pateintRepository.save(new Patient(null,"ahmed","ahmed@gmail.com",new Date(),false,100));

           pateintRepository.save(new Patient(null,"hassan","ahmed@gmail.com",new Date(),false,100));

           pateintRepository.save(new Patient(null,"hassnae","ahmed@gmail.com",new Date(),false,100));

           pateintRepository.save(new Patient(null,"karim","ahmed@gmail.com",new Date(),false,100));

           pateintRepository.save(new Patient(null,"karima","ahmed@gmail.com",new Date(),false,100));

       };
    }


   @Bean
    CommandLineRunner testMethodes(securityService service){

        return args -> {

//            service.saveNewUser("mohamed","1234","1234");
//            service.saveNewUser("hamide","1234","1234");
//            service.saveNewUser("rachide","1234","1234");
//            service.saveNewUser("karime","1234","1234");
//
//            service.saveNewRole("USER","userrole");
//            service.saveNewRole("ADMIN","userrole");

            service.addRoleToUser("mohamed","ADMIN");
            service.addRoleToUser("mohamed","USER");
            service.addRoleToUser("rachid","USER");
            service.addRoleToUser("karim","USER");

        };
    }

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
