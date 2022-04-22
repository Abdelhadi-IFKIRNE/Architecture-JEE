package com.example.projetetudiant.entities;

import com.example.projetetudiant.EnumType.genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class etudiant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 2,max = 8)
    private String nom;
    @Size(min = 2,max = 10)
    private String prenom;
    @Email
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNiassance;
    private boolean enRegle;
    @Enumerated(javax.persistence.EnumType.STRING)
    private genre genre;

}
