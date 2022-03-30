package com.example.springmvc_part1.Repository;

import com.example.springmvc_part1.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PateintRepository extends JpaRepository<Patient,Long> {
    Page<Patient>  findByNameContains(String ky, Pageable pageable);
}
