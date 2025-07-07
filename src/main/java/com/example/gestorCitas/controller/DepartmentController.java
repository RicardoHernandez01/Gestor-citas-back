package com.example.gestorCitas.controller;

import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.service.DepartmentService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getDepartmentList(){
        return ResponseEntity.ok(departmentService.getDepartmentList());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable int id){
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> getDepartmentByName(@PathVariable String name){
        return ResponseEntity.ok(departmentService.getDepartmentFindByName(name));
    }

    @PostMapping
    public ResponseEntity<?> saveDepartment(@Valid @RequestBody Department department){
        departmentService.saveDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body("department created successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateDepartment(@Valid @RequestBody Department department){
        departmentService.updateDepartment(department);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable int id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("department successfully deleted");
    }

}
