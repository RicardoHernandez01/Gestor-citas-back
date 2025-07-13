package com.example.gestorCitas.controller;

import com.example.gestorCitas.domain.Appointment;
import com.example.gestorCitas.domain.Employee;
import com.example.gestorCitas.projectionInterface.AppointmentProjection;
import com.example.gestorCitas.service.AppointmentService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAppointmentList(){
        return ResponseEntity.ok(appointmentService.getAppointmentList());
    }


    @GetMapping("/findAppointment")
    public ResponseEntity<List<AppointmentProjection>> getAppointmentListByCriteria(
            @RequestParam(required = false) Integer idEmployee,
            @RequestParam(required = false) Integer idDepartment,
            @RequestParam(required = false) Integer idVacant,
            @RequestParam(required = false) LocalDate dateAppointment){
        return ResponseEntity.ok(appointmentService.getAppointmentListByCriteria(
                idEmployee,
                idDepartment,
                idVacant,
                dateAppointment));
    }

    @PostMapping
    public ResponseEntity<?> saveAppointment(@Valid @RequestBody Appointment appointment,
                                             @RequestParam int idEmployee,
                                             @RequestParam int idVacant){
        appointmentService.saveAppointment(appointment,idEmployee, idVacant);
        return ResponseEntity.status(HttpStatus.CREATED).body("The appointment created successfully");
    }

    @PutMapping
    public ResponseEntity<?> updateAppointment(@Valid @RequestBody Appointment appointment){
        appointmentService.updateAppointment(appointment);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable int id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
    
}
