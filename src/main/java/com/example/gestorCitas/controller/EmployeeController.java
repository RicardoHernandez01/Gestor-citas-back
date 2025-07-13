package com.example.gestorCitas.controller;

import com.example.gestorCitas.domain.Employee;
import com.example.gestorCitas.repository.EmployeeRepository;
import com.example.gestorCitas.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployeeList(){
        return ResponseEntity.ok(employeeService.getEmployeeList());
    }

    @GetMapping("/searchEmployee")
    public ResponseEntity<?> getEmployeeId(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name){
        return ResponseEntity.ok(employeeService.findBySpecification(id, name));
    }

    @PostMapping
    public ResponseEntity<?> saveEmployee(@Valid @RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body("employee created successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateEmployee(@Valid @RequestBody Employee employeeUpdate){
        employeeService.updateEmployee(employeeUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
