package com.example.gestionproduit;

import com.example.gestionproduit.Entities.Produit;
import com.example.gestionproduit.Repository.ProduitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class GestionProduitApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionProduitApplication.class, args);
    }
//    @Bean
  CommandLineRunner start(ProduitRepository produitRepository){
       return args->{
           Stream.of("ordinateur","téléphone","télévision", "machine à laver").forEach(pr->{
               Produit p=new Produit();
               p.setNom(pr);
               p.setDesigniation("bonne qualité");
               p.setPrix((int)(Math.random()*100+5000));
               produitRepository.save(p);
           });
        };
  }

}
