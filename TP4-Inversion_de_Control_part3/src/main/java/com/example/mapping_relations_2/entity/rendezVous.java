package com.example.mapping_relations_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class rendezVous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateRendezVous;
    @ManyToOne
    private patient patient;
    @ManyToOne
    private  medcin medcin;
    @OneToOne(mappedBy = "rendezVous", fetch = FetchType.LAZY)
    private  consultation consultation;
}
