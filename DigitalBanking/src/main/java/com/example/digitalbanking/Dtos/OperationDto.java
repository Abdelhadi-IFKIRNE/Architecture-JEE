package com.example.digitalbanking.Dtos;

import com.example.digitalbanking.Entities.BankAccount;
import com.example.digitalbanking.Enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;



@Data
public class OperationDto {
    private Long id;
    private Date date;
    private double amount;
    private String description;
    private OperationType operationType;
}
