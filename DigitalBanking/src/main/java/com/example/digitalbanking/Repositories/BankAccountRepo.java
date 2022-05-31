package com.example.digitalbanking.Repositories;

import com.example.digitalbanking.Entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepo extends JpaRepository<BankAccount,String> {
    List<BankAccount> findByCustomer_Id(Long id);
}
