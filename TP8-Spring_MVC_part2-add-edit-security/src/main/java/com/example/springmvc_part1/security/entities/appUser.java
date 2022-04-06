package com.example.springmvc_part1.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class appUser {
    @Id
    private String id;
    @Column(unique = true)
    private String userName;
    private String password;
    private boolean avtive;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<appRole>  userRoles=new ArrayList<>();
}
