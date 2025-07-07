package com.example.gestorCitas.service;

import com.example.gestorCitas.domain.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeList();
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(int id);
    Employee findByIdEmployee(int id);
    Employee findByNameEmployee(String name);
}
