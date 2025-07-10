package com.example.gestorCitas.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vacant {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int idVacant;
    @Column(
            unique = true
    )
    @NotBlank(message = "the name of the vacancy cannot be null")
    private String nameVacant;
    private String positionVacant;
    private String statusVacant;
    @ManyToOne
    @JoinColumn(
            name = "id_department",
            referencedColumnName = "idDepartment"
    )
    private Department department;
}
