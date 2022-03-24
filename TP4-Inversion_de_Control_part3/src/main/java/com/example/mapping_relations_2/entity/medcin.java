package com.example.mapping_relations_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class medcin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String nom;
    private  String email;
    @OneToMany(mappedBy = "medcin", fetch = FetchType.LAZY)
    private Collection<rendezVous> rendezVous;
    @OneToMany(mappedBy = "medcin",fetch = FetchType.LAZY)
    private Collection<consultation> consultations;
}
