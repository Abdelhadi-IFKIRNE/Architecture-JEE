package com.example.mapping_relations_2.repository;

import com.example.mapping_relations_2.entity.patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface patientRepository extends JpaRepository<patient,Long> {
}
