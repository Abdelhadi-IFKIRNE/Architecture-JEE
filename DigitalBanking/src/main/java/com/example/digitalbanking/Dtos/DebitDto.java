package com.example.digitalbanking.Dtos;


import lombok.Data;

@Data
public class DebitDto {
    private String idAccount;
    private double amount;
    private String description;
}
