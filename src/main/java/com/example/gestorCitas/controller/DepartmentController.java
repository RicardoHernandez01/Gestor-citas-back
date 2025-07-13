package com.example.gestorCitas.controller;

import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.domain.Institution;
import com.example.gestorCitas.projectionInterface.DepartmentProjection;
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


    @GetMapping("/findByInstitution/{idInstitution}")
    public ResponseEntity<List<DepartmentProjection>> getDepartmentListByInstitution(@PathVariable int idInstitution){
        return ResponseEntity.ok(departmentService.getDepartmentListByInstitution(idInstitution));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable int id){
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> getDepartmentByName(@PathVariable String name){
        return ResponseEntity.ok(departmentService.getDepartmentFindByName(name));
    }

    @GetMapping("/findByNameInstitution")
    public ResponseEntity<?> getDepartmentByNameInstitution(@RequestParam String nameInstitution){
        return ResponseEntity.ok(departmentService.getDepartmentListByNameInstitution(nameInstitution));
    }

    @PostMapping
    public ResponseEntity<?> saveDepartment(@Valid @RequestBody Department department,
                                            @RequestParam int idInstitution){
        departmentService.saveDepartment(department, idInstitution);
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
