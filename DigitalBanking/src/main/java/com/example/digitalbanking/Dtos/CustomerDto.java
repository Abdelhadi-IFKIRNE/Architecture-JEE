package com.example.digitalbanking.Dtos;

import com.example.digitalbanking.Entities.BankAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
}
