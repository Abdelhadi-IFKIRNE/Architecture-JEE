package com.example.digitalbanking.Dtos;

import com.example.digitalbanking.Entities.Customer;
import com.example.digitalbanking.Entities.Operation;
import com.example.digitalbanking.Enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
public class SavingBankAccountDto extends BankAccountDto {

    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private double interestRate;
    private CustomerDto customer;

}
