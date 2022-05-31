package com.example.digitalbanking.Dtos;

import lombok.Data;


import java.util.List;

@Data
public class InfosHistoryAccountDto {
    private String id;
    private double balance;
    private String type;
    private int currentPage;
    private int nbrPages;
    private int size;
    private List<OperationDto> operations;
}
