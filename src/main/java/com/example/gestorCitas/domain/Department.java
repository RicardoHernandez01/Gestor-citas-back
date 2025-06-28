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
@Builder
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDepartment;
    @Column(
            nullable = false,
            unique = true
    )
    private String nameDepartment;
    private String addressDepartment;
    @Column(nullable = false)
    private String phoneDepartment;
    @ManyToOne
    @JoinColumn(
            name = "id_institution",
            referencedColumnName = "idInstitution"
    )
    private Institution institution;
    @OneToMany
    @JoinColumn(
            name = "id_department",
            referencedColumnName = "idDepartment"
    )
    private List<Employee> employeeList;
}
