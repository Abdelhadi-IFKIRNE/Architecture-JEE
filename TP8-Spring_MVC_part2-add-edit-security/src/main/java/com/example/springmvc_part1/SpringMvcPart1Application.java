package com.example.springmvc_part1;

import com.example.springmvc_part1.Repository.PateintRepository;
import com.example.springmvc_part1.entities.Patient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

}
