package com.example.mapping_relations_2.repository;

import com.example.mapping_relations_2.entity.medcin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface madcinRepository extends JpaRepository<medcin,Long> {
    medcin findById(long id);
}
