package com.example.mapping_relations_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class patient {
    @Id
    private String id;
    private String nom;
    private String Email;
    private boolean malade;
    @OneToMany(mappedBy ="patient", fetch = FetchType.LAZY)
    private Collection<rendezVous> rendezVous;
}
