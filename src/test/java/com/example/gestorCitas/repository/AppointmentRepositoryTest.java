package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Appointment;
import com.example.gestorCitas.domain.Employee;
import com.example.gestorCitas.domain.Vacant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentRepositoryTest {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    VacantRepository vacantRepository;

    private Employee findById(int id){
        return employeeRepository.findById(id).orElseThrow(()-> new RuntimeException("the employee is not available"));
    }

    private Vacant findByIdVacant(int id){
        return vacantRepository.findById(id).orElseThrow(()->new RuntimeException("the vacant is not available"));
    }

    @Test
    @Transactional
    public void findAppointmentFindByEmployee(){
        Employee employee = findById(2);
        List<Appointment> appointmentList = appointmentRepository.findByEmployee(employee);
        System.out.println(appointmentList.toString());
    }

    @Test
    @Transactional
    public void ExistEmployeeAndVacantAppointment(){
        Employee employee = findById(2);
        Vacant vacant = findByIdVacant(4);
        boolean valid = appointmentRepository.existsByEmployeeAndVacant(employee, vacant);
        if(valid){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }

    @Test
    public void existAppointmentTime(){
        Employee employee = findById(2);
        boolean existsEmployeeAndTime = appointmentRepository.existsByEmployeeAndTimeAppointment(employee, LocalTime.of(11, 0));
        if(existsEmployeeAndTime){
            System.out.println("true");
        }else {
            System.out.println("false");
        }
    }

    @Test
    @Transactional
    public void findAppointmentByDateAndEmployee(){
        Employee employee = findById(2);
        List<Appointment> appointmentList= appointmentRepository.findByDateAppointmentAndEmployee(
                LocalDate.of(2025, 7,11),employee);
        System.out.println(appointmentList);
    }


}