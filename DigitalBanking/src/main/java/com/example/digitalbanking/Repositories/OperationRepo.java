package com.example.digitalbanking.Repositories;

import com.example.digitalbanking.Entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.util.List;

public interface OperationRepo extends JpaRepository<Operation,Long> {
    List<Operation> findByBankAccountId(String id);
    Page<Operation> findByBankAccountIdOrderByDateDesc(String id, Pageable pageable);
}
