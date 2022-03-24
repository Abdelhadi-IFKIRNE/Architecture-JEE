package com.example.mapping_relations_2.entity;

import com.example.mapping_relations_2.TypesEn.EnumType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  Date dateConsultation;
    @Enumerated(javax.persistence.EnumType.STRING)
    private EnumType status;
    @OneToOne
    private rendezVous rendezVous;
    @ManyToOne
    private  medcin medcin;
}
