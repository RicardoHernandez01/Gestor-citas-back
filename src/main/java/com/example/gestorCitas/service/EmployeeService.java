package com.example.gestorCitas.service;

import com.example.gestorCitas.domain.Employee;
import com.example.gestorCitas.projectionInterface.EmployeeProjection;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeList();
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(int id);
    EmployeeProjection findBySpecification(Integer id, String name);
}
