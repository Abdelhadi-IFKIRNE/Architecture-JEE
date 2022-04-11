package com.example.tp_patient.repository;

import com.example.tp_patient.entities.etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface etudiantRepository extends JpaRepository<etudiant,Integer> {
    Page<etudiant> findAllByNomContains(String Nom,Pageable pageable);
}
