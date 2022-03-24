package com.example.mapping_relations_2.repository;

import com.example.mapping_relations_2.entity.patient;
import com.example.mapping_relations_2.entity.rendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<rendezVous,Long> {
    rendezVous findById(long id);
}
