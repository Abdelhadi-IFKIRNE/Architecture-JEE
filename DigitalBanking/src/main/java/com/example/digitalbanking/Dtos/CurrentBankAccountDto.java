package com.example.digitalbanking.Dtos;

import com.example.digitalbanking.Enums.AccountStatus;
import lombok.Data;

import java.util.Date;


@Data
public class CurrentBankAccountDto extends BankAccountDto {

    private String id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    private double overDraft;
    private CustomerDto customer;

}
