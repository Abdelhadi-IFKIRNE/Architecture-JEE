package com.example.tp_patient;

import com.example.tp_patient.entities.etudiant;
import com.example.tp_patient.repository.etudiantRepository;
import org.apache.el.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class TpPatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpPatientApplication.class, args);
    }

    @Bean
       CommandLineRunner start(etudiantRepository etudiantRepository){

        return args->{
            String[] names={"ahmed","karim","fatima","khadija","naoual","aouatif","farah"};
            String[] prenoms={"elardi","benfarih","benfath","benhardi","benkarim","radi","farih"};
            for (int i = 0; i < names.length; i++) {
                 etudiant e=new etudiant();
                 e.setNom(names[i]);
                 e.setPrenom(prenoms[i]);
                 e.setEmail(names[i]+"@"+prenoms[i]+".com");
                 e.setDateNaissance(new Date());
                 e.setEnRegle(false);
                 e.setGenre("Homme");
                 etudiantRepository.save(e);
            }
            };
        };
       }

