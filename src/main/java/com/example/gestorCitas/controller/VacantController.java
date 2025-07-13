package com.example.gestorCitas.controller;

import com.example.gestorCitas.domain.Vacant;
import com.example.gestorCitas.projectionInterface.VacantProjection;
import com.example.gestorCitas.repository.VacantRepository;
import com.example.gestorCitas.service.VacantService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacant")
public class VacantController {

    @Autowired
    VacantService vacantService;

    @GetMapping
    public ResponseEntity<List<Vacant>> getVacantList(){
        return ResponseEntity.ok(vacantService.getVacantList());
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> getVacantById(@PathVariable int id){
        return ResponseEntity.ok(vacantService.getVacantById(id));
    }

    @GetMapping("/FindByName/{nameVacant}")
    public ResponseEntity<?> getVacantByName(@PathVariable String nameVacant){
        return ResponseEntity.ok(vacantService.getVacantByNameVacant(nameVacant));
    }

    //consulta por especificacion
    @GetMapping("/searchBySpecification")
    public ResponseEntity<List<VacantProjection>> getVacantListBySpecification(
            @RequestParam(required = false) String positionVacant,
            @RequestParam(required = false) Integer idDepartment,
            @RequestParam(required = false) String nameDepartment){
        return ResponseEntity.ok(vacantService.getVacantListBySpecification(positionVacant,idDepartment,nameDepartment));
    }

    @PostMapping
    public ResponseEntity<?> saveVacant(@Valid @RequestBody Vacant vacant,
                                        @RequestParam int idDepartment){
        vacantService.saveVacant(vacant, idDepartment);
        return ResponseEntity.status(HttpStatus.CREATED).body("the vacant created successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateVacant(@Valid @RequestBody Vacant vacant){
        vacantService.updateVacant(vacant);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVacant(@PathVariable int id){
        vacantService.deleteVacant(id);
        return ResponseEntity.noContent().build();
    }



}
