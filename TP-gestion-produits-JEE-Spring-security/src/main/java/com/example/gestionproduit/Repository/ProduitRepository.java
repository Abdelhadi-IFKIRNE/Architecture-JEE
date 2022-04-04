package com.example.gestionproduit.Repository;

import com.example.gestionproduit.Entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository  extends JpaRepository<Produit,Integer> {
    Page<Produit> findAllByNomContains(String mc,Pageable pageable);
}
