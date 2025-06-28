package com.example.gestorCitas.domain;

import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Employee {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int idEmployee;
    @Column(nullable = false)
    private String fistNameEmployee;
    @Column(nullable = false)
    private String lastNameEmployee;
    private int ageEmployee;
    @Column(nullable = false)
    private LocalDate birthdateEmployee;
    private String addressEmployee;
    @Column(nullable = false)
    private String phoneEmployee;
    private String emailEmployee;
}
