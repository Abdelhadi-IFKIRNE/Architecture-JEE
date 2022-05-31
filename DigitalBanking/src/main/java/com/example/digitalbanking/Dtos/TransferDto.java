package com.example.digitalbanking.Dtos;


import lombok.Data;

@Data
public class TransferDto {
    private String idSource;
    private String idDest;
    private double amount;
}
