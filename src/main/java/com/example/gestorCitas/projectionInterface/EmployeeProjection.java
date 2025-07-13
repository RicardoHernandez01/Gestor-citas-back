package com.example.gestorCitas.projectionInterface;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface EmployeeProjection {

    @Value("#{target.firstNameEmployee + ' ' + target.lastNameEmployee}")
    String getNameFullEmployee();
    LocalDate getBirthdateEmployee();
    String getAddressEmployee();
    String getPhoneEmployee();
    String getEmailEmployee();
}
