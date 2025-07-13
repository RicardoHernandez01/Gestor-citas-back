package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Appointment;
import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.domain.Employee;
import com.example.gestorCitas.domain.Vacant;
import com.example.gestorCitas.projectionInterface.AppointmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<AppointmentProjection> findByVacant(Vacant vacant);
    List<AppointmentProjection> findByDepartment(Department department);
    List<AppointmentProjection> findByEmployee(Employee employee);
    //search by date
    List<AppointmentProjection> findByDateAppointmentAndEmployee(LocalDate date, Employee employee);
    List<AppointmentProjection> findByDateAppointmentAndDepartment(LocalDate date, Department department);

    boolean existsByEmployeeAndVacant(Employee employee, Vacant vacant);

    boolean existsByEmployeeAndTimeAppointment(Employee employee, LocalTime timeAppointment);

}
