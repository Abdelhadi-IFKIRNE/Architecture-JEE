package com.example.tp_patient.entities;

import com.example.tp_patient.enumtype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class etudiant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String nom;
    private  String prenom;
    private String email;
    private Date dateNaissance;
    private String genre;
    private  boolean enRegle;

}
