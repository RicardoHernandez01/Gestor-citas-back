package com.example.gestorCitas.domain;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Appointment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int idAppointment;
    @Column(nullable = false)
    private LocalDate dateAppointment;
    @Column(nullable = false)
    private LocalTime timeAppointment;
    @ManyToOne
    @JoinColumn(
            name = "id_department",
            referencedColumnName = "idDepartment"
    )
    private Department department;
    @ManyToOne
    @JoinColumn(
            name = "id_employee",
            referencedColumnName = "idEmployee"
    )
    private Employee employee;
    @ManyToOne
    @JoinColumn(
            name = "id_vacant",
            referencedColumnName = "idVacant"
    )
    private Vacant vacant;
}
