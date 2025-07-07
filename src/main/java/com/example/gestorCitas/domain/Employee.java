package com.example.gestorCitas.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
    private String firstNameEmployee;
    @Column(nullable = false)
    private String lastNameEmployee;
    private int ageEmployee;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de nacimiento no puede ser nula.") // Asegura que no sea null
    @Past(message = "La fecha de nacimiento debe ser en el pasado.") // Asegura que la fecha no sea futura ni hoy
    private LocalDate birthdateEmployee;
    private String addressEmployee;
    @Column(nullable = false)
    private String phoneEmployee;
    private String emailEmployee;
}
