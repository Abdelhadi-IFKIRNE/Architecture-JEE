package com.example.digitalbanking.Dtos;


import lombok.Data;

@Data
public class CreditDto {
    private String idAccount;
    private double amount;
    private String description;
}
