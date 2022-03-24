package com.example.mapping_relations_2;

import com.example.mapping_relations_2.TypesEn.EnumType;
import com.example.mapping_relations_2.entity.consultation;
import com.example.mapping_relations_2.entity.medcin;
import com.example.mapping_relations_2.entity.patient;
import com.example.mapping_relations_2.entity.rendezVous;
import com.example.mapping_relations_2.repository.RendezVousRepository;
import com.example.mapping_relations_2.repository.consultationsRepository;
import com.example.mapping_relations_2.repository.madcinRepository;
import com.example.mapping_relations_2.repository.patientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.mapping_relations_2.sevices.Ihopital;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class MappingRelations2Application {

    public static void main(String[] args) {
        SpringApplication.run(MappingRelations2Application.class, args);
    }

    @Bean
    CommandLineRunner start(Ihopital ihopital,patientRepository patientRepository, madcinRepository madcinRepository, consultationsRepository consultationsRepository, RendezVousRepository rendezVousRepository){

        return args -> {
            Stream.of("mohammed","karim","farah","karima").forEach(name->{
                patient  patient1=new patient();
                patient1.setNom(name);
                patient1.setEmail(name+"@gmail.com");
                patient1.setMalade(Math.random()<0.5?true:false);
                ihopital.savePatient(patient1);
            });


            Stream.of("abdelhadi","kaoutar","halima","jamal").forEach(nom->{
                medcin medcin=new medcin();
                medcin.setNom(nom);
                medcin.setEmail(nom+"@gmail.com");
                madcinRepository.save(medcin);
            });

            rendezVous rendezVous1=new rendezVous();
            rendezVous1.setDateRendezVous(new Date());
            rendezVous1.setPatient(patientRepository.findAll().get(0));
            rendezVous1.setMedcin(madcinRepository.findAll().get(0));
            rendezVousRepository.save(rendezVous1);
            rendezVous rendezVous2=new rendezVous();
            rendezVous2.setDateRendezVous(new Date());
            rendezVous2.setPatient(patientRepository.findAll().get(0));
            rendezVous2.setMedcin(madcinRepository.findAll().get(0));
            rendezVousRepository.save(rendezVous2);

             medcin medcin1=madcinRepository.findById(1L);
             rendezVous rendezVous3=rendezVousRepository.findById(2L);

            consultation consultation=new consultation();
            consultation.setDateConsultation(new Date());
            consultation.setStatus(EnumType.Encore);
            consultation.setRendezVous(rendezVous3);
            consultation.setMedcin(medcin1);
            consultationsRepository.save(consultation);
        };
    }

}
