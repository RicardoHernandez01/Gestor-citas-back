package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Employee;
import com.example.gestorCitas.projectionInterface.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional<EmployeeProjection> findByFirstNameEmployee(String name);
    Optional<EmployeeProjection> findByIdEmployee(int id);
}
