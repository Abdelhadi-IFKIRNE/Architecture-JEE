package com.example.springmvc_part1.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class appRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(unique = true)
    private String roleName;
    private  String roleDescription;
}
