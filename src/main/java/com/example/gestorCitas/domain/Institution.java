package com.example.gestorCitas.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInstitution;
    @Column(nullable = false)
    private String nameInstitution;
    @Column(unique = true)
    private String emailInstitution;
    private String addressInstitution;
    private String phoneInstitution;

}
