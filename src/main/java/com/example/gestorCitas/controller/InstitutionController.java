package com.example.gestorCitas.controller;

import com.example.gestorCitas.domain.Institution;
import com.example.gestorCitas.service.InstitutionService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/institution")
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @GetMapping
    public ResponseEntity<List<Institution>> getInstitutionList(){
        return ResponseEntity.ok(institutionService.getInstitutionList());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> getFindByIdInstitution(@PathVariable int id){
        return ResponseEntity.ok(institutionService.findByIdInstitution(id));
    }

    @PostMapping()
    public ResponseEntity<?> getSaveInstitution(@RequestBody Institution institution){
        return ResponseEntity.ok(institutionService.saveInstitution(institution));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInstitution(@PathVariable int id, @RequestBody Institution institution){
        return ResponseEntity.ok(institutionService.updateInstitution(id, institution));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstitution(@PathVariable int id){
        institutionService.deleteInstitution(id);
        return  ResponseEntity.noContent().build();
    }

}
